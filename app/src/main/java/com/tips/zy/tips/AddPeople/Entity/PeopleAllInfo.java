package com.tips.zy.tips.AddPeople.Entity;

/**
 * Created by zy on 2017/3/31.
 */

public class PeopleAllInfo {
    String G_Name;
    private PeopleInfo peopleInfo;
    private PeopleWork peopleWork;
    private PeopleCharacter peopleCharacter;
    private PeopleHobby peopleHobby;

    public PeopleAllInfo(PeopleInfo peopleInfo, PeopleWork peopleWork, PeopleCharacter peopleCharacter, PeopleHobby peopleHobby) {
        this.peopleInfo = peopleInfo;
        this.peopleWork = peopleWork;
        this.peopleCharacter = peopleCharacter;
        this.peopleHobby = peopleHobby;
    }



    public String getG_Name() {
        return G_Name;
    }

    public void setG_Name(String g_Name) {
        G_Name = g_Name;
    }



    public PeopleAllInfo(){
        super();
    }

    public PeopleInfo getPeopleInfo() {
        return peopleInfo;
    }

    public void setPeopleInfo(PeopleInfo peopleInfo) {
        this.peopleInfo = peopleInfo;
    }

    public PeopleWork getPeopleWork() {
        return peopleWork;
    }

    public void setPeopleWork(PeopleWork peopleWork) {
        this.peopleWork = peopleWork;
    }

    public PeopleCharacter getPeopleCharacter() {
        return peopleCharacter;
    }

    public void setPeopleCharacter(PeopleCharacter peopleCharacter) {
        this.peopleCharacter = peopleCharacter;
    }

    public PeopleHobby getPeopleHobby() {
        return peopleHobby;
    }

    public void setPeopleHobby(PeopleHobby peopleHobby) {
        this.peopleHobby = peopleHobby;
    }

    @Override
    public String toString() {
        return "PeopleAllInfo{" +
                "peopleInfo=" + peopleInfo +
                ", peopleWork=" + peopleWork +
                ", peopleCharacter=" + peopleCharacter +
                ", peopleHobby=" + peopleHobby +
                '}';
    }
}
