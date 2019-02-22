package com.sinosoft.entity.model;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "oauth_refresh_token")
public class OauthRefreshToken {
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 该字段的值是将refresh_token的值通过MD5加密后存储的.

     */
    @Column(name = "token_id")
    private String tokenId;

    /**
     * 存储将OAuth2RefreshToken.java对象序列化后的二进制数据.
     */
    private byte[] token;

    /**
     * 存储将OAuth2Authentication.java对象序列化后的二进制数据.

     */
    private byte[] authentication;

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
     * @return token_id - 该字段的值是将refresh_token的值通过MD5加密后存储的.

     */
    public String getTokenId() {
        return tokenId;
    }

    /**
     * 设置该字段的值是将refresh_token的值通过MD5加密后存储的.

     *
     * @param tokenId 该字段的值是将refresh_token的值通过MD5加密后存储的.

     */
    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    /**
     * 获取存储将OAuth2RefreshToken.java对象序列化后的二进制数据.
     *
     * @return token - 存储将OAuth2RefreshToken.java对象序列化后的二进制数据.
     */
    public byte[] getToken() {
        return token;
    }

    /**
     * 设置存储将OAuth2RefreshToken.java对象序列化后的二进制数据.
     *
     * @param token 存储将OAuth2RefreshToken.java对象序列化后的二进制数据.
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