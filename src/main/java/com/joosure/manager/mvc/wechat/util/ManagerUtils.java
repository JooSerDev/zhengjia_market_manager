package com.joosure.manager.mvc.wechat.util;

import static com.shawn.server.core.util.EncryptUtil.byte2hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joosure.server.mvc.wechat.constant.CommonConstant;

public class ManagerUtils {

	private static final String KEY_SHA_256 = "SHA-256";

	/**
	 * SHA-256 加密
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static String encryptBySHA256(String data) throws NoSuchAlgorithmException{
		MessageDigest sha = MessageDigest.getInstance(KEY_SHA_256);
		sha.update(data.getBytes());
		return byte2hex(sha.digest());
	}

	public static String encryptBySHA256(String data,String salt) throws NoSuchAlgorithmException{
		salt = salt == null ? "":salt;
		MessageDigest sha = MessageDigest.getInstance(KEY_SHA_256);
		sha.update((data+salt).getBytes());
		return byte2hex(sha.digest());
	}
	
	public static String getDefaultPass(String salt){
		try {
			return encryptBySHA256(CommonConstant.PASSWORD,salt);
		} catch (NoSuchAlgorithmException e) {
			//log
		}
		return "";
	}
	
	/**
	 * 传入一个时间，获取该时间当天的 0 点 和 24点
	 * @param cal
	 * @return
	 */
	public static List<Date> getDefaultBETime(Calendar cal){
		List<Date> date = new ArrayList<Date>();
		if(cal == null){
			cal = Calendar.getInstance();
		}
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND, 0);
		Date startTime = cal.getTime();//开始时间
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE,59);
		cal.set(Calendar.SECOND, 59);
		Date endTime = cal.getTime();//结束时间
		date.add(startTime);
		date.add(endTime);
		return date;
	}
}
