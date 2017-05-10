package com.cbs.hzb.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.cbs.domain.RegisterRequestBody;
import com.cbs.hzb.R;
import com.cbs.hzb.ui.adapt.HomeFragmentAdapter;
import com.cbs.hzb.ui.fragments.BillFragment;
import com.cbs.hzb.ui.fragments.CommunityFragment;
import com.cbs.hzb.ui.fragments.DutyFragment;
import com.cbs.hzb.ui.fragments.MeFragment;
import com.cbs.hzb.ui.view.HomeTabViewPager;
import com.cbs.impl.RegisterImpl;

import java.util.ArrayList;


/**
 * Created by c on 2017/4/12.
 */

public class MainActivity extends BaseActivity{

    private HomeTabViewPager mHomeView;
    private ViewPager mViewPager;
    private ArrayList<Fragment> mFragments;
    private HomeFragmentAdapter mFragmentAdapter;

    private BillFragment billFragment;
    private CommunityFragment communityFragment;
    private DutyFragment dutyFragment;
    private MeFragment meFragment;

    //m
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

        dutyFragment = new DutyFragment();
        mFragments.add(dutyFragment);

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
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
