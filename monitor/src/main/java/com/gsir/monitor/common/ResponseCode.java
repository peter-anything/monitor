package com.gsir.monitor.common;

public enum ResponseCode {
    /**
     * ���سɹ�״̬��Ϊ200
     */
    SUCCESS(200, "SUCCESS"),
    /**
     * ����ʧ��״̬��Ϊ500
     */
    ERROR(500, "ERROR");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }
    
    public String getDesc() {
        return this.desc;
    }
}
