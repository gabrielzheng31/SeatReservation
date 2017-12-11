package com.example.gabriel.seatreservation.utils;

/**
 * Created by gabriel on 17-12-11.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
