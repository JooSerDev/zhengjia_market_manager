package com.joosure.manager.mvc.wechat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.bean.ItemTypeCountBean;
import com.joosure.manager.mvc.wechat.dao.TasksDao;
import com.joosure.manager.mvc.wechat.service.TasksService;
import com.joosure.server.mvc.wechat.entity.pojo.ItemType;
import com.joosure.server.mvc.wechat.service.db.IItemDbService;

@Service("tasksService")
public class TasksServiceImpl implements TasksService{
	
	private Logger log = Logger.getLogger(TasksServiceImpl.class);
	
	@Autowired
	private TasksDao tasksDao;
	@Autowired
	private IItemDbService itemDbService;

	@Override
	public int chgExgStatus(Date startTime, Date endTime) {
		return tasksDao.chgExgStatus(startTime, endTime);
	}

	@Override
	public int reorderItemType(Date startTime, Date endTime) {
		//获取当前itemType
		List<ItemType> old = itemDbService.getItemTypes();
		if(old!=null){
			//取到一个按照 数量从大到小 的类别 数组，
			List<ItemTypeCountBean> typesCount = tasksDao.getTypeCount(startTime,endTime);
			int cycleCount = 0;
			if(typesCount!=null && typesCount.size()>0){
				int sort = 0;
				List<Integer> changedTypeId = new ArrayList<Integer>();//被改变的typeid
				//对在这个时间段内 发布宝贝的  类别 重新排序
				for(ItemTypeCountBean typeCount : typesCount){
					changedTypeId.add(typeCount.getTypeId());
					for(ItemType itemType:old){
						if(itemType.getTypeId().equals(typeCount.getTypeId())){
							cycleCount++;
							itemType.setSort(String.valueOf(sort));
							break;
						}
					}
					sort++;
				}
				//对在这个时间段内没有发布宝贝的  类别 重新排序
				for(ItemType itemType:old){
					if(!changedTypeId.contains(itemType.getTypeId().intValue())){
						cycleCount++;
						itemType.setSort(String.valueOf(sort));
						sort++;
					}
				}
				//更新 宝贝类别排序信息到数据库
				for(ItemType itemType : old){
					tasksDao.updateItemType(itemType.getTypeId(),Integer.valueOf(itemType.getSort()));
					cycleCount++;
				}
				log.info("---完成重排序，共进行："+cycleCount+" 次循环操作---");
				return 1;
			}
		}
		return 0;
	}
	

}
