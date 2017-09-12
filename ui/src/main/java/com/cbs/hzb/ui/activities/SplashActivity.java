package com.cbs.hzb.ui.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cbs.hzb.R;
import com.cbs.hzb.ui.adapt.SplashPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c on 2017/4/12.
 */

public class SplashActivity extends BaseActivity{

    private ViewPager mViewPager;
    private ImageView mCircleImage;
    private SplashPagerAdapter mAdapter;
    private List<View> mImageViews;
    private Button splashBtn;
    private int[] images = new int[] { R.drawable.splash_01, R.drawable.splash_02, R.drawable.splash_03, R.drawable.splash_04 };

    long time = 0;
    private boolean isScrollable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void init() {
        time = System.currentTimeMillis();
        mImageViews = new ArrayList<View>();
        mViewPager = (ViewPager) findViewById(R.id.splash_view_pager);
        //测试取消滚动页
//        initViewPagerContent();
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void initViewPagerContent() {
        try {
            for(int i = 0; i < images.length; i++) {
                View view = getLayoutInflater().inflate(R.layout.splash_view, null);
                RelativeLayout root = (RelativeLayout) view.findViewById(R.id.splash_root);
                root.setBackgroundResource(images[i]);
                splashBtn = (Button) view.findViewById(R.id.splash_btn);
                splashBtn.setOnClickListener(this);
                if (i == images.length - 1) {
                    splashBtn.setVisibility(View.VISIBLE);
                    splashBtn.setOnClickListener(this);
                }
                mImageViews.add(view);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        mAdapter = new SplashPagerAdapter(this,mImageViews);
        mViewPager.setAdapter(mAdapter);
        if(isScrollable) {
            mViewPager.setCurrentItem(images.length-1);
        }
        mViewPager.addOnPageChangeListener(new PageChangeListener());
        mViewPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return isScrollable;
            }
        });
        if (mViewPager.getChildCount() > 0) {
            mViewPager.setCurrentItem(0);
        }
        mCircleImage = (ImageView) findViewById(R.id.splash_circle_img);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int position) {

        }

        @Override
        public void onPageScrolled(int position, float arg1, int arg2) {
            if(position >= images.length - 1) {
                isScrollable = true;
            }
        }

        @Override
        public void onPageSelected(int position) {
            // 设置底部小点选中状态

        }

    }
}
