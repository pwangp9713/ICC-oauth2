package com.sinosoft.token;

import com.sinosoft.token.store.JwtAccessToken;
import com.sinosoft.token.store.RedisTemplateTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.sql.DataSource;

/** 
* @author owen 624191343@qq.com
 * @version 创建时间：2017年11月12日 上午22:57:51
* 类说明 
* redis存储token
*/
@Configuration
public class TokenStoreConfig {


//	@Resource
//	private DataSource dataSource ;
	
	@Autowired(required=false)
	private RedisTemplate redisTemplate ;


//	@Bean
//	@ConditionalOnProperty(prefix="security.oauth2.token.store",name="type" ,havingValue="jdbc" ,matchIfMissing=false)
//	public JdbcTokenStore jdbcTokenStore(){
//
////		oauth_access_token oauth_refresh_token 创建两张表
//		return new JdbcTokenStore( dataSource ) ;
//
//	}
	@Bean
	@ConditionalOnProperty(prefix="security.oauth2.token.store",name="type" ,havingValue="redis" ,matchIfMissing=true)
	public RedisTemplateTokenStore redisTokenStore(){
//		return new RedisTokenStore( redisTemplate.getConnectionFactory() ) ; //单台redis服务器
		Assert.state(redisTemplate != null, "RedisTemplate must be provided");

		RedisTemplateTokenStore redisTemplateStore = new RedisTemplateTokenStore()  ;
		redisTemplateStore.setRedisTemplate(redisTemplate);
		return redisTemplateStore ;
		 

	}
	
	//使用jwt替换原有的uuid生成token方式
	@Configuration
	@ConditionalOnProperty(prefix="security.oauth2.token.store",name="type" ,havingValue="jwt" ,matchIfMissing=false)
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

			final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
			// 导入证书   Resourceg下jks 证书  密钥
			KeyStoreKeyFactory keyStoreKeyFactory =
					new KeyStoreKeyFactory(new ClassPathResource("mytest.jks"), "mypass".toCharArray());
			converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));//别名

			return converter;
		}
	}
	
	
	
}
