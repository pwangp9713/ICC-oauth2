package com.sinosoft.utils.classzz;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

/**
 * 扩展Apache Commons BeanUtils, 提供反射方面缺失功能的封装.
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {

	protected static final Log logger = LogFactory.getLog(BeanUtils.class);

	private BeanUtils() {
	}

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 *
	 * @throws NoSuchFieldException 如果没有该Field时抛�?.
	 */
	public static Field getDeclaredField(Object object, String propertyName) throws NoSuchFieldException {
		Assert.notNull(object,"object is required; it must not be null");
		Assert.hasText(propertyName,"propertyName is required; it must not be null");

		return getDeclaredField(object.getClass(), propertyName);
	}

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 *
	 * @throws NoSuchFieldException 如果没有该Field时抛
	 */
	public static Field getDeclaredField(Class<?> clazz, String propertyName) throws NoSuchFieldException {
		Assert.notNull(clazz,"clazz is required; it must not be null");
		Assert.hasText(propertyName,"propertyName is required; it must not be null");
		for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(propertyName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定�?,继续向上转型
			}
		}
		throw new NoSuchFieldException("No such field: " + clazz.getName() + '.' + propertyName);
	}

	/**
	 * 暴力获取对象变量�?,忽略private,protected修饰符的限制.
	 *
	 * @throws NoSuchFieldException 如果没有该Field时抛�?.
	 */
	public static Object forceGetProperty(Object object, String propertyName) throws NoSuchFieldException {
		Assert.notNull(object,"object is required; it must not be null");
		Assert.hasText(propertyName,"propertyName is required; it must not be null");

		Field field = getDeclaredField(object, propertyName);

		boolean accessible = field.isAccessible();
		field.setAccessible(true);

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			logger.info("error wont' happen");
		}
		field.setAccessible(accessible);
		return result;
	}

	/**
	 * 暴力设置对象变量�?,忽略private,protected修饰符的限制.
	 *
	 * @throws NoSuchFieldException 如果没有该Field时抛�?.
	 */
	public static void forceSetProperty(Object object, String propertyName, Object newValue)
			throws NoSuchFieldException {
		Assert.notNull(object,"object is required; it must not be null");
		Assert.hasText(propertyName,"propertyName is required; it must not be null");

		Field field = getDeclaredField(object, propertyName);
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		try {
			field.set(object, newValue);
		} catch (IllegalAccessException e) {
			logger.info("Error won't happen");
		}
		field.setAccessible(accessible);
	}

	/**
	 * 暴力调用对象函数,忽略private,protected修饰符的限制.
	 *
	 * @throws NoSuchMethodException 如果没有该Method时抛�?.
	 */
	public static Object invokePrivateMethod(Object object, String methodName, Object... params)
			throws NoSuchMethodException {
		Assert.notNull(object,"object is required; it must not be null");
		Assert.hasText(methodName,"methodName is required; it must not be null");
		@SuppressWarnings("rawtypes")
		Class[] types = new Class[params.length];
		for (int i = 0; i < params.length; i++) {
			types[i] = params[i].getClass();
		}

		Class<?> clazz = object.getClass();
		Method method = null;
		for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				method = superClass.getDeclaredMethod(methodName, types);
				break;
			} catch (NoSuchMethodException e) {
				// 方法不在当前类定�?,继续向上转型
			}
		}

		if (method == null)
			throw new NoSuchMethodException("No Such Method:" + clazz.getSimpleName() + methodName);

		boolean accessible = method.isAccessible();
		method.setAccessible(true);
		Object result = null;
		try {
			result = method.invoke(object, params);
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
		method.setAccessible(accessible);
		return result;
	}

	/**
	 * 按Filed的类型取得Field列表.
	 */
	public static List<Field> getFieldsByType(Object object, Class<?> type) {
		List<Field> list = new ArrayList<Field>();
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getType().isAssignableFrom(type)) {
				list.add(field);
			}
		}
		return list;
	}

	/**
	 * 按FiledName获得Field的类�?.
	 */
	public static Class<?> getPropertyType(Class<?> type, String name) throws NoSuchFieldException {
		return getDeclaredField(type, name).getType();
	}

	/**
	 * 获得field的getter函数名称.
	 */
	@SuppressWarnings("deprecation")
	public static String getGetterName(Class<?> type, String fieldName) {
		Assert.notNull(type, "Type required");
		Assert.hasText(fieldName, "FieldName required");

		if (type.getName().equals("boolean")) {
			return "is" + StringUtils.capitalise(fieldName);
		} else {
			return "get" + StringUtils.capitalise(fieldName);
		}
	}

	/**
	 * 获得field的getter函数,如果找不到该方法,返回null.
	 */
	public static Method getGetterMethod(Class<?> type, String fieldName) {
		try {
			return type.getMethod(getGetterName(type, fieldName));
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	public static boolean isExist(Class<?> type, String fieldName) {
		try {
			type.getMethod(getGetterName(type, fieldName));
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
		return true;
	}
}
