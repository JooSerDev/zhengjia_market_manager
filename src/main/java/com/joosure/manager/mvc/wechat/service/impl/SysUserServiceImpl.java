package com.joosure.manager.mvc.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.common.base.entity.QryCondBean;
import com.joosure.manager.mvc.wechat.bean.SysUser;
import com.joosure.manager.mvc.wechat.dao.SysUserDao;
import com.joosure.manager.mvc.wechat.service.SysUserService;
import com.joosure.manager.mvc.wechat.util.ManagerUtils;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService{

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
		return sysUserDao.insertSelective(user);
	}

	@Override
	public int deleteSysUser(SysUser user) {
		user.setStatus(0);//状态置为无效
		return sysUserDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public int changeSysUser(SysUser user) {
		return sysUserDao.updateByPrimaryKeySelective(user);
	}

}
