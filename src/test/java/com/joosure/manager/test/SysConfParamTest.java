package com.joosure.manager.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.joosure.manager.mvc.wechat.bean.SysConfParam;
import com.joosure.manager.mvc.wechat.service.SysConfParamService;

public class SysConfParamTest extends SpringBaseTest{

	@Autowired
	private SysConfParamService sysConfParamService;
	
	@Test
	public void testInsert(){
		SysConfParam record = new SysConfParam();
		record.setParamid("a");
		record.setParamgroup("a");
		int ret = sysConfParamService.saveConfParam(record);
		System.out.println(ret);
	}
}
