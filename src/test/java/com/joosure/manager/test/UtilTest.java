package com.joosure.manager.test;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.joosure.manager.mvc.wechat.util.ManagerUtils;

public class UtilTest {

	@Test
	public void testEncrypt(){
		String pass = "admin123";//240BE518FABD2724DDB6F04EEB1DA5967448D7E831C08C8FA822809F74C720A9
		try {
			System.out.println(ManagerUtils.encryptBySHA256(pass));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testStringPlusNull(){
		String a = "a";
		String b = null;
		b = b == null ? "" : b;
		System.out.println(a+b);
	}
}
