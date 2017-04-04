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

public class RegistActivity extends BaseActivity {

    private TextView title_name;
    private TextView title_right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);
        initView();
        initData();
        initEvent();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    public static Intent CreateIntent(Context context){
        return new Intent(context,RegistActivity.class);
    }

    @Override
    public void initView() {
        title_name= (TextView) findViewById(R.id.titlename);
        title_name.setText("注册");
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
