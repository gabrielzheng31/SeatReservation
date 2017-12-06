package com.example.gabriel.seatreservation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by gabriel on 17-12-6.
 */

public class PersonPageFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View pagePersonLayout = inflater.inflate(R.layout.page_person, container, false);
        return pagePersonLayout;
    }
}
