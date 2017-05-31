package com.tips.zy.tips.Main.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.widget.SearchView;

import com.tips.zy.tips.Main.Adapter.ItemMenuAdapter;
import com.tips.zy.tips.Main.Entity.People;
import com.tips.zy.tips.Main.View.MenuListView;
import com.tips.zy.tips.R;

import java.util.ArrayList;
import java.util.List;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.util.Log;

/**
 * Created by zy on 2017/3/28.
 */

public class SearchActivity extends BaseActivity {

    private SearchView searchView;
    private ItemMenuAdapter itemMenuAdapter;
    private MenuListView menuListView;
    private List<People> peoples;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        initView();
        initData();
        initEvent();
    }



    /**启动这个Activity的Intent
     * @param context
     * @return
     */
    public static Intent createIntent(Context context) {
        return new Intent(context, SearchActivity.class);
    }


    public Activity getActivity() {
        return this;
    }


    @Override
    public void initView() {
        searchView= (SearchView) findViewById(R.id.searchView);
        menuListView= (MenuListView) findViewById(R.id.menuListView);

        peoples=new ArrayList<People>();

    }

    @Override
    public void initData() {

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

        Log.d("peopesize",String.valueOf(peoples.size()));
        itemMenuAdapter=new ItemMenuAdapter(context,peoples);
        menuListView.setAdapter(itemMenuAdapter);
    }

    @Override
    public void initEvent() {


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                //过滤数据逻辑


                return false;
            }
        });
    }
}
