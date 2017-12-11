package com.example.gabriel.seatreservation;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by gabriel on 17-12-11.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    //覆写onCreateDialog方法，返回DatePickerDialog对象
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //实例化一个日历对象，通过该对象获取当前的年，月，日，用于初始化DatePickerDialog上的日期
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        //返回一个DatePickerDialog实例
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    //当设置完日期后，后调用该方法
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(getActivity(), "当前日期设置为:" + year + "年" + monthOfYear + "月" +
                dayOfMonth + "日", Toast.LENGTH_LONG).show();
    }
    
}
