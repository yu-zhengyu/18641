package com.example.zhengyu.partb.exception;

/**
 * Created by zhengyu on 15/11/13.
 */
public class UserException extends Exception {
    private static final long serialVersionUID = 3353081391162535534L;
    private String msg;

    public UserException(String m) {
        msg = m;
    }

    public void printMassage() {
        System.out.println("The error is: " + msg);
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
