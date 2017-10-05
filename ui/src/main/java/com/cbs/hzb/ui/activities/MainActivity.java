package com.cbs.hzb.ui.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.cbs.hzb.R;
import com.cbs.hzb.ui.adapt.HomeFragmentAdapter;
import com.cbs.hzb.ui.fragments.BillFragment;
import com.cbs.hzb.ui.fragments.CommunityFragment;
import com.cbs.hzb.ui.fragments.AffairFragment;
import com.cbs.hzb.ui.fragments.MeFragment;
import com.cbs.hzb.ui.jpush.ExampleUtil;
import com.cbs.hzb.ui.jpush.LocalBroadcastManager;
import com.cbs.hzb.ui.view.HomeTabViewPager;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by c on 2017/4/12.
 */

public class MainActivity extends BaseActivity{

    public static boolean isForeground = false;
    private HomeTabViewPager mHomeView;
    private ViewPager mViewPager;
    private ArrayList<Fragment> mFragments;
    private HomeFragmentAdapter mFragmentAdapter;

    private BillFragment billFragment;
    private CommunityFragment communityFragment;
    private AffairFragment affairFragment;
    private MeFragment meFragment;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void findViews() {
        mHomeView = (HomeTabViewPager) findViewById(R.id.home_tag_view_pager);
        mViewPager = mHomeView.getViewPager();
        mViewPager.setOffscreenPageLimit(3);
    }

    @Override
    protected void init() {
        //推送
        JPushInterface.init(getApplicationContext());
        initToolBar();

        mViewPager = mHomeView.getViewPager();
        mViewPager.setOffscreenPageLimit(3);
        mHomeView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setToolBarTitle(position);
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initFragment();
        setToolBarTitle(0);

    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle("XXX");
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_4991fb));
        setSupportActionBar(toolbar);
    }

    private void initFragment() {
        mFragments = new ArrayList<Fragment>();

        billFragment = new BillFragment();
        mFragments.add(billFragment);

        communityFragment = new CommunityFragment();
        mFragments.add(communityFragment);

        affairFragment = new AffairFragment();
        mFragments.add(affairFragment);

        meFragment = new MeFragment();
        mFragments.add(meFragment);

        mFragmentAdapter = new HomeFragmentAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mFragmentAdapter);

    }

    private void setToolBarTitle(int position) {
        switch (position) {
            case 0:
                getSupportActionBar().setTitle(getResources().getString(R.string.home_bill));
                break;
            case 1:
                getSupportActionBar().setTitle(getResources().getString(R.string.home_duty));
                break;
            case 2:
                getSupportActionBar().setTitle(getResources().getString(R.string.home_community));
                break;
            case 3:
                getSupportActionBar().setTitle(getResources().getString(R.string.home_me));
                break;
            default:
                getSupportActionBar().setTitle("APP");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //推送
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    //推送
    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                    setCostomMsg(showMsg.toString());
                }
            } catch (Exception e){
            }
        }
    }

    private void setCostomMsg(String msg){

    }

}
