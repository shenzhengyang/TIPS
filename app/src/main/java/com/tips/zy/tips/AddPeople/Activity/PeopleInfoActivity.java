package com.tips.zy.tips.AddPeople.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tips.zy.tips.AddPeople.DBHelper.AddPeopleHelper;
import com.tips.zy.tips.AddPeople.Entity.PeopleInfo;
import com.tips.zy.tips.R;

import java.util.ArrayList;
import java.util.List;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.ui.BottomMenuWindow;
import zuo.biao.library.ui.CutPictureActivity;
import zuo.biao.library.ui.DatePickerWindow;
import zuo.biao.library.ui.EditTextInfoWindow;
import zuo.biao.library.ui.PlacePickerWindow;
import zuo.biao.library.ui.SelectPictureActivity;
import zuo.biao.library.ui.TimePickerWindow;
import zuo.biao.library.util.StringUtil;
import zuo.biao.library.util.TimeUtil;

/**
 * Created by zy on 2017/4/2.
 */

public class PeopleInfoActivity extends BaseActivity implements View.OnClickListener{
    public static final String TAG="PeopleInfoActivity";
    //private Button button;
    private AddPeopleHelper addPeopleHelper;
    PeopleInfo peopleInfos=new PeopleInfo();
    ContentValues value;
    Cursor curcor;

    private LinearLayout birth;
    private LinearLayout degree;
    private LinearLayout address;
    private TextView birth_Text;
    private TextView degree_Text;
    private TextView address_Text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
//取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.peopleinfo);

        initView();
        initData();
        initEvent();
    }

    @Override
    public Activity getActivity() {
        return this;
    }
    public static Intent CreateIntent(Context contxt){
        return new Intent(contxt,PeopleInfoActivity.class);
    }

    @Override
    public void initView() {
        birth= (LinearLayout) findViewById(R.id.birth);
        address= (LinearLayout) findViewById(R.id.address);
        degree= (LinearLayout) findViewById(R.id.degree);
        birth_Text= (TextView) findViewById(R.id.birth_Text);
        address_Text= (TextView) findViewById(R.id.address_Text);
        degree_Text= (TextView) findViewById(R.id.degree_Text);
    }

    @Override
    public void initData() {
        addPeopleHelper=new AddPeopleHelper(context);
        for(int i=1;i<6;i++){
            insert();
        }

    }

    private void insert() {
        showProgressDialog("inserting ...");
        runThread(TAG + "insert", new Runnable() {
            @Override
            public void run() {

                ContentValues values=new ContentValues();
                values.put(AddPeopleHelper.P_Name,"我爱Android");
                addPeopleHelper.insert(values);

            }
        });
        runUiThread(new Runnable() {
            @Override
            public void run() {
                runThread(TAG + "query", new Runnable() {
                    @Override
                    public void run() {
                        curcor=addPeopleHelper.query(AddPeopleHelper.P_Name,"我爱Android");
                        runUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(curcor.moveToFirst()){
                                    //button.setText(curcor.getString(curcor.getColumnIndex(AddPeopleHelper.P_Name)));
                                    dismissProgressDialog();
                                }
                            }
                        });

                    }
                });
            }
        });
    }

    @Override
    public void initEvent() {
        birth.setOnClickListener(this);
        address.setOnClickListener(this);
        degree.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id){
            case R.id.birth:{
                toActivity(DatePickerWindow.createIntent(context, new int[]{1971, 0, 1}
                        , TimeUtil.getDateDetail(System.currentTimeMillis())), REQUEST_TO_DATE_PICKER, false);
                break;
            }
            case R.id.address:{
                toActivity(PlacePickerWindow.createIntent(context, getPackageName(), 2), REQUEST_TO_PLACE_PICKER, false);
                break;
            }
            case R.id.degree:{
                toActivity(BottomMenuWindow.createIntent(context, DegreeString)
                        .putExtra(BottomMenuWindow.INTENT_TITLE, "选择学历"), REQUEST_TO_BOTTOM_MENU, false);
                break;
            }
        }
    }

    private int[] selectedDate = new int[]{1971, 0, 1};
    private static final int REQUEST_TO_BOTTOM_MENU = 31;
    private static final int REQUEST_TO_PLACE_PICKER = 32;
    private static final int REQUEST_TO_DATE_PICKER = 33;

    private static final String[] DegreeString = {"博士", "硕士", "本科","高中","高中以下"};

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
                        address_Text.setText(place);
                    }
                }
                break;
            case REQUEST_TO_DATE_PICKER:
                if (data != null) {
                    Log.d("dadaisnotnull","数据不为空");
                    ArrayList<Integer> list = data.getIntegerArrayListExtra(DatePickerWindow.RESULT_DATE_DETAIL_LIST);
                    if (list != null && list.size() >= 3) {

                        selectedDate = new int[list.size()];
                        for (int i = 0; i < list.size(); i++) {
                            selectedDate[i] = list.get(i);
                        }

                        showShortToast("选择的日期为" + selectedDate[0] + "-" + (selectedDate[1] + 1) + "-" + selectedDate[2]);
                        birth_Text.setText(selectedDate[0] + "-" + (selectedDate[1] + 1) + "-" + selectedDate[2]);
                    }
                }
                break;
            case REQUEST_TO_BOTTOM_MENU:
                if (data != null) {
                    Log.d("dadaisnotnull","数据不为空");
                    int selectedPosition = data.getIntExtra(BottomMenuWindow.RESULT_ITEM_ID, -1);
                    if (selectedPosition >= 0 && selectedPosition < DegreeString.length) {
                        degree_Text.setText(DegreeString[selectedPosition]);
                    }
                }
                break;
            default:
                break;
        }

    }
}
