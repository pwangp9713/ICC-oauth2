package com.sinosoft.utils.path;

import com.sinosoft.utils.string.StringUtils;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;



public class GetServerPort {

	private static String ip = "";
	private static String port = "";

	public static String getIpAddressAndPort() {
		String ipadd = "";
		try {
			MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
			Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName(
					"*:type=Connector,*"), Query.match(Query.attr("protocol"),
					Query.value("HTTP/1.1")));
			String host = InetAddress.getLocalHost().getHostAddress();
			String port = objectNames.iterator().next().getKeyProperty("port");
			ipadd = "http" + "://" + host + ":" + port;
			System.out.println(ipadd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ipadd;
	}
	
	/**
	 * 获取tomcat容器ip
	 * 
	 * @return
	 */
	public static String getIpAddress() {
		
		if (StringUtils.isBlank(ip)) {
			try {
//				MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
//				Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName(
//						"*:type=Connector,*"), Query.match(Query.attr("protocol"),
//						Query.value("HTTP/1.1")));
//				String host = InetAddress.getLocalHost().getHostAddress();
//				String port = objectNames.iterator().next().getKeyProperty("port");
//				ip = "http" + "://" + host + ":" + port;
//				System.out.println(ip);
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return ip;
	}

	/**
	 * 获取tomcat容器端口
	 * 
	 * @return
	 */
	public static String getTomcatPort() {
		if (StringUtils.isBlank(port)) {
			try {
				MBeanServer beanServer = ManagementFactory
						.getPlatformMBeanServer();
				Set<ObjectName> objectNames = beanServer.queryNames(
						new ObjectName("*:type=Connector,*"),
						Query.match(Query.attr("protocol"),
								Query.value("HTTP/1.1")));
				port = objectNames.iterator().next().getKeyProperty("port");

			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}

		return port;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
