package com.sinosoft.utils.classzz;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.lang.ClassUtils;
import org.springframework.util.ReflectionUtils;



public class ClassUtil extends ClassUtils {
	/**
	 * @param obj
	 * @return 取得类的所有FIELD
	 */
	public static Field[] getField(Object obj) {
		Field[] fields = new Field[getFieldLength(obj)];
		copyField(fields, obj.getClass(), 0);
		return fields;
	}
	
	/**
	 * @param className 完整class字串 如：com.XXX.AAA
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static Field[] getField(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return getField(Class.forName(className).newInstance());
	}

	/**
	 * @param fields
	 * @param obj
	 * @param dsize
	 */
	private static void copyField(Field[] fields, Class<?> obj, int dsize) {
		if (obj.getSuperclass() != null) {
			System.arraycopy(obj.getDeclaredFields(), 0, fields, dsize, obj
					.getDeclaredFields().length);
			dsize = dsize + obj.getDeclaredFields().length;
			copyField(fields, obj.getSuperclass(), dsize);
		} else {
			System.arraycopy(obj.getDeclaredFields(), 0, fields, dsize, obj
					.getDeclaredFields().length);
			dsize = dsize + obj.getDeclaredFields().length;
		}
	}

	/**
	 * @param obj
	 * @return
	 */
	private static int getFieldLength(Object obj) {
		return obj.getClass().getDeclaredFields().length
				+ getClassFieldLength(obj.getClass());
	}

	/**
	 * @param obj
	 * @return
	 */
	private static int getClassFieldLength(Class<?> obj) {
		int length = 0;
		if (obj.getSuperclass() != null) {
			length = length + obj.getSuperclass().getDeclaredFields().length;
			getClassFieldLength(obj.getSuperclass());
		}
		return length;
	}
	/**得到对象属性
	 * @param obj 对象
	 * @param filedName 属性名
	 * @return
	 * @author zouren
	 */
	public static Field getFieldByName(Object obj,String filedName){
		Field f = null;
		try {
			f = obj.getClass().getDeclaredField(filedName);
		} catch (Exception e) {
			try {
				if (obj.getClass().getSuperclass() != null) {
					f = obj.getClass().getSuperclass().getDeclaredField(
							filedName);
				}
			} catch (Exception e1) {
				e.printStackTrace();
				System.out.println(" not find filed ");
			}
		}
		return f;
	}
	/**
	 * @param obj
	 * @param filedName
	 * @param filedValue
	 *            改变一个filedName 的 filedValue
	 */
	public static void setFiledValue(Object obj, String filedName,
			Object filedValue) {
		Field f = getFieldByName(obj,filedName);
		if (f != null) {
			f.setAccessible(true);
			try {
				f.set(obj, filedValue);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * @param obj
	 * @param filedName
	 * @return 取得属性的值的值
	 */
	public static Object getFledValue(Object obj, String filedName) {
		if(filedName.indexOf(".")!=-1){
			String[] filedNames=filedName.split("\\.");
			Object val=obj;
			for(int i=0;i<filedNames.length;i++){
				val=getFledValue(val,filedNames[i]);
				if(val==null || "".equals(val)){
					val="";
					break;
				}
			}
			
			return val;
		}else{
		try {
				Class<?> objClass = obj.getClass();
				Method method;
				try {
					method = objClass.getMethod("get"
							+ filedName.substring(0, 1).toUpperCase()
							+ filedName.substring(1));
				} catch (Exception e) {
					method = objClass.getMethod("get"
							+ filedName.substring(0, 1).toLowerCase()
							+ filedName.substring(1));
				}
				obj = method.invoke(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (obj == null) {
				return "";
			}
		}
		return obj;
	}

	/**
	 * 通过反射, 获得Class定义中声明的父类的泛型参数的类型. 如无法找到, 返回Object.class.
	 * 
	 * 如public UserDao extends HibernateDao<User,Long>
	 * 
	 * @param clazz
	 *            clazz The class to introspect
	 * @param index
	 *            the Index of the generic ddeclaration,start from 0.
	 * @return the index generic declaration, or Object.class if cannot be
	 *         determined
	 */

	public static Class<?> getSuperClassGenricType(final Class<?> clazz,
			final int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return  (Class<?>) params[index];
	}
	
	/**
	 * @param entityClass 
	 * @param clomeName
	 * @return 对象属性的类型
	 */
	public static Class<?> getFieldClass(Class<?> entityClass,String clomeName) {
		Class<?> clazz = null;
		try {
			clazz = BeanUtils.getPropertyType(entityClass, clomeName);
		}
		catch(Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
		return clazz;
	}
	
	public static void main(String args[]){
	
		
	}

}
