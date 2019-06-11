package com.example.lenovo.accounting;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {

    public MyAdapter(FragmentManager manager){
        super(manager);//将manager传递给父类用来管理Fragment
    }

    @Override//根据位置显示第几个页面，默认显示支出
    public Fragment getItem(int i) {
        if (i==0){
            return new Expenses();
        }else {
            return new Income();
        }
    }

    @Nullable
    @Override//实现标题内容
    public CharSequence getPageTitle(int position) {
        if (position==0){
            return "支出";
        }else {
            return "收入";
        }
    }

    @Override//一共有2个页面
    public int getCount() {
        return 2;
    }
}
