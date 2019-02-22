package com.sinosoft.vo.http;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinosoft.enums.http.StatusCode;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 交互是返回的json对象
 * @Author: zouren
 * @CreateDate: 2019/1/24 13:32
 * @Version: 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {


    private String code = StatusCode.SUCCESS.getCode();
    private String msg = StatusCode.SUCCESS.getMassage();
    private String path;
    private String timestamp = String.valueOf(new Date().getTime());
    private String description;
    private T data;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    private HashMap<String, Object> exend;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public HashMap<String, Object> getExend() {
        return exend;
    }

    public void setExend(HashMap<String, Object> exend) {
        this.exend = exend;
    }

    public Result() {
        exend = new HashMap<>();

    }

    public static Result failure(StatusCode statusCode) {
        Result result = new Result();
        result.setCode(statusCode.getCode());
        result.setMsg(statusCode.getMassage());
        return result;
    }


    public static Result ok(String msg) {
        Result result = new Result();
        result.put("msg", msg);
        return result;
    }

    public static Result ok(Map<String, Object> exendMap) {
        Result result = new Result();
        result.exend.putAll(exendMap);
        return result;
    }
    public  String toJSONString () throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
    public static Result ok() {
        return new Result();
    }

    public Result put(String key, Object value) {
        exend.put(key, value);
        return this;
    }
}
