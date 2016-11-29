package com.joosure.server.mvc.wechat.service.itf;

import java.util.List;

import com.joosure.server.mvc.wechat.entity.pojo.Community;
import com.joosure.server.mvc.wechat.entity.pojo.CommunityType;

/**
 * 
 * @author Ted-wuhuhu
 * @Time 2016年10月21日 下午4:27:18
 *
 */
public interface ICommunityService {
	
	int updateCommunity(Community record);
	
	List<Community> getAllCommunity();
	
	Community getCmtyById();

	/**
	 * 获取某社群文章帖子的二级分类
	 * @author Ted-wuhuhu
	 * @Time 2016年11月14日 上午11:46:37
	 * @param cmtyId
	 * @return
	 */
	List<CommunityType> getSubCmtyTypes(Integer cmtyId);
	
	/**
	 * 获取所有的文章帖子 二级分类
	 * @author Ted-wuhuhu
	 * @Time 2016年11月14日 下午2:25:43
	 * @return
	 */
	List<CommunityType> getAllCmtySubTypes();
}
