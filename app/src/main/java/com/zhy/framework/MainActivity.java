package com.zhy.framework;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost mTabHost;
    private FragmentTabHostHelper mTabHostHelper;

    private int mCountTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHostHelper = new FragmentTabHostHelper().initTabHost(this, mTabHost);

        mTabHostHelper.setOnItemclickListener(2, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCountTemp++ > 2) {
                    mTabHost.setCurrentTab(2);
                }
            }
        });
        modifyTabHost();
    }

    public void modifyTabHost(){
        List<FragmentTabHostHelper.TabItem> tabItems=new ArrayList<>();
        tabItems.add(new FragmentTabHostHelper.TabItem(0,"首页", ContextCompat.getDrawable(this,R.drawable.tab_icon_review),NormalFragment.class,null));
        tabItems.add(new FragmentTabHostHelper.TabItem(1,"妹子", ContextCompat.getDrawable(this,R.drawable.tab_icon_test),NormalFragment.class,null));
        tabItems.add(new FragmentTabHostHelper.TabItem(2,"账户", ContextCompat.getDrawable(this,R.drawable.tab_icon_other),NormalFragment.class,null));
        tabItems.add(new FragmentTabHostHelper.TabItem(3,"也是账户", ContextCompat.getDrawable(this,R.mipmap.main_account_tab_icon),NormalFragment.class,null));
        mTabHostHelper.modifyTabItem(tabItems);
    }
}
