package com.tips.zy.tips.Other.Entity;

/**
 * Created by zy on 2017/3/31.
 */

public class Back {

    private int B_Id;
    private String B_Content;
    private String B_Connection;
    private String B_Time;

    public Back(int b_Id, String b_Content, String b_Connection, String b_Time) {
        B_Id = b_Id;
        B_Content = b_Content;
        B_Connection = b_Connection;
        B_Time = b_Time;
    }
    public Back(){
        super();
    }

    public int getB_Id() {
        return B_Id;
    }

    public void setB_Id(int b_Id) {
        B_Id = b_Id;
    }

    public String getB_Content() {
        return B_Content;
    }

    public void setB_Content(String b_Content) {
        B_Content = b_Content;
    }

    public String getB_Connection() {
        return B_Connection;
    }

    public void setB_Connection(String b_Connection) {
        B_Connection = b_Connection;
    }

    public String getB_Time() {
        return B_Time;
    }

    public void setB_Time(String b_Time) {
        B_Time = b_Time;
    }

    @Override
    public String toString() {
        return "Back{" +
                "B_Id=" + B_Id +
                ", B_Content='" + B_Content + '\'' +
                ", B_Connection='" + B_Connection + '\'' +
                ", B_Time='" + B_Time + '\'' +
                '}';
    }
}
