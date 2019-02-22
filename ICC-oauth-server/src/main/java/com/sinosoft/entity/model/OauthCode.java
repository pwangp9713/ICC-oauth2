package com.sinosoft.entity.model;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "oauth_code")
public class OauthCode {
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 存储服务端系统生成的code的值(未加密).
     */
    private String code;

    /**
     * 存储将AuthorizationRequestHolder.java对象序列化后的二进制数据
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
     * 获取存储服务端系统生成的code的值(未加密).
     *
     * @return code - 存储服务端系统生成的code的值(未加密).
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置存储服务端系统生成的code的值(未加密).
     *
     * @param code 存储服务端系统生成的code的值(未加密).
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取存储将AuthorizationRequestHolder.java对象序列化后的二进制数据
     *
     * @return authentication - 存储将AuthorizationRequestHolder.java对象序列化后的二进制数据
     */
    public byte[] getAuthentication() {
        return authentication;
    }

    /**
     * 设置存储将AuthorizationRequestHolder.java对象序列化后的二进制数据
     *
     * @param authentication 存储将AuthorizationRequestHolder.java对象序列化后的二进制数据
     */
    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }
}