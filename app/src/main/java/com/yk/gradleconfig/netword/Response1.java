package com.yk.gradleconfig.netword;

public class Response1<T> {
    //{"code":200,"msg":"成功!","data"
    public int code;
    public String msg;
    public T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public void setT(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response1{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
