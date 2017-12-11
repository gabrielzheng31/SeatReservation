package com.example.gabriel.seatreservation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.gabriel.seatreservation.utils.Configure;
import com.example.gabriel.seatreservation.utils.HttpCallbackListener;
import com.example.gabriel.seatreservation.utils.HttpUtil;
import com.example.gabriel.seatreservation.utils.LoginUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {


    private View view_main, view_status, view_person;
    private ViewPager viewPager;
    private List<View> viewList = new ArrayList<View>();;
    private List<PersonPage> mPersonPageList = new ArrayList<>();
    private boolean view_status_logoff, view_status_login_0, view_status_login_1;

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

        viewPager = findViewById(R.id.viewpager);
        final LayoutInflater inflater = getLayoutInflater();
        view_main = inflater.inflate(R.layout.page_main, null);
        view_person = inflater.inflate(R.layout.page_person, null);

        CardView cardView = view_main.findViewById(R.id.cardview);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SeatActivity.actionStart(view.getContext());
            }
        });

        viewList.add(view_main);



        LoginUtil.checkLogin(MainActivity.this, new LoginUtil.LoginForCallBack() {
            @Override
            public void callBack() {
                if (Configure.TOKEN == null || TextUtils.isEmpty(Configure.TOKEN)) {
                    view_status_logoff = true;
                } else {
                    String address="http://172.26.40.36:8080/SeatReservation/login";
//                    String address="http://192.168.1.7:8080/SeatReservation/login";
                    HttpUtil.GetReservedStatus(Configure.TOKEN, address, new HttpCallbackListener() {
                        @Override
                        public void onFinish(String response) {
                            Log.d("test", response);
                            Gson gson = new Gson();
                            UserData userData = gson.fromJson(response, UserData.class);
                            switch (userData.getResCode()) {
                                case 0:
                                    Configure.STATUS = userData.getResStatus();
                                    break;
                                case 1:
                                    break;
                            }
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
                    if (Configure.STATUS == true) {
                        view_status_login_1 = true;
                    }
                    else {
                        view_status_login_0 = true;
                    }
                }
            }
        });

        if (view_status_logoff) {
            view_status = inflater.inflate(R.layout.page_status_logoff, null);
        } else if (view_status_login_0) {
            view_status = inflater.inflate(R.layout.page_status_login_0, null);
        } else {
            view_status = inflater.inflate(R.layout.page_status_login_1, null);
        }

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


        final BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
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

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    /*public static void init(Context mContext) {
        Configure.USERID = SharedUtil.getString(mContext, "USERID");
        if (!Util.checkNULL(Configure.USERID))
            getUserInfo();
    }*/
}
