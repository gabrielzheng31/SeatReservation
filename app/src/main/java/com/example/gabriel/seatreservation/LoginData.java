package com.example.gabriel.seatreservation;

/**
 * Created by gabriel on 17-12-11.
 */

public class LoginData {

    private int resCode;

    private String resMsg;

    private String token;

    private String UserId;

    public int getResCode() {
        return resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public String getUserId() {
        return UserId;
    }

    public String getToken() {
        return token;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public void setResMsg(String msg) {
        this.resMsg = msg;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserID(String token) {
        this.UserId = token;
    }

}
