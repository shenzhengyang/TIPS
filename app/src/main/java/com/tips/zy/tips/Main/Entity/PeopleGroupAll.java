package com.tips.zy.tips.Main.Entity;

import java.util.List;

/**
 * Created by zy on 2017/5/30.
 */

public class PeopleGroupAll {
    private String Group;
    private List<PeopleAll> peopleAlls;
    public PeopleGroupAll(){
        super();
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public List<PeopleAll> getPeopleAlls() {
        return peopleAlls;
    }

    public void setPeopleAlls(List<PeopleAll> peopleAlls) {
        this.peopleAlls = peopleAlls;
    }

    @Override
    public String toString() {
        return "PeopleGroupAll{" +
                "Group='" + Group + '\'' +
                ", peopleAlls=" + peopleAlls +
                '}';
    }
}
