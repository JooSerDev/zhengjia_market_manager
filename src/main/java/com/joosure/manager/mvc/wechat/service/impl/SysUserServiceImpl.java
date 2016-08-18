package com.joosure.manager.mvc.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.bean.SysUser;
import com.joosure.manager.mvc.wechat.dao.SysUserDao;
import com.joosure.manager.mvc.wechat.service.SysUserService;

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

}
