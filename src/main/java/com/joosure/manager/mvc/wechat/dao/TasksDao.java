package com.joosure.manager.mvc.wechat.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joosure.manager.mvc.wechat.bean.ItemTypeCountBean;

public interface TasksDao {
	/**
	 * 定时任务，更新交易状态
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int chgExgStatus(@Param("startTime")Date startTime, @Param("endTime")Date endTime);

	List<ItemTypeCountBean> getTypeCount(@Param("startTime")Date startTime, @Param("endTime")Date endTime);
}
