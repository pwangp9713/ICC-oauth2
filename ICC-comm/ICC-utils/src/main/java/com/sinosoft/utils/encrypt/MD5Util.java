package com.sinosoft.utils.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	/**
	 * 获取md5的核心方法
	 * 
	 * @param str
	 * @return
	 */
	public static  String getMd5(String str) {
		String s = str;
		if (s == null) {
			return "";
		} else {
			String value = null;
			MessageDigest md5 = null;
			try {
				md5 = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException ex) {
				ex.printStackTrace();
			}
			sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder();
			try {
				value = baseEncoder.encode(md5.digest(s.getBytes("utf-8")));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return value;
		}
	}
	
	
	/**
	 * md5测试用例
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MD5Util nMd5 = new MD5Util();
		String value = nMd5.getMd5("qwert5");
		System.out.println(value);
		System.out.printf("wCT+CUlWXJDpKMuSkUFDhg==");
		
		
	}

}


	