package com.example.gabriel.seatreservation.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by gabriel on 17-12-11.
 */

public class ShareUtil {

    public static void hideKeyBoard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        // 得到InputMethodManager的实例
       /* if (imm.isActive()) {
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_NOT_ALWAYS);
            // 关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
        } else {*/
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
    }
}
