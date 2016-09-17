package com.joosure.manager.mvc.wechat.service;

import java.util.List;

import com.joosure.manager.mvc.wechat.bean.dto.WxUserDetail;
import com.joosure.server.mvc.wechat.entity.pojo.User;

public interface WxUserDetailService {
	/**
	 * 查询详细用户信息 条目
	 * @param cond
	 * @return
	 */
	int getDetailUserInfoCount(User cond);

	/**
	 * 查询详细用户信息 列表
	 * @param cond
	 * @return
	 */
	List<WxUserDetail> getDetailUserInfoList(User cond);
}
