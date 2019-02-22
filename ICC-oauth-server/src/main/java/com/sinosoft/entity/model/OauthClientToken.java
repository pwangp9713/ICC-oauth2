package com.sinosoft.entity.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "oauth_client_token")
public class OauthClientToken {
    /**
     * 该字段具有唯一性, 是根据当前的username(如果有),client_id与scope通过MD5加密生成的. 
     */
    @Id
    @Column(name = "authentication_id")
    private String authenticationId;

    /**
     * 从服务器端获取到的access_token的值.
     */
    @Column(name = "token_id")
    private String tokenId;

    /**
     * 登录时的用户名
     */
    @Column(name = "user_name")
    private String userName;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * oauth_client_details 主键
     */
    @Column(name = "client_id")
    private String clientId;

    /**
     * 这是一个二进制的字段, 存储的数据是OAuth2AccessToken.java对象序列化后的二进制数据.

     */
    private byte[] token;

    /**
     * 获取该字段具有唯一性, 是根据当前的username(如果有),client_id与scope通过MD5加密生成的. 
     *
     * @return authentication_id - 该字段具有唯一性, 是根据当前的username(如果有),client_id与scope通过MD5加密生成的. 
     */
    public String getAuthenticationId() {
        return authenticationId;
    }

    /**
     * 设置该字段具有唯一性, 是根据当前的username(如果有),client_id与scope通过MD5加密生成的. 
     *
     * @param authenticationId 该字段具有唯一性, 是根据当前的username(如果有),client_id与scope通过MD5加密生成的. 
     */
    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    /**
     * 获取从服务器端获取到的access_token的值.
     *
     * @return token_id - 从服务器端获取到的access_token的值.
     */
    public String getTokenId() {
        return tokenId;
    }

    /**
     * 设置从服务器端获取到的access_token的值.
     *
     * @param tokenId 从服务器端获取到的access_token的值.
     */
    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    /**
     * 获取登录时的用户名
     *
     * @return user_name - 登录时的用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置登录时的用户名
     *
     * @param userName 登录时的用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * 获取oauth_client_details 主键
     *
     * @return client_id - oauth_client_details 主键
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * 设置oauth_client_details 主键
     *
     * @param clientId oauth_client_details 主键
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * 获取这是一个二进制的字段, 存储的数据是OAuth2AccessToken.java对象序列化后的二进制数据.

     *
     * @return token - 这是一个二进制的字段, 存储的数据是OAuth2AccessToken.java对象序列化后的二进制数据.

     */
    public byte[] getToken() {
        return token;
    }

    /**
     * 设置这是一个二进制的字段, 存储的数据是OAuth2AccessToken.java对象序列化后的二进制数据.

     *
     * @param token 这是一个二进制的字段, 存储的数据是OAuth2AccessToken.java对象序列化后的二进制数据.

     */
    public void setToken(byte[] token) {
        this.token = token;
    }
}