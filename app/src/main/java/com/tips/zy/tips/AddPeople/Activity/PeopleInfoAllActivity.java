package com.tips.zy.tips.AddPeople.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

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
import com.tips.zy.tips.Login.View.PullScrollView;
import com.tips.zy.tips.Main.Activity.MainActivity;
import com.tips.zy.tips.Main.Entity.Group;
import com.tips.zy.tips.Main.Entity.People;
import com.tips.zy.tips.Main.Entity.PeopleAll;
import com.tips.zy.tips.Main.Entity.PeopleGroupAll;
import com.tips.zy.tips.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.util.ImageLoaderUtil;

public class PeopleInfoAllActivity extends BaseActivity implements PullScrollView.OnTurnListener ,View.OnClickListener{
    private PullScrollView mScrollView;
    private LinearLayout mHeadImg;

    private TableLayout mMainLayout;
    int P_Id;

    private ImageView iP_Icon;
    private TextView iP_Name;
    private TextView iP_Info;
    private TextView iP_Work;
    private TextView iP_Hobby;
    private TextView iP_Charactor;

    private ImageView Edit_info;
    private ImageView Edit_work;
    private ImageView Edit_Hobby;
    private ImageView Edit_Charactor;

    PeopleInfo peopleInfo;
    PeopleWork peopleWork;
    PeopleHobby peopleHobby;
    PeopleCharacter peopleCharacter;

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

        iP_Icon= (ImageView) findViewById(R.id.iP_Icon);
        iP_Name= (TextView) findViewById(R.id.iP_Name);
        iP_Info= (TextView) findViewById(R.id.iP_Info);
        iP_Work= (TextView) findViewById(R.id.iP_Work);
        iP_Hobby= (TextView) findViewById(R.id.iP_Hobby);
        iP_Charactor= (TextView) findViewById(R.id.iP_Charactor);

        Edit_info= (ImageView) findViewById(R.id.Edit_info);
        Edit_work= (ImageView) findViewById(R.id.Edit_work);
        Edit_Hobby= (ImageView) findViewById(R.id.Edit_hobbys);
        Edit_Charactor= (ImageView) findViewById(R.id.Edit_charactor);
    }

    @Override
    public void initData() {
        Intent intent=getIntent();//getIntent将该项目中包含的原始intent检索出来，将检索出来的intent赋值给一个Intent类型的变量intent
        Bundle bundle=intent.getExtras();//.getExtras()得到intent所附带的额外数据
        P_Id=bundle.getInt("P_Id");//getString()返回指定key的值
        Log.d("BundleP_Id",P_Id+"");
        showProgressDialog("数据加载中。。。");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queryByP_ID(P_Id);
            }
        }).start();
    }

    @Override
    public void initEvent() {
        Edit_info.setOnClickListener(this);
        Edit_work.setOnClickListener(this);
        Edit_Hobby.setOnClickListener(this);
        Edit_Charactor.setOnClickListener(this);
    }



    @Override
    public void onTurn() {

    }

    public void queryByP_ID(int P_Id){
        PeopleInfoAllHelper peopleInfoAllHelper=new PeopleInfoAllHelper(context);
        List<PeopleInfoAll> peopleInfoAlls=peopleInfoAllHelper.queryByP_Id(P_Id);
        PeopleInfoAll peopleInfoAll=peopleInfoAlls.get(0);
        Log.d("queryByP_ID",peopleInfoAll.toString());
        //查询peopleInfo
        PeopleInfoHelper peopleInfoHelper=new PeopleInfoHelper(context);
        peopleInfo=peopleInfoHelper.queryById(peopleInfoAll.getP_Id());
        Log.d("queryByP_ID",peopleInfo.toString());
        /*iP_Icon.setImageResource(Integer.valueOf(peopleInfo.getP_Icon()));
        iP_Name.setText(peopleInfo.getP_Name());
        iP_Info.setText(peopleInfo.toString());*/
        //查询peopleWork
        PeopleWorkHelper peopleWorkHelper=new PeopleWorkHelper(context);
        peopleWork=peopleWorkHelper.queryById(peopleInfoAll.getW_Id());
        Log.d("queryByP_ID",peopleWork.toString());
        //iP_Work.setText(peopleWork.toString());
        //查询PeopleHobby
        PeopleHobbyHelper peopleHobbyHelper=new PeopleHobbyHelper(context);
        peopleHobby=peopleHobbyHelper.queryById(peopleInfoAll.getH_Id());
        Log.d("queryByP_ID",peopleHobby.toString());
        //iP_Hobby.setText(peopleHobby.toString());
        //查询PeopleCharactor
        PeopleCharacterHelper peopleCharacterHelper=new PeopleCharacterHelper(context);
        peopleCharacter=peopleCharacterHelper.queryById(peopleInfoAll.getC_Id());
        Log.d("queryByP_ID",peopleCharacter.toString());
        //iP_Charactor.setText(peopleCharacter.toString());
        runUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("ip_con",peopleInfo.getP_Icon());
                //iP_Icon.setImageResource(R.mipmap.icon1);
                ImageLoaderUtil.loadImage(iP_Icon,peopleInfo.getP_Icon());
                iP_Name.setText(peopleInfo.getP_Name());
                iP_Info.setText(peopleInfo.toString());
                iP_Work.setText(peopleWork.toString());
                iP_Hobby.setText(peopleHobby.toString());
                iP_Charactor.setText(peopleCharacter.toString());
            }
        });
        dismissProgressDialog();
    }
    public MyApplication getMyApplication(){
        return (MyApplication) getApplicationContext();
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id){
            case R.id.Edit_info:{
                Intent intent=PeopleInfoEdit2Activity.CreateIntent(PeopleInfoAllActivity.this);
                Log.d("intentEdit_info",peopleInfo.getP_Id()+"");
                intent.putExtra("P_Id",peopleInfo.getP_Id());

                startActivity(intent);
                finish();
                break;
            }
            case R.id.Edit_work:{
                Intent intent=PeopleWorkEditActivity.CreateIntent(context);
                intent.putExtra("W_Id",peopleWork.getW_id());
                startActivity(intent);
                break;
            }
            case R.id.Edit_hobbys:{
                Intent intent=PeopleHobbyEditActivity.CreateIntent(context);
                intent.putExtra("H_Id",peopleHobby.getH_Id());
                startActivity(intent);
                break;
            }
            case R.id.Edit_charactor:{
                Intent intent=PeopleCharacterEditActivity.CreateIntent(context);
                intent.putExtra("C_Id",peopleCharacter.getC_Id());
                startActivity(intent);
                break;
            }
        }
    }
}
