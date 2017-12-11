package com.example.gabriel.seatreservation;

/**
 * Created by gabriel on 17-12-11.
 */

public class AccountData {

    private int resCode;

    private String msg;

    private String token;

    public int getResCode() {
        return resCode;
    }

    public String getMsg() {
        return msg;
    }

    public String getToken() {
        return token;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
