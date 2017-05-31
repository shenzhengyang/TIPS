package com.tips.zy.tips.AddPeople.Entity;

/**
 * Created by zy on 2017/3/31.
 */

public class PeopleHobby {

    private int H_Id;
    private String H_field;
    private String H_Sport;
    private String H_Hate;
    private String H_Remark;

    public PeopleHobby(int h_Id, String h_field, String h_Sport, String h_Hate, String h_Remark) {
        H_Id = h_Id;
        H_field = h_field;
        H_Sport = h_Sport;
        H_Hate = h_Hate;
        H_Remark = h_Remark;
    }
    public PeopleHobby(){
        super();
    }

    public int getH_Id() {
        return H_Id;
    }

    public void setH_Id(int h_Id) {
        H_Id = h_Id;
    }

    public String getH_field() {
        return H_field;
    }

    public void setH_field(String h_field) {
        H_field = h_field;
    }

    public String getH_Sport() {
        return H_Sport;
    }

    public void setH_Sport(String h_Sport) {
        H_Sport = h_Sport;
    }

    public String getH_Hate() {
        return H_Hate;
    }

    public void setH_Hate(String h_Hate) {
        H_Hate = h_Hate;
    }

    public String getH_Remark() {
        return H_Remark;
    }

    public void setH_Remark(String h_Remark) {
        H_Remark = h_Remark;
    }

    @Override
    public String toString() {
        return
                "所在领域：'" + H_field + '\'' +
                "爱好的运动：" + H_Sport + '\'' +
                "讨厌：'" + H_Hate + '\'' +
                "备注：'" + H_Remark + '\'';
    }
}
