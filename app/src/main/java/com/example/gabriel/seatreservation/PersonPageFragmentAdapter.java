package com.example.gabriel.seatreservation;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by gabriel on 17-12-6.
 */

public class PersonPageFragmentAdapter extends FragmentPagerAdapter {

    private List<PersonPageFragment> mPersonPageFragments;

    public PersonPageFragmentAdapter(FragmentManager fm, List<PersonPageFragment> fragments) {
        super(fm);
        this.mPersonPageFragments = fragments;
    }


    @Override
    public PersonPageFragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return mPersonPageFragments.get(arg0);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mPersonPageFragments.size();
    }
}
