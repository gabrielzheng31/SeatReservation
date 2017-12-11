package com.example.gabriel.seatreservation;

/**
 * Created by gabriel on 17-12-11.
 */

public class RegisterData {

    private int resCode;

    private String resMsg;

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

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public void setResMsg(String msg) {
        this.resMsg = msg;
    }

    public void setUserID(String token) {
        this.UserId = token;
    }
}
