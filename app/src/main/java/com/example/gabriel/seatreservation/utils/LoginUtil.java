package com.example.gabriel.seatreservation.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;

import com.example.gabriel.seatreservation.ActivityCollector;
import com.example.gabriel.seatreservation.LoginActivity;

import java.lang.ref.WeakReference;

/**
 * Created by gabriel on 17-12-10.
 */

public class LoginUtil {

    public static void checkLogin(final Context context, final LoginForCallBack callBack) {
        // 弱引用，防止内存泄露，
        WeakReference<Context> reference= new WeakReference<>(context);

        if (TextUtils.isEmpty(Configure.TOKEN)) { // 判断是否登录，否返回true

            Configure.CALLBACK = new ICallBack() {

                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void postExec() {
                    // 登录回调后执行登录回调前需要做的操作
                    if (!Configure.TOKEN.isEmpty()) {
                        // 这里需要再次判断是否登录，防止用户取消登录，取消则不执行登录成功需要执行的回调操作
                        callBack.callBack();
                        //防止调用界面的回调方法中有传进上下文的引用导致内存泄漏
                        Configure.CALLBACK = null;
                    }
                }
            };

            Context mContext = reference.get();

            if (mContext != null) {
                LoginActivity.actionStart(mContext);
                reference=null;
            }
        } else {
            // 登录状态直接执行登录回调前需要做的操作
            callBack.callBack();
        }
    }

    public void clear() {
        try {
            finalize();
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 声明一个登录成功回调的接口
    public interface ICallBack {
        // 在登录操作及信息获取完成后调用这个方法来执行登录回调需要做的操作
        void postExec();
    }

    @FunctionalInterface//Java8 函数注解，没有升级java8的去掉这一句
    public interface LoginForCallBack {
        void callBack();
    }

}
