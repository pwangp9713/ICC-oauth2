package com.sinosoft.entity.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "oauth_client_details")
public class OauthClientDetails {
    /**
     * 主键,必须唯一,不能为空. 
用于唯一标识每一个客户端(client); 在注册时必须填写(也可由服务端自动生成). 
对于不同的grant_type,该字段都是必须的. 在实际应用中的另一个名称叫appKey,与client_id是同一个概念.
     */
    @Id
    @Column(name = "client_id")
    private String clientId;

    /**
     * 客户端所能访问的资源id集合,多个资源时用逗号(,)分隔,如: "unity-resource,mobile-resource". 
     */
    @Column(name = "resource_ids")
    private String resourceIds;

    /**
     * 用于指定客户端(client)的访问密匙
     */
    @Column(name = "client_secret")
    private String clientSecret;

    /**
     * 指定客户端申请的权限范围,可选值包括read,write,trust;若有多个权限范围用逗号(,)分隔,如: "read,write". 
     */
    private String scope;

    /**
     * 指定客户端支持的grant_type,可选值包括authorization_code,password,refresh_token,implicit,client_credentials, 若支持多个grant_type用逗号(,)分隔,如: "authorization_code,password". 
     */
    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;

    /**
     * 客户端的重定向URI,可为空, 当grant_type为authorization_code或implicit时, 在Oauth的流程中会使用并检查与注册时填写的redirect_uri是否一致.
     */
    @Column(name = "web_server_redirect_uri")
    private String webServerRedirectUri;

    /**
     * 指定客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔, 如: "ROLE_UNITY,ROLE_USER". 

     */
    private String authorities;

    /**
     * 设定客户端的access_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时)
     */
    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;

    /**
     * 设定客户端的refresh_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天). 
     */
    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;

    /**
     * 这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据,如:
     */
    @Column(name = "additional_information")
    private String additionalInformation;

    /**
     * 数据的创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 用于标识客户端是否已存档(即实现逻辑删除),默认值为'0'(即未存档).
     */
    private Integer archived;

    /**
     * 设置客户端是否为受信任的,默认为'0'(即不受信任的,1为受信任的). 
     */
    private Integer trusted;

    /**
     * 设置用户是否自动Approval操作, 默认值为 'false', 可选值包括 'true','false', 'read','write'. 
     */
    private String autoapprove;

    /**
     * 获取主键,必须唯一,不能为空. 
用于唯一标识每一个客户端(client); 在注册时必须填写(也可由服务端自动生成). 
对于不同的grant_type,该字段都是必须的. 在实际应用中的另一个名称叫appKey,与client_id是同一个概念.
     *
     * @return client_id - 主键,必须唯一,不能为空. 
用于唯一标识每一个客户端(client); 在注册时必须填写(也可由服务端自动生成). 
对于不同的grant_type,该字段都是必须的. 在实际应用中的另一个名称叫appKey,与client_id是同一个概念.
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * 设置主键,必须唯一,不能为空. 
用于唯一标识每一个客户端(client); 在注册时必须填写(也可由服务端自动生成). 
对于不同的grant_type,该字段都是必须的. 在实际应用中的另一个名称叫appKey,与client_id是同一个概念.
     *
     * @param clientId 主键,必须唯一,不能为空. 
用于唯一标识每一个客户端(client); 在注册时必须填写(也可由服务端自动生成). 
对于不同的grant_type,该字段都是必须的. 在实际应用中的另一个名称叫appKey,与client_id是同一个概念.
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * 获取客户端所能访问的资源id集合,多个资源时用逗号(,)分隔,如: "unity-resource,mobile-resource". 
     *
     * @return resource_ids - 客户端所能访问的资源id集合,多个资源时用逗号(,)分隔,如: "unity-resource,mobile-resource". 
     */
    public String getResourceIds() {
        return resourceIds;
    }

    /**
     * 设置客户端所能访问的资源id集合,多个资源时用逗号(,)分隔,如: "unity-resource,mobile-resource". 
     *
     * @param resourceIds 客户端所能访问的资源id集合,多个资源时用逗号(,)分隔,如: "unity-resource,mobile-resource". 
     */
    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    /**
     * 获取用于指定客户端(client)的访问密匙
     *
     * @return client_secret - 用于指定客户端(client)的访问密匙
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * 设置用于指定客户端(client)的访问密匙
     *
     * @param clientSecret 用于指定客户端(client)的访问密匙
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * 获取指定客户端申请的权限范围,可选值包括read,write,trust;若有多个权限范围用逗号(,)分隔,如: "read,write". 
     *
     * @return scope - 指定客户端申请的权限范围,可选值包括read,write,trust;若有多个权限范围用逗号(,)分隔,如: "read,write". 
     */
    public String getScope() {
        return scope;
    }

