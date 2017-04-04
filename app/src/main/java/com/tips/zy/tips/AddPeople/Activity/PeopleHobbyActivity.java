package com.tips.zy.tips.AddPeople.Activity;

import android.app.Activity;
import android.os.Bundle;

import com.tips.zy.tips.R;

import zuo.biao.library.base.BaseActivity;

/**
 * Created by zy on 2017/4/2.
 */

public class PeopleHobbyActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peoplehobby);
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
