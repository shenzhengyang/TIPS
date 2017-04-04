package com.tips.zy.tips.Login.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.tips.zy.tips.Const.Const;
import com.tips.zy.tips.Login.View.PullScrollView;
import com.tips.zy.tips.Main.Activity.MainActivity;
import com.tips.zy.tips.Other.Activity.BackActivity;
import com.tips.zy.tips.R;

import java.util.ArrayList;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.ui.BottomMenuWindow;
import zuo.biao.library.ui.CutPictureActivity;
import zuo.biao.library.ui.DatePickerWindow;
import zuo.biao.library.ui.EditTextInfoActivity;
import zuo.biao.library.ui.EditTextInfoWindow;
import zuo.biao.library.ui.PlacePickerWindow;
import zuo.biao.library.ui.SelectPictureActivity;
import zuo.biao.library.ui.TimePickerWindow;
import zuo.biao.library.ui.TopMenuWindow;
import zuo.biao.library.ui.WebViewActivity;
import zuo.biao.library.util.CommonUtil;
import zuo.biao.library.util.DataKeeper;
import zuo.biao.library.util.ImageLoaderUtil;
import zuo.biao.library.util.StringUtil;


/**
 * Pull down ScrollView demo.
 *
 * @author markmjw
 * @date 2014-04-30
 */
public class UserActivity extends BaseActivity implements PullScrollView.OnTurnListener,View.OnClickListener {
    private PullScrollView mScrollView;
    private ImageView mHeadImg;


    private ImageView userIcon;
    private LinearLayout lin_userProtect;
    private LinearLayout lin_changePass;
    private LinearLayout lin_helpAndBack;
    private LinearLayout lin_share;
    private LinearLayout lin_aboutUs;
    private LinearLayout lin_updateVersion;

    private Button loginOut;
    private TextView protect_Phone;

    public static final String TAG="UserActivity";
    @Override
    public Activity getActivity() {
        return this;
    }

    public static Intent CreateIntent(Context context){
        return new Intent(context,UserActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);

        initView();
        initEvent();
    }

    public void initView() {
        mScrollView = (PullScrollView) findViewById(R.id.scroll_view);
        mHeadImg = (ImageView) findViewById(R.id.background_img);

        mScrollView.setHeader(mHeadImg);
        mScrollView.setOnTurnListener(this);

        userIcon= (ImageView) findViewById(R.id.user_avatar);
        lin_userProtect= (LinearLayout) findViewById(R.id.lin_userprotect);
        lin_changePass= (LinearLayout) findViewById(R.id.lin_changePass);
        lin_helpAndBack= (LinearLayout) findViewById(R.id.helpAndBack);
        lin_share= (LinearLayout) findViewById(R.id.share);
        lin_aboutUs= (LinearLayout) findViewById(R.id.aboutUs);
        lin_updateVersion= (LinearLayout) findViewById(R.id.updateVersion);
        loginOut= (Button) findViewById(R.id.loginOut);
        protect_Phone= (TextView) findViewById(R.id.protect_Phone);

        Log.d("initView","innitView");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        userIcon.setOnClickListener(this);
        lin_userProtect.setOnClickListener(this);
        lin_changePass.setOnClickListener(this);
        lin_helpAndBack.setOnClickListener(this);
        lin_share.setOnClickListener(this);
        lin_aboutUs.setOnClickListener(this);
        lin_updateVersion.setOnClickListener(this);
        loginOut.setOnClickListener(this);
        Log.d("initView","innitEvent");
    }



