package com.joosure.manager.mvc.wechat.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.shawn.server.core.util.EncryptUtil.*;

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
}
