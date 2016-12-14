package com.joosure.manager.mvc.wechat.service;

import java.util.List;

import com.joosure.manager.mvc.wechat.bean.dto.ItemStatisticsDto;

public interface IStatisticsService {

	/**
	 * 获取所有用户量
	 * @author Ted-wuhuhu
	 * @Time 2016年12月6日 上午11:27:52
	 * @return
	 */
	List<Integer> getAllUserCount();

	/**
	 * 单日用户新增量
	 * @author Ted-wuhuhu
	 * @Time 2016年12月6日 上午11:31:31
	 * @return
	 */
	List<Integer> getNewUserCount();

	/**
	 * 统计宝贝数据
	 * @author Ted-wuhuhu
	 * @Time 2016年12月7日 上午10:37:06
	 * @param itemType
	 * @param day
	 * @param rankType
	 * @return
	 */
	List<ItemStatisticsDto> statisItemData(int itemType, int day, String rankType);

	/**
	 * 获取平台pv量
	 * @author Ted-wuhuhu
	 * @Time 2016年12月7日 下午7:17:59
	 * @return
	 */
	List<Integer> getSysPvCount();

	/**
	 * 获取平台uv量
	 * @author Ted-wuhuhu
	 * @Time 2016年12月7日 下午7:18:24
	 * @return
	 */
	List<Integer> getSysUvCount();

	/**
	 * 获取新增加的量
	 * @author Ted-wuhuhu
	 * @Time 2016年12月8日 上午10:46:11
	 * @param days
	 * @return
	 */
	List<Integer> getNewCount(int days);

	/**
	 * 获取留存的量
	 * @author Ted-wuhuhu
	 * @Time 2016年12月8日 上午10:46:55
	 * @param days
	 * @return
	 */
	List<Integer> getRetentionCount(int days);

}
