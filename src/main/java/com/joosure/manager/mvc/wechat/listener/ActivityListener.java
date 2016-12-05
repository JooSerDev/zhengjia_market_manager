package com.joosure.manager.mvc.wechat.listener;

import com.joosure.manager.mvc.wechat.controller.ActivityController;
import com.joosure.server.mvc.wechat.entity.pojo.Activity;
import com.joosure.server.mvc.wechat.service.itf.IActivityService;
import com.shawn.server.core.util.SpringUtil;

public class ActivityListener {

	public static void onStartUp(){
		resetApprovalLimitCount();
		resetAlreadyApprovalCount();
	}

	public static void onDestroy(){
		saveCountToDb();
	}
	
	/**
	 * 将controller中的数据值存在数据库中
	 * @author Ted-wuhuhu
	 * @Time 2016年12月5日 上午10:26:50
	 */
	public static void saveCountToDb() {
		IActivityService activityService = SpringUtil.getBean("activityService");
		Activity enterActy = activityService.getTheEnterActy();
		enterActy.setLimitCount(ActivityController.approvalLimitCount);
		enterActy.setAlreadyCount(ActivityController.alreadyApprovedCount);
		activityService.changeEnterActyInfo(enterActy);
	}

	/**
	 * 更新审核限制名额
	 * @author Ted-wuhuhu
	 * @Time 2016年12月2日 下午5:03:20
	 */
	public static void resetApprovalLimitCount(){
		IActivityService activityService = SpringUtil.getBean("activityService");
		Activity enterActy = activityService.getTheEnterActy();
		ActivityController.approvalLimitCount = enterActy.getLimitCount();
	}

	/**
	 * 重置已经通过审核的名额
	 * @author Ted-wuhuhu
	 * @Time 2016年12月2日 下午5:03:36
	 */
	public static void resetAlreadyApprovalCount(){
		IActivityService activityService = SpringUtil.getBean("activityService");
		Activity enterActy = activityService.getTheEnterActy();
		ActivityController.alreadyApprovedCount = enterActy.getAlreadyCount();
	}

}
