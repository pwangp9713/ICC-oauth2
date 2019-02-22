package com.sinosoft.oauth2.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;


/**
* @Description:    url白名单处理 application.yml中配置需要放权的url白名单
* @Author:         zouren
* @CreateDate:     2019/2/20 17:10
* @Version:        1.0
*/
//@ConfigurationProperties(prefix = "permit")
@ConfigurationProperties(prefix = "security.oauth2")
public class PermitUrlProperties {

	/**
	 * 监控中心和swagger需要访问的url
	 */
	private static final String[] ENDPOINTS = { "/**/actuator/health", "/**/actuator/env", "/**/actuator/metrics/**",
			"/**/actuator/trace", "/**/actuator/dump", "/**/actuator/jolokia", "/**/actuator/info",
			"/**/actuator/logfile", "/**/actuator/refresh", "/**/actuator/flyway", "/**/actuator/liquibase",
			"/**/actuator/heapdump", "/**/actuator/loggers", "/**/actuator/auditevents", "/**/actuator/env/PID",
			"/**/actuator/jolokia/**", "/**/actuator/archaius/**", "/**/actuator/beans/**", "/**/actuator/httptrace",
			"/**/v2/api-docs/**", "/**/swagger-ui.html", "/**/swagger-resources/**", "/**/webjars/**", "/**/druid/**",
			"/**/actuator/hystrix.stream","/**/actuator/hystrix.stream**/**", "/**/turbine.stream", "/**/turbine.stream**/**",
			"/**/hystrix","/**/hystrix.stream", "/**/hystrix/**" ,"/**/hystrix/**/**" ,"/**/proxy.stream/**" ,"/**/favicon.ico" };

	private String[] ignored;

	/**
	 * 需要放开权限的url
	 *
	 * @param urls
	 *            自定义的url
	 * @return 自定义的url和监控中心需要访问的url集合
	 */
	public String[] getIgnored() {
		if (ignored == null || ignored.length == 0) {
			return ENDPOINTS;
		}

		List<String> list = new ArrayList<>();
		for (String url : ENDPOINTS) {
			list.add(url);
		}
		for (String url : ignored) {
			list.add(url);
		}

		return list.toArray(new String[list.size()]);
	}

	public void setIgnored(String[] ignored) {
		this.ignored = ignored;
	}
	
	 
 

}
