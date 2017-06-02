package com.tips.zy.tips.Other.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tips.zy.tips.R;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.util.StringUtil;

/**
 * Created by zy on 2017/4/2.
 */

public class BackActivity extends BaseActivity implements View.OnClickListener
{
    private TextView title_name;
    private TextView title_right;

    private EditText add_content;
    private EditText connection;
    private Button put;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.back);
        initView();
        initData();
        initEvent();
    }
    public static Intent CreateIntent(Context context){
        return new Intent(context,BackActivity.class);
    }
    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void initView() {
        title_name= (TextView) findViewById(R.id.titlename);
        title_name.setText("帮助与反馈");
        title_right= (TextView) findViewById(R.id.titleright);
        title_right.setText("");

        add_content= (EditText) findViewById(R.id.add_content);
        connection= (EditText) findViewById(R.id.connection);
        put= (Button) findViewById(R.id.put);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        put.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id){
            case R.id.put:{
                if(!StringUtil.isNotEmpty(add_content.getText().toString(),true)){
                    Toast.makeText(context,"提交内容为空",Toast.LENGTH_SHORT).show();
                }else{
                    showProgressDialog("内容提交中。。。");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                                dismissProgressDialog();
                                runUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(context,"感谢您的反馈",Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                break;
            }
        }
    }
}
