package com.tips.zy.tips.AddPeople.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tips.zy.tips.AddPeople.Entity.PeopleWork;
import com.tips.zy.tips.R;

import java.util.ArrayList;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.manager.SystemBarTintManager;
import zuo.biao.library.ui.BottomMenuWindow;
import zuo.biao.library.ui.DatePickerWindow;
import zuo.biao.library.ui.PlacePickerWindow;
import zuo.biao.library.util.StringUtil;

/**
 * Created by zy on 2017/4/2.
 */

public class PeopleWorkActivity extends BaseActivity implements View.OnClickListener{


    private LinearLayout company_address;
    private TextView company_addressText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(false);
        setContentView(R.layout.peoplework);
        initView();
        initData();
        initEvent();
    }
    private void hideStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
    }


    public static Intent CreateIntent(Context context){
        return new Intent(context, PeopleWork.class);
    }
    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void initView() {
        company_address= (LinearLayout) findViewById(R.id.company_address);
        company_addressText= (TextView) findViewById(R.id.company_addressText);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        company_address.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id){
            case R.id.company_address:{
                toActivity(PlacePickerWindow.createIntent(context, getPackageName(), 2), REQUEST_TO_PLACE_PICKER, false);
                break;
            }
        }
    }


    private static final int REQUEST_TO_PLACE_PICKER = 32;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {

            case REQUEST_TO_PLACE_PICKER:

                if (data != null) {
                    Log.d("dadaisnotnull","数据不为空");
                    ArrayList<String> placeList = data.getStringArrayListExtra(PlacePickerWindow.RESULT_PLACE_LIST);
                    if (placeList != null) {
                        String place = "";
                        for (String s : placeList) {
                            place += StringUtil.getTrimedString(s);
                        }

                        showShortToast("选择的地区为: " + place);
                        company_addressText.setText(place);
                    }
                }
                break;
            default:
                break;
        }

    }
}
