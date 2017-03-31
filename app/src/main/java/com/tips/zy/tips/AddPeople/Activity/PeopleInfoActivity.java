package com.tips.zy.tips.AddPeople.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import zuo.biao.library.base.BaseActivity;

/**
 * Created by zy on 2017/3/31.
 */

public class PeopleInfoActivity extends BaseActivity{
    @Override
    public Activity getActivity() {
        return this;
    }

    public static Intent CreateIntent(Context context){
        return new Intent(context,PeopleInfoActivity.class);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
