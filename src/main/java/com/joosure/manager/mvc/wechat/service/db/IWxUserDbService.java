package com.joosure.manager.mvc.wechat.service.db;

import java.util.List;

import com.joosure.server.mvc.wechat.entity.pojo.User;

public interface IWxUserDbService {

	int getWxUserCount(User cond);

	List<User> getWxUserList(User cond);

}
