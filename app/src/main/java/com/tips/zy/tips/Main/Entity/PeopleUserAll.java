package com.tips.zy.tips.Main.Entity;

import java.util.List;

/**
 * Created by zy on 2017/5/30.
 */

public class PeopleUserAll {
    private List<PeopleGroupAll> peopleGroupAlls;
    public PeopleUserAll(){
        super();

    }

    public List<PeopleGroupAll> getPeopleGroupAlls() {
        return peopleGroupAlls;
    }

    public void setPeopleGroupAlls(List<PeopleGroupAll> peopleGroupAlls) {
        this.peopleGroupAlls = peopleGroupAlls;
    }

    @Override
    public String toString() {
        return "PeopleUserAll{" +
                "peopleGroupAlls=" + peopleGroupAlls +
                '}';
    }
}
