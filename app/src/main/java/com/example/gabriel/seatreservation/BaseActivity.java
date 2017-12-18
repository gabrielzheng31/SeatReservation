package com.example.gabriel.seatreservation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by gabriel on 17-11-21.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private LogoffReceiver logoffReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.gabriel.seatreservation.LOG_OFF");
        logoffReceiver = new LogoffReceiver();
        registerReceiver(logoffReceiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (logoffReceiver != null) {
            unregisterReceiver(logoffReceiver);
            logoffReceiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    class LogoffReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("注销");
            builder.setCancelable(true);
            builder.setMessage("您确定要注销帐号吗？");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SharedPreferences.Editor editor = getSharedPreferences("user", 0).edit();
                    editor.putString("TOKEN", "");
                    editor.apply();
                    //TODO seems can't write in
                    ActivityCollector.finishAll();
                    LoginActivity.actionStart(context);
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
        }
    }
}
