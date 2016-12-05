package com.joosure.server.mvc.wechat.service.itf;

import java.util.List;

import com.joosure.server.mvc.wechat.entity.pojo.Activity;
import com.joosure.server.mvc.wechat.entity.pojo.ActivityEnter;

public interface IActivityService {

	/**
	 * 获取所有有效的活动信息
	 * @author Ted-wuhuhu
	 * @param cond 
	 * @Time 2016年11月29日 下午6:34:07
	 * @return
	 */
	List<Activity> getAllAvailableActy(Activity cond);
	
	/**
	 * 获取所有展示的活动信息
	 * @author Ted-wuhuhu
	 * @Time 2016年11月30日 上午10:11:53
	 * @return
	 */
	List<Activity> getAllVisibleActy();
	
	/**
	 * 获取线下报名活动信息
	 * @author Ted-wuhuhu
	 * @Time 2016年11月30日 上午10:16:14
	 * @return
	 */
	Activity getTheEnterActy();

	/**
	 * 添加报名信息记录
	 * @author Ted-wuhuhu
	 * @Time 2016年12月1日 上午10:57:10
	 * @param enter
	 * @return
	 */
	int addEnterRecord(ActivityEnter enter);
	
	/**
	 * 通过手机号和活动周期时间查询摊主报名信息
	 * @author Ted-wuhuhu
	 * @Time 2016年12月1日 上午11:00:04
	 * @param mobile
	 * @param enterTime
	 * @return
	 */
	ActivityEnter qryEnterRecord(ActivityEnter enter);

	/**
	 * 正常已发布的活动的数量
	 * @author Ted-wuhuhu
	 * @Time 2016年12月1日 下午5:28:42
	 * @param cond
	 * @return
	 */
	int getAllAvailableActyCount(Activity cond);
	
	/**
	 * 活动报名的记录
	 * @author Ted-wuhuhu
	 * @Time 2016年12月1日 下午6:42:46
	 * @param cond
	 * @return
	 */
	List<ActivityEnter> getActyEnterList(ActivityEnter cond);
	
	/**
	 * 活动报名的记录数量
	 * @author Ted-wuhuhu
	 * @Time 2016年12月1日 下午6:42:50
	 * @param cond
	 * @return
	 */
	int getActyEnterCount(ActivityEnter cond);

	/**
	 * 改变报名活动的信息
	 * @author Ted-wuhuhu
	 * @Time 2016年12月1日 下午8:30:18
	 * @param enterActy
	 * @return
	 */
	int changeEnterActyInfo(Activity enterActy);
	
	/**
	 * 根据id获取活动信息
	 * @author Ted-wuhuhu
	 * @Time 2016年12月1日 下午8:50:13
	 * @param id
	 * @return
	 */
	Activity getActivityById(Integer id);
	
	/**
	 * 添加自定义活动
	 * @author Ted-wuhuhu
	 * @Time 2016年12月2日 下午2:06:29
	 * @param record
	 * @return
	 */
	int saveActivity(Activity record);

	/**
	 * 审核报名状态
	 * @author Ted-wuhuhu
	 * @Time 2016年12月5日 上午10:49:59
	 * @param actyId
	 * @param oId
	 * @param approvalStatus
	 * @return 
	 * 		-1	表示报名信息不存在  
	 * 		0	表示状态相同
	 * 
	 */
	int approvalTheActy(int actyId, int oId, String approvalStatus);
}
