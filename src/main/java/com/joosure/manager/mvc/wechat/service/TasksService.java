package com.joosure.manager.mvc.wechat.service;

import java.util.Date;

public interface TasksService {

	/**
	 * 改变过期交易的状态
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int chgExgStatus(Date startTime, Date endTime);

	/**
	 * 根据发布的宝贝数重新排序宝贝类型
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int reorderItemType(Date startTime, Date endTime);

}
