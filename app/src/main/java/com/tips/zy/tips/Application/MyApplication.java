package com.tips.zy.tips.Application;

import android.app.Application;

import com.tips.zy.tips.AddPeople.Entity.PeopleAllInfo;
import com.tips.zy.tips.AddPeople.Entity.PeopleCharacter;
import com.tips.zy.tips.AddPeople.Entity.PeopleHobby;
import com.tips.zy.tips.AddPeople.Entity.PeopleInfo;
import com.tips.zy.tips.AddPeople.Entity.PeopleWork;
import com.tips.zy.tips.Login.Enity.User;
import com.tips.zy.tips.Main.Entity.Group;
import com.tips.zy.tips.Main.Entity.People;
import com.tips.zy.tips.Main.Entity.PeopleGroupAll;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zy on 2017/5/21.
 */

public class MyApplication extends Application {
    /**
     * 设置全局的变量实体-分组
     */
    private PeopleWork peopleWork;
    private PeopleInfo peopleInfo;
    private PeopleHobby peopleHobby;
    private PeopleCharacter peopleCharacter;
    private PeopleAllInfo peopleAllInfo;
    private List<People> peoples;
    private List<Group> groups;

    public List<People> getPeoples() {
        return peoples;
    }

    public void setPeoples(List<People> peoples) {
        this.peoples = peoples;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public PeopleAllInfo getPeopleAllInfo() {
        return peopleAllInfo;
    }

    public void setPeopleAllInfo(PeopleAllInfo peopleAllInfo) {
        this.peopleAllInfo = peopleAllInfo;
    }

    public PeopleInfo getPeopleInfo() {
        return peopleInfo;
    }

    public void setPeopleInfo(PeopleInfo peopleInfo) {
        this.peopleInfo = peopleInfo;
    }

    public PeopleHobby getPeopleHobby() {
        return peopleHobby;
    }

    public void setPeopleHobby(PeopleHobby peopleHobby) {
        this.peopleHobby = peopleHobby;
    }

    public PeopleCharacter getPeopleCharacter() {
        return peopleCharacter;
    }

    public void setPeopleCharacter(PeopleCharacter peopleCharacter) {
        this.peopleCharacter = peopleCharacter;
    }

    public PeopleWork getPeopleWork() {
        return peopleWork;
    }

    public void setPeopleWork(PeopleWork peopleWork) {
        this.peopleWork = peopleWork;
    }

    /**
     * 以下为用户的全局变量
     */
    private User user=new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String User_Name="TIPS";
    private String Pass_Word="123";

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getPass_Word() {
        return Pass_Word;
    }

    public void setPass_Word(String pass_Word) {
        Pass_Word = pass_Word;
    }

    @Override
    public void onCreate() {

        peopleWork=new PeopleWork();
        peopleInfo=new PeopleInfo();
        peopleHobby=new PeopleHobby();
        peopleCharacter=new PeopleCharacter();
        super.onCreate();
    }

    /**
     * query 所有信息
     */
    private List<PeopleGroupAll> peopleGroupAlls=new ArrayList<>();

    public List<PeopleGroupAll> getPeopleGroupAlls() {
        return peopleGroupAlls;
    }

    public void setPeopleGroupAlls(List<PeopleGroupAll> peopleGroupAlls) {
        this.peopleGroupAlls = peopleGroupAlls;
    }
    //C_ID

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