    @Override
    public void onTurn() {

    }


    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id){
            case R.id.user_avatar:{
                selectPicture();
                break;
            }
            case R.id.lin_userprotect:{
                editName(false);
                break;
            }
            case R.id.lin_changePass:{
                startActivity(FoundPassActivity.CreateIntent(context));
                break;
            }
            case R.id.helpAndBack:{
                startActivity(BackActivity.CreateIntent(context));
                break;
            }
            case R.id.share:{
                CommonUtil.shareInfo(context, getString(R.string.share_app) + "\n 点击链接直接查看TIPS\n" + Const.DOWNLOADAPP);
                break;
            }
            case R.id.aboutUs:{
                toActivity(WebViewActivity.createIntent(context, "产品介绍",Const.PRODUCTION));
                break;
            }
            case R.id.updateVersion:{
                Toast.makeText(context,"已经为最新版本",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.loginOut:{
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("真的离开了么~");
                builder.setMessage("点击确认按钮退出登录");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.show();
                break;
            }
        }
    }

    private String picturePath;
    /**选择图片
     */
    private void selectPicture() {
        toActivity(SelectPictureActivity.createIntent(context), REQUEST_TO_SELECT_PICTURE, false);
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

        toActivity(CutPictureActivity.createIntent(context, path
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
        this.picturePath = path;
        mScrollView.smoothScrollTo(0, 0);
        ImageLoaderUtil.loadImage(userIcon, path);
    }

    /**编辑图片名称
     */
    private void editName(boolean toWindow) {
        if (toWindow) {
            intent = EditTextInfoWindow.createIntent(context, EditTextInfoWindow.TYPE_NICK
                    , "电话号码", StringUtil.getTrimedString(protect_Phone), getPackageName());
        } else {
            intent = EditTextInfoActivity.createIntent(context, EditTextInfoActivity.TYPE_NICK
                    , "电话号码", StringUtil.getTrimedString(protect_Phone));
        }

        toActivity(intent, REQUEST_TO_EDIT_TEXT_INFO, ! toWindow);
    }
    private int[] selectedDate = new int[]{1971, 0, 1};
    private int[] selectedTime = new int[]{12, 0, 0};
    private static final int REQUEST_TO_SELECT_PICTURE = 20;
    private static final int REQUEST_TO_CUT_PICTURE = 21;
    private static final int REQUEST_TO_EDIT_TEXT_INFO = 23;
    private static final int REQUEST_TO_BOTTOM_MENU = 31;
    private static final int REQUEST_TO_PLACE_PICKER = 32;
    private static final int REQUEST_TO_DATE_PICKER = 33;
    private static final int REQUEST_TO_TIME_PICKER = 34;

    private static final String[] TOPBAR_COLOR_NAMES = {"灰色", "蓝色", "黄色"};

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
                    setPicture(data.getStringExtra(CutPictureActivity.RESULT_PICTURE_PATH));
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
                    }
                }
                break;
            case REQUEST_TO_DATE_PICKER:
                if (data != null) {
                    ArrayList<Integer> list = data.getIntegerArrayListExtra(DatePickerWindow.RESULT_DATE_DETAIL_LIST);
                    if (list != null && list.size() >= 3) {

                        selectedDate = new int[list.size()];
                        for (int i = 0; i < list.size(); i++) {
                            selectedDate[i] = list.get(i);
                        }

                        showShortToast("选择的日期为" + selectedDate[0] + "-" + (selectedDate[1] + 1) + "-" + selectedDate[2]);
                    }
                }
                break;
            case REQUEST_TO_TIME_PICKER:
                if (data != null) {
                    ArrayList<Integer> list = data.getIntegerArrayListExtra(TimePickerWindow.RESULT_TIME_DETAIL_LIST);
                    if (list != null && list.size() >= 2) {

                        selectedTime = new int[list.size()];
                        for (int i = 0; i < list.size(); i++) {
                            selectedTime[i] = list.get(i);
                        }

                        String minute = "" + selectedTime[1];
                        if (minute.length() < 2) {
                            minute = "0" + minute;
                        }
                        showShortToast("选择的时间为" + selectedTime[0] + ":" + minute);
                    }
                }
                break;
            case REQUEST_TO_EDIT_TEXT_INFO:
                if (data != null) {
                    mScrollView.smoothScrollTo(0, 0);
                    protect_Phone.setText(StringUtil.getTrimedString(
                            data.getStringExtra(EditTextInfoWindow.RESULT_VALUE)));
                }
                break;
            default:
                break;
        }

    }
}
