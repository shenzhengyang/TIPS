package com.tips.zy.tips.AddPeople.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.tips.zy.tips.R;

import java.util.ArrayList;
import java.util.List;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.ui.BottomMenuWindow;
import zuo.biao.library.ui.EditTextInfoActivity;
import zuo.biao.library.ui.EditTextInfoWindow;
import zuo.biao.library.util.Log;
import zuo.biao.library.util.StringUtil;

/**
 * Created by zy on 2017/4/2.
 */

public class PeopleCharacterEditActivity extends BaseActivity implements View.OnClickListener{
    public static final String TAG="PeopleCharacterActivity";
    private Button finish;
    private EditText mark_text;
    private CheckBox[] checkBoxes=new CheckBox[11];
    private String mark_name;
    private String charac_name;

    int charactor_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peoplecharacter);
        initView();
        initData();
        initEvent();
    }
    public static Intent CreateIntent(Context context){
        return new Intent(context,PeopleCharacterEditActivity.class);
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
        Intent intent=getIntent();//getIntent将该项目中包含的原始intent检索出来，将检索出来的intent赋值给一个Intent类型的变量intent
        Bundle bundle=intent.getExtras();//.getExtras()得到intent所附带的额外数据
        final int C_Id=bundle.getInt("C_Id");//getString()返回指定key的值
        Log.d("W_ID",C_Id+"");
        charactor_id=C_Id;
        new Thread(new Runnable() {
            @Override
            public void run() {
                PeopleCharacterHelper peopleCharacterHelper=new PeopleCharacterHelper(context);
                PeopleCharacter peopleCharacter=peopleCharacterHelper.queryById(C_Id);
                String []str_cha=peopleCharacter.getC_Character().split("\\&");
                for(int i=0;i<str_cha.length;i++){
                    for(int j=0;j<11;j++){
                        if(checkBoxes[j].getText().toString().equals(str_cha[i])){
                            checkBoxes[j].setChecked(true);
                        }else{
                            checkBoxes[10].setChecked(true);
                            checkBoxes[10].setText(str_cha[i]);
                        }
                    }
                }
                mark_text.setText(peopleCharacter.getC_Remark());
            }
        }).start();
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
                startActivityForResult(intent, REQUEST_TO_EDIT_TEXT_INFO_zidingyi);
                break;
            }
            case R.id.next:{
                insertCharac();
                finish();
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
        peopleCharacter.setC_Id(charactor_id);
        peopleCharacter.setC_Remark(mark_name);
        peopleCharacter.setC_Character(charac_name);
        Log.d("PeopleCharacter",getMyApplication().getPeopleCharacter().toString());
        charac_name="";
        showProgressDialog("插入中");
        new Thread(new Runnable() {

            @Override
            public void run() {
                //插入charactor
                PeopleCharacterHelper peopleCharacterHelper=new PeopleCharacterHelper(context);
                PeopleCharacter peopleCharacter1=getMyApplication().getPeopleCharacter();
                peopleCharacterHelper.updatePeopleCharacter(peopleCharacter);
                Log.d("PeopleCharacter","更新"+peopleCharacter1.toString());
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
