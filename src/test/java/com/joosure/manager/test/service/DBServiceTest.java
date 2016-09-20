package com.joosure.manager.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.joosure.manager.mvc.wechat.service.db.IWxUserMsgDbService;
import com.joosure.manager.test.SpringBaseTest;
import com.joosure.server.mvc.wechat.constant.CommonConstant;
import com.joosure.server.mvc.wechat.entity.pojo.WxUserMsg;


public class DBServiceTest extends SpringBaseTest{
	
	@Autowired
	private IWxUserMsgDbService wxUserMsgDbService;
	
	@Test
	public void testMsgDbInsert(){
		WxUserMsg record = new WxUserMsg();
		record.setUserid(1);
		record.setUsername("b");
		System.out.println(wxUserMsgDbService.insertWxUserMsg(record));
	}
	@Test
	public void testMsgDbItf(){
		wxUserMsgDbService.receiveWxUserMsg(CommonConstant.UserMsgType_Rep, 2); //0,1,0--2
		wxUserMsgDbService.receiveWxUserMsg(CommonConstant.UserMsgType_Rep, 3);//0,1,0--3
		wxUserMsgDbService.receiveWxUserMsg(CommonConstant.UserMsgType_Rep, 3);//0,2,0--3
		wxUserMsgDbService.receiveWxUserMsg(CommonConstant.UserMsgType_Req, 3);//1,2,0--3
		wxUserMsgDbService.receiveWxUserMsg(CommonConstant.UserMsgType_Apr, 3);//1,2,1--3
		wxUserMsgDbService.readWxUserMsg(CommonConstant.UserMsgType_Rep, 2);//0,0,0--2
		wxUserMsgDbService.readWxUserMsg(CommonConstant.UserMsgType_Rep, 3);//1,1,1--3
		wxUserMsgDbService.readWxUserMsg(CommonConstant.UserMsgType_Apr, 3);//1,1,0--3
	}
	
	@Test
	public void t(){
		/*String a = " 1";
		int b = Integer.parseInt(a);
		System.out.println(b);
		String a = "[2, 1, 1]";
		System.out.println(a.substring(1, a.length()-1));*/
	}

}
