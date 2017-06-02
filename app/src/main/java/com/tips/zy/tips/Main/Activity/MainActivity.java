package com.tips.zy.tips.Main.Activity;

import android.app.ProgressDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.tips.zy.tips.AddPeople.Activity.AddPeopleActivity;
import com.tips.zy.tips.AddPeople.Activity.PeopleCharacterActivity;
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
import com.tips.zy.tips.Const.Const;
import com.tips.zy.tips.Login.Activity.UserActivity;
import com.tips.zy.tips.Login.Enity.User;
import com.tips.zy.tips.Main.Adapter.PinnedHeaderExpandableAdapter;
import com.tips.zy.tips.Main.Entity.Group;
import com.tips.zy.tips.Main.Entity.People;
import com.tips.zy.tips.Main.Entity.PeopleAll;
import com.tips.zy.tips.Main.Entity.PeopleGroupAll;
import com.tips.zy.tips.Main.View.PinnedHeaderExpandableListView;
import com.tips.zy.tips.Other.Activity.BackActivity;
import com.tips.zy.tips.Other.Activity.ScanActivity;
import com.tips.zy.tips.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zuo.biao.library.ui.WebViewActivity;
import zuo.biao.library.util.CommonUtil;
import zuo.biao.library.util.ImageLoaderUtil;
import zuo.biao.library.util.Log;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,View.OnClickListener,Toolbar.OnMenuItemClickListener{
    private Toolbar toolbar;
    private PinnedHeaderExpandableListView explistview;

    /*private String[][] childrenData = new String[10][10];
    private String[] groupData = new String[10];*/
    private List<Group> groups=new ArrayList<>();
    private int expandFlag = -1;//控制列表的展开
    private PinnedHeaderExpandableAdapter adapter;

    private LinearLayout search;
    private ImageView userImage;
    private TextView user_name;
    private TextView label;
    ProgressDialog progressDialog;
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

        toolbar.setOnMenuItemClickListener(this);

        //PinnerHeaderExpandale
        explistview= (PinnedHeaderExpandableListView) findViewById(R.id.explistview);

        search= (LinearLayout) findViewById(R.id.search);
        search.setOnClickListener(this);
        View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_main);
        userImage= (ImageView) headerLayout.findViewById(R.id.imageView);
        user_name= (TextView) headerLayout.findViewById(R.id.head_user_name);
        label= (TextView) headerLayout.findViewById(R.id.label);
        userImage.setOnClickListener(this);
    }
    private void initData() {

        Log.d("Group",getMyApplication().getGroups().toString());
        groups=getMyApplication().getGroups();


        //Log.d("测试",groups.get(0).getPeoples().get(0).getP_Name()+"");
        //groupData();
        //设置悬浮头部VIEW
        explistview.setHeaderView(getLayoutInflater().inflate(R.layout.group_head,
                explistview, false));
        adapter = new PinnedHeaderExpandableAdapter(groups, getApplicationContext(),explistview);
        explistview.setAdapter(adapter);

        //设置单个分组展开
        explistview.setOnGroupClickListener(new GroupClickListener());

    }

   /* private void groupData() {

        String fenzu[]={"朋友","同学","同事","老朋友","家人","领导","好友","兴趣相同"};
        for(int i=0;i<fenzu.length;i++){
            List<People> peoples=new ArrayList<>();
            People people=new People();
            people.setIcon(R.mipmap.icon1);
            people.setP_Name("王捷");
            people.setP_Hobby("打球");
            peoples.add(people);
            People people2=new People();
            people2.setIcon(R.mipmap.icon2);
            people2.setP_Name("王小捷");
            people2.setP_Hobby("跑步");
            peoples.add(people2);
            People people3=new People();
            people3.setIcon(R.mipmap.icon3);
            people3.setP_Name("王志捷");
            people3.setP_Hobby("玩游戏");
            peoples.add(people3);
            People people4=new People();
            people4.setIcon(R.mipmap.icon4);
            people4.setP_Name("王明");
            people4.setP_Hobby("游泳");
            peoples.add(people4);
            People people5=new People();
            people5.setIcon(R.mipmap.icon5);
            people5.setP_Name("王鹏");
            people5.setP_Hobby("旅游");
            peoples.add(people5);
            People people6=new People();
            people6.setIcon(R.mipmap.icon6);
            people6.setP_Name("王同为");
            people6.setP_Hobby("上网");
            peoples.add(people6);
            People people7=new People();
            people7.setIcon(R.mipmap.icon7);
            people7.setP_Name("王解开");
            people7.setP_Hobby("下棋");
            peoples.add(people7);
            People people8=new People();
            people8.setIcon(R.mipmap.icon8);
            people8.setP_Name("王雷奇");
            people8.setP_Hobby("玩游戏");
            peoples.add(people8);
            People people9=new People();
            people9.setIcon(R.mipmap.icon9);
            people9.setP_Name("王凯文");
            people9.setP_Hobby("踢足球");
            peoples.add(people9);
            Group group=new Group();
            group.setGroupName(fenzu[i]);
            group.setPeoples(peoples);
            groups.add(group);
            Log.d("groups",groups.toString());
        }

    }*/


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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     *toobar菜单栏
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.shuaxin:{
                Log.d("onResume","更新数据-----------------------------------------");
                final ProgressDialog dialog = ProgressDialog.show(this, null, "数据加载中，请稍候...", true, false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            queryAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                groups=getMyApplication().getGroups();
                                adapter = new PinnedHeaderExpandableAdapter(groups, getApplicationContext(),explistview);
                                //adapter.notifyDataSetChanged();
                                explistview.setAdapter(adapter);
                                Log.d("notifyDataSetChanged","notifyDataSetChanged");
                                dialog.dismiss();
                            }
                        });
                    }
                }).start();
                break;
            }
        }
        return true;
    }
    /*
    *navagation菜单
    *
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
            CommonUtil.shareInfo(MainActivity.this, getString(R.string.share_app) + "\n 点击链接直接查看TIPS\n" + Const.DOWNLOADAPP);

        } else if (id == R.id.Back) {
            startActivity(BackActivity.CreateIntent(MainActivity.this));
        } else if (id == R.id.Upload) {
            //Toast.makeText(MainActivity.this,"云端接口占时未开放",Toast.LENGTH_LONG).show();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setIcon(R.mipmap.logo_green);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMessage("已完成 0%");
            progressDialog.setCancelable(true);
            progressDialog.setProgress(100);
            progressDialog.setIndeterminate(false);
            progressDialog.show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<100;i++){
                        try {
                            Thread.sleep(100);
                            final int finalI = i;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.setProgress(finalI);
                                    progressDialog.setMessage("已完成 "+ finalI +"%");
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this,"数据上传完成",Toast.LENGTH_SHORT).show();
                }
            }).start();
        }else if (id == R.id.AboutUs) {
            toActivity(WebViewActivity.createIntent(MainActivity.this, "开发者",Const.PRODUCTION));
        }else if (id == R.id.UpdateLog) {
            toActivity(WebViewActivity.createIntent(MainActivity.this, "开发者", Const.GitHUB_CODE));
        } else if (id == R.id.Team) {
            toActivity(WebViewActivity.createIntent(MainActivity.this, "开发者", Const.TEAM));
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
    public MyApplication getMyApplication(){
        return (MyApplication) getApplicationContext();
    }

    @Override
    protected void onResume() {
        //加载用户数据
        User user=getMyApplication().getUser();
        Log.d("user加载",user.toString());

        ImageLoaderUtil.loadImage(userImage, user.getU_Icon());
        user_name.setText(user.getU_name());
        if(!user.getU_Phone().equals(user.getU_name())){
            label.setText(user.getU_Phone());
        }
        super.onResume();

    }
    public void queryAll(){
        int icons[]=new int[]{R.mipmap.icon1,R.mipmap.icon2,
                R.mipmap.icon3,R.mipmap.icon4,
                R.mipmap.icon5,R.mipmap.icon6,
                R.mipmap.icon7,R.mipmap.icon8,R.mipmap.icon9};
        PeopleInfoAllHelper peopleInfoAllHelper=new PeopleInfoAllHelper(MainActivity.this);
        List<String>G_Names=peopleInfoAllHelper.queryG_Name();
        Log.d("G_Names",G_Names.toString());
        List<PeopleGroupAll> peopleGroupAlls=new ArrayList<>();

        //生成Groups
        List<Group>groups=new ArrayList<>();

        for(int j=0;j<G_Names.size();j++){
            PeopleGroupAll peopleGroupAll=new PeopleGroupAll();
            peopleGroupAll.setGroup(G_Names.get(j));
            List<PeopleAll>peopleAlls=new ArrayList<>();
            List<PeopleInfoAll>peopleInfoAlls =peopleInfoAllHelper.queryByG_Name(G_Names.get(j));
            //Group
            Group group=new Group();
            group.setGroupName(G_Names.get(j));
            List<People> peoples=new ArrayList<>();
            for (int i= 0; i  < peopleInfoAlls.size(); i++) {

                PeopleAll peopleAll = new PeopleAll();
                People people=new People();
                //people.setIcon(icons[new Random().nextInt(1)]);
                Log.d("查询peopleInfoAll", peopleInfoAlls.get(i).toString());
                //查询peopleInfo
                int P_Id = peopleInfoAlls.get(i).getP_Id();
                PeopleInfoHelper peopleInfoHelper = new PeopleInfoHelper(MainActivity.this);
                PeopleInfo peopleInfo = peopleInfoHelper.queryById(P_Id);
                Log.d("查询查询peopleInfo", peopleInfo.toString());
                peopleAll.setPeopleInfo(peopleInfo);
                people.setP_Id(peopleInfo.getP_Id());
                people.setP_Name(peopleInfo.getP_Name());
                people.setIcon(peopleInfo.getP_Icon());
                //查询peopleWork
                int W_Id = peopleInfoAlls.get(i).getW_Id();
                PeopleWorkHelper peopleWorkHelper = new PeopleWorkHelper(MainActivity.this);
                PeopleWork peopleWork = peopleWorkHelper.queryById(W_Id);
                Log.d("查询peopleWork", peopleWork.toString());
                peopleAll.setPeopleWork(peopleWork);
                //查询PeopleHobby
                int H_Id = peopleInfoAlls.get(i).getH_Id();
                PeopleHobbyHelper peopleHobbyHelper = new PeopleHobbyHelper(MainActivity.this);
                PeopleHobby peopleHobby = peopleHobbyHelper.queryById(H_Id);
                Log.d("查询PeopleHobby", peopleHobby.toString());
                peopleAll.setPeopleHobby(peopleHobby);
                people.setP_Hobby(peopleHobby.getH_field()+peopleHobby.getH_Sport());
                //查询PeopleCharactor
                int C_Id = peopleInfoAlls.get(i).getC_Id();
                PeopleCharacterHelper peopleCharacterHelper = new PeopleCharacterHelper(MainActivity.this);
                PeopleCharacter peopleCharacter = peopleCharacterHelper.queryById(C_Id);
                Log.d("查询peopleCharacters", peopleCharacter.toString());
                peopleAll.setPeopleCharacter(peopleCharacter);

                peopleAlls.add(peopleAll);
                peoples.add(people);
            }
            peopleGroupAll.setPeopleAlls(peopleAlls);
            peopleGroupAlls.add(peopleGroupAll);
            Log.d("peopleGroupAlls",peopleGroupAlls.toString());
            getMyApplication().setPeopleGroupAlls(peopleGroupAlls);
            //添加Group
            group.setPeoples(peoples);
            groups.add(group);
            getMyApplication().setGroups(groups);
            Log.d("groups",getMyApplication().getGroups().toString());
        }
    }
}