package com.joosure.manager.mvc.wechat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joosure.manager.mvc.wechat.bean.dto.WxUserDetail;
import com.joosure.manager.mvc.wechat.common.JsonResultBean;
import com.joosure.manager.mvc.wechat.service.WxUserDetailService;
import com.joosure.manager.mvc.wechat.service.WxUserService;
import com.joosure.server.mvc.wechat.entity.pojo.Item;
import com.joosure.server.mvc.wechat.entity.pojo.User;

@Controller
@RequestMapping("/wx")
public class WxUserController {
	
	@Autowired
	private WxUserService wxUserService;
	@Autowired
	private WxUserDetailService wxUserDetailService;

	@RequestMapping("/userManage")
	public String toWxUserManage(){
		return "wxUserManage";
	}
	
	@RequestMapping("/userList")
	@ResponseBody
	public JsonResultBean<User> getWxUserList(User userCond){
		Map<String,Object> cond = new HashMap<String,Object>();
		cond.put("startIndex", userCond.getOffset());
		cond.put("limit", userCond.getLimit());
		cond.put("state", userCond.getState());
		cond.put("mobile", userCond.getMobile());
		cond.put("nickname", userCond.getNickname());
		cond.put("openid", userCond.getOpenid());
		int count = wxUserService.getWxUserCount(cond);
		List<User> data = wxUserService.getWxUserList(cond);
		JsonResultBean<User> ret = new JsonResultBean<User>(data,count);
		return ret;
	}
	
	@RequestMapping("/userDetail")
	public String toWxUserDetail(@RequestParam("userId")String userId,HttpServletRequest request){
		//通过userId 获取页面需要的信息，如基本信息，宝贝信息，交易信息
		request.setAttribute("userId", userId);
		return "wxUserDetail";
	}
	
	
	@RequestMapping("/userDetailInfo")
	@ResponseBody
	public JsonResultBean<WxUserDetail> getWxUserDetail(@RequestParam("userId")String userId){
		Map<String,Object> cond = new HashMap<String,Object>();
		cond.put("userId", userId);
//		int count = wxUserDetailService.getDetailUserInfoCount(cond);
		int count = 1;
		List<WxUserDetail> data = wxUserDetailService.getDetailUserInfoList(cond);
		JsonResultBean<WxUserDetail> ret = new JsonResultBean<WxUserDetail>(data,count);
		return ret;
	}
	/**
	 * 封号
	 * @param userId
	 * @return
	 */
	@RequestMapping("/banUser")
	@ResponseBody
	public JsonResultBean<WxUserDetail> banUser(@RequestParam("userId")String userId){
		Map<String,Object> cond = new HashMap<String,Object>();
		cond.put("userId", userId);
		boolean flag = wxUserService.banUser(cond);
		JsonResultBean<WxUserDetail> ret = new JsonResultBean<WxUserDetail>(flag,null);
		return ret;
	}
	
	/**
	 * 解除封号
	 * @param userId
	 * @return
	 */
	@RequestMapping("/cancelBanUser")
	@ResponseBody
	public JsonResultBean<WxUserDetail> cancelBanUser(@RequestParam("userId")String userId){
		Map<String,Object> cond = new HashMap<String,Object>();
		cond.put("userId", userId);
		boolean flag = wxUserService.cancelBanUser(cond);
		JsonResultBean<WxUserDetail> ret = new JsonResultBean<WxUserDetail>(flag,null);
		return ret;
	}
	
	/**
	 * 清除所有评论
	 * @param userId
	 * @return
	 */
	@RequestMapping("/clearAllCmt")
	@ResponseBody
	public JsonResultBean<WxUserDetail> clearUserCmt(@RequestParam("userId")String userId){
		Map<String,Object> cond = new HashMap<String,Object>();
		cond.put("userId", userId);
		boolean flag = wxUserService.clearAllComment(cond);
		JsonResultBean<WxUserDetail> ret = new JsonResultBean<WxUserDetail>(flag,null);
		return ret;
	}
	
	/**
	 * 该用户发布的所有商品
	 * @param userId
	 * @return
	 */
	@RequestMapping("/userItems")
	@ResponseBody
	public JsonResultBean<Item> getWxUserItem(User userCond){
		Map<String,Object> cond = new HashMap<String,Object>();
		cond.put("userId", userCond.getUserId());
		cond.put("startIndex", userCond.getOffset());
		cond.put("limit", userCond.getLimit());
		List<Item> items = wxUserService.getItemsList(cond);
		int count = wxUserService.getItemsCount(cond);
		JsonResultBean<Item> ret = new JsonResultBean<Item>(items,count);
		return ret;
	}
}
