package com.joosure.server.mvc.wechat.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joosure.server.mvc.wechat.dao.database.ActivityDao;
import com.joosure.server.mvc.wechat.entity.pojo.Activity;
import com.joosure.server.mvc.wechat.entity.pojo.ActivityEnter;
import com.joosure.server.mvc.wechat.service.itf.IActivityService;

@Service("activityService")
public class ActivityService implements IActivityService{
	
	@Autowired
	private ActivityDao activityDao;

	@Override
	public List<Activity> getAllAvailableActy(Activity cond) {
		return activityDao.selectAllAvailable(cond);
	}
	
	@Override
	public int getAllAvailableActyCount(Activity cond){
		return activityDao.getAllAvailableActyCount(cond);
	}
	
	@Override
	public List<Activity> getAllVisibleActy(){
		return activityDao.selectAllVisible();
	}

	@Override
	public Activity getTheEnterActy() {
		return activityDao.selectTheEnterActy();
	}

	@Override
	public int addEnterRecord(ActivityEnter enter) {
		//判断当前活动的状态和活动的截止时间
		Activity enterActy = activityDao.selectById(enter.getActyId());
		if(enter.getEnterTime().getTime() > enterActy.getEnterEndTime().getTime()
				|| "stop".equals(enterActy.getStatus()) ){
			return 0;//当前不允许报名
		}
		
		//首先检查有没有报名
		ActivityEnter enterRecord = qryEnterRecord(enter);
		if(enterRecord != null){
			return -1;//已经存在
		}
		return activityDao.insertEnter(enter);
	}

	@Override
	public ActivityEnter qryEnterRecord(ActivityEnter enter) {
		return activityDao.getCurrentCycleEnterInfo(enter);
	}

	@Override
	public List<ActivityEnter> getActyEnterList(ActivityEnter cond) {
		return activityDao.getEnterListByPage(cond);
	}

	@Override
	public int getActyEnterCount(ActivityEnter cond) {
		return activityDao.getEnterListCount(cond);
	}

	@Override
	public int changeEnterActyInfo(Activity enterActy) {
		return activityDao.updateActivity(enterActy);
	}

	@Override
	public Activity getActivityById(Integer id) {
		return activityDao.selectById(id);
	}

	@Override
	public int saveActivity(Activity record) {
		return activityDao.insertActivity(record);
	}

	@Transactional
	public int approvalTheActy(int actyId, int oId, String approvalStatus) {
		ActivityEnter enter = activityDao.getEnterInfoById(oId);
		if(enter == null){
			return -1;//不存在
		}
		if(approvalStatus.equals(enter.getApprovalStatus())){
			return 0;//相同状态，不需审核
		}
		Activity enterActy = getTheEnterActy();
		int approvalLimitCount = enterActy.getLimitCount();
		int alreadyApprovedCount = enterActy.getAlreadyCount();
		if(ActivityEnter.ApprovalStatus_Approved.equals(approvalStatus)){
			if(approvalLimitCount < 1 || alreadyApprovedCount >= approvalLimitCount){
				return -2;//审核人数已满
			}
		}
		enter.setApprovalStatus(approvalStatus);
		enter.setApprovalTime(new Date());
		if(activityDao.changeEnterInfo(enter) > 0){ //更新报名信息
			if(ActivityEnter.ApprovalStatus_Approved.equals(approvalStatus)){
				enterActy.setAlreadyCount(alreadyApprovedCount + 1);//审核通过同时在报名记录中+1;
				return changeEnterActyInfo(enterActy);//更新活动报名记录
			}
			return 1;
		}
		return 0;
	}

	@Override
	public int startCycle() {
		Activity enterActy = getTheEnterActy();
		enterActy.setStatus(Activity.STATUS_normal);
		Calendar cal = Calendar.getInstance();
		cal.setTime(enterActy.getEnterEndTime());
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		enterActy.setEnterEndTime(cal.getTime());//设置报名截止时间；
		cal.add(Calendar.DAY_OF_WEEK, 1);//报名时间为截止时间当周的第一天，即周日
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		enterActy.setEnterStartTime(cal.getTime());//设置报名开始时间
		enterActy.setAlreadyCount(0);//设置已经报名名额为0
		return changeEnterActyInfo(enterActy);
	}
	
}
