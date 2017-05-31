package com.tips.zy.tips.AddPeople.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.tips.zy.tips.AddPeople.DBHelper.PeopleCharacterHelper;
import com.tips.zy.tips.AddPeople.DBHelper.PeopleHobbyHelper;
import com.tips.zy.tips.AddPeople.DBHelper.PeopleInfoAllHelper;
import com.tips.zy.tips.AddPeople.DBHelper.PeopleInfoHelper;
import com.tips.zy.tips.AddPeople.DBHelper.PeopleWorkHelper;
import com.tips.zy.tips.AddPeople.Entity.PeopleCharacter;
import com.tips.zy.tips.AddPeople.Entity.PeopleHobby;
import com.tips.zy.tips.AddPeople.Entity.PeopleInfo;
import com.tips.zy.tips.AddPeople.Entity.PeopleInfoAll;
import com.tips.zy.tips.AddPeople.Entity.PeopleWork;
import com.tips.zy.tips.Application.MyApplication;
import com.tips.zy.tips.Main.Entity.People;
import com.tips.zy.tips.R;

import java.util.ArrayList;
import java.util.List;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.ui.BottomMenuWindow;
import zuo.biao.library.ui.EditTextInfoActivity;
import zuo.biao.library.ui.EditTextInfoWindow;
import zuo.biao.library.ui.PlacePickerWindow;
import zuo.biao.library.util.Log;
import zuo.biao.library.util.StringUtil;

/**
 * Created by zy on 2017/4/2.
 */

