package com.joosure.manager.mvc.wechat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.manager.mvc.wechat.bean.SysUserOpr;
import com.joosure.manager.mvc.wechat.dao.SysUserOprDao;
import com.joosure.manager.mvc.wechat.service.SysUserOprService;

@Service("sysUserOprService")
public class SysUserOprServiceImpl implements SysUserOprService{

	@Autowired
	private SysUserOprDao sysUserOprDao;
	
	public int insertUserOpr(SysUserOpr userOpr){
		return sysUserOprDao.insertSelective(userOpr);
	}
	
}
