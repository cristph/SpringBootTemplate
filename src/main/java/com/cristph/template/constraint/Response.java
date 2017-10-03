package com.cristph.template.constraint;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Response implements Serializable{

    private static final long serialVersionUID = 4637248581108425488L;

    //请求数据是否成功
    public enum Status {
        SUCCESS,
        ERROR
    }

    //请求的状态
    private Status status;

    //信息和错误码
    private Code code;

    //数据或列表
    private Object data;

    //扩展信息，map结构，可存放额外信息，如分页数，数据总量等
    private Map<String, Object> extend;


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public Response(Status status, Code code, Object data, Map<String, Object> extend) {
        this.status = status;
        this.code = code;
        this.data = data;
        this.extend = extend;
    }

    /**
     * 请求数据处理成功时调用
     * @return Response
     */
    public static Response success() {
        return new Response(Status.SUCCESS, null, null, null);
    }
    /**
     * 请求数据处理成功时调用
     * @param data Object  数据或列表
     * @return Response
     */
    public static Response success(Object data) {
        return new Response(Status.SUCCESS, null , data, null);
    }

    /**
     * 请求数据处理成功时调用
     * @param extend Map<String, Object> 扩展信息，map结构，可存放额外信息，如分页数，数据总量等
     * @return  Response
     */
    public static Response success(Map<String, Object> extend) {
        return new Response(Status.SUCCESS, null , null, extend);
    }

    /**
     * 请求数据处理成功时调用
     * @param data Object  数据或列表
     * @param extend Map<String, Object> 扩展信息，map结构，可存放额外信息，如分页数，数据总量等
     * @return Response
     */
    public static Response success(Object data, Map<String, Object> extend) {
        return new Response(Status.SUCCESS, null, data, extend);
    }

    public static Response success(Object data, Page page) {
        Map<String,Object> map=new HashMap();
        map.put("page",page);
        return new Response(Status.SUCCESS, null, data, map);
    }


    /**
     * 请求数据处理失败时调用nu
     * @return Response
     */
    public static Response error() {
        return new Response(Status.ERROR, null, null, null);
    }

    /**
     * 请求数据处理失败时调用
     * @param code Code 返回错误状态码
     * @return Response
     */
    public static Response error(Code code) {
        return new Response(Status.ERROR, code, null, null);
    }

    /**
     * 请求数据处理失败时调用
     * @param code Code 返回错误状态码
     * @param extend Map<String, Object> 扩展信息，map结构，可存放额外信息，如分页数，数据总量等
     * @return Response
     */
    public static Response error(Code code, Map<String, Object> extend) {
        return new Response(Status.ERROR, code, "ERROR", extend);
    }

}
