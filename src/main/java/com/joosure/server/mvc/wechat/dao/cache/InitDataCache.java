package com.joosure.server.mvc.wechat.dao.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.joosure.server.mvc.wechat.entity.pojo.Community;
import com.joosure.server.mvc.wechat.entity.pojo.CommunityType;
import com.joosure.server.mvc.wechat.service.itf.ICommunityService;
import com.shawn.server.core.util.SpringUtil;

/**
 * 
 * @author Ted-wuhuhu
 * @Time 2016年10月21日 上午11:03:13
 * 
 * 初始化数据缓存到内存中
 *
 */
public class InitDataCache {
	
	private static List<Community> allCommunity = null;
	private static List<CommunityType> cmtySubTypes = null;
	
	private static Map<Integer,List<CommunityType>> cmtySub = null;
	private static Timer timer = new Timer();
	
	public static void init(){
		loadData();
	}
	
	private static void loadData(){
//		timer.schedule(new LoadDataTask(), 0);
		//因为社群信息时通过配置在数据库中的，所以不需要实时刷新
		refreshCommunity();
		refreshCommunitySubTypes();
		fillCmtySubType();
	}

	/**
	 * 获取所有的社群
	 * @return
	 */
	public static List<Community> getCommunity() {
		if(allCommunity == null || allCommunity.size() == 0){
			ICommunityService communityService = SpringUtil.getBean("communityService");
			allCommunity = communityService.getAllCommunity();
		}
		return allCommunity;
	}
	
	public static List<CommunityType> getAllCmtySubTypes(){
		if(cmtySubTypes == null || cmtySubTypes.size() == 0){
			ICommunityService communityService = SpringUtil.getBean("communityService");
			cmtySubTypes = communityService.getAllCmtySubTypes();
		}
		return cmtySubTypes;
	}
	
	/**
	 * 主动刷新 社群信息
	 * @return
	 */
	public static List<Community> refreshCommunity(){
		if(allCommunity != null){
			allCommunity.clear();
		}
		allCommunity = getCommunity();
		return allCommunity;
	}
	
	/**
	 * 主动刷新 社群信息
	 * @return
	 */
	public static List<CommunityType> refreshCommunitySubTypes(){
		if(cmtySubTypes != null){
			cmtySubTypes.clear();
		}
		cmtySubTypes = getAllCmtySubTypes();
		return cmtySubTypes;
	}
	
	/**
	 * 对每一个社群和它所拥有的二级分类放在map中
	 * @author Ted-wuhuhu
	 * @Time 2016年11月14日 下午2:36:32
	 */
	public static void fillCmtySubType(){
		if(cmtySub == null){
			cmtySub = new HashMap<Integer,List<CommunityType>>();
		}
		ICommunityService communityService = SpringUtil.getBean("communityService");
		if(allCommunity == null){
			allCommunity = communityService.getAllCommunity();
		}
		for(Community cmty : allCommunity){
			List<CommunityType> subType = communityService.getSubCmtyTypes(cmty.getCmtyId());
			if(cmtySub.get(cmty.getCmtyId()) == null){
				cmtySub.put(cmty.getCmtyId(), subType);
			}
		}
	}
	
	
	/**
	 * 从内存中获取单个社群信息
	 * @param cmtyId
	 * @return
	 */
	public static Community getCommunityById(Integer cmtyId){
		if(allCommunity != null){
			for(Community cmty : allCommunity){
				if(cmty.getCmtyId().equals(cmtyId)){
					return cmty;
				}
			}
		}
		return null;
	}
	
	/**
	 * 定时刷新数据
	 * @author Ted-wuhuhu
	 * @Time 2016年10月21日 上午11:57:22
	 *
	 */
	private static class LoadDataTask extends TimerTask {
		@Override
		public void run() {
			refreshCommunity();
			refreshCommunitySubTypes();
			timer.schedule(new LoadDataTask(), 5 * 60 * 1000);
		}
	}
	
	/**
	 * 获取某一个社群的所有分类
	 * @author Ted-wuhuhu
	 * @Time 2016年11月14日 下午2:40:48
	 * @param cmtyId
	 * @return
	 */
	public static List<CommunityType> getCmtySubTypes(Integer cmtyId){
		if(cmtySub == null){
			fillCmtySubType();
		}
		return cmtySub.get(cmtyId); 
	}
	
	/**
	 * 获取某个指定的 社群二级分类
	 * @author Ted-wuhuhu
	 * @Time 2016年11月17日 下午4:29:51
	 * @param typeId
	 * @return
	 */
	public static CommunityType getCmtySubType(Integer typeId){
		getAllCmtySubTypes();//check if null;
		for(CommunityType sub : cmtySubTypes){
			if(sub.getTypeId().equals(typeId)){
				return sub;
			}
		}
		return null;
	}
	
	
}
