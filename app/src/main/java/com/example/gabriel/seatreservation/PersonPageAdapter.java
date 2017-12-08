package com.example.gabriel.seatreservation;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gabriel on 17-12-5.
 */

public class PersonPageAdapter extends RecyclerView.Adapter<PersonPageAdapter.ViewHolder> {

    private List<PersonPage> mPersonPageList;
//    private OnItemClickListener mOnItemClickListener = null;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View mPersonPageView;
        ImageView mPersonPageImage;
        TextView mPersonPageName;

        public ViewHolder(View view) {
            super(view);
            mPersonPageView = view;
            mPersonPageImage = (ImageView) view.findViewById(R.id.item_page_person_image);
            mPersonPageName = (TextView) view.findViewById(R.id.item_page_person_name);
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
        /*holder.mPersonPageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view,(int)view.getTag());
            }
        });*/

        holder.mPersonPageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                PersonPage personPage = mPersonPageList.get(position);
                switch (position){
                    case 2:
                        Intent intent = new Intent(view.getContext(), SettingsActivity.class);
                        view.getContext().startActivity(intent);
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
