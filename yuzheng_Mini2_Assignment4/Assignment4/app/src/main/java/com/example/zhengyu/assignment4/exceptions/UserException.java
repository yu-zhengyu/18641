package com.example.zhengyu.assignment4.exceptions;

/**
 * Created by zhengyu on 15/11/4.
 * This class is handle the user exception
 */
public class UserException extends Exception {
    private static final long serialVersionUID = 3353081391162535534L;
    private String msg;

    public UserException(String str) {
        this.msg = str;
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
