package com.tips.zy.tips.AddPeople.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.tips.zy.tips.Login.View.PullScrollView;
import com.tips.zy.tips.R;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.manager.SystemBarTintManager;


/**
 * Pull down ScrollView demo.
 *
 * @author markmjw
 * @date 2014-04-30
 */
public class PeopleInfoAllActivity extends BaseActivity implements PullScrollView.OnTurnListener {
    private PullScrollView mScrollView;
    private LinearLayout mHeadImg;

    private TableLayout mMainLayout;


    @Override
    public Activity getActivity() {
        return this;
    }

    public static Intent CreateIntent(Context context){
        return new Intent(context,PeopleInfoAllActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.peopleinfoall);

        initView();
        initData();
        initEvent();
    }

    public void initView() {
        mScrollView = (PullScrollView) findViewById(R.id.scroll_view);
        mHeadImg = (LinearLayout) findViewById(R.id.background_img);


        mScrollView.setHeader(mHeadImg);
        mScrollView.setOnTurnListener(this);


    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }



    @Override
    public void onTurn() {

    }


}
