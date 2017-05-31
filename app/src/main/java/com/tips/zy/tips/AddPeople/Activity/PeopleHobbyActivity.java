package com.tips.zy.tips.AddPeople.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.tips.zy.tips.AddPeople.Entity.PeopleHobby;
import com.tips.zy.tips.AddPeople.Entity.PeopleWork;
import com.tips.zy.tips.Application.MyApplication;
import com.tips.zy.tips.R;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.ui.BottomMenuWindow;
import zuo.biao.library.ui.EditTextInfoActivity;
import zuo.biao.library.ui.EditTextInfoWindow;
import zuo.biao.library.util.StringUtil;

/**
 * Created by zy on 2017/4/2.
 */

public class PeopleHobbyActivity extends BaseActivity implements View.OnClickListener,OnTabActivityResultListener{
    public static final String TAG="PeopleHobbyActivity";
    private Button next;
    private EditText mark_text;
    //领域
    private CheckBox field_check1;
    private CheckBox field_check2;
    private CheckBox field_check3;
    private CheckBox field_check4;
    private CheckBox field_check5;
    private CheckBox field_check6;
    private CheckBox field_check7;
    private CheckBox field_check8;
    //运动
    private CheckBox sport_check1;
    private CheckBox sport_check2;
    private CheckBox sport_check3;
    private CheckBox sport_check4;
    private CheckBox sport_check5;
    private CheckBox sport_check6;
    private CheckBox sport_check7;
    private CheckBox sport_check8;
    //憎恨
    private CheckBox hate_check1;
    private CheckBox hate_check2;
    private CheckBox hate_check3;
    private CheckBox hate_check4;
    private CheckBox hate_check5;
    private CheckBox hate_check6;
    private CheckBox hate_check7;
    private CheckBox hate_check8;
    //字段
    private String mark_name="";
    private String field_name="";
    private String sport_name="";
    private String hate_name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peoplehobby);
        initView();
        initData();
        initEvent();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void initView() {
        next= (Button) findViewById(R.id.next);
        mark_text= (EditText) findViewById(R.id.mark_text);
        field_check1= (CheckBox) findViewById(R.id.field_check1);
        field_check2= (CheckBox) findViewById(R.id.field_check2);
        field_check3= (CheckBox) findViewById(R.id.field_check3);
        field_check4= (CheckBox) findViewById(R.id.field_check4);
        field_check5= (CheckBox) findViewById(R.id.field_check5);
        field_check6= (CheckBox) findViewById(R.id.field_check6);
        field_check7= (CheckBox) findViewById(R.id.field_check7);
        field_check8= (CheckBox) findViewById(R.id.field_check8);
        sport_check1= (CheckBox) findViewById(R.id.sport_check1);
        sport_check2= (CheckBox) findViewById(R.id.sport_check2);
        sport_check3= (CheckBox) findViewById(R.id.sport_check3);
        sport_check4= (CheckBox) findViewById(R.id.sport_check4);
        sport_check5= (CheckBox) findViewById(R.id.sport_check5);
        sport_check6= (CheckBox) findViewById(R.id.sport_check6);
        sport_check7= (CheckBox) findViewById(R.id.sport_check7);
        sport_check8= (CheckBox) findViewById(R.id.sport_check8);
        hate_check1= (CheckBox) findViewById(R.id.hate_check1);
        hate_check2= (CheckBox) findViewById(R.id.hate_check2);
        hate_check3= (CheckBox) findViewById(R.id.hate_check3);
        hate_check4= (CheckBox) findViewById(R.id.hate_check4);
        hate_check5= (CheckBox) findViewById(R.id.hate_check5);
        hate_check6= (CheckBox) findViewById(R.id.hate_check6);
        hate_check7= (CheckBox) findViewById(R.id.hate_check7);
        hate_check8= (CheckBox) findViewById(R.id.hate_check8);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        next.setOnClickListener(this);
        field_check8.setOnClickListener(this);
        sport_check8.setOnClickListener(this);
        hate_check8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id =v.getId();
        switch(id){
            case R.id.field_check8:{
                intent = EditTextInfoWindow.createIntent(context, EditTextInfoWindow.TYPE_NICK
                        , "设置分组", StringUtil.getTrimedString(field_check8), getPackageName());
                getParent().startActivityForResult(intent, REQUEST_TO_EDIT_TEXT_INFO1);
                break;
            }
            case R.id.sport_check8:{
                intent = EditTextInfoWindow.createIntent(context, EditTextInfoWindow.TYPE_NICK
                        , "设置分组", StringUtil.getTrimedString(sport_check8), getPackageName());
                getParent().startActivityForResult(intent, REQUEST_TO_EDIT_TEXT_INFO2);
                break;
            }
            case R.id.hate_check8:{
                intent = EditTextInfoWindow.createIntent(context, EditTextInfoWindow.TYPE_NICK
                        , "设置分组", StringUtil.getTrimedString(field_check8), getPackageName());
                getParent().startActivityForResult(intent, REQUEST_TO_EDIT_TEXT_INFO3);
                break;
            }
            case R.id.next:{
                mark_name=String.valueOf(mark_text.getText());
                if(field_check1.isChecked()){
                    field_name+=field_check1.getText();
                }if(field_check2.isChecked()){
                    field_name+=field_check2.getText();
                }if(field_check3.isChecked()){
                    field_name+=field_check3.getText();
                }if(field_check4.isChecked()){
                    field_name+=field_check4.getText();
                }if(field_check5.isChecked()){
                    field_name+=field_check5.getText();
                }if(field_check6.isChecked()){
                    field_name+=field_check6.getText();
                }if(field_check7.isChecked()){
                    field_name+=field_check7.getText();
                }if(field_check8.isChecked()){
                    field_name+=field_check8.getText();
                }
                if(sport_check1.isChecked()){
                    sport_name+=sport_check1.getText();
                }if(sport_check2.isChecked()){
                    sport_name+=sport_check2.getText();
                }if(sport_check3.isChecked()){
                    sport_name+=sport_check3.getText();
                }if(sport_check4.isChecked()){
                    sport_name+=sport_check4.getText();
                }if(sport_check5.isChecked()){
                    sport_name+=sport_check5.getText();
                }if(sport_check6.isChecked()){
                    sport_name+=sport_check6.getText();
                }if(sport_check7.isChecked()){
                    sport_name+=sport_check7.getText();
                }if(sport_check8.isChecked()){
                    sport_name+=sport_check8.getText();
                }
                if(hate_check1.isChecked()){
                    hate_name+=hate_check1.getText();
                }if(hate_check2.isChecked()){
                    hate_name+=hate_check2.getText();
                }if(hate_check3.isChecked()){
                    hate_name+=hate_check3.getText();
                }else if(hate_check4.isChecked()){
                    hate_name+=hate_check4.getText();
                }if(hate_check5.isChecked()){
                    hate_name+=hate_check5.getText();
                }else if(hate_check6.isChecked()){
                    hate_name+=hate_check6.getText();
                }if(hate_check7.isChecked()){
                    hate_name+=hate_check7.getText();
                }if(hate_check8.isChecked()){
                    hate_name+=hate_check8.getText();
                }
                insertHobby();
                break;
            }
        }
    }

    private void insertHobby() {
        showProgressDialog("数据保存中。。。");
        runThread(TAG + "保存work数据", new Runnable() {
            @Override
            public void run() {
                //MyApplication myApplication= (MyApplication) getApplicationContext();
                PeopleHobby peopleHobby=getMyApplication().getPeopleHobby();
                peopleHobby.setH_field(field_name);
                peopleHobby.setH_Sport(sport_name);
                peopleHobby.setH_Hate(hate_name);
                peopleHobby.setH_Remark(mark_name);
                Log.d("peopleHobby",peopleHobby.toString());
                field_name="";
                sport_name="";
                hate_name="";
                mark_name="";
                dismissProgressDialog();
            }
        });
    }
    public MyApplication getMyApplication(){
        return (MyApplication) getApplicationContext();
    }

    private static final int REQUEST_TO_EDIT_TEXT_INFO1 = 41;
    private static final int REQUEST_TO_EDIT_TEXT_INFO2 = 42;
    private static final int REQUEST_TO_EDIT_TEXT_INFO3 = 43;
    @Override
    public void onTabActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {

            case REQUEST_TO_EDIT_TEXT_INFO1:
                if (data != null) {

                    field_check8.setText(StringUtil.getTrimedString(
                            data.getStringExtra(EditTextInfoWindow.RESULT_VALUE)));

                }
                break;
            case REQUEST_TO_EDIT_TEXT_INFO2:
                if (data != null) {

                    sport_check8.setText(StringUtil.getTrimedString(
                            data.getStringExtra(EditTextInfoWindow.RESULT_VALUE)));

                }
                break;
            case REQUEST_TO_EDIT_TEXT_INFO3:
                if (data != null) {

                    hate_check8.setText(StringUtil.getTrimedString(
                            data.getStringExtra(EditTextInfoWindow.RESULT_VALUE)));

                }
                break;
            default:
                break;
        }

    }

}
