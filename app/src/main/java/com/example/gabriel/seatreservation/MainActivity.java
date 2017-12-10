package com.example.gabriel.seatreservation;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {


    private View view_main, view_status, view_person;
    private ViewPager viewPager;
    private List<View> viewList;
    private List<PersonPage> mPersonPageList = new ArrayList<>();
//    private List<Fragment> fragments = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        LayoutInflater inflater=getLayoutInflater();
        view_main = inflater.inflate(R.layout.layout1, null);
        view_status = inflater.inflate(R.layout.page_status_logoff,null);
        view_person = inflater.inflate(R.layout.page_person, null);


        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view_main);
        viewList.add(view_status);

        initPersonPage();
        RecyclerView recyclerView = view_person.findViewById(R.id.page_person_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        PersonPageAdapter adapter = new PersonPageAdapter(mPersonPageList);
        recyclerView.setAdapter(adapter);

        viewList.add(view_person);

        PagerAdapter pagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return viewList.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewList.get(position));


                return viewList.get(position);
            }
        };

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                navigation.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        viewPager.setAdapter(pagerAdapter);


        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private void initPersonPage() {
        PersonPage account = new PersonPage(R.string.page_person_account,
                R.drawable.ic_account_circle_black_24dp);
        mPersonPageList.add(account);
        PersonPage history = new PersonPage(R.string.page_person_history,
                R.drawable.ic_history_black_24dp);
        mPersonPageList.add(history);
        PersonPage settings = new PersonPage(R.string.page_person_settings,
                R.drawable.ic_settings_black_24dp);
        mPersonPageList.add(settings);
    }

    /*public static void init(Context mContext) {
        Configure.USERID = SharedUtil.getString(mContext, "USERID");
        if (!Util.checkNULL(Configure.USERID))
            getUserInfo();
    }*/
}
