package com.sinosoft.utils.collection;

import java.util.Collection;



public class CollectionUtils extends org.apache.commons.collections.CollectionUtils{

	/**当前集合是否为空
	 * @param collection
	 * @return
	 */
	public static boolean isNotBlank(Collection collection){
		boolean re = false;
		if(collection!=null&&collection.size()>0){
			re = true;
		}
		return re;
	}
	/**当前集合是否不为空
	 * @param collection
	 * @return
	 */
	public static boolean isBlank(Collection collection){
		
		return !isBlank(collection);
	}
}
