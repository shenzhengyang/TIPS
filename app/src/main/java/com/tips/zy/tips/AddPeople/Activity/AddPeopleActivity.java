package com.tips.zy.tips.AddPeople.Activity;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tips.zy.tips.AddPeople.Adapter.AddPeopleAdapter;
import com.tips.zy.tips.Main.Activity.SearchActivity;
import com.tips.zy.tips.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import zuo.biao.library.base.BaseActivity;

/**
 * Created by zy on 2016/7/18.
 */
public class AddPeopleActivity extends BaseActivity implements View.OnClickListener{
    ArrayList<Map<String,Object>> viewlist=new ArrayList<Map<String, Object>>();
    LocalActivityManager activityManager;
    TabLayout tabLayout=null;
    ViewPager viewpager=null;

    /**启动这个Activity的Intent
     * @param context
     * @return
     */
    public static Intent createIntent(Context context) {
        return new Intent(context, AddPeopleActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpeoplemain);
        activityManager=new LocalActivityManager(this,true);
        activityManager.dispatchCreate(savedInstanceState);
        tabLayout= (TabLayout) findViewById(R.id.tablayout);
        viewpager= (ViewPager) findViewById(R.id.container);

        Map<String,Object>map1=new HashMap<String, Object>();
        map1.put("title", "第一个Activity");
        map1.put("view", getView((String) map1.get("title"), new Intent(this, SearchActivity.class)));
        viewlist.add(map1);
        Map<String,Object>map2=new HashMap<String, Object>();
        map2.put("title","第二个Activity");
        map2.put("view", getView((String) map2.get("title"), new Intent(this, SearchActivity.class)));
        viewlist.add(map2);
        Map<String,Object>map3=new HashMap<String, Object>();
        map3.put("title","第三个Activity");
        map3.put("view", getView((String) map3.get("title"), new Intent(this, SearchActivity.class)));
        viewlist.add(map3);
        Map<String,Object>map4=new HashMap<String, Object>();
        map4.put("title","第三个Activity");
        map4.put("view", getView((String) map4.get("title"), new Intent(this, SearchActivity.class)));
        viewlist.add(map4);
        Map<String,Object>map5=new HashMap<String, Object>();
        map5.put("title","第四个Activity");
        map5.put("view", getView((String) map5.get("title"), new Intent(this, SearchActivity.class)));
        viewlist.add(map5);

        AddPeopleAdapter addPeopleAdapter=new AddPeopleAdapter(viewlist,getSupportFragmentManager());
        tabLayout.setTabsFromPagerAdapter(addPeopleAdapter);
        viewpager.setAdapter(addPeopleAdapter);
        tabLayout.setupWithViewPager(viewpager);
        viewpager.setOnPageChangeListener(new MyViewPagerPageChangeListener());
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    public class MyViewPagerPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            loadActivity(position);
            switch (position){
                case 0:{

                    break;
                }
                case 1:{

                    break;
                }
                case 3:{

                    break;
                }
                case 4:{

                    break;
                }
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private View getView(String id, Intent intent) {
        return activityManager.startActivity(id, intent).getDecorView();
    }

    //调用子Activity发放
    private void loadActivity(int position) {
        Activity activity = activityManager.getActivity((String) viewlist.get(position).get("title"));

    }

}
