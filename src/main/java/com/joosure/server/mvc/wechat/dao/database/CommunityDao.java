package com.joosure.server.mvc.wechat.dao.database;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joosure.server.mvc.wechat.entity.pojo.Community;
import com.joosure.server.mvc.wechat.entity.pojo.CommunityType;

public interface CommunityDao {
	
	/**
	 * 获取所有有效社群
	 * select * from zj_community where status = 'normal' order by showOrder, readNum desc
	 * @author Ted-wuhuhu
	 * @Time 2016年11月9日 下午2:19:20
	 * @return
	 */
	List<Community> getAllCommunity();
	
	/**
	 * 根据社群编号获取详细信息
	 * @author Ted-wuhuhu
	 * @Time 2016年11月9日 下午2:19:06
	 * @param cmtyId
	 * @return
	 */
	Community getCommunityById(@Param("cmtyId")Integer cmtyId);
	
	
	int updateCommunity(Community record);
	
	/**
	 * 根据社群编号 获取子分类
	 * @author Ted-wuhuhu
	 * @Time 2016年11月9日 下午2:18:51
	 * @param cmtyTypeId
	 * @return
	 */
	List<CommunityType> getSubCmtyTypeByParentId(@Param("cmtyTypeId")Integer cmtyTypeId);

	/**
	 * 获取所有的文章帖子 二级分类
	 * @author Ted-wuhuhu
	 * @Time 2016年11月14日 下午2:25:43
	 * @return
	 */
	List<CommunityType> getAllCmtySubTypes();
	
	/**
	 * 添加 社群二级分类
	 * @author Ted-wuhuhu
	 * @Time 2016年11月17日 上午10:20:36
	 * @param record
	 * @return
	 */
	int insertCmtyType(CommunityType record);
}
