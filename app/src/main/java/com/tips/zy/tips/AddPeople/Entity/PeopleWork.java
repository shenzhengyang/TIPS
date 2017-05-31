package com.tips.zy.tips.AddPeople.Entity;

/**
 * Created by zy on 2017/3/31.
 */

public class PeopleWork {

    private int W_Id;
    private String W_Company;
    private String W_Position;
    private String W_Address;
    private String W_Remark;

    public PeopleWork() {
        super();
    }

    public PeopleWork(int w_id, String w_company, String w_Position, String w_Address, String w_Remark) {
        W_Id = w_id;
        W_Company = w_company;
        W_Position = w_Position;
        W_Address = w_Address;
        W_Remark = w_Remark;
    }

    public int getW_id() {
        return W_Id;
    }

    public void setW_id(int w_Id) {
        W_Id = w_Id;
    }

    public String getW_Company() {
        return W_Company;
    }

    public void setW_Company(String w_Company) {
        W_Company = w_Company;
    }

    public String getW_Position() {
        return W_Position;
    }

    public void setW_Position(String w_Position) {
        W_Position = w_Position;
    }

    public String getW_Address() {
        return W_Address;
    }

    public void setW_Address(String w_Address) {
        W_Address = w_Address;
    }

    public String getW_Remark() {
        return W_Remark;
    }

    public void setW_Remark(String w_Remark) {
        W_Remark = w_Remark;
    }

    @Override
    public String toString() {
        return
                "公司：'" + W_Company + '\'' +
                "职位：'" + W_Position + '\'' +
                "住址：'" + W_Address + '\'' +
                "备注：'" + W_Remark + '\'';
    }
}
