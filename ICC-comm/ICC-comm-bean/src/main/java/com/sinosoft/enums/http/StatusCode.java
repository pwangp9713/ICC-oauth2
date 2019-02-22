package com.sinosoft.enums.http;

/**
 * @Auther: zouren
 * @Date: 2019/1/24 11:20
 * @Description:
 */
public enum StatusCode {
    SUCCESS("200","操作成功"),
    FAILURE("201","操作失败"),

    BAD_REQUEST("400","BAD_REQUEST"),
    UNAUTHORIZED("401","无权限访问"),
    FORBIDDEN("403","FORBIDDEN"),
    NOT_FOUNT("404","NOT_FOUNT"),

    SERVER_ERROR("500","SERVER_ERROR"),
    BAD_GATEWAY("502","BAD_GATEWAY"),
    SERVICE_UNAVAILABLE("503","SERVICE_UNAVAILABLE"),
    SERVICE_TIMEOUT("504","SERVICE_TIMEOUT"),

    MICRO_SERVICE_UNAVAILABLE("40001","微服务不可用");
            ;
    private String code;
    private String massage;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getCode() {
        return code;
    }

    public String getMassage() {
        return massage;
    }
   private StatusCode(String code, String massage){
        this.code=code;
        this.massage=massage;

    }
}
