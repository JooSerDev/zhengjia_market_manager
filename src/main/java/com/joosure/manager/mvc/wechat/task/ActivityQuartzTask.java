package com.joosure.manager.mvc.wechat.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.joosure.server.mvc.wechat.service.itf.IActivityService;

@Component("actyQuartzTask")
public class ActivityQuartzTask {
	
	@Autowired
	private IActivityService activityService;

	//项目启动后
	
	/**
	 * <strong>重新启动一个周期，</strong> 这时需要重新设置数据库中的活动配置数据 <br>
	 * 1. 可通过审核上限人数		沿用上一个周期的设置 <br>
	 * 2. 已经通过审核人数		归0 <br>
	 * 3. 活动状态			normal <br>
	 * 4. 活动报名开始和截止时间	沿用上一个设置+一周<br>
	 * <strong>每周六凌晨启动 00:01:00</strong> 
	 * @author Ted-wuhuhu
	 * @Time 2016年12月8日 下午5:15:30
	 */
	@Scheduled(cron="0 1 0 ? * 7")
	public void startCycle(){
		activityService.startCycle();
	}
}
