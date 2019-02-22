package com.sinosoft.utils.ID;

import java.util.UUID;

/**
 * @ClassName: CommUtil
 * @Description: 工具类
 * @version V1.0
 */
public class UUIDUtil {
	/**
	 * 获取UUID
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
