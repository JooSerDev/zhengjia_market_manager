package com.joosure.manager.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.joosure.manager.mvc.wechat.task.QuartzTasks;

public class BuzServiceTest extends SpringBaseTest{
	
	@Autowired
	private QuartzTasks quartzTasks;
	
	@Test
	public void testReorder(){
		quartzTasks.reorderItemType();
	}

}
