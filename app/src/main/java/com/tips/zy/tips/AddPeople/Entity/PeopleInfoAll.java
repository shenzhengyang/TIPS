package com.tips.zy.tips.AddPeople.Entity;

/**
 * Created by zy on 2017/5/29.
 */

public class PeopleInfoAll {
    private String User_Name;
    private String G_Name;
    private int P_Id;
    private int W_Id;
    private int H_Id;
    private int C_Id;
    public PeopleInfoAll(){
        super();
    }

    public PeopleInfoAll(String user_Name, String g_Name, int p_Id, int w_Id, int h_Id, int c_Id) {
        User_Name = user_Name;
        G_Name = g_Name;
        P_Id = p_Id;
        W_Id = w_Id;
        H_Id = h_Id;
        C_Id = c_Id;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getG_Name() {
        return G_Name;
    }

    public void setG_Name(String g_Name) {
        G_Name = g_Name;
    }

    public int getP_Id() {
        return P_Id;
    }

    public void setP_Id(int p_Id) {
        P_Id = p_Id;
    }

    public int getW_Id() {
        return W_Id;
    }

    public void setW_Id(int w_Id) {
        W_Id = w_Id;
    }

    public int getH_Id() {
        return H_Id;
    }

    public void setH_Id(int h_Id) {
        H_Id = h_Id;
    }

    public int getC_Id() {
        return C_Id;
    }

    public void setC_Id(int c_Id) {
        C_Id = c_Id;
    }

    @Override
    public String toString() {
        return "PeopleInfoAll{" +
                "User_Name='" + User_Name + '\'' +
                ", G_Name='" + G_Name + '\'' +
                ", P_Id=" + P_Id +
                ", W_Id=" + W_Id +
                ", H_Id=" + H_Id +
                ", C_Id=" + C_Id +
                '}';
    }
}
