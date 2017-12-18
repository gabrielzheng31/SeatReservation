package com.example.gabriel.seatreservation;

import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.gabriel.seatreservation.utils.Configure;
import com.example.gabriel.seatreservation.utils.HttpCallbackListener;
import com.example.gabriel.seatreservation.utils.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

public class SeatActivity extends BaseActivity {

    private SeatTable seatTableView;
    private int row, col, checked_r, checked_c;
    private Button button_confirm;

    public static void setChecked_seat(Pair<Integer, Integer> temp) {
        Configure.temp = temp;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_seat);
        seatTableView = findViewById(R.id.seatView);
        seatTableView.setScreenName("教409");//设置屏幕名称
        seatTableView.setMaxSelected(1);//设置最多选中
        row = col =7;
        seatTableView.setData(row,col);

        /*seatTableView.setSeatChecker(new SeatTable.SeatChecker() {


            @Override
            public boolean isValidSeat(int row, int column) {
                if(column==2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if(row==6 && column==6 ||
                        row ==5 && column ==4 ||
                        row ==3 && column ==2){
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {

            }

            @Override
            public void unCheck(int row, int column) {

            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });*/

        final SeatTable.SeatChecker seatChecker = new SeatTable.SeatChecker() {
            @Override
            public boolean isValidSeat(int row, int column) {
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                Pair<Integer, Integer> temp = new Pair<>(row, column);
                if (Configure.SEATMAP.contains(temp))
                    return true;
                return false;
            }

            @Override
            public void checked(int row, int column) {

            }

            @Override
            public void unCheck(int row, int column) {

            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return new String[0];
            }
        };

        /*HttpUtil.CheckFreeSeat(HttpUtil.time_string(1), new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                Log.d("456", response);
                JsonParser jsonParser = new JsonParser();
                JsonArray jsonArray = jsonParser.parse(response).getAsJsonArray();
                ArrayList<SeatData> seatDataArrayList = new ArrayList<>();
                Gson gson = new Gson();

                for (JsonElement seat: jsonArray) {
                    SeatData seatData = gson.fromJson(seat, SeatData.class);
                    seatDataArrayList.add(seatData);
                    Log.d("7890", ""+seatData.getSeatStatus());
                }

                for (SeatData seatData: seatDataArrayList) {

                    Log.d("gcd", "status"+seatData.getSeatStatus());

                    if (seatData.getSeatStatus()) {
                        int r = (seatData.getSeatID() - 1) / row ;
                        int c = (seatData.getSeatID() - 1) % row ;
                        Log.d("asdf", r+" "+c);
                        Pair<Integer, Integer> temp = new Pair<>(r, c);
                        Configure.SEATMAP.add(temp);
                    }

                }

            }

            @Override
            public void onError(Exception e) {

            }

        });
*/
        seatTableView.setSeatChecker(seatChecker);

        final Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        final String start_hour = String.format("%02d", hour);
        final String start_minute = String.format("%02d", 0);
        String end_hour = String.format("%02d", hour+1);
        String end_minute = String.format("%02d", 0);
        final int[] start = new int[1];
        final int[] end = new int[1];


        final TextView start_time = findViewById(R.id.timepicker_start);
        final TextView end_time = findViewById(R.id.timepicker_end);

        start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TimePickerDialog timePickerDialog_start = new TimePickerDialog(SeatActivity.this,
                R.style.TimePickerDialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        start[0] = i;
                        if (start[0] < 8 || start[0] > 21) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SeatActivity.this);
                            builder.setTitle("非法时间");
                            builder.setCancelable(true);
                            builder.setMessage("请选择其他时间");
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder.show();
                            start_time.setText("开始时间");
                        } else {
                            start_time.setText(i+":"+"00");
                        }
                    }
                }, hour, minute, true);
                timePickerDialog_start.show();
            }
        });

        end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TimePickerDialog timePickerDialog_end = new TimePickerDialog(SeatActivity.this,
                        R.style.TimePickerDialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        end[0] = i;
                        if (start[0] == 0 || end[0] <= start[0] || end[0] > 22) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SeatActivity.this);
                            builder.setTitle("非法时间");
                            builder.setCancelable(true);
                            builder.setMessage("请选择其他时间");
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder.show();
                            end_time.setText("结束时间");
                            HttpUtil.CheckFreeSeat(start[0], end[0], new HttpCallbackListener() {
                                @Override
                                public void onFinish(String response) {
                                    Log.d("456", response);
                                    JsonParser jsonParser = new JsonParser();
                                    JsonArray jsonArray = jsonParser.parse(response).getAsJsonArray();
                                    ArrayList<SeatData> seatDataArrayList = new ArrayList<>();
                                    Gson gson = new Gson();

                                    for (JsonElement seat: jsonArray) {
                                        SeatData seatData = gson.fromJson(seat, SeatData.class);
                                        seatDataArrayList.add(seatData);
                                        Log.d("7890", ""+seatData.getSeatStatus());
                                    }

                                    for (SeatData seatData: seatDataArrayList) {

                                        Log.d("gcd", "status"+seatData.getSeatStatus());

                                        if (seatData.getSeatStatus()) {
                                            int r = (seatData.getSeatID() - 1) / row ;
                                            int c = (seatData.getSeatID() - 1) % row ;
                                            Log.d("asdf", r+" "+c);
                                            Pair<Integer, Integer> temp = new Pair<>(r, c);
                                            Configure.SEATMAP.add(temp);
                                        }

                                    }
                                }

                                @Override
                                public void onError(Exception e) {

                                }
                            });
                        } else {
                            end_time.setText(i+":"+"00");
                        }
                    }
                }, hour, minute, true);


                timePickerDialog_end.show();

            }

        });

        button_confirm = findViewById(R.id.button_confirm);
        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){

        }
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SeatActivity.class);
        context.startActivity(intent);
    }
}
