package com.gsir.monitor.common;

import java.io.Serializable;

public class ResponseData<T> implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -2310392082177682344L;
    /**
     * ��Ϣ״̬�룬200��ʾ�ɹ���500��ʾʧ��
     */
    private Integer status;
    /**
     * ��ʾ��Ϣ
     */
    private String msg;
    /**
     * ���صĶ���,��Ϊ�з�ҳ��Ϣ�����㷵��json���ݼ���֮�⣬����ֱ�ӱ�����ΪpageInfo��
     */
    private T data;

    /**
     * ���캯��
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
     * �ɹ����ص�״̬��
     */
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    /**
     * �ɹ�ʱ���õĺ���
     * @param data Ҫ���صĶ���
     * @return
     */
    public static <T> ResponseData<T> success(T data) {

        return new ResponseData<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    /**
     * �ɹ�ʱ�����û��Ҫ���صĶ��󣬵��ô˷���
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
