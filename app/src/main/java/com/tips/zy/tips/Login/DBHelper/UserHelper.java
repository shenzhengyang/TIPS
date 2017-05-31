package com.tips.zy.tips.Login.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tips.zy.tips.AddPeople.Entity.PeopleWork;
import com.tips.zy.tips.Login.Enity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zy on 2017/5/30.
 */

public class UserHelper extends SQLiteOpenHelper{

    private static final String TAG="UserHelper";
    private static final int TABLE_VERSION=1;
    public static final String TABLE_NAME = "User";
    public static final String U_Id="U_Id";
    public static final String U_Icon="U_Icon";
    public static final String U_name="U_name";
    public static final String U_Pass="U_Pass";
    public static final String U_Phone="U_Phone";
    public UserHelper(Context context){
        super(context,TABLE_NAME,null,TABLE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE "+TABLE_NAME+" ("+
                U_Id+ " INTEGER primary key autoincrement, "+
                U_Icon+ " text, "+U_name +" text, "+
                U_Pass+ " text, "+U_Phone+ " text )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    /**
     *添加User
     */
    public void addUser(User user){
        SQLiteDatabase db=this.getReadableDatabase();
        try{
            db.execSQL("INSERT INTO "+TABLE_NAME +" VALUES(null, ?, ?, ?, ?)",
                    new Object[]{user.getU_Icon(),user.getU_name(),
                    user.getU_Pass(),user.getU_Phone()});

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean update(String User_Name,String Pass_word){
        User user=query_User(User_Name);
        if(user.getU_name()!=null) {
            Log.d("User_update",User_Name+Pass_word);
            SQLiteDatabase db = this.getReadableDatabase();
//            db.execSQL("UPDATE " + TABLE_NAME + " SET " + U_Pass + " = ? WHERE " + U_name + " = ?", new Object[]{User_Name,Pass_word});
            ContentValues values = new ContentValues();
            values.put(U_Pass, Pass_word);
            db.update(TABLE_NAME, values, "U_name = ?", new String[] { User_Name });
            return true;
        }
        return false;
    }
    /**
     *将查询数据转换为实体返回
     * @return
     */
    public User query(String User_Name,String Pass_word){
        User user=new User();
        Cursor c=queryAll();
        while(c.moveToNext()){
            if(User_Name.equals(c.getString(c.getColumnIndex(U_name)))&&Pass_word.equals(c.getString(c.getColumnIndex(U_Pass))))
            {

                user.setU_Id(c.getInt(c.getColumnIndex(U_Id)));
                user.setU_Icon(c.getString(c.getColumnIndex(U_Icon)));
                user.setU_name(c.getString(c.getColumnIndex(U_name)));
                user.setU_Pass(c.getString(c.getColumnIndex(U_Pass)));
                user.setU_Phone(c.getString(c.getColumnIndex(U_Phone)));
            }
        }
        c.close();
        return user;
    }

    public User query_User(String User_Name){
        User user=new User();
        Cursor c=queryAll();
        while(c.moveToNext()){
            if(User_Name.equals(c.getString(c.getColumnIndex(U_name))))
            {

                user.setU_Id(c.getInt(c.getColumnIndex(U_Id)));
                user.setU_Icon(c.getString(c.getColumnIndex(U_Icon)));
                user.setU_name(c.getString(c.getColumnIndex(U_name)));
                user.setU_Pass(c.getString(c.getColumnIndex(U_Pass)));
                user.setU_Phone(c.getString(c.getColumnIndex(U_Phone)));
            }
        }
        c.close();
        return user;
    }
    /**
     * 查询所有数据
     * @return
     */
    public Cursor queryAll(){
        SQLiteDatabase db=this.getReadableDatabase();
        try{
            return db.query(TABLE_NAME,null, null, null, null, null, null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 查询所有数据
     */
    public Cursor queryTheCursor(){
        SQLiteDatabase db=this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
    }
}
