package com.joosure.manager.mvc.wechat.listener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.joosure.manager.mvc.wechat.service.WxUserService;
import com.joosure.manager.mvc.wechat.util.ManagerUtils;
import com.joosure.server.mvc.wechat.service.itf.IPvService;
import com.shawn.server.core.util.SpringUtil;

public class StatisticsCache {
	
	private static WxUserService wxUserService = null;
	private static IPvService pvService = null;
	
	public static int sysStatisticsDayAmount = 30;//统计平台30天的访问量
	public static List<Integer> sysPvCountCache = null;//平台的pv访问量
	public static List<Integer> sysUvCountCache = null;//平台的uv访问量
	private static String sysVisitUrl = "/wechat/home";
	
	public static int allUserCountDayAmount = 30;//统计30天的数据
	//private static int newUserCountHourAmount = 3;//每3个小时统计一次
	public static List<Integer> allUserCountCache = null;//缓存近30天的用户增量
	public static List<Integer> newUserCountWithinHoursCache = null;//缓存当天的用户量，分时统计
	
	public static int newUserCountDayAmount = 30;
	public static List<Integer> oneDaysRetentionUserCache;//统计一天留存量
	public static List<Integer> threeDaysRetentionUserCache;//统计三天留存量
	public static List<Integer> oneWeeksRetentionUserCache;//统计七天留存量
	public static List<Integer> oneMonthsRetentionUserCache;//统计30天留存量
	public static List<Integer> newUserCountWithinDaysCache;//缓存当天的用户量，分天统计
	
	private static Timer timer = new Timer();
	
	public static void onInit(){
		//获取数据库中的数据
		freshAllUserCache();
		freshNewUserCache();
		
		freshSysPvCache();
		freshSysUvCache();
		
		
		freshNewUserCountWithinDaysCache();
		/*timer.schedule(new StatisticsCacheSchedule(), 0);
		scheduleAtFixedRate();*/
	}