public class PeopleCharacterActivity extends BaseActivity implements View.OnClickListener,OnTabActivityResultListener{
    public static final String TAG="PeopleCharacterActivity";
    private Button finish;
    private EditText mark_text;
    private CheckBox[] checkBoxes=new CheckBox[11];
    private String mark_name;
    private String charac_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peoplecharacter);
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
        finish= (Button) findViewById(R.id.next);
        mark_text= (EditText) findViewById(R.id.mark_text);
        checkBoxes[0]= (CheckBox) findViewById(R.id.charac_check1);
        checkBoxes[1]= (CheckBox) findViewById(R.id.charac_check2);
        checkBoxes[2]= (CheckBox) findViewById(R.id.charac_check3);
        checkBoxes[3]= (CheckBox) findViewById(R.id.charac_check4);
        checkBoxes[4]= (CheckBox) findViewById(R.id.charac_check5);
        checkBoxes[5]= (CheckBox) findViewById(R.id.charac_check6);
        checkBoxes[6]= (CheckBox) findViewById(R.id.charac_check7);
        checkBoxes[7]= (CheckBox) findViewById(R.id.charac_check8);
        checkBoxes[8]= (CheckBox) findViewById(R.id.charac_check9);
        checkBoxes[9]= (CheckBox) findViewById(R.id.charac_check10);
        checkBoxes[10]= (CheckBox) findViewById(R.id.charac_check11);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        finish.setOnClickListener(this);
        checkBoxes[10].setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id){
            case R.id.charac_check11:{
                intent = EditTextInfoWindow.createIntent(context, EditTextInfoWindow.TYPE_NICK
                        , "设置分组", StringUtil.getTrimedString(checkBoxes[10]), getPackageName());
                getParent().startActivityForResult(intent, REQUEST_TO_EDIT_TEXT_INFO_zidingyi);
                break;
            }
            case R.id.next:{
                getParent().startActivityForResult(BottomMenuWindow.createIntent(context.getParent(), groups)
                        .putExtra(BottomMenuWindow.INTENT_TITLE, "选择职位"), REQUEST_TO_BOTTOM_MENU);


                break;
            }
        }
    }

    private void insertCharac() {
        mark_name=String.valueOf(mark_text.getText());
        for(int i=0;i<11;i++){
            if(checkBoxes[i].isChecked())
                charac_name+=checkBoxes[i].getText();
        }
        //MyApplication myApplication= (MyApplication) getApplicationContext();
        final PeopleCharacter peopleCharacter=getMyApplication().getPeopleCharacter();
        peopleCharacter.setC_Remark(mark_name);
        peopleCharacter.setC_Character(charac_name);
        Log.d("PeopleCharacter",getMyApplication().getPeopleCharacter().toString());
        charac_name="";
        showProgressDialog("插入中");
        new Thread(new Runnable() {

            @Override
            public void run() {

                //插入peopleInfo
                PeopleInfoHelper peopleInfoHelper=new PeopleInfoHelper(context);
                PeopleInfo peopleInfo=getMyApplication().getPeopleInfo();
                List<PeopleInfo> peopleInfos=new ArrayList<PeopleInfo>();
                peopleInfos.add(peopleInfo);
                peopleInfoHelper.addPeopleInfo(peopleInfos);
                Log.d("插入peopleInfo",peopleInfo.toString());
                //插入peopleWork
                PeopleWorkHelper peopleWorkHelper=new PeopleWorkHelper(context);
                PeopleWork peopleWork=getMyApplication().getPeopleWork();
                List<PeopleWork> peopleWorks=new ArrayList<PeopleWork>();
                peopleWorks.add(peopleWork);
                peopleWorkHelper.addPeopleWork(peopleWorks);
                Log.d("插入peopleWork",peopleWork.toString());

                //插入peopleHobby
                PeopleHobbyHelper peopleHobbyHelper=new PeopleHobbyHelper(context);
                PeopleHobby peopleHobby=getMyApplication().getPeopleHobby();
                List<PeopleHobby> peopleHobbys=new ArrayList<PeopleHobby>();
                peopleHobbys.add(peopleHobby);
                peopleHobbyHelper.addPeopleHobby(peopleHobbys);
                Log.d("插入peopleHobby",peopleHobby.toString());
                //插入charactor
                PeopleCharacterHelper peopleCharacterHelper=new PeopleCharacterHelper(context);
                List<PeopleCharacter> peopleCharacters=new ArrayList<PeopleCharacter>();
                PeopleCharacter peopleCharacter1=getMyApplication().getPeopleCharacter();
                Log.d("PeopleCharacter",peopleCharacter1.toString());
                peopleCharacters.add(peopleCharacter1);
                peopleCharacterHelper.addPeopleCharacter(peopleCharacters);
                //插入peopleInfoAll
                String User_Name=getMyApplication().getUser_Name();
                String G_Name=finish.getText().toString();
                int P_Id=peopleInfoHelper.query_PID();
                int W_Id=peopleWorkHelper.query_WID();
                int H_Id=peopleHobbyHelper.query_HID();
                int C_Id=peopleCharacterHelper.query_CID();
                PeopleInfoAllHelper peopleInfoAllHelper=new PeopleInfoAllHelper(context);
                PeopleInfoAll peopleInfoAll=new PeopleInfoAll(User_Name,G_Name,P_Id,W_Id,H_Id,C_Id);
                List<PeopleInfoAll> peopleInfoAlls=new ArrayList<PeopleInfoAll>();
                peopleInfoAlls.add(peopleInfoAll);
                peopleInfoAllHelper.addPeopeInfoAll(peopleInfoAlls);
                Log.d("插入PeopleInfoAll",peopleInfoAll.toString());
                dismissProgressDialog();
            }
        }).start();

    }

    public MyApplication getMyApplication(){
        return (MyApplication) getApplicationContext();
    }
    private static final int REQUEST_TO_BOTTOM_MENU = 46;

    private static final String[] groups = {"朋友", "同学", "同事","家人","领导","其他"};
    private static final int REQUEST_TO_EDIT_TEXT_INFO = 47;
    private static final int REQUEST_TO_EDIT_TEXT_INFO_zidingyi = 48;
    @Override
    public void onTabActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_TO_BOTTOM_MENU:
                if (data != null) {
                    int selectedPosition = data.getIntExtra(BottomMenuWindow.RESULT_ITEM_ID, -1);
                    if (selectedPosition >= 0 && selectedPosition < groups.length) {
                        if(selectedPosition==groups.length-1){
                            editName(true);
                        }else{
                            finish.setText(groups[selectedPosition]);
                            //插入数据
                            insertCharac();
                        }
                    }
                }
                break;
            case REQUEST_TO_EDIT_TEXT_INFO_zidingyi:
                if (data != null) {

                    checkBoxes[10].setText(StringUtil.getTrimedString(
                            data.getStringExtra(EditTextInfoWindow.RESULT_VALUE)));
                    //插入数据
                    insertCharac();
                }
                break;
            case REQUEST_TO_EDIT_TEXT_INFO:
                if (data != null) {

                    finish.setText(StringUtil.getTrimedString(
                            data.getStringExtra(EditTextInfoWindow.RESULT_VALUE)));
                    //插入数据
                    insertCharac();
                }
                break;
            default:
                break;
        }

    }
    private void editName(boolean toWindow) {
        if (toWindow) {
            intent = EditTextInfoWindow.createIntent(context, EditTextInfoWindow.TYPE_NICK
                    , "设置分组", StringUtil.getTrimedString(finish), getPackageName());
        } else {
            intent = EditTextInfoActivity.createIntent(context, EditTextInfoActivity.TYPE_NICK
                    , "设置分组", StringUtil.getTrimedString(finish));
        }

        getParent().startActivityForResult(intent, REQUEST_TO_EDIT_TEXT_INFO);
    }
}
