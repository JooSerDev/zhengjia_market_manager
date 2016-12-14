package com.joosure.manager.mvc.wechat.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.bean.dto.ItemStatisticsDto;
import com.joosure.manager.mvc.wechat.listener.StatisticsCache;
import com.joosure.manager.mvc.wechat.service.IStatisticsService;
import com.joosure.manager.mvc.wechat.util.ManagerUtils;
import com.joosure.server.mvc.wechat.entity.pojo.Item;
import com.joosure.server.mvc.wechat.entity.pojo.PvSum;
import com.joosure.server.mvc.wechat.service.db.IItemDbService;
import com.joosure.server.mvc.wechat.service.itf.IPvService;

@Service("statisticsService")
public class StatisticsService implements IStatisticsService{

	private static int StatisItemCount = 100;

	@Autowired
	private IItemDbService itemDbService;

	@Autowired
	private IPvService pvService;

	@Override
	public List<Integer> getAllUserCount() {
		return StatisticsCache.allUserCountCache;
	}

	@Override
	public List<Integer> getNewUserCount() {
		return StatisticsCache.newUserCountWithinHoursCache;
	}

	@Override
	public List<ItemStatisticsDto> statisItemData(int itemType, int day, String rankType) {
		List<ItemStatisticsDto> datas = new ArrayList<ItemStatisticsDto>();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -day);
		List<Date> dates = ManagerUtils.getDayToDay(cal, day-1);
		List<PvSum> biggestPv;
		if(ItemStatisticsDto.RankType_PV.equals(rankType)){
			//统计pv
			biggestPv = pvService.getItemPvByTypeAndDate(dates.get(0), 
						dates.get(1),itemType, StatisItemCount,null);
		}else{
			//统计交换成功率，因为 pv 越大，交换成功率越小
			biggestPv = pvService.getItemPvByTypeAndDate(dates.get(0), dates.get(1),
					itemType, StatisItemCount,ItemStatisticsDto.LOCK_EXCHANGED);
			Collections.reverse(biggestPv);
		}
		if(biggestPv == null){
			return datas;
		}
		ItemStatisticsDto itemDto;
		for(PvSum pv : biggestPv){
			itemDto = new ItemStatisticsDto();
			itemDto.setItemPv(pv.getSum());
			itemDto.setItemId(Integer.parseInt(pv.getPkId()));
			Item item = itemDbService.getItemById(Integer.parseInt(pv.getPkId()));
			itemDto.setCreateTime(item.getCreateTime());
			itemDto.setItemType(item.getItemType());
			itemDto.setName(item.getName());
			itemDto.setOwnerNickname(item.getOwnerNickname());
			itemDto.setExgStatus(item.getLockStatus());
			if(ItemStatisticsDto.LOCK_EXCHANGED.equals(itemDto.getExgStatus())){
				itemDto.setExgFinishRate(String.format("%.4f",1d/itemDto.getItemPv()));
			}else{
				itemDto.setExgFinishRate("0");
			}
			datas.add(itemDto);
		}
		return datas;
	}

	@Override
	public List<Integer> getSysPvCount() {
		return StatisticsCache.sysPvCountCache;
	}

	@Override
	public List<Integer> getSysUvCount() {
		return StatisticsCache.sysUvCountCache;
	}

	@Override
	public List<Integer> getNewCount(int days) {
		return StatisticsCache.newUserCountWithinDaysCache;
	}

	@Override
	public List<Integer> getRetentionCount(int days) {
		if(days == 1){
			return StatisticsCache.oneDaysRetentionUserCache;
		}
		if(days == 3){
			return StatisticsCache.threeDaysRetentionUserCache;
		}
		if(days == 7){
			return StatisticsCache.oneWeeksRetentionUserCache;
		}
		if(days == 30){
			return StatisticsCache.oneMonthsRetentionUserCache;
		}
		return null;
	}

}