	/**
	 * 获取系统平台uv的数据缓存
	 * @author Ted-wuhuhu
	 * @Time 2016年12月7日 下午6:21:36
	 */
	private static List<Integer> freshSysUvCache() {
		if(sysUvCountCache != null){
			sysUvCountCache.clear();
		}
		sysUvCountCache = new ArrayList<Integer>();
		if(pvService == null){
			pvService = SpringUtil.getBean("pvService");
		}
		for(int i = sysStatisticsDayAmount; i > 0; i--){
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -i);
			List<Date> dates = ManagerUtils.getDefaultBETime(cal);
			int count = pvService.getSysUvCountByDay(dates.get(0),dates.get(1),sysVisitUrl);
			sysUvCountCache.add(count);
		}
		System.out.println("获取平台30天uv量成功： " + sysUvCountCache);
		return sysUvCountCache;
	}
	
	/**
	 * 获取系统平台pv的数据缓存
	 * @author Ted-wuhuhu
	 * @Time 2016年12月7日 下午6:21:17
	 */
	private static List<Integer> freshSysPvCache() {
		if(sysPvCountCache != null){
			sysPvCountCache.clear();
		}
		sysPvCountCache = new ArrayList<Integer>();
		if(pvService == null){
			pvService = SpringUtil.getBean("pvService");
		}
		for(int i = sysStatisticsDayAmount; i > 0; i--){
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -i);
			List<Date> dates = ManagerUtils.getDefaultBETime(cal);
			int count = pvService.getSysPvCountByDay(dates.get(0),dates.get(1),sysVisitUrl);
			sysPvCountCache.add(count);
		}
		System.out.println("获取平台30天pv量成功： " + sysPvCountCache);
		return sysPvCountCache;
	}

	/**
	 * 定点定时执行
	 * @author Ted-wuhuhu
	 * @Time 2016年12月6日 上午10:31:51
	 */
	private static void scheduleAtFixedRate(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND, 30);
		Date scheduleTime = cal.getTime();
		timer.scheduleAtFixedRate(new StatisticsCacheSchedule(), scheduleTime, 1000 * 60 * 60 * 24);
	}
	
	private static class StatisticsCacheSchedule extends TimerTask{
		@Override
		public void run() {
			System.out.println(new Date() + " 执行定时任务开始: freshUserCache freshSysPvCache freshSysUvCache freshNewUserCountWithinDaysCache");
			freshAllUserCache();
			freshNewUserCache();
			
			freshSysPvCache();
			freshSysUvCache();
			
			freshNewUserCountWithinDaysCache();
			System.out.println(new Date() + " 执行定时任务结束: freshUserCache freshSysPvCache freshSysUvCache freshNewUserCountWithinDaysCache");
		}
	}

	/**
	 * 刷新用户所有量的缓存
	 * @author Ted-wuhuhu
	 * @Time 2016年12月6日 上午11:02:25
	 * @return
	 */
	private static List<Integer> freshNewUserCache() {
		if(newUserCountWithinHoursCache != null){
			newUserCountWithinHoursCache.clear();
		}
		newUserCountWithinHoursCache = new ArrayList<Integer>();
		if(wxUserService == null){
			wxUserService = SpringUtil.getBean("wxUserService");
		}
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date start = cal.getTime();
		for(int i = 0; i < 24; i++){
			cal.set(Calendar.HOUR_OF_DAY, i);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			Date endTime = cal.getTime();
			int count = wxUserService.getUserCountByDay(start,endTime);
			newUserCountWithinHoursCache.add(count);
		}
		System.out.println("获取用户当天新增数量成功： " + newUserCountWithinHoursCache);
		return newUserCountWithinHoursCache;
	}

	/**
	 * 获取用户单日新增量的缓存
	 * @author Ted-wuhuhu
	 * @Time 2016年12月6日 上午11:16:33
	 * @return
	 */
	private static List<Integer> freshAllUserCache(){
		if(allUserCountCache != null){
			allUserCountCache.clear();
		}
		allUserCountCache = new ArrayList<Integer>(allUserCountDayAmount);
		if(wxUserService == null){
			wxUserService = SpringUtil.getBean("wxUserService");
		}
		for(int i = 0; i < allUserCountDayAmount; i++){
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -allUserCountDayAmount);
			List<Date> dates = ManagerUtils.getDayToDay(cal, i);
			int count = wxUserService.getUserCountByDay(dates.get(0),dates.get(1));
			allUserCountCache.add(count);
		}
		System.out.println("获取用户30天数量成功： " + allUserCountCache);
		return allUserCountCache;
	}
	
	/**
	 * 获取当前 n 天新增用户量
	 * @author Ted-wuhuhu
	 * @Time 2016年12月8日 上午11:04:07
	 * @return
	 */
	private static List<Integer> freshNewUserCountWithinDaysCache(){
		if(newUserCountWithinDaysCache != null){
			newUserCountWithinDaysCache.clear();
		}
		newUserCountWithinDaysCache = new ArrayList<Integer>();
		if(pvService == null){
			pvService = SpringUtil.getBean("pvService");
		}
		for(int i=newUserCountDayAmount; i>0; i--){
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -i);
			List<Date> dates = ManagerUtils.getDefaultBETime(cal);
			int count = pvService.getNewUserCountWithinTheDay(dates.get(0),dates.get(1));
			newUserCountWithinDaysCache.add(count);
		}
		System.out.println("获取30天内用户当天新增数量成功： " + newUserCountWithinDaysCache);
		return newUserCountWithinDaysCache;
	}
	
	/**
	 * 缓存 1/3/7/30 天的用户留存量
	 * @author Ted-wuhuhu
	 * @Time 2016年12月8日 上午11:27:05
	 * @return
	 */
	private static void freshRetentionUserCaches(){
		
		
	}
}
