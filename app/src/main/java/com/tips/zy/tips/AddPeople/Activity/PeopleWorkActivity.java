package com.tips.zy.tips.AddPeople.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tips.zy.tips.AddPeople.Entity.PeopleWork;
import com.tips.zy.tips.Application.MyApplication;
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

public class PeopleWorkActivity extends BaseActivity implements View.OnClickListener,OnTabActivityResultListener{
    public static final String TAG="PeopleWorkActivity";
    private Button next;
    private EditText company_text;
    private LinearLayout company_position;
    private TextView position_text;
    private TextView mark_text;
    private LinearLayout company_address;
    private TextView company_addressText;

    private String company_name;
    private String position_name;
    private String address_name;
    private String mark_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        next= (Button) findViewById(R.id.next);
        company_text= (EditText) findViewById(R.id.company_text);
        company_position= (LinearLayout) findViewById(R.id.company_position);
        position_text= (TextView) findViewById(R.id.position_text);
        mark_text= (TextView) findViewById(R.id.mark_text);
        company_address= (LinearLayout) findViewById(R.id.company_address);
        company_addressText= (TextView) findViewById(R.id.company_addressText);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        company_position.setOnClickListener(this);
        company_address.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id){
            case R.id.company_position:{
                getParent().startActivityForResult(BottomMenuWindow.createIntent(context.getParent(), position)
                        .putExtra(BottomMenuWindow.INTENT_TITLE, "选择职位"), REQUEST_TO_BOTTOM_MENU);
                break;
            }
            case R.id.company_address:{
                getParent().startActivityForResult(PlacePickerWindow.createIntent(context.getParent(), getPackageName(), 2), REQUEST_TO_PLACE_PICKER);
                break;
            }
            case R.id.next:{
                company_name=String.valueOf(company_text.getText().toString());
                Log.d("company_text.getText()",company_name);
                position_name=String.valueOf(position_text.getText());
                address_name=String.valueOf(company_addressText.getText());
                mark_name=String.valueOf(mark_text.getText());

                insertWork();
                break;
            }
        }
    }

    private void insertWork() {
        showProgressDialog("数据保存中。。。");
        runThread(TAG + "保存work数据", new Runnable() {
            @Override
            public void run() {
                //MyApplication myApplication= (MyApplication) getApplicationContext();
                PeopleWork peopleWork=getMyApplication().getPeopleWork();
                peopleWork.setW_Company(company_name);
                peopleWork.setW_Position(position_name);
                peopleWork.setW_Address(address_name);
                peopleWork.setW_Remark(mark_name);
                Log.d("peopleWork",getMyApplication().getPeopleWork().toString());
                dismissProgressDialog();
            }
        });
    }
public MyApplication getMyApplication(){
    return (MyApplication) getApplicationContext();
}

    private static final int REQUEST_TO_PLACE_PICKER = 36;
    private static final int REQUEST_TO_BOTTOM_MENU = 37;

    private static final String[] position = {"老板", "教授", "经理","职员","其他"};

    @Override
    public void onTabActivityResult(int requestCode, int resultCode, Intent data) {
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
            case REQUEST_TO_BOTTOM_MENU:
                if (data != null) {
                    int selectedPosition = data.getIntExtra(BottomMenuWindow.RESULT_ITEM_ID, -1);
                    if (selectedPosition >= 0 && selectedPosition < position.length) {
                        position_text.setText(position[selectedPosition]);
                    }
                }
                break;
            default:
                break;
        }

    }
}
