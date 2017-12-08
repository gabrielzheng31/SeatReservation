package com.example.gabriel.seatreservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private String[] data = {"关于", "反馈", "退出"};
    private List<String> mSettingLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_list_item_1, data);
        ListView listView = findViewById(R.id.settings_recycler_view);
        listView.setAdapter(adapter);


        /*RecyclerView recyclerView = findViewById(R.id.settings_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new RecyclerView.Adapter(mSettingLists);
        recyclerView.setAdapter(adapter);*/
    }

}
