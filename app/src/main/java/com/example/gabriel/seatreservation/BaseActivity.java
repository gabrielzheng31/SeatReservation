package com.example.gabriel.seatreservation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by gabriel on 17-11-21.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
