package com.example.gabriel.seatreservation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabriel.seatreservation.utils.Configure;
import com.example.gabriel.seatreservation.utils.LoginUtil;

import java.util.List;

/**
 * Created by gabriel on 17-12-9.
 */

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {

    private List<Settings> mSettingsList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View mSettingsView;
        TextView mSettingsName;

        public ViewHolder(View view) {
            super(view);
            mSettingsView = view;
            mSettingsName = view.findViewById(R.id.item_settings_name);
        }
    }

    public SettingsAdapter(List<Settings> mSettingsList) {
        this.mSettingsList = mSettingsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_settings,
                parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.mSettingsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Settings settings = mSettingsList.get(position);
                switch (position){
                    case 2:
                        if (TextUtils.isEmpty(Configure.TOKEN)) {
                            AlertDialog.Builder dialog_logoff = new AlertDialog.Builder(view.getContext());
                            dialog_logoff.setTitle("您尚未登录");
                            dialog_logoff.setCancelable(true);
                            dialog_logoff.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            dialog_logoff.show();
                        } else {
                            Intent intent = new Intent("com.example.gabriel.seatreservation.LOG_OFF");
                            view.getContext().sendBroadcast(intent);
                        }
                        break;
                    case 3:
                        AlertDialog.Builder dialog_quit = new AlertDialog.Builder(view.getContext());
                        dialog_quit.setTitle("退出");
                        dialog_quit.setMessage("您确定要退出程序吗？");
                        dialog_quit.setCancelable(true);
                        dialog_quit.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.d("ok", "ok");
                                ActivityCollector.finishAll();
                            }
                        });
                        dialog_quit.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        dialog_quit.show();
                        break;
                    default:
                            break;
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(SettingsAdapter.ViewHolder holder, int position) {
        Settings settings = mSettingsList.get(position);
        holder.mSettingsName.setText(settings.getName());
    }

    @Override
    public int getItemCount() {
        return mSettingsList.size();
    }
}
