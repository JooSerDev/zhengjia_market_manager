package com.joosure.manager.mvc.wechat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joosure.manager.mvc.wechat.bean.dto.WxUserDetail;
import com.joosure.manager.mvc.wechat.common.JsonResultBean;
import com.joosure.manager.mvc.wechat.service.WxUserService;
import com.joosure.manager.mvc.wechat.service.db.IWxUserDbService;
import com.joosure.server.mvc.wechat.entity.pojo.Item;
import com.joosure.server.mvc.wechat.entity.pojo.ItemComment;
import com.joosure.server.mvc.wechat.entity.pojo.User;

@Controller
@RequestMapping("/wx")
public class WxUserController {
	
	@Autowired
	private WxUserService wxUserService;
//	@Autowired
//	private WxUserDetailService wxUserDetailService;
	@Autowired
	private IWxUserDbService wxUserDbService;

	@RequestMapping("/userManage")
	public String toWxUserManage(){
		return "wxUserManage";
	}
	
	@RequestMapping("/userList")
	@ResponseBody
	public JsonResultBean<User> getWxUserList(User userCond){
		int count = wxUserDbService.getWxUserCount(userCond);
		List<User> data = wxUserDbService.getWxUserList(userCond);
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
	public JsonResultBean<WxUserDetail> getWxUserDetail(User cond){
		int count = 1;
		List<WxUserDetail> data = wxUserService.getDetailUserInfoList(cond);
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
	public JsonResultBean<WxUserDetail> banUser(User cond){
		int flag = wxUserService.banUser(cond);
		JsonResultBean<WxUserDetail> ret = new JsonResultBean<WxUserDetail>();
		if(flag == 0){
			ret.setRetMsg("该用户已被屏蔽，无需重复操作");
		}else if(flag == 1){
			ret.setRetMsg("屏蔽该用户成功！");
		}else{
			ret.setRetMsg("屏蔽该用户失败，请稍后重试或联系维护人员产看相关数据");
		}
		return ret;
	}
	
	/**
	 * 解除封号
	 * @param userId
	 * @return
	 */
	@RequestMapping("/cancelBanUser")
	@ResponseBody
	public JsonResultBean<WxUserDetail> cancelBanUser(@RequestParam("userId")int userId){
		int flag = wxUserService.cancelBanUser(userId);
		JsonResultBean<WxUserDetail> ret = new JsonResultBean<WxUserDetail>();
		if(flag == 0){
			ret.setRetMsg("该用户未被屏蔽，无需解除屏蔽操作");
		}else if(flag == 1){
			ret.setRetMsg("用户解除屏蔽成功！");
		}else{
			ret.setRetMsg("用户解除屏蔽失败，请稍后重试或联系维护人员产看相关数据");
		}
		return ret;
	}
	
	/**
	 * 清除所有评论
	 * @param userId
	 * @return
	 */
	@RequestMapping("/clearAllCmt")
	@ResponseBody
	public JsonResultBean<WxUserDetail> clearUserCmt(@RequestParam("userId")int userId){
		ItemComment itemCmt = new ItemComment();
		itemCmt.setFromUserId(userId);
		boolean flag = wxUserService.clearAllComment(itemCmt);
		JsonResultBean<WxUserDetail> ret = new JsonResultBean<WxUserDetail>();
		ret.setRetFlag(flag);
		if(flag){
			ret.setRetMsg("清除评论完成！");
		}else{
			ret.setRetMsg("清除失败，请重试");
		}
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
		List<Item> items = wxUserService.getItemsList(userCond);
		int count = wxUserService.getItemsCount(userCond);
		JsonResultBean<Item> ret = new JsonResultBean<Item>(items,count);
		return ret;
	}
	
	@RequestMapping("/showUserInfo")
	public String toUserInfo(@RequestParam("userId")String userId,HttpServletRequest request){
		request.setAttribute("userId", userId);
		return "showUserInfo";
	}
}
