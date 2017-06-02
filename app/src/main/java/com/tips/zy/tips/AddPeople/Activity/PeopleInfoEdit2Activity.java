package com.tips.zy.tips.AddPeople.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tips.zy.tips.AddPeople.DBHelper.PeopleInfoHelper;
import com.tips.zy.tips.AddPeople.Entity.PeopleInfo;
import com.tips.zy.tips.Application.MyApplication;
import com.tips.zy.tips.R;

import java.util.ArrayList;
import java.util.List;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.ui.BottomMenuWindow;
import zuo.biao.library.ui.CutPictureActivity;
import zuo.biao.library.ui.DatePickerWindow;
import zuo.biao.library.ui.PlacePickerWindow;
import zuo.biao.library.ui.SelectPictureActivity;
import zuo.biao.library.util.DataKeeper;
import zuo.biao.library.util.ImageLoaderUtil;
import zuo.biao.library.util.StringUtil;
import zuo.biao.library.util.TimeUtil;

/**
 * Created by zy on 2017/4/2.
 */

public class PeopleInfoEdit2Activity extends BaseActivity implements View.OnClickListener,OnTabActivityResultListener{
    public static final String TAG="PeopleInfoActivity";
    //private Button button;
//    private PeopleInfoHelper addPeopleHelper;
//    PeopleInfo peopleInfos=new PeopleInfo();
//    ContentValues value;
//    Cursor curcor;

    private ImageView P_Icon;
    private EditText P_Name;
    private RadioGroup P_Gender_Group;
    private RadioButton P_Gender_button;
    private EditText P_Phone;
    private EditText P_Mail;
    private TextView birth_Text;
    private TextView address_Text;
    private Button P_Nation;
    private RadioGroup P_Faith_Group;
    private RadioButton P_Faith_button;
    private TextView degree_Text;
    private EditText mark_text;
    private String P_Icon_name="R.mipmap.avatar_default";
    private String P_Name_name,P_Gender_name
            ,P_Phone_name,P_Mail_name,P_birth_name,P_address_name,
            P_Nation_name,P_Faith_name,P_degree_name,P_mark_name;
    private LinearLayout birth;
    private LinearLayout degree;
    private LinearLayout address;
    private Button next;

