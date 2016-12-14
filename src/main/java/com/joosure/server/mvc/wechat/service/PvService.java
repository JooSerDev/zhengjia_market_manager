package com.joosure.server.mvc.wechat.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.server.mvc.wechat.dao.database.PvDao;
import com.joosure.server.mvc.wechat.entity.pojo.PvStc;
import com.joosure.server.mvc.wechat.entity.pojo.PvSum;
import com.joosure.server.mvc.wechat.service.itf.IPvService;

@Service
public class PvService implements IPvService{

	@Autowired
	private PvDao pvDao;

	@Override
	public int savePvRecord(PvStc ntatRecord) {
		return pvDao.insertPv(ntatRecord);
	}

	@Override
	public List<PvSum> getBiggestPvRecord(Date startTime, Date endTime, String flag, int limit) {
		return pvDao.getBiggestPvRecords(startTime,endTime,flag,limit);
	}

	@Override
	public List<PvSum> getItemPvByTypeAndDate(Date startTime, Date endTime,int itemType, 
			int statisItemCount,String exgStatus) {
		return pvDao.getItemRateByTypeAndDate(startTime,endTime,itemType,statisItemCount,exgStatus);
	}

	@Override
	public int getSysUvCountByDay(Date startTime, Date endTime,String visitUrl) {
		return pvDao.getSysUvCountByDay(startTime,endTime,visitUrl);
	}

	@Override
	public int getSysPvCountByDay(Date startTime, Date endTime,String visitUrl) {
		return pvDao.getSysPvCountByDay(startTime,endTime,visitUrl);
	}

	@Override
	public int getNewUserCountWithinTheDay(Date startTime, Date endTime) {
		return 0;
	}

}
