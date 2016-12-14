package com.joosure.server.mvc.wechat.service.itf;

import java.util.Date;
import java.util.List;

import com.joosure.server.mvc.wechat.entity.pojo.PvStc;
import com.joosure.server.mvc.wechat.entity.pojo.PvSum;

/***
 * pv 接口
 * @author Ted-wuhuhu
 * @Time 2016年11月24日 下午12:06:41
 *
 */
public interface IPvService {

	/**
	 * 插入pv记录
	 * @author Ted-wuhuhu
	 * @Time 2016年11月24日 下午12:06:28
	 * @param ntatRecord
	 * @return
	 */
	int savePvRecord(PvStc ntatRecord);

	/**
	 * 在一段时间里，查找某一类的pv记录，限制数量
	 * @author Ted-wuhuhu
	 * @Time 2016年11月24日 下午12:59:46
	 * @param startTime
	 * @param endTime
	 * @param flag
	 * @param limit
	 * @return
	 */
	List<PvSum> getBiggestPvRecord(Date startTime, Date endTime, String flag, int limit);

	/**
	 * 获取宝贝pv，通过时间和类型
	 * @author Ted-wuhuhu
	 * @Time 2016年12月7日 上午11:51:05
	 * @param date
	 * @param date2
	 * @param statisItemCount
	 * @return
	 */
	List<PvSum> getItemPvByTypeAndDate(Date date, Date date2,int itemType, int statisItemCount,String exgStatus);

	/**
	 * 获取系统当天的uv量
	 * @author Ted-wuhuhu
	 * @param visitUrl 
	 * @Time 2016年12月7日 下午6:33:03
	 * @param date
	 * @param date2
	 * @return
	 */
	int getSysUvCountByDay(Date startTime, Date endTime, String visitUrl);

	/**
	 * 获取系统当天的pv量
	 * @author Ted-wuhuhu
	 * @Time 2016年12月7日 下午7:04:04
	 * @param date
	 * @param date2
	 * @param sysVisitUrl
	 * @return
	 */
	int getSysPvCountByDay(Date date, Date date2, String sysVisitUrl);

	/**
	 * 获取当天的新增量
	 * @author Ted-wuhuhu
	 * @Time 2016年12月8日 上午11:12:27
	 * @param date
	 * @param date2
	 * @return
	 */
	int getNewUserCountWithinTheDay(Date date, Date date2);
}
