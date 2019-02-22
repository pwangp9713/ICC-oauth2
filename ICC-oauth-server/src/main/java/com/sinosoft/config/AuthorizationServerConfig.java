package com.sinosoft.config;

import com.sinosoft.erro.MssWebResponseExceptionTranslator;
import com.sinosoft.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**

* @Description:    授权服务器配置
* @Author:         zouren
* @CreateDate:     2019/1/24 9:29
* @Version:        1.0
*/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 认证管理器
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;
    /**
     * 用户信息相关的实现
     */
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    /**
     * 存储链接
     */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    /**
    * @Description:    token存放位置
    * @Author:         zouren
    * @CreateDate:     2019/1/24 9:32
    * @UpdateUser:     yc
    * @Version:        1.0
    */
    @Bean
    RedisTokenStore redisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }

    //token存储数据库
//    @Bean
//    public JdbcTokenStore jdbcTokenStore(){
//        return new JdbcTokenStore(dataSource);
//    }
    /**
    * @Description:    这个方法主要是用于校验注册的第三方客户端的信息，可以存储在数据库中
    * @Author:         zouren
    * @CreateDate:     2019/1/24 16:02
    * @Version:        1.0
    */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
    }
    /**
    * @Description:    声明 ClientDetails实现
    * @Author:         zouren
    * @CreateDate:     2019/1/24 16:01
    * @Version:        1.0
    */
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }
    @Bean
    public WebResponseExceptionTranslator webResponseExceptionTranslator(){
        return new MssWebResponseExceptionTranslator();
    }
    /**
     * @Description:    配置认证管理器以及用户信息业务实现
     * @Author:         zouren
     * @CreateDate:     2019/1/24 9:33
     * @Version:        1.0
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(redisTokenStore())
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager);
        endpoints.tokenServices(defaultTokenServices());
        //允许 GET、POST 请求获取 token，即访问端点：oauth/token
        endpoints .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        endpoints.exceptionTranslator(webResponseExceptionTranslator());//认证异常翻译
    }

    /**
     * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p>
     * @return
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(redisTokenStore());
        // 复用refresh token
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetails());
        tokenServices.setAccessTokenValiditySeconds(60*60*12); // token有效期自定义设置，默认12小时
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);//默认30天，这里修改
        return tokenServices;
    }
    /** 授权端点开放
    * @Description:  配置认证规则，那些需要认证那些不需要
    * @Author:         zouren
    * @CreateDate:     2019/1/24 9:35
    * @Version:        1.0
    */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 开启/oauth/token_key验证端口无权限访问
        security.tokenKeyAccess("permitAll()");
        // 开启/oauth/check_token验证端口认证权限访问
        security .checkTokenAccess("isAuthenticated()");
        //允许表单验证
        security.allowFormAuthenticationForClients();
    }
}
