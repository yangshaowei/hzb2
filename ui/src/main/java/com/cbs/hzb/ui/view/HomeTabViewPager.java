package com.cbs.hzb.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cbs.hzb.R;

import java.util.ArrayList;

/**
 * Created by c on 2017/3/20.
 * 主界面
 */

public class HomeTabViewPager extends LinearLayout implements View.OnClickListener {

    private View mView;
    private ViewPager mViewPager;
    private LinearLayout mTagBar;
    private ArrayList<TextView> mTags;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;

    public HomeTabViewPager(Context context) {
        super(context, null);
    }

    public HomeTabViewPager(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.view_home_tab_view_pager, this, true);
        mViewPager = (ViewPager) mView.findViewById(R.id.home_view_pager);
        mTagBar = (LinearLayout) mView.findViewById(R.id.home_tagbar);
        mTags = new ArrayList<TextView>();

        {
            TextView tvMessage = (TextView) mTagBar.findViewById(R.id.tv_bill);
            tvMessage.setId(0);
            tvMessage.setOnClickListener(this);
            mTags.add(tvMessage);
        }

        {
            TextView tvCall = (TextView) mTagBar.findViewById(R.id.tv_duty);
            tvCall.setId(1);
            tvCall.setOnClickListener(this);
            mTags.add(tvCall);
        }

        {
            TextView tvContact = (TextView) mTagBar.findViewById(R.id.tv_community);
            tvContact.setId(2);
            tvContact.setOnClickListener(this);
            mTags.add(tvContact);
        }

        {
            TextView tvMe = (TextView) mTagBar.findViewById(R.id.tv_me);
            tvMe.setId(3);
            tvMe.setOnClickListener(this);
            mTags.add(tvMe);
        }


        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                setSelector(position);
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageScrollStateChanged(state);
                }
            }
        });

        //初始显示第一个tab的内容
        setSelector(0);
    }


    public void setSelector(int index) {
        if (index >= 0 && index < mTagBar.getChildCount()) {
            //更改viewpager显示
            mViewPager.setCurrentItem(index);
            //更改tag状态
            for (int i = 0; i < mTags.size(); i++) {
                TextView tag = mTags.get(i);
                if (index == i) {
                    tag.setSelected(true);
                } else {
                    tag.setSelected(false);
                }
            }
        }
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        setSelector(i);
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mOnPageChangeListener = listener;
    }
}
