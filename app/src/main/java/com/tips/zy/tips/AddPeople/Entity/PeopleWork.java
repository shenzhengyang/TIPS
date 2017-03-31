package com.tips.zy.tips.AddPeople.Entity;

/**
 * Created by zy on 2017/3/31.
 */

public class PeopleWork {

    private int W_id;
    private String W_company;
    private String W_Position;
    private String W_Address;
    private String W_Remark;

    public PeopleWork() {
        super();
    }

    public PeopleWork(int w_id, String w_company, String w_Position, String w_Address, String w_Remark) {
        W_id = w_id;
        W_company = w_company;
        W_Position = w_Position;
        W_Address = w_Address;
        W_Remark = w_Remark;
    }

    public int getW_id() {
        return W_id;
    }

    public void setW_id(int w_id) {
        W_id = w_id;
    }

    public String getW_company() {
        return W_company;
    }

    public void setW_company(String w_company) {
        W_company = w_company;
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
        return "PeopleWork{" +
                "W_id=" + W_id +
                ", W_company='" + W_company + '\'' +
                ", W_Position='" + W_Position + '\'' +
                ", W_Address='" + W_Address + '\'' +
                ", W_Remark='" + W_Remark + '\'' +
                '}';
    }
}
