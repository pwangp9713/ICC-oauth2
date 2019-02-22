package com.sinosoft.oauth2.client.token;

import com.sinosoft.oauth2.client.token.store.RedisTemplateTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/** 
* @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2018年4月5日 下午19:52:21
* 类说明 
* redis存储token
*/
@Configuration
public class TokenStoreConfig {
 
//	@Resource
//	private DataSource dataSource ;
@Autowired(required = false)
	private RedisTemplate  redisTemplate ;
	
	
//	@Bean
//	@ConditionalOnProperty(prefix="security.oauth2.token.store",name="type" ,havingValue="jdbc" ,matchIfMissing=true)
//	public JdbcTokenStore jdbcTokenStore(){
//
////		oauth_access_token oauth_refresh_token 创建两张表
////		return new JdbcTokenStore( dataSource ) ;
//		return new JdbcTokenStore( dataSource ) ;
//
//	}
	
	@Bean
	@ConditionalOnProperty(prefix="security.oauth2.token.store",name="type" ,havingValue="redis" ,matchIfMissing=true)
	public RedisTemplateTokenStore redisTokenStore(){
//		return new RedisTokenStore( redisTemplate.getConnectionFactory() ) ; //单台redis服务器
		

		RedisTemplateTokenStore redisTemplateStore = new RedisTemplateTokenStore()  ;
		redisTemplateStore.setRedisTemplate(redisTemplate);
		return redisTemplateStore ;
		 

	}
	
	//使用jwt替换原有的uuid生成token方式
	@Configuration
	@ConditionalOnProperty(prefix="security.oauth2.token.store",name="type" ,havingValue="jwt" ,matchIfMissing=true)
	public static class JWTTokenConfig {
		@Bean
		public JwtTokenStore jwtTokenStore(){
			return new JwtTokenStore( jwtAccessTokenConverter() ) ;
		}
		
//		@Bean
//		public JwtAccessTokenConverter jwtAccessTokenConverter(){
//			JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
//			accessTokenConverter.setSigningKey("sinosoft");
//			return accessTokenConverter ;
//		}
		/**
		 * 使用非对称加密算法来对Token进行签名
		 * @return
		 */
		@Bean
		public JwtAccessTokenConverter jwtAccessTokenConverter() {
			JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
			Resource resource = new ClassPathResource("public.txt");
			String publicKey = null;
			try {
				publicKey = inputStream2String(resource.getInputStream());
			} catch (final IOException e) {
				throw new RuntimeException(e);
			}
			converter.setVerifierKey(publicKey);
//			converter.setAccessTokenConverter(new CustomerAccessTokenConverter());
			return converter;
		}
		String inputStream2String(InputStream is) throws IOException {
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
			return buffer.toString();
		}

	}
	
}
