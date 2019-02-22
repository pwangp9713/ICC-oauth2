package com.sinosoft.entity.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "oauth_access_token")
public class OauthAccessToken {
    /**
     * 该字段具有唯一性, 其值是根据当前的username(如果有),client_id与scope通过MD5加密生成的
     */
    @Id
    @Column(name = "authentication_id")
    private String authenticationId;

    /**
     * 该字段的值是将access_token的值通过MD5加密后存储的.
     */
    @Column(name = "token_id")
    private String tokenId;

    /**
     * 登录时的用户名, 若客户端没有用户名(如grant_type="client_credentials"),则该值等于client_id

     */
    @Column(name = "user_name")
    private String userName;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 该字段的值是将refresh_token的值通过MD5加密后存储的.
     */
    @Column(name = "refresh_token")
    private String refreshToken;

    /**
     * 存储将OAuth2AccessToken.java对象序列化后的二进制数据, 是真实的AccessToken的数据值.

     */
    private byte[] token;

    /**
     * 存储将OAuth2Authentication.java对象序列化后的二进制数据.
     */
    private byte[] authentication;

    /**
     * 获取该字段具有唯一性, 其值是根据当前的username(如果有),client_id与scope通过MD5加密生成的
     *
     * @return authentication_id - 该字段具有唯一性, 其值是根据当前的username(如果有),client_id与scope通过MD5加密生成的
     */
    public String getAuthenticationId() {
        return authenticationId;
    }

    /**
     * 设置该字段具有唯一性, 其值是根据当前的username(如果有),client_id与scope通过MD5加密生成的
     *
     * @param authenticationId 该字段具有唯一性, 其值是根据当前的username(如果有),client_id与scope通过MD5加密生成的
     */
    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    /**
     * 获取该字段的值是将access_token的值通过MD5加密后存储的.
     *
     * @return token_id - 该字段的值是将access_token的值通过MD5加密后存储的.
     */
    public String getTokenId() {
        return tokenId;
    }

    /**
     * 设置该字段的值是将access_token的值通过MD5加密后存储的.
     *
     * @param tokenId 该字段的值是将access_token的值通过MD5加密后存储的.
     */
    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    /**
     * 获取登录时的用户名, 若客户端没有用户名(如grant_type="client_credentials"),则该值等于client_id

     *
     * @return user_name - 登录时的用户名, 若客户端没有用户名(如grant_type="client_credentials"),则该值等于client_id

     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置登录时的用户名, 若客户端没有用户名(如grant_type="client_credentials"),则该值等于client_id

     *
     * @param userName 登录时的用户名, 若客户端没有用户名(如grant_type="client_credentials"),则该值等于client_id

     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return client_id
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取该字段的值是将refresh_token的值通过MD5加密后存储的.
     *
     * @return refresh_token - 该字段的值是将refresh_token的值通过MD5加密后存储的.
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * 设置该字段的值是将refresh_token的值通过MD5加密后存储的.
     *
     * @param refreshToken 该字段的值是将refresh_token的值通过MD5加密后存储的.
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * 获取存储将OAuth2AccessToken.java对象序列化后的二进制数据, 是真实的AccessToken的数据值.

     *
     * @return token - 存储将OAuth2AccessToken.java对象序列化后的二进制数据, 是真实的AccessToken的数据值.

     */
    public byte[] getToken() {
        return token;
    }

    /**
     * 设置存储将OAuth2AccessToken.java对象序列化后的二进制数据, 是真实的AccessToken的数据值.

     *
     * @param token 存储将OAuth2AccessToken.java对象序列化后的二进制数据, 是真实的AccessToken的数据值.

     */
    public void setToken(byte[] token) {
        this.token = token;
    }

    /**
     * 获取存储将OAuth2Authentication.java对象序列化后的二进制数据.
     *
     * @return authentication - 存储将OAuth2Authentication.java对象序列化后的二进制数据.
     */
    public byte[] getAuthentication() {
        return authentication;
    }

    /**
     * 设置存储将OAuth2Authentication.java对象序列化后的二进制数据.
     *
     * @param authentication 存储将OAuth2Authentication.java对象序列化后的二进制数据.
     */
    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }
}