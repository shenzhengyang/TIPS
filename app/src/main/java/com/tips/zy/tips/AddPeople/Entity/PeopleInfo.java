package com.tips.zy.tips.AddPeople.Entity;

/**
 * Created by zy on 2017/3/31.
 */

public class PeopleInfo {
    private int P_Id;
    private String P_Icon;
    private String P_Name;
    private String P_Gender;
    private String P_Phone;
    private String P_Mail;
    private String P_Address;
    private String P_National;
    private String P_Religion;
    private String P_Degree;
    private String P_BirthDay;
    private String P_Remark;

    public PeopleInfo(int p_id, String p_Icon, String p_Name, String p_Gender, String p_Phone, String p_Mail, String p_Address, String p_National, String p_Religion, String p_Degree, String p_BirthDay, String p_Remark) {
        P_Id = p_id;
        P_Icon = p_Icon;
        P_Name = p_Name;
        P_Gender = p_Gender;
        P_Phone = p_Phone;
        P_Mail = p_Mail;
        P_Address = p_Address;
        P_National = p_National;
        P_Religion = p_Religion;
        P_Degree = p_Degree;
        P_BirthDay = p_BirthDay;
        P_Remark = p_Remark;
    }

    public PeopleInfo() {
        super();
    }

    public int getP_Id() {
        return P_Id;
    }

    public void setP_Id(int p_Id) {
        P_Id = p_Id;
    }

    public String getP_Icon() {
        return P_Icon;
    }

    public void setP_Icon(String p_Icon) {
        P_Icon = p_Icon;
    }

    public String getP_Name() {
        return P_Name;
    }

    public void setP_Name(String p_Name) {
        P_Name = p_Name;
    }

    public String getP_Gender() {
        return P_Gender;
    }

    public void setP_Gender(String p_Gender) {
        P_Gender = p_Gender;
    }

    public String getP_Phone() {
        return P_Phone;
    }

    public void setP_Phone(String p_Phone) {
        P_Phone = p_Phone;
    }

    public String getP_Mail() {
        return P_Mail;
    }

    public void setP_Mail(String p_Mail) {
        P_Mail = p_Mail;
    }

    public String getP_Address() {
        return P_Address;
    }

    public void setP_Address(String p_Address) {
        P_Address = p_Address;
    }

    public String getP_National() {
        return P_National;
    }

    public void setP_National(String p_National) {
        P_National = p_National;
    }

    public String getP_Religion() {
        return P_Religion;
    }

    public void setP_Religion(String p_Religion) {
        P_Religion = p_Religion;
    }

    public String getP_Degree() {
        return P_Degree;
    }

    public void setP_Degree(String p_Degree) {
        P_Degree = p_Degree;
    }

    public String getP_BirthDay() {
        return P_BirthDay;
    }

    public void setP_BirthDay(String p_BirthDay) {
        P_BirthDay = p_BirthDay;
    }

    public String getP_Remark() {
        return P_Remark;
    }

    public void setP_Remark(String p_Remark) {
        P_Remark = p_Remark;
    }

    @Override
    public String toString() {
        return

                "姓名：'" + P_Name + '\'' +
                "性别：'" + P_Gender + '\'' +
                "电话：'" + P_Phone + '\'' +
                "邮箱：'" + P_Mail + '\'' +
                "地址：'" + P_Address + '\'' +
                "民族：'" + P_National + '\'' +
                "信仰：'" + P_Religion + '\'' +
                "学历：'" + P_Degree + '\'' +
                "生日：'" + P_BirthDay + '\'' +
                "备注：'" + P_Remark + '\'';
    }
}
