package com.tips.zy.tips.AddPeople.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tips.zy.tips.AddPeople.Entity.PeopleWork;
import com.tips.zy.tips.Main.Entity.People;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zy on 2017/5/20.
 */

public class PeopleWorkHelper extends SQLiteOpenHelper {

    private static final String TAG="PeopleWorkHelper";
    private static final int TABLE_VERSION=1;
    public static final String TABLE_NAME = "PeopleWork";
    public static final String W_Id="W_Id";
    public static final String W_Company="W_Company";
    public static final String W_Position="W_Position";
    public static final String W_Address="W_Address";
    public static final String W_Remark="W_Remark";
    public PeopleWorkHelper(Context context){
        super(context,TABLE_NAME,null,TABLE_VERSION);
    }
    public PeopleWorkHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql="CREATE TABLE "+TABLE_NAME+" ("+
                W_Id+ " INTEGER primary key autoincrement, "+
                W_Company+ " text, "+W_Position +" text, "+
                W_Address+ " text, "+W_Remark+ " text )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    /**
    *添加peopleWork
     */
    public void addPeopleWork(List<PeopleWork> peopleWorks){
        SQLiteDatabase db=this.getReadableDatabase();
        //db.beginTransaction();
        try{
            for(PeopleWork peopleWork:peopleWorks){

               db.execSQL("INSERT INTO "+TABLE_NAME +" VALUES(null, ?, ?, ?, ?)",
                       new Object[]{peopleWork.getW_Company(),
                               peopleWork.getW_Position(),
                               peopleWork.getW_Address(),
                               peopleWork.getW_Remark()});
            }
            //db.setTransactionSuccessful();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 更新peoplework数据
     * @return
     */
    public void updatePeopleWork(PeopleWork peopleWork){
        ContentValues cv=new ContentValues();
        cv.put(W_Company,peopleWork.getW_Company());
        cv.put(W_Position,peopleWork.getW_Position());
        cv.put(W_Address,peopleWork.getW_Address());
        cv.put(W_Remark,peopleWork.getW_Remark());
        SQLiteDatabase db=this.getReadableDatabase();
        db.update(TABLE_NAME,cv,"W_Id = ?",new String[]{String.valueOf(peopleWork.getW_id())});
    }

    /**
     * 删除peopleWork信息
     * @param peopleWork
     */
    public void deletePeopleWork(PeopleWork peopleWork){
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete(TABLE_NAME,"W_Id=?",new String[]{String.valueOf(peopleWork.getW_id())});
    }

    /**
     *将查询数据转换为实体返回
     * @return
     */
    public List<PeopleWork> query(){
        ArrayList<PeopleWork> peopleWorks=new ArrayList<PeopleWork>();
        Cursor c=queryAll();
        while(c.moveToNext()){
            PeopleWork peopleWork=new PeopleWork();
            peopleWork.setW_id(c.getInt(c.getColumnIndex(W_Id)));
            peopleWork.setW_Company(c.getString(c.getColumnIndex(W_Company)));
            peopleWork.setW_Position(c.getString(c.getColumnIndex(W_Position)));
            peopleWork.setW_Address(c.getString(c.getColumnIndex(W_Address)));
            peopleWork.setW_Remark(c.getString(c.getColumnIndex(W_Remark)));
            peopleWorks.add(peopleWork);
        }
        c.close();
        return peopleWorks;
    }

    public PeopleWork queryById(int id){
        //ArrayList<PeopleWork> peopleWorks=new ArrayList<PeopleWork>();
        PeopleWork peopleWork=new PeopleWork();
        Cursor c=queryAll();
        while(c.moveToNext()){
            if(id==c.getInt(c.getColumnIndex(W_Id))) {
                peopleWork.setW_id(c.getInt(c.getColumnIndex(W_Id)));
                peopleWork.setW_Company(c.getString(c.getColumnIndex(W_Company)));
                peopleWork.setW_Position(c.getString(c.getColumnIndex(W_Position)));
                peopleWork.setW_Address(c.getString(c.getColumnIndex(W_Address)));
                peopleWork.setW_Remark(c.getString(c.getColumnIndex(W_Remark)));
                //peopleWorks.add(peopleWork);
            }
        }
        c.close();
        return peopleWork;
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
    public int query_WID(){

        Cursor curcor=queryAll();
        if(curcor.getCount()==0){
            return 0;
        }
        curcor.moveToLast();
        return curcor.getInt(curcor.getColumnIndex(W_Id));
    }
}
