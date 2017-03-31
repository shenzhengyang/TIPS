package com.tips.zy.tips.Other.Entity;

/**
 * Created by zy on 2017/3/31.
 */

public class Note {

    private int N_id;
    private String N_Name;
    private String N_Content;
    private String N_Time;

    public Note(int n_id, String n_Name, String n_Content, String n_Time) {
        N_id = n_id;
        N_Name = n_Name;
        N_Content = n_Content;
        N_Time = n_Time;
    }

    public Note() {
        super();
    }

    public int getN_id() {
        return N_id;
    }

    public void setN_id(int n_id) {
        N_id = n_id;
    }

    public String getN_Name() {
        return N_Name;
    }

    public void setN_Name(String n_Name) {
        N_Name = n_Name;
    }

    public String getN_Content() {
        return N_Content;
    }

    public void setN_Content(String n_Content) {
        N_Content = n_Content;
    }

    public String getN_Time() {
        return N_Time;
    }

    public void setN_Time(String n_Time) {
        N_Time = n_Time;
    }

    @Override
    public String toString() {
        return "Note{" +
                "N_id=" + N_id +
                ", N_Name='" + N_Name + '\'' +
                ", N_Content='" + N_Content + '\'' +
                ", N_Time='" + N_Time + '\'' +
                '}';
    }
}
