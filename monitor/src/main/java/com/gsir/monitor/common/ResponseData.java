package com.gsir.monitor.common;

import java.io.Serializable;

public class ResponseData<T> implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -2310392082177682344L;
    /**
     * 信息状态码，200表示成功，500表示失败
     */
    private Integer status;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 返回的对象,因为有分页信息，方便返回json数据见名之意，这里直接变量名为pageInfo。
     */
    private T data;

    /**
     * 构造函数
     */
    private ResponseData() {
    }

    private ResponseData(int status){
        this.status = status;
    }

    private ResponseData(int status, T data){
        this.status =status;
        this.data = data;
    }

    private ResponseData(int status, String msg){
        this.status = status;
        this.msg = msg;
    }

    private ResponseData(int status, String msg, T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功返回的状态码
     */
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    /**
     * 成功时调用的函数
     * @param data 要返回的对象
     * @return
     */
    public static <T> ResponseData<T> success(T data) {

        return new ResponseData<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    /**
     * 成功时，如果没有要返回的对象，调用此方法
     * @return
     */
    public static <T> ResponseData<T> success() {

        return new ResponseData<T>(ResponseCode.SUCCESS.getCode());
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
