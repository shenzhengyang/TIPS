package com.tips.zy.tips.AddPeople.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tips.zy.tips.AddPeople.DBHelper.PeopleInfoHelper;
import com.tips.zy.tips.AddPeople.DBHelper.PeopleWorkHelper;
import com.tips.zy.tips.AddPeople.Entity.PeopleInfo;
import com.tips.zy.tips.AddPeople.Entity.PeopleWork;
import com.tips.zy.tips.Application.MyApplication;
import com.tips.zy.tips.R;

import java.util.ArrayList;
import java.util.List;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.ui.BottomMenuWindow;
import zuo.biao.library.ui.PlacePickerWindow;
import zuo.biao.library.util.StringUtil;

/**
 * Created by zy on 2017/4/2.
 */

public class PeopleWorkEditActivity extends BaseActivity implements View.OnClickListener{
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

    int work_id;
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
        return new Intent(context, PeopleWorkEditActivity.class);
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
        Intent intent=getIntent();//getIntent将该项目中包含的原始intent检索出来，将检索出来的intent赋值给一个Intent类型的变量intent
        Bundle bundle=intent.getExtras();//.getExtras()得到intent所附带的额外数据
        final int W_Id=bundle.getInt("W_Id");//getString()返回指定key的值
        Log.d("W_ID",W_Id+"");
        work_id=W_Id;
        new Thread(new Runnable() {
            @Override
            public void run() {
                PeopleWorkHelper peopleWorkHelper=new PeopleWorkHelper(context);
                PeopleWork peopleWork=peopleWorkHelper.queryById(W_Id);
                company_text.setText(peopleWork.getW_Company());
                position_text.setText(peopleWork.getW_Position());
                mark_text.setText(peopleWork.getW_Remark());
                company_addressText.setText(peopleWork.getW_Address());
            }
        }).start();
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
                startActivityForResult(BottomMenuWindow.createIntent(context, position)
                        .putExtra(BottomMenuWindow.INTENT_TITLE, "选择职位"), REQUEST_TO_BOTTOM_MENU);
                break;
            }
            case R.id.company_address:{
                startActivityForResult(PlacePickerWindow.createIntent(context, getPackageName(), 2), REQUEST_TO_PLACE_PICKER);
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
                peopleWork.setW_id(work_id);
                peopleWork.setW_Company(company_name);
                peopleWork.setW_Position(position_name);
                peopleWork.setW_Address(address_name);
                peopleWork.setW_Remark(mark_name);
                Log.d("peopleWork",getMyApplication().getPeopleWork().toString());
                //插入peopleWork
                PeopleWorkHelper peopleWorkHelper=new PeopleWorkHelper(context);
                peopleWork=getMyApplication().getPeopleWork();
                peopleWorkHelper.updatePeopleWork(peopleWork);
                zuo.biao.library.util.Log.d("更新peopleWork",peopleWork.toString());
                dismissProgressDialog();
                finish();

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
