package com.tips.zy.tips.Main.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tips.zy.tips.AddPeople.Activity.AddPeopleActivity;
import com.tips.zy.tips.Login.Activity.UserActivity;
import com.tips.zy.tips.Main.Adapter.PinnedHeaderExpandableAdapter;
import com.tips.zy.tips.Main.View.PinnedHeaderExpandableListView;
import com.tips.zy.tips.Other.Activity.BackActivity;
import com.tips.zy.tips.Other.Activity.ScanActivity;
import com.tips.zy.tips.R;

import zuo.biao.library.ui.WebViewActivity;
import zuo.biao.library.util.CommonUtil;
import zuo.biao.library.util.Log;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,View.OnClickListener{
    private Toolbar toolbar;
    private PinnedHeaderExpandableListView explistview;

    private String[][] childrenData = new String[10][10];
    private String[] groupData = new String[10];
    private int expandFlag = -1;//控制列表的展开
    private PinnedHeaderExpandableAdapter adapter;

    private LinearLayout search;
    private ImageView userImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("分组管理");
        setSupportActionBar(toolbar);
        init();
        initData();

    }


    /*
    *初始化
     */
    private void init() {
        //fabBUTTON
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"添加联系人",Toast.LENGTH_LONG).show();
                startActivity(AddPeopleActivity.createIntent(MainActivity.this));
            }
        });
        //抽屉
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //PinnerHeaderExpandale
        explistview= (PinnedHeaderExpandableListView) findViewById(R.id.explistview);

        search= (LinearLayout) findViewById(R.id.search);
        search.setOnClickListener(this);
        View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_main);
        userImage= (ImageView) headerLayout.findViewById(R.id.imageView);

        userImage.setOnClickListener(this);
    }
    private void initData() {
        for(int i=0;i<10;i++){
            groupData[i] = "分组"+i;
        }

        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                childrenData[i][j] = "好友"+i+"-"+j;
            }
        }
        //设置悬浮头部VIEW
        explistview.setHeaderView(getLayoutInflater().inflate(R.layout.group_head,
                explistview, false));
        adapter = new PinnedHeaderExpandableAdapter(childrenData, groupData, getApplicationContext(),explistview);
        explistview.setAdapter(adapter);

        //设置单个分组展开
        explistview.setOnGroupClickListener(new GroupClickListener());

    }


    /*
    *返回按键事件
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    /*
    *菜单栏
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /*
    *navagation菜单
     */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Group) {
            // Handle the camera action
        } else if (id == R.id.AddPeople) {
            startActivity(AddPeopleActivity.createIntent(MainActivity.this));
        } else if (id == R.id.BusinessCard) {
            startActivity(ScanActivity.createIntent(MainActivity.this));
        } else if (id == R.id.Share) {
            CommonUtil.shareInfo(MainActivity.this, getString(R.string.share_app) + "\n 点击链接直接查看ZBLibrary\n" + "占时无下载地址");

        } else if (id == R.id.Back) {
            startActivity(BackActivity.CreateIntent(MainActivity.this));
        } else if (id == R.id.Upload) {
            Toast.makeText(MainActivity.this,"云端接口占时未开放",Toast.LENGTH_LONG).show();
        }else if (id == R.id.AboutUs) {

        }else if (id == R.id.UpdateLog) {
            toActivity(WebViewActivity.createIntent(MainActivity.this, "开发者", "https://github.com/shenzhengyang/TIPS"));
        } else if (id == R.id.Team) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.search:{
                startActivity(SearchActivity.createIntent(MainActivity.this));
                break;
            }
            case R.id.imageView:{
                startActivity(UserActivity.CreateIntent(MainActivity.this));
                break;
            }
        }
    }

    class GroupClickListener implements ExpandableListView.OnGroupClickListener {
        @Override
        public boolean onGroupClick(ExpandableListView parent, View v,
                                    int groupPosition, long id) {
            if (expandFlag == -1) {
                // 展开被选的group
                explistview.expandGroup(groupPosition);
                // 设置被选中的group置于顶端
                explistview.setSelectedGroup(groupPosition);
                expandFlag = groupPosition;
            } else if (expandFlag == groupPosition) {
                explistview.collapseGroup(expandFlag);
                expandFlag = -1;
            } else {
                explistview.collapseGroup(expandFlag);
                // 展开被选的group
                explistview.expandGroup(groupPosition);
                // 设置被选中的group置于顶端
                explistview.setSelectedGroup(groupPosition);
                expandFlag = groupPosition;
            }
            return true;
        }
    }
    /**打开新的Activity，向左滑入效果
     * @param intent
     */
    public void toActivity(Intent intent) {
        toActivity(intent, true);
    }
    /**打开新的Activity
     * @param intent
     * @param showAnimation
     */
    public void toActivity(Intent intent, boolean showAnimation) {
        toActivity(intent, -1, showAnimation);
    }
    /**打开新的Activity，向左滑入效果
     * @param intent
     * @param requestCode
     */
    public void toActivity(Intent intent, int requestCode) {
        toActivity(intent, requestCode, true);
    }
    /**打开新的Activity
     * @param intent
     * @param requestCode
     * @param showAnimation
     */
    public void toActivity(final Intent intent, final int requestCode, final boolean showAnimation) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (intent == null) {
                    Log.w("TAG", "toActivity  intent == null >> return;");
                    return;
                }
                //fragment中使用context.startActivity会导致在fragment中不能正常接收onActivityResult
                if (requestCode < 0) {
                    startActivity(intent);
                } else {
                    startActivityForResult(intent, requestCode);
                }
                if (showAnimation) {
                    overridePendingTransition(zuo.biao.library.R.anim.right_push_in, zuo.biao.library.R.anim.hold);
                } else {
                    overridePendingTransition(zuo.biao.library.R.anim.null_anim, zuo.biao.library.R.anim.null_anim);
                }
            }
        });
    }
}