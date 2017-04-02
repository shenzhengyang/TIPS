package com.tips.zy.tips.Other.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tips.zy.tips.R;

import zuo.biao.library.base.BaseActivity;

/**
 * Created by zy on 2017/4/2.
 */

public class BackActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.back);
    }
    public static Intent CreateIntent(Context context){
        return new Intent(context,BackActivity.class);
    }
    @Override
    public Activity getActivity() {
        return this;
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
