package com.example.gabriel.seatreservation.data;

/**
 * Created by gabriel on 17-12-12.
 */

public class UserData {

    private int resCode;

    private boolean resStatus;

    private String date;

    private int start;

    private int end;

    public String getDate() {
        return this.date;
    }

    public int getBeginTime() {
        return this.start;
    }

    public int getEndTime() {
        return this.end;
    }

    public int getResCode() {
        return resCode;
    }

    public boolean getResStatus() {
        return resStatus;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public void setResStatus(boolean resStatus) {
        this.resStatus = resStatus;
    }
}
