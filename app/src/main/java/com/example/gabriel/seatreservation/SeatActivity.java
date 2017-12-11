package com.example.gabriel.seatreservation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SeatActivity extends BaseActivity {

    public SeatTable seatTableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_seat);

        seatTableView = findViewById(R.id.seatView);
        seatTableView.setScreenName("教411");//设置屏幕名称
        seatTableView.setMaxSelected(1);//设置最多选中

        seatTableView.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                /*if(column==2) {
                    return false;
                }*/
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

        });

        seatTableView.setData(7,7);

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SeatActivity.class);
        context.startActivity(intent);
    }
}
