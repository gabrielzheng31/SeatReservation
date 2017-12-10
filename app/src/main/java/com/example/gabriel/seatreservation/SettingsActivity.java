package com.example.gabriel.seatreservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends BaseActivity {

    private String[] data = {"关于", "反馈", "退出"};
    private List<Settings> mSettingLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initSettings();
        RecyclerView recyclerView = findViewById(R.id.settings_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        SettingsAdapter adapter = new SettingsAdapter(mSettingLists);
        recyclerView.setAdapter(adapter);
    }

    private void initSettings() {
        Settings about = new Settings(R.string.settings_about);
        mSettingLists.add(about);
        Settings feedback = new Settings(R.string.settings_feedback);
        mSettingLists.add(feedback);
        Settings logoff = new Settings(R.string.settings_logoff);
        mSettingLists.add(logoff);
        Settings quit = new Settings(R.string.settings_quit);
        mSettingLists.add(quit);
    }

}
