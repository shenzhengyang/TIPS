package com.tips.zy.tips.Main.Entity;

/**
 * Created by zy on 2017/3/31.
 */

public class People {
    private String icon;
    private String P_Name;
    private String P_Hobby;

    public People(String icon, String p_Name, String p_Hobby) {
        this.icon = icon;
        P_Name = p_Name;
        P_Hobby = p_Hobby;
    }

    public People() {
        super();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getP_Name() {
        return P_Name;
    }

    public void setP_Name(String p_Name) {
        P_Name = p_Name;
    }

    public String getP_Hobby() {
        return P_Hobby;
    }

    public void setP_Hobby(String p_Hobby) {
        P_Hobby = p_Hobby;
    }

    @Override
    public String toString() {
        return "People{" +
                "icon='" + icon + '\'' +
                ", P_Name='" + P_Name + '\'' +
                ", P_Hobby='" + P_Hobby + '\'' +
                '}';
    }
}
