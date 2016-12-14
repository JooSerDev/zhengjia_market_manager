package com.joosure.server.mvc.wechat.dao.database;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joosure.server.mvc.wechat.entity.pojo.PvStc;
import com.joosure.server.mvc.wechat.entity.pojo.PvSum;

public interface PvDao {

	int insertPv(PvStc record);

	List<PvSum> getBiggestPvRecords(
			@Param("startTime")Date startTime, @Param("endTime")Date endTime,
			@Param("flag")String flag, @Param("limit")int limit);

	List<PvSum> getItemRateByTypeAndDate(
			@Param("startTime")Date startTime, @Param("endTime")Date endTime,
			@Param("itemType")int itemType, @Param("limit")int limit,
			@Param("exgStatus")String exgStatus);

	int getSysUvCountByDay(
			@Param("startTime")Date startTime, @Param("endTime")Date endTime,
			@Param("visitUri")String visitUri);

	int getSysPvCountByDay(
			@Param("startTime")Date startTime, @Param("endTime")Date endTime,
			@Param("visitUri")String visitUri);
	
	int getLoginCount(
			@Param("startTime")Date startTime, @Param("endTime")Date endTime,
			@Param("dayStartTime")Date dayStartTime, @Param("dayEndTime")Date dayEndTime,
			@Param("visitUri")String visitUri);
	int getNewCount(@Param("startTime")Date startTime, @Param("endTime")Date endTime);
}
