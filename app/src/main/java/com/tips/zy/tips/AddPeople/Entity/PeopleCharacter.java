package com.tips.zy.tips.AddPeople.Entity;

/**
 * Created by zy on 2017/3/31.
 */

public class PeopleCharacter {

    private int C_Id;
    private String C_Character;
    private String C_Remark;

    public PeopleCharacter(int c_Id, String c_character, String c_Remark) {
        C_Id = c_Id;
        C_Character = c_character;
        C_Remark = c_Remark;
    }
    public PeopleCharacter(){
        super();
    }
    public int getC_Id() {
        return C_Id;
    }

    public void setC_Id(int c_Id) {
        C_Id = c_Id;
    }

    public String getC_Character() {
        return C_Character;
    }

    public void setC_Character(String c_character) {
        C_Character = c_character;
    }

    public String getC_Remark() {
        return C_Remark;
    }

    public void setC_Remark(String c_Remark) {
        C_Remark = c_Remark;
    }

    @Override
    public String toString() {
        return
                "性格：'" + C_Character + '\'' +
                "备注：'" + C_Remark + '\'';
    }
}
