package com.example.gabriel.seatreservation;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gabriel.seatreservation.utils.Configure;
import com.example.gabriel.seatreservation.utils.LoginUtil;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by gabriel on 17-12-5.
 */

public class PersonPageAdapter extends RecyclerView.Adapter<PersonPageAdapter.ViewHolder> {

    private List<PersonPage> mPersonPageList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View mPersonPageView;
        ImageView mPersonPageImage;
        TextView mPersonPageName;

        public ViewHolder(View view) {
            super(view);
            mPersonPageView = view;
            mPersonPageImage = view.findViewById(R.id.item_page_person_image);
            mPersonPageName = view.findViewById(R.id.item_page_person_name);
        }
    }

    public PersonPageAdapter(List<PersonPage> mPersonPageList) {
        this.mPersonPageList = mPersonPageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_page_person,
                parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.mPersonPageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                PersonPage personPage = mPersonPageList.get(position);
                switch (position){
                    case 0:
                        Log.d("abc", "ok");
                        LoginUtil.checkLogin(view.getContext(), new LoginUtil.LoginForCallBack() {
                            @Override
                            public void callBack() {
                                Log.d("abc", "1234");
                                //TODO start AccountActivity
                            }
                        });
                        break;
                    case 2:
                        SettingsActivity.actionStart(view.getContext());
                        break;
                }

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PersonPage personPage = mPersonPageList.get(position);
        holder.mPersonPageImage.setImageResource(personPage.getImageId());
        holder.mPersonPageName.setText(personPage.getName());
        holder.mPersonPageView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mPersonPageList.size();
    }

    /*public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }*/

}
