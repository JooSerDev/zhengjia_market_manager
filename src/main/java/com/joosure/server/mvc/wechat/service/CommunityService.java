package com.joosure.server.mvc.wechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.server.mvc.wechat.dao.database.CommunityDao;
import com.joosure.server.mvc.wechat.entity.pojo.Community;
import com.joosure.server.mvc.wechat.entity.pojo.CommunityType;
import com.joosure.server.mvc.wechat.service.itf.ICommunityService;

/**
 * 
 * @author Ted-wuhuhu
 * @Time 2016年10月22日 下午3:44:16
 *
 */
@Service("communityService")
public class CommunityService implements ICommunityService{
	
	@Autowired
	private CommunityDao communityDao;

	@Override
	public int updateCommunity(Community record) {
		return 0;
	}

	@Override
	public List<Community> getAllCommunity() {
		return communityDao.getAllCommunity();
	}

	@Override
	public Community getCmtyById() {
		return null;
	}

	@Override
	public List<CommunityType> getSubCmtyTypes(Integer cmtyId) {
		return communityDao.getSubCmtyTypeByParentId(cmtyId);
	}

	@Override
	public List<CommunityType> getAllCmtySubTypes() {
		return communityDao.getAllCmtySubTypes();
	}

	
}
