package com.joosure.manager.test;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.joosure.manager.mvc.wechat.util.ManagerUtils;

public class UtilTest {

	@Test
	public void testEncrypt(){
		String pass = "88888888";//E79952EF635DDC0590B6290DA207E9AF314371B93F4E15BCDF733624195F3B26
		try {
			System.out.println(ManagerUtils.encryptBySHA256(pass,"9999"));
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
