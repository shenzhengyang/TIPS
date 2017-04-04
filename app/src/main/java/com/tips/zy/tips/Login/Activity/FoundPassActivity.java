package com.tips.zy.tips.Login.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.tips.zy.tips.R;

import zuo.biao.library.base.BaseActivity;

/**
 * Created by zy on 2017/4/2.
 */

public class FoundPassActivity extends BaseActivity {
    private TextView title_name;
    private TextView title_right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foundpass);
        initView();
        initData();
        initEvent();
    }
    public static Intent CreateIntent(Context context){
        return new Intent(context,FoundPassActivity.class);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void initView() {
        title_name= (TextView) findViewById(R.id.titlename);
        title_name.setText("找回密码");
        title_right= (TextView) findViewById(R.id.titleright);
        title_right.setText("");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}