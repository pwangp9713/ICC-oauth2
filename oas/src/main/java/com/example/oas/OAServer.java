/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.oas;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 *
 * @author root
 */
@Configuration
public class OAServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

//        clients.inMemory()
//                .withClient("test")
//                //.secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("oauth"))
//                .secret("abcd")
//                .authorities("TEST")
//                .scopes("all")
//                .authorizedGrantTypes("authorization_code", "refresh_token", "implicit")
//                //.redirectUris("https://cn.bing.com")
//                .redirectUris("http://localhost:9998/givemetoken", "http://localhost:7000/givemetoken")
//                .autoApprove(true);
        clients.withClientDetails(clientDetails());

    }

    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        //endpoints.tokenStore(new InMemoryTokenStore())
        endpoints.tokenStore(new JdbcTokenStore(dataSource))
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

//        DefaultTokenServices dts = new DefaultTokenServices();
//        dts.setClientDetailsService(endpoints.getClientDetailsService());
//        dts.setTokenStore(endpoints.getTokenStore());
//        dts.setTokenEnhancer(endpoints.getTokenEnhancer());
//        dts.setAuthenticationManager(authenticationManager);
//        endpoints.tokenServices(dts);
    }

}
