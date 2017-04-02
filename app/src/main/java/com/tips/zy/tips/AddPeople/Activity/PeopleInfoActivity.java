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
import android.widget.Button;
import android.widget.Toast;

import com.tips.zy.tips.AddPeople.DBHelper.AddPeopleHelper;
import com.tips.zy.tips.AddPeople.Entity.PeopleInfo;
import com.tips.zy.tips.R;

import java.util.List;

import zuo.biao.library.base.BaseActivity;

/**
 * Created by zy on 2017/4/2.
 */

public class PeopleInfoActivity extends BaseActivity {
    public static final String TAG="PeopleInfoActivity";
    //private Button button;
    private AddPeopleHelper addPeopleHelper;
    PeopleInfo peopleInfos=new PeopleInfo();
    ContentValues value;
    Cursor curcor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        //button= (Button) findViewById(R.id.dbtext);
        //button.setText("我爱Android");
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

    }

}
