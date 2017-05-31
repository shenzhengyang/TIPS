package com.tips.zy.tips.Main.Entity;

import java.util.List;

/**
 * Created by zy on 2017/4/28.
 */

public class Group {
    private String GroupName;
    private List<People> peoples;

    public Group(String groupName, List<People> peoples) {
        GroupName = groupName;
        this.peoples = peoples;
    }
    public Group(){
        super();
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public List<People> getPeoples() {
        return peoples;
    }

    public void setPeoples(List<People> peoples) {
        this.peoples = peoples;
    }

    @Override
    public String toString() {
        return "Group{" +
                "GroupName='" + GroupName + '\'' +
                ", peoples=" + peoples +
                '}';
    }
}
