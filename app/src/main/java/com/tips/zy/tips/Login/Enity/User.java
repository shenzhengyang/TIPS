package com.tips.zy.tips.Login.Enity;

/**
 * Created by zy on 2017/3/31.
 */

public class User {

    private int U_Id;
    private String U_Icon;
    private String U_name;
    private String U_Pass;
    private String U_Phone;

    public User(int u_Id, String u_Icon, String u_name, String u_Pass, String u_Phone) {
        U_Id = u_Id;
        U_Icon = u_Icon;
        U_name = u_name;
        U_Pass = u_Pass;
        U_Phone = u_Phone;
    }

    public User(){
        super();
    }

    public int getU_Id() {
        return U_Id;
    }

    public void setU_Id(int u_Id) {
        U_Id = u_Id;
    }

    public String getU_Icon() {
        return U_Icon;
    }

    public void setU_Icon(String u_Icon) {
        U_Icon = u_Icon;
    }

    public String getU_name() {
        return U_name;
    }

    public void setU_name(String u_name) {
        U_name = u_name;
    }

    public String getU_Pass() {
        return U_Pass;
    }

    public void setU_Pass(String u_Pass) {
        U_Pass = u_Pass;
    }

    public String getU_Phone() {
        return U_Phone;
    }

    public void setU_Phone(String u_Phone) {
        U_Phone = u_Phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "U_Id=" + U_Id +
                ", U_Icon='" + U_Icon + '\'' +
                ", U_name='" + U_name + '\'' +
                ", U_Pass='" + U_Pass + '\'' +
                ", U_Phone='" + U_Phone + '\'' +
                '}';
    }
}
