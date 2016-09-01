package com.joosure.manager.mvc.wechat.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.bean.ItemTypeCountBean;
import com.joosure.manager.mvc.wechat.dao.TasksDao;
import com.joosure.manager.mvc.wechat.service.TasksService;
import com.joosure.server.mvc.wechat.dao.database.ItemDao;
import com.joosure.server.mvc.wechat.entity.pojo.ItemType;

@Service("tasksService")
public class TasksServiceImpl implements TasksService{
	
	@Autowired
	private TasksDao tasksDao;
	@Autowired
	private ItemDao itemDao;

	@Override
	public int chgExgStatus(Date startTime, Date endTime) {
		return tasksDao.chgExgStatus(startTime, endTime);
	}

	@Override
	public int reorderItemType(Date startTime, Date endTime) {
		//获取当前itemType
		List<ItemType> old = itemDao.getItemTypes();
		List<ItemType> temp = itemDao.getItemTypes();
		if(old!=null){
			//取到一个按照 数量从大到小 的类别 数组，
			List<ItemTypeCountBean> typesCount = tasksDao.getTypeCount(startTime,endTime);
			if(typesCount!=null && typesCount.size()>0){
				int sort = 0;
				for(ItemTypeCountBean typeCount : typesCount){
					for(ItemType itemType:old){
						if(itemType.getTypeId().equals(typeCount.getTypeId())){
							itemType.setSort(String.valueOf(sort));
						}
					}
					sort++;
				}
			}
		}
		return 0;
	}
	

}
