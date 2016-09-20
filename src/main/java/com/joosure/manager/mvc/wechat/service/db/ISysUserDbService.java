package com.joosure.manager.mvc.wechat.service.db;

import java.util.List;

import com.joosure.manager.mvc.wechat.bean.SysUser;
import com.joosure.manager.mvc.wechat.common.QryCondBean;

public interface ISysUserDbService {

	SysUser getUserById(String loginId);

	List<SysUser> getUserList(QryCondBean qryCond);

	int getUserListCount(QryCondBean qryCond);

	int addSysUser(SysUser user);

	int deleteSysUser(SysUser user);

	int changeSysUser(SysUser user);

}
