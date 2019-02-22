package com.sinosoft.utils.string;

import java.util.UUID;

public class UUIDUtil {
	/**
	 * 32位UUID
	 * @return
	 */
	public static String getUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("[-]", "");
	}

	public static void main(String arg[]){
		System.out.println(UUIDUtil.getUUID());
		//String s = "11479b3f3131461c9829e96ab2a19256";
		//System.out.println(s.length());
	}
}
