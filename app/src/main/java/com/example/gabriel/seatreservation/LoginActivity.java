package com.example.gabriel.seatreservation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;

import com.example.gabriel.seatreservation.utils.Configure;
import com.example.gabriel.seatreservation.utils.HttpCallbackListener;
import com.example.gabriel.seatreservation.utils.HttpUtil;
import com.example.gabriel.seatreservation.utils.ShareUtil;
import com.google.gson.Gson;

public class LoginActivity extends BaseActivity {

    private TextInputEditText id, key;
    private Button login;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(uiOptions);

        id = findViewById(R.id.login_IDInput);
        key = findViewById(R.id.login_PasswordInput);

        ViewParent idParent = id.getParent();
        if (idParent != null && idParent instanceof TextInputLayout) {
            final TextInputLayout idLayout = (TextInputLayout) idParent;
            id.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    idLayout.setErrorEnabled(false);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

        ViewParent keyParent = key.getParent();
        if (keyParent != null && keyParent instanceof TextInputLayout) {
            final TextInputLayout keyLayout = (TextInputLayout) keyParent;
            key.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    keyLayout.setErrorEnabled(false);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

        login = findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                id= findViewById(R.id.login_IDInput);
                key= findViewById(R.id.login_PasswordInput);
                String username = id.getText().toString();
                String password = key.getText().toString();
                String address="http://138.68.254.73:8080/SeatReservation/login";
//                String address="http://172.26.40.36:8080/SeatReservation/login";
//                String address="http://192.168.1.7:8080/SeatReservation/login";

                if (username == null || TextUtils.isEmpty(username))
                    id.setError("用户名不能为空");

                HttpUtil.SendPost(address, username, password, new HttpCallbackListener() {

                    @Override
                    public void onFinish(String response) {
                        Log.d("test", response);
                        Gson gson = new Gson();
                        LoginData loginData = gson.fromJson(response, LoginData.class);
                        switch (loginData.getResCode()) {
                            case 0:
                                ShareUtil.hideKeyBoard(view.getContext(), view);
                                Configure.TOKEN = loginData.getToken();
                                SharedPreferences.Editor editor = getSharedPreferences("user",
                                        MODE_PRIVATE).edit();
                                editor.putString("TOKEN", loginData.getToken());
                                editor.apply();
                                Snackbar.make(login, "登录成功", Snackbar.LENGTH_SHORT).show();
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(2000);
                                            ActivityCollector.finishAll();
                                            MainActivity.actionStart(view.getContext());
                                        } catch (InterruptedException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    }
                                }).start();
                                break;
                            case 1:
                                ShareUtil.hideKeyBoard(view.getContext(), view);
                                Snackbar.make(view, "用户名或者密码错误", Snackbar.LENGTH_SHORT).show();
                                break;
                        }
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }
        });

        register = findViewById(R.id.login_to_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.actionStart(view.getContext());
            }
        });
    }



    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        if (Configure.CALLBACK != null)
            Configure.CALLBACK.postExec();
    }
}
