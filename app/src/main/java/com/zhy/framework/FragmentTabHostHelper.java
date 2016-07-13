package com.zhy.framework;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dzy on 2016/7/7.
 */
public class FragmentTabHostHelper {
    public FragmentTabHost mTabHost;
    public List<TabItem> mTabItemLsit=new ArrayList<>();
    private View mCurrentView;
    private AppCompatActivity mActivity;

    public FragmentTabHostHelper initTabHost(AppCompatActivity activity, FragmentTabHost fTabHost) {
        this.mTabHost=fTabHost;
        this.mActivity=activity;
        initTabData();
        mTabHost.setup(activity, activity.getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setShowDividers(TabWidget.SHOW_DIVIDER_NONE);
        for (TabItem item : mTabItemLsit) {
            mTabHost.addTab(mTabHost.newTabSpec(String.valueOf(item.mID)).setIndicator(getTabItemView(item)), item.mClazz, item.mArgs);
        }

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                View viewTemp = mTabHost.getCurrentTabView();
                if (mCurrentView != null) {
                    runOutAnimation(mCurrentView);
                }
                runEnterAnimation(viewTemp);
                mCurrentView = viewTemp;

            }
        });
        return this;
    }
    public FragmentTabHostHelper setOnItemclickListener(int index, View.OnClickListener listener){
        if(index<mTabItemLsit.size()) {
            mTabHost.getTabWidget().getChildTabViewAt(index).setOnClickListener(listener);
        }
        return this;
    }
    private void runEnterAnimation(View view) {

        view.setScaleX(0.9f);
        view.setScaleY(0.9f);
        view.animate()
                .scaleX(1.1f)
                .scaleY(1.1f)
                .setStartDelay(100)
                .setInterpolator(new AnticipateOvershootInterpolator(3.f))
                .setDuration(700)
                .start();
    }
    private void runOutAnimation(View view) {

        view.animate()
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(200)
                .start();
    }
    //初始化Tab数据
    /**
     * 给每个Tab按钮设置图标和文字
     */
    private View getTabItemView(TabItem item) {
        View view = mActivity.getLayoutInflater().inflate(R.layout.tab_item_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageDrawable(item.mImage);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(item.mTitle);
        return view;
    }
    /**
     *初始化tab
     */
    private void initTabData() {
        //添加tab
        mTabItemLsit.add(new TabItem(0,"0", ContextCompat.getDrawable(mActivity,R.mipmap.ic_launcher),NormalFragment.class, null));
        mTabItemLsit.add(new TabItem(1,"1", ContextCompat.getDrawable(mActivity,R.mipmap.ic_launcher),NormalFragment.class, null));
        mTabItemLsit.add(new TabItem(2,"2", ContextCompat.getDrawable(mActivity,R.mipmap.ic_launcher),NormalFragment.class, null));
    }

    /**
     * 动态修改tab
     * @param list
     */
    public void modifyTabItem(List<TabItem> list){
        if(list==null||list.size()<1){
            return;
        }
        View view=null;
        TabItem item=null;
        for(int i=0;i<list.size();i++) {
            item = list.get(i);
            if (i < mTabHost.getTabWidget().getTabCount()) {
                view = mTabHost.getTabWidget().getChildTabViewAt(i);
                ((ImageView) view.findViewById(R.id.imageview)).setImageDrawable(item.mImage);
                ((TextView) view.findViewById(R.id.textview)).setText(item.mTitle);
            }else {
                mTabItemLsit.add(item);
                mTabHost.addTab(mTabHost.newTabSpec(String.valueOf(item.mID)).setIndicator(getTabItemView(item)), item.mClazz, item.mArgs);
            }
        }
    }

    public static class TabItem {
        public int mID;
        public String mTitle;
        public Drawable mImage;
        public Class mClazz;
        public Bundle mArgs;

        public TabItem(int id,String title, Drawable image, Class clazz, Bundle args){
            mID=id;
            mTitle=title;
            mImage=image;
            mClazz=clazz;
            mArgs=args;
        }
    }
}
