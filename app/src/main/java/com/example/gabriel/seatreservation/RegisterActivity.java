package com.example.gabriel.seatreservation;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gabriel.seatreservation.utils.HttpCallbackListener;
import com.example.gabriel.seatreservation.utils.HttpUtil;
import com.google.gson.Gson;

import com.example.gabriel.seatreservation.utils.ShareUtil;

import org.w3c.dom.Text;

public class RegisterActivity extends BaseActivity {

    private TextInputEditText id, key, keyConfirm;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(uiOptions);

        id = findViewById(R.id.register_IDInput);
        key = findViewById(R.id.register_PasswordInput);
        keyConfirm = findViewById(R.id.register_PasswordInputConfirm);
        registerButton = findViewById(R.id.register_button);


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

        ViewParent keyConfirmParent = keyConfirm.getParent();
        if (keyConfirmParent != null && keyConfirmParent instanceof TextInputLayout) {
            final TextInputLayout keyConfirmLayout = (TextInputLayout) keyConfirmParent;
            keyConfirm.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    keyConfirmLayout.setErrorEnabled(false);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String username = id.getText().toString();
                String password = key.getText().toString();
                String passwordConfirm = keyConfirm.getText().toString();
//                String address="http://138.68.254.73:8080/SeatReservation/register";
                String address = "http://172.26.40.63:8080/SeatReservation/register";

                boolean usernameStatus = (username == null || TextUtils.isEmpty(username));
                boolean passwordStatus = (password == null || TextUtils.isEmpty(password));
                boolean passwordConfirmStatus = (passwordConfirm == null || TextUtils.isEmpty(passwordConfirm) ||
                        !passwordConfirm.equals(password));

                if (usernameStatus)
                    id.setError("用户名不能为空");

                if (passwordStatus)
                    key.setError("密码不能少于8位");

                if (passwordConfirmStatus)
                    keyConfirm.setError("密码不一致，请重试");
                if (!(usernameStatus || passwordConfirmStatus || passwordConfirmStatus)) {
                    HttpUtil.SendPost(address, username, password, new HttpCallbackListener() {

                        @Override
                        public void onFinish(String response) {
                            Gson gson = new Gson();
                            AccountData accountData = gson.fromJson(response, AccountData.class);
                            switch (accountData.getResCode()) {
                                case 100:
                                    ShareUtil.hideKeyBoard(view.getContext());
                                    Snackbar.make(registerButton, "注册成功，正在前往登录界面...", Snackbar.LENGTH_SHORT).show();
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                Thread.sleep(2000);
                                                ActivityCollector.finishAll();
                                                LoginActivity.actionStart(view.getContext());
                                            } catch (InterruptedException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        }
                                    }).start();
                                    break;
                                case 201:
                                    Log.d("abc", "ok");
                                    ShareUtil.hideKeyBoard(view.getContext());
                                    Snackbar.make(registerButton, "注册成功，正在前往登录界面...", Snackbar.LENGTH_SHORT).show();
//                                    id.setError("用户名已存在");
                                    Snackbar.make(registerButton, "用户名已存在", Snackbar.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Snackbar.make(registerButton, "连接超时，请重试", Snackbar.LENGTH_SHORT).show();
                                    break;
                            }
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
                }
            }
        });
    }



    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }
}
