package com.joosure.manager.mvc.wechat.service;

import java.util.List;
import java.util.Map;

import com.joosure.manager.mvc.wechat.bean.dto.WxUserDetail;

public interface WxUserDetailService {
	/**
	 * 查询详细用户信息 条目
	 * @param cond
	 * @return
	 */
	int getDetailUserInfoCount(Map<String, Object> cond);

	/**
	 * 查询详细用户信息 列表
	 * @param cond
	 * @return
	 */
	List<WxUserDetail> getDetailUserInfoList(Map<String, Object> cond);
}
