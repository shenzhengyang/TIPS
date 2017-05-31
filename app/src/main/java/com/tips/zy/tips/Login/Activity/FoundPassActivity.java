package com.tips.zy.tips.Login.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tips.zy.tips.Login.DBHelper.UserHelper;
import com.tips.zy.tips.Login.Enity.User;
import com.tips.zy.tips.R;

import java.util.Random;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.util.StringUtil;

/**
 * Created by zy on 2017/4/2.
 */

public class FoundPassActivity extends BaseActivity implements View.OnClickListener{
    private TextView title_name;
    private TextView title_right;

    private AutoCompleteTextView User_Name;
    private AutoCompleteTextView yanzhengma;
    private Button yanzhengma_Button;
    private EditText Pass_word;
    private EditText Pass_word2;
    private Button Found_Button;

    String randomNum="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foundpass);
        initView();
        initData();
        initEvent();
    }
    public static Intent CreateIntent(Context context){
        return new Intent(context,FoundPassActivity.class);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void initView() {
        title_name= (TextView) findViewById(R.id.titlename);
        title_name.setText("找回密码");
        title_right= (TextView) findViewById(R.id.titleright);
        title_right.setText("");

        User_Name= (AutoCompleteTextView) findViewById(R.id.email);
        yanzhengma= (AutoCompleteTextView) findViewById(R.id.yanzhengma);
        yanzhengma_Button= (Button) findViewById(R.id.getYanzhengma);
        Pass_word= (EditText) findViewById(R.id.password);
        Pass_word2= (EditText) findViewById(R.id.password2);
        Found_Button= (Button) findViewById(R.id.found_button);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        yanzhengma_Button.setOnClickListener(this);
        Found_Button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id){
            case R.id.getYanzhengma:{
                showProgressDialog("获取验证码..");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Random random=new Random();
                        for (int i = 0; i <6; i++) {
                            randomNum+=random.nextInt(10);
                        }
                        runUiThread(new Runnable() {
                            @Override
                            public void run() {
                                yanzhengma.setText(randomNum);
                                Toast.makeText(context,"你的验证码为："+randomNum,Toast.LENGTH_SHORT).show();
                                randomNum="";
                                dismissProgressDialog();
                            }
                        });

                    }
                }).start();
                break;
            }
            case R.id.found_button:{
                final String User_Name_text=User_Name.getText().toString();
                final String Pass_word_text=Pass_word.getText().toString();
                final String Pass_word_text2=Pass_word2.getText().toString();
                if(!StringUtil.isPhone(User_Name.getText().toString())){
                    Toast.makeText(context,"用户名输入错误！",Toast.LENGTH_SHORT).show();
                }if(!StringUtil.isNotEmpty(yanzhengma.getText(),true)){
                    Toast.makeText(context,"验证码为空！",Toast.LENGTH_SHORT).show();
                }
                if(!StringUtil.isNotEmpty(Pass_word.getText().toString(),true)){
                    Toast.makeText(context,"密码为空！",Toast.LENGTH_SHORT).show();
                }if(!StringUtil.isNotEmpty(Pass_word2.getText().toString(),true)){
                    Toast.makeText(context,"密码2为空！",Toast.LENGTH_SHORT).show();
                }if(!Pass_word_text.equals(Pass_word_text2)){
                    Toast.makeText(context,"密码不一致",Toast.LENGTH_SHORT).show();
                }
                else{
                    showProgressDialog("修改密码中。。。");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                UserHelper userHelper=new UserHelper(context);
                                if(userHelper.update(User_Name_text,Pass_word_text)){
                                    Log.d("shen1","userHelper.update(User_Name_text,Pass_word_text)");

                                    runUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            Log.d("shen2","userHelper.update(User_Name_text,Pass_word_text)");

                                            dismissProgressDialog();
                                            startActivity(LoginActivity.CreateIntent(context));
                                            finish();
                                        }
                                    });
                                }else{
                                    runUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.d("shen3","userHelper.update(User_Name_text,Pass_word_text)");

                                            dismissProgressDialog();
                                            Toast.makeText(context,"修改失败",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    dismissProgressDialog();
                                    Toast.makeText(context,"修改失败",Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception e){
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
