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

	/**
	 * 获取在一段时间内 发布的宝贝数量  和所属类别
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<ItemTypeCountBean> getTypeCount(@Param("startTime")Date startTime, @Param("endTime")Date endTime);

	/**
	 * 更新宝贝类别排序
	 * @param typeId
	 * @param valueOf
	 */
	void updateItemType(@Param("typeId")Integer typeId, @Param("sort")Integer sort);
}
