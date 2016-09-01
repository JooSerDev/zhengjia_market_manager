package com.joosure.manager.mvc.wechat.task;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joosure.manager.mvc.wechat.service.TasksService;
import com.joosure.manager.mvc.wechat.util.ManagerUtils;
import com.shawn.server.core.util.DateUtil;

/**
 * 
 * @author Administrator
 *
 */
@Component("quartzTasks")
public class QuartzTasks {
	
	private Logger log = Logger.getLogger(QuartzTasks.class);
	
	@Autowired
	private TasksService tasksService;
	
	
	/**
	 * 当createTime 距离今天 > 10 天则判断交易状态，
	 * 如果为exchanging 则改为 cancel
	 */
	public void cancelExgWhenExpire(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -10);//十天前
		List<Date> date = ManagerUtils.getDefaultBETime(cal);
		if(date!=null){
			Date startTime = date.get(0);
			Date endTime = date.get(1);	
			int ret = tasksService.chgExgStatus(startTime,endTime);
			if(ret > 0){
				log.info("今日："+DateUtil.defaultFormat(new Date())+"定时任务"
						+ "【修改时间过长交易】成功完成，修改数据: "+ret+" 条");
			}else if(ret == 0){
				log.info("今日："+DateUtil.defaultFormat(new Date())+"定时任务"
						+ "【修改时间过长交易】 修改0条数据");
			}else{
				log.info("今日："+DateUtil.defaultFormat(new Date())+"定时任务"
						+ "【修改时间过长交易】 失败");
			}
		}
	}
	
	/**
	 * 重新排序物品类型，根据上一个自然周
	 */
	public void reorderItemType(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -7);//7天前
		Date startTime = ManagerUtils.getDefaultBETime(cal).get(0);
		cal.add(Calendar.DAY_OF_MONTH, 6);//1天前
		Date endTime = ManagerUtils.getDefaultBETime(cal).get(1);
		int ret = tasksService.reorderItemType(startTime,endTime);
	}

}
