package com.tips.zy.tips.Main.Entity;

import com.tips.zy.tips.AddPeople.Entity.PeopleCharacter;
import com.tips.zy.tips.AddPeople.Entity.PeopleHobby;
import com.tips.zy.tips.AddPeople.Entity.PeopleInfo;
import com.tips.zy.tips.AddPeople.Entity.PeopleWork;

/**
 * Created by zy on 2017/5/30.
 */

public class PeopleAll {
    private PeopleInfo peopleInfo;
    private PeopleWork peopleWork;
    private PeopleHobby peopleHobby;
    private PeopleCharacter peopleCharacter;

    public PeopleAll(){
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

    @Override
    public String toString() {
        return "PeopleAll{" +
                "peopleInfo=" + peopleInfo +
                ", peopleWork=" + peopleWork +
                ", peopleHobby=" + peopleHobby +
                ", peopleCharacter=" + peopleCharacter +
                '}';
    }
}
