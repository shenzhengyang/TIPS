package com.tips.zy.tips.Main.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
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
        for(int i=0;i<10;i++){
            People people=new People();
            people.setIcon("R.mipmap.logo");
            people.setP_Name("小花");
            people.setP_Hobby("打球");
            peoples.add(people);
        }
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
