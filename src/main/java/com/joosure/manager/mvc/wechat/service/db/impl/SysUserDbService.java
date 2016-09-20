package com.joosure.manager.mvc.wechat.service.db.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.bean.SysUser;
import com.joosure.manager.mvc.wechat.common.QryCondBean;
import com.joosure.manager.mvc.wechat.dao.SysUserDao;
import com.joosure.manager.mvc.wechat.service.db.ISysUserDbService;

@Service("sysUserDbService")
public class SysUserDbService implements ISysUserDbService{

	@Autowired
	private SysUserDao sysUserDao;
	
	@Override
	public SysUser getUserById(String loginId) {
		List<SysUser> ret = sysUserDao.getSysUserByLoginId(loginId);
		if(ret != null && ret.size()==1){
			return ret.get(0);
		}
		return null;
	}

	@Override
	public List<SysUser> getUserList(QryCondBean qryCond) {
		return sysUserDao.getUserList(qryCond);
	}

	@Override
	public int getUserListCount(QryCondBean qryCond) {
		return sysUserDao.getUserListCount(qryCond);
	}

	@Override
	public int addSysUser(SysUser user) {
		//先检查是否存在，然后再插入
//		if()
		return sysUserDao.insertSelective(user);
	}

	@Override
	public int deleteSysUser(SysUser user) {
		return sysUserDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public int changeSysUser(SysUser user) {
		return sysUserDao.updateByPrimaryKeySelective(user);
	}

}