    private int info_id;

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
        return new Intent(contxt,PeopleInfoEdit2Activity.class);
    }

    @Override
    public void initView() {
        P_Icon= (ImageView) findViewById(R.id.P_Icon);
        P_Name= (EditText) findViewById(R.id.P_Name);
        P_Gender_Group= (RadioGroup) findViewById(R.id.P_Gender_Group);
        P_Gender_button= (RadioButton) findViewById(P_Gender_Group.getCheckedRadioButtonId());
        P_Phone= (EditText) findViewById(R.id.P_Phone);
        P_Mail= (EditText) findViewById(R.id.P_Mail);
        birth_Text= (TextView) findViewById(R.id.birth_Text);
        address_Text= (TextView) findViewById(R.id.address_Text);
        P_Nation= (Button) findViewById(R.id.P_National);
        P_Faith_Group= (RadioGroup) findViewById(R.id.P_Faith_Group);
        P_Faith_button= (RadioButton) findViewById(P_Faith_Group.getCheckedRadioButtonId());
        degree_Text= (TextView) findViewById(R.id.degree_Text);
        mark_text= (EditText) findViewById(R.id.mark_text);

        birth= (LinearLayout) findViewById(R.id.birth);
        address= (LinearLayout) findViewById(R.id.address);
        degree= (LinearLayout) findViewById(R.id.degree);
        next= (Button) findViewById(R.id.next);
    }

    @Override
    public void initData() {
        Intent intent=getIntent();//getIntent将该项目中包含的原始intent检索出来，将检索出来的intent赋值给一个Intent类型的变量intent
        Bundle bundle=intent.getExtras();//.getExtras()得到intent所附带的额外数据
        final int P_Id=bundle.getInt("P_Id");//getString()返回指定key的值
        Log.d("P_ID",P_Id+"");
        new Thread(new Runnable() {
            @Override
            public void run() {
                PeopleInfoHelper peopleInfoHelper=new PeopleInfoHelper(context);
                PeopleInfo peopleInfo=peopleInfoHelper.queryById(P_Id);
                //P_Icon.setImageResource(R.mipmap.icon1);
                info_id=P_Id;
                picturePath=peopleInfo.getP_Icon();
                ImageLoaderUtil.loadImage(P_Icon,peopleInfo.getP_Icon());
                P_Name.setText(peopleInfo.getP_Name());
                P_Phone.setText(peopleInfo.getP_Phone());
                P_Mail.setText(peopleInfo.getP_Mail());
                birth_Text.setText(peopleInfo.getP_BirthDay());
                address_Text.setText(peopleInfo.getP_Address());
                P_Nation.setText(peopleInfo.getP_National());
                degree_Text.setText(peopleInfo.getP_Degree());
                mark_text.setText(peopleInfo.getP_Remark());
            }
        }).start();

    }

    /*private void insert() {
        showProgressDialog("inserting ...");
        runThread(TAG + "insert", new Runnable() {
            @Override
            public void run() {

                ContentValues values=new ContentValues();
                values.put(PeopleInfoHelper.P_Name,"我爱Android");
                addPeopleHelper.insert(values);

            }
        });
        runUiThread(new Runnable() {
            @Override
            public void run() {
                runThread(TAG + "query", new Runnable() {
                    @Override
                    public void run() {
                        curcor=addPeopleHelper.query(PeopleInfoHelper.P_Name,"我爱Android");
                        runUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(curcor.moveToFirst()){
                                    //button.setText(curcor.getString(curcor.getColumnIndex(PeopleInfoHelper.P_Name)));
                                    dismissProgressDialog();
                                }
                            }
                        });

                    }
                });
            }
        });
    }*/

    @Override
    public void initEvent() {
        P_Icon.setOnClickListener(this);
        P_Nation.setOnClickListener(this);
        birth.setOnClickListener(this);
        address.setOnClickListener(this);
        degree.setOnClickListener(this);
        next.setOnClickListener(this);
        P_Icon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id){
            case R.id.P_Icon:{
                //点击图片更换头像
                selectPicture();
                break;
            }
            case R.id.P_National:{
                break;
            }
            case R.id.next:{

                P_Name_name=String.valueOf(P_Name.getText());
                P_Gender_name=String.valueOf(P_Gender_button.getText());
                P_Phone_name=String.valueOf(P_Phone.getText());
                P_Mail_name=String.valueOf(P_Mail.getText());
                P_birth_name=String.valueOf(birth_Text.getText());
                P_address_name=String.valueOf(address_Text.getText());
                P_Nation_name=String.valueOf(P_Nation.getText());
                P_Faith_name=String.valueOf(P_Faith_button.getText().toString());
                P_degree_name=String.valueOf(degree_Text.getText());
                P_mark_name=String.valueOf(mark_text.getEditableText());

                showProgressDialog("数据保存中。。。");
                runThread(TAG + "inserting...", new Runnable() {
                    @Override
                    public void run() {
                        PeopleInfo peopleInfo=getMyApplication().getPeopleInfo();
                        peopleInfo.setP_Id(info_id);
                        peopleInfo.setP_Icon(picturePath);
                        peopleInfo.setP_Name(P_Name_name);
                        peopleInfo.setP_Gender(P_Gender_name);
                        peopleInfo.setP_Phone(P_Phone_name);
                        peopleInfo.setP_Mail(P_Mail_name);
                        peopleInfo.setP_BirthDay(P_birth_name);
                        peopleInfo.setP_Address(P_address_name);
                        peopleInfo.setP_National(P_Nation_name);
                        peopleInfo.setP_Religion(P_Faith_name);
                        peopleInfo.setP_Degree(P_degree_name);
                        peopleInfo.setP_Remark(P_mark_name);
                        runUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dismissProgressDialog();
                            }
                        });
                        Log.d("getPeopleInfo()",getMyApplication().getPeopleInfo().toString());
                        //插入peopleInfo
                        PeopleInfoHelper peopleInfoHelper=new PeopleInfoHelper(context);
                        peopleInfo=getMyApplication().getPeopleInfo();

                        peopleInfoHelper.updatePeopleInfo(peopleInfo);
                        zuo.biao.library.util.Log.d("更新peopleInfo",peopleInfo.toString());
                        finish();
                    }
                });

                break;
            }
            case R.id.birth:{
                getParent().startActivityForResult(DatePickerWindow.createIntent(context.getParent(), new int[]{1971, 0, 1}
                        , TimeUtil.getDateDetail(System.currentTimeMillis())), REQUEST_TO_DATE_PICKER);
                break;
            }
            case R.id.address:{
                getParent().startActivityForResult(PlacePickerWindow.createIntent(context.getParent(), getPackageName(), 2), REQUEST_TO_PLACE_PICKER);
                break;
            }
            case R.id.degree:{
                getParent().startActivityForResult(BottomMenuWindow.createIntent(context.getParent(), DegreeString)
                        .putExtra(BottomMenuWindow.INTENT_TITLE, "选择学历"), REQUEST_TO_BOTTOM_MENU);
                break;
            }
        }
    }

    private String picturePath;
    /**选择图片
     */
    private void selectPicture() {
        getParent().startActivityForResult(SelectPictureActivity.createIntent(context.getParent()), REQUEST_TO_SELECT_PICTURE);
    }

    /**裁剪图片
     * @param path
     */
    private void cutPicture(String path) {
        if (StringUtil.isFilePath(path) == false) {
            Log.e(TAG, "cutPicture  StringUtil.isFilePath(path) == false >> showShortToast(找不到图片);return;");
            showShortToast("找不到图片");
            return;
        }
        this.picturePath = path;
        //ImageLoaderUtil.loadImage(mHeadImg, path);
        Log.d("picturePath",picturePath);
        getParent().startActivityForResult(CutPictureActivity.createIntent(context.getParent(), path
                , DataKeeper.fileRootPath + DataKeeper.imagePath, "photo" + System.currentTimeMillis(), 200)
                , REQUEST_TO_CUT_PICTURE);
    }

    /**显示图片
     * @param path
     */
    private void setPicture(String path) {
        if (StringUtil.isFilePath(path) == false) {
            Log.e(TAG, "setPicture  StringUtil.isFilePath(path) == false >> showShortToast(找不到图片);return;");
            showShortToast("找不到图片");
            return;
        }
        Log.d("path",path);
        this.picturePath = path;
        ImageLoaderUtil.loadImage(P_Icon, path);
        P_Icon_name=path;
    }
    public MyApplication getMyApplication(){
        return (MyApplication) getApplicationContext();
    }
    private int[] selectedDate = new int[]{1971, 0, 1};
    private static final int REQUEST_TO_BOTTOM_MENU = 31;
    private static final int REQUEST_TO_PLACE_PICKER = 32;
    private static final int REQUEST_TO_DATE_PICKER = 33;
    private static final int REQUEST_TO_SELECT_PICTURE=20;
    private static final int REQUEST_TO_CUT_PICTURE=21;
    private static final String[] DegreeString = {"博士", "硕士", "本科","高中","高中以下"};

    @Override
    public void onTabActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case REQUEST_TO_SELECT_PICTURE:
                if (data != null) {
                    cutPicture(data.getStringExtra(SelectPictureActivity.RESULT_PICTURE_PATH));
                }
                break;
            case REQUEST_TO_CUT_PICTURE:
                if (data != null) {
                    Log.d("setPicture",data.getStringExtra(CutPictureActivity.RESULT_PICTURE_PATH));
                    setPicture(data.getStringExtra(CutPictureActivity.RESULT_PICTURE_PATH));
                    Log.d("setPicture",data.getStringExtra(CutPictureActivity.RESULT_PICTURE_PATH));
                }
                break;
            case REQUEST_TO_PLACE_PICKER:

                if (data != null) {
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
                    Log.d("dadaisnotnull", "数据不为空");
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
                    Log.d("dadaisnotnull", "数据不为空");
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