    /**
     * 设置指定客户端申请的权限范围,可选值包括read,write,trust;若有多个权限范围用逗号(,)分隔,如: "read,write". 
     *
     * @param scope 指定客户端申请的权限范围,可选值包括read,write,trust;若有多个权限范围用逗号(,)分隔,如: "read,write". 
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * 获取指定客户端支持的grant_type,可选值包括authorization_code,password,refresh_token,implicit,client_credentials, 若支持多个grant_type用逗号(,)分隔,如: "authorization_code,password". 
     *
     * @return authorized_grant_types - 指定客户端支持的grant_type,可选值包括authorization_code,password,refresh_token,implicit,client_credentials, 若支持多个grant_type用逗号(,)分隔,如: "authorization_code,password". 
     */
    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    /**
     * 设置指定客户端支持的grant_type,可选值包括authorization_code,password,refresh_token,implicit,client_credentials, 若支持多个grant_type用逗号(,)分隔,如: "authorization_code,password". 
     *
     * @param authorizedGrantTypes 指定客户端支持的grant_type,可选值包括authorization_code,password,refresh_token,implicit,client_credentials, 若支持多个grant_type用逗号(,)分隔,如: "authorization_code,password". 
     */
    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    /**
     * 获取客户端的重定向URI,可为空, 当grant_type为authorization_code或implicit时, 在Oauth的流程中会使用并检查与注册时填写的redirect_uri是否一致.
     *
     * @return web_server_redirect_uri - 客户端的重定向URI,可为空, 当grant_type为authorization_code或implicit时, 在Oauth的流程中会使用并检查与注册时填写的redirect_uri是否一致.
     */
    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    /**
     * 设置客户端的重定向URI,可为空, 当grant_type为authorization_code或implicit时, 在Oauth的流程中会使用并检查与注册时填写的redirect_uri是否一致.
     *
     * @param webServerRedirectUri 客户端的重定向URI,可为空, 当grant_type为authorization_code或implicit时, 在Oauth的流程中会使用并检查与注册时填写的redirect_uri是否一致.
     */
    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    /**
     * 获取指定客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔, 如: "ROLE_UNITY,ROLE_USER". 

     *
     * @return authorities - 指定客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔, 如: "ROLE_UNITY,ROLE_USER". 

     */
    public String getAuthorities() {
        return authorities;
    }

    /**
     * 设置指定客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔, 如: "ROLE_UNITY,ROLE_USER". 

     *
     * @param authorities 指定客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔, 如: "ROLE_UNITY,ROLE_USER". 

     */
    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    /**
     * 获取设定客户端的access_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时)
     *
     * @return access_token_validity - 设定客户端的access_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时)
     */
    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    /**
     * 设置设定客户端的access_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时)
     *
     * @param accessTokenValidity 设定客户端的access_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时)
     */
    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    /**
     * 获取设定客户端的refresh_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天). 
     *
     * @return refresh_token_validity - 设定客户端的refresh_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天). 
     */
    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    /**
     * 设置设定客户端的refresh_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天). 
     *
     * @param refreshTokenValidity 设定客户端的refresh_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天). 
     */
    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    /**
     * 获取这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据,如:
     *
     * @return additional_information - 这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据,如:
     */
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    /**
     * 设置这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据,如:
     *
     * @param additionalInformation 这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据,如:
     */
    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    /**
     * 获取数据的创建时间
     *
     * @return create_time - 数据的创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置数据的创建时间
     *
     * @param createTime 数据的创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取用于标识客户端是否已存档(即实现逻辑删除),默认值为'0'(即未存档).
     *
     * @return archived - 用于标识客户端是否已存档(即实现逻辑删除),默认值为'0'(即未存档).
     */
    public Integer getArchived() {
        return archived;
    }

    /**
     * 设置用于标识客户端是否已存档(即实现逻辑删除),默认值为'0'(即未存档).
     *
     * @param archived 用于标识客户端是否已存档(即实现逻辑删除),默认值为'0'(即未存档).
     */
    public void setArchived(Integer archived) {
        this.archived = archived;
    }

    /**
     * 获取设置客户端是否为受信任的,默认为'0'(即不受信任的,1为受信任的). 
     *
     * @return trusted - 设置客户端是否为受信任的,默认为'0'(即不受信任的,1为受信任的). 
     */
    public Integer getTrusted() {
        return trusted;
    }

    /**
     * 设置设置客户端是否为受信任的,默认为'0'(即不受信任的,1为受信任的). 
     *
     * @param trusted 设置客户端是否为受信任的,默认为'0'(即不受信任的,1为受信任的). 
     */
    public void setTrusted(Integer trusted) {
        this.trusted = trusted;
    }

    /**
     * 获取设置用户是否自动Approval操作, 默认值为 'false', 可选值包括 'true','false', 'read','write'. 
     *
     * @return autoapprove - 设置用户是否自动Approval操作, 默认值为 'false', 可选值包括 'true','false', 'read','write'. 
     */
    public String getAutoapprove() {
        return autoapprove;
    }

    /**
     * 设置设置用户是否自动Approval操作, 默认值为 'false', 可选值包括 'true','false', 'read','write'. 
     *
     * @param autoapprove 设置用户是否自动Approval操作, 默认值为 'false', 可选值包括 'true','false', 'read','write'. 
     */
    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove;
    }
}