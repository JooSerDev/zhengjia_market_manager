package com.joosure.manager.mvc.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joosure.manager.mvc.wechat.common.JsonResultBean;
import com.joosure.server.mvc.wechat.entity.pojo.Item;
import com.joosure.server.mvc.wechat.entity.pojo.User;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@RequestMapping("/itemManage")
	public String toItemManage(){
		return "itemManage";
	}
	
	@RequestMapping("/itemList")
	@ResponseBody
	public JsonResultBean<Item> getItemList(@RequestParam("itemId")String itemId){
		JsonResultBean<Item> ret = new JsonResultBean<Item>(null,0);
		return ret;
	}
	
	@RequestMapping("/itemDetail")
	public String toItemDetail(@RequestParam("itemId")String itemId,HttpServletRequest request){
		request.setAttribute("itemId", itemId);
		return "itemDetail";
	}
	
	@RequestMapping("/itemDetail")
	@ResponseBody
	public JsonResultBean<Item> getItemDetail(@RequestParam("itemId")String itemId){
		JsonResultBean<Item> ret = new JsonResultBean<Item>(null,0);
		return ret;
	}
	
}
