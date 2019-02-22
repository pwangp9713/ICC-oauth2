package com.sinosoft.config;

import com.sinosoft.erro.webResponse.CustomWebResponseExceptionTranslator;
import com.sinosoft.service.impl.UserDetailsServiceImpl;
import com.sinosoft.token.store.RedisTemplateTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**

* @Description:    授权服务器配置
* @Author:         zouren
* @CreateDate:     2019/1/24 9:29
* @Version:        1.0
*/
@Configuration
@EnableAuthorizationServer
@AutoConfigureAfter(AuthorizationServerEndpointsConfigurer.class)
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

    @Autowired(required = false)
    private RedisTemplateTokenStore redisTemplateTokenStore ;
    @Autowired(required = false)
    private JwtTokenStore jwtTokenStore;
    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;



    //token存储数据库
//    @Bean
//    public JdbcTokenStore jdbcTokenStore(){
//        return new JdbcTokenStore(dataSource);
//    }
    /**
    * @Description:    使用特定的方式存储client detail
    * @Author:         zouren
    * @CreateDate:     2019/1/24 16:02
    * @Version:        1.0
    */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
    }
    /**
    * @Description:    定义clientDetails存储的方式
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

        return new CustomWebResponseExceptionTranslator();

    }
    /**
     * @Description:    配置认证管理器以及用户信息业务实现
     * @Author:         zouren
     * @CreateDate:     2019/1/24 9:33
     * @Version:        1.0
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        if (jwtTokenStore != null) {
            endpoints.tokenStore(jwtTokenStore).authenticationManager(authenticationManager)
                    .userDetailsService(userDetailsService); // 支持
            // password
            // grant
            // type;
        } else if (redisTemplateTokenStore != null) {
            endpoints.tokenStore(redisTemplateTokenStore).authenticationManager(authenticationManager)
                    .userDetailsService(userDetailsService); // 支持
            // password
            // grant
            // type;
        }

        if (jwtAccessTokenConverter != null) {
            endpoints.accessTokenConverter(jwtAccessTokenConverter);
        }

        endpoints .authenticationManager(authenticationManager);
        //允许 GET、POST 请求获取 token，即访问端点：oauth/token
        endpoints .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        endpoints.exceptionTranslator(webResponseExceptionTranslator());//认证异常翻译

        // 自定义token生成方式
//        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
//        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(customerEnhancer()));
//        endpoints.tokenEnhancer(tokenEnhancerChain);

        // 配置TokenServices参数
        DefaultTokenServices tokenServices = (DefaultTokenServices) endpoints.getDefaultAuthorizationServerTokenServices();
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.HOURS.toSeconds(3)); // 3小时
        endpoints.tokenServices(tokenServices);




    }


    /** 授权端点开放
    * @Description:  配置认证规则，那些需要认证那些不需要
    * @Author:         zouren
    * @CreateDate:     2019/1/24 9:35
    * @Version:        1.0
    */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 开启/oauth/token_key验证端口无权限访问  url:/oauth/token_key,exposes   public key for token verification if using   JWT tokens
        security.tokenKeyAccess("permitAll()");
        // 开启/oauth/check_token验证端口认证权限访问
        security .checkTokenAccess("isAuthenticated()");
        //允许表单验证
        security.allowFormAuthenticationForClients();
    }
}
