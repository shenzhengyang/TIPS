package com.tips.zy.tips.AddPeople.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tips.zy.tips.AddPeople.Entity.PeopleHobby;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zy on 2017/5/21.
 */

public class PeopleHobbyHelper extends SQLiteOpenHelper {

    private static final String TAG="PeopleHobbyHelper";
    private static final int TABLE_VERSION=1;
    public static final String TABLE_NAME = "PeopleHobby";
    public static final String H_Id="H_Id";
    public static final String H_field="H_field";
    public static final String H_Sport="H_Sport";
    public static final String H_Hate="H_Hate";
    public static final String H_Remark="H_Remark";
    public PeopleHobbyHelper(Context context){
        super(context,TABLE_NAME,null,TABLE_VERSION);
    }
    public PeopleHobbyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE "+TABLE_NAME+" ("+
                H_Id+ " INTEGER primary key autoincrement, "+
                H_field+ " text, "+H_Sport +" text, "+
                H_Hate+ " text, "+H_Remark+ " text )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    /**
     *添加peopleHobby
     */
    public void addPeopleHobby(List<PeopleHobby> peopleHobbys){
        SQLiteDatabase db=this.getReadableDatabase();
        //db.beginTransaction();
        try{
            for(PeopleHobby peopleHobby:peopleHobbys){

                db.execSQL("INSERT INTO "+TABLE_NAME +" VALUES(null, ?, ?, ?, ?)",
                        new Object[]{peopleHobby.getH_field(),
                        peopleHobby.getH_Sport(),
                        peopleHobby.getH_Hate(),
                        peopleHobby.getH_Remark()});
            }
            //db.setTransactionSuccessful();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 更新peopleHobby数据
     * @return
     */
    public void updatePeopleHobby(PeopleHobby peopleHobby){
        ContentValues cv=new ContentValues();
        cv.put(H_field,peopleHobby.getH_field());
        cv.put(H_Sport,peopleHobby.getH_Sport());
        cv.put(H_Hate,peopleHobby.getH_Hate());
        cv.put(H_Remark,peopleHobby.getH_Remark());
        SQLiteDatabase db=this.getReadableDatabase();
        db.update(TABLE_NAME,cv,"H_Id = ?",new String[]{String.valueOf(peopleHobby.getH_Id())});
    }

    /**
     * 删除peopleHobby信息
     * @param
     */
    public void deletePeopleHobby(PeopleHobby peopleHobby){
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete(TABLE_NAME,"H_Id=?",new String[]{String.valueOf(peopleHobby.getH_Id())});
    }

    /**
     *将查询数据转换为实体返回
     * @return
     */
    public List<PeopleHobby> query(){
        ArrayList<PeopleHobby> peopleHobbys=new ArrayList<PeopleHobby>();
        Cursor c=queryAll();
        while(c.moveToNext()){
            PeopleHobby peopleHobby=new PeopleHobby();
            peopleHobby.setH_Id(c.getInt(c.getColumnIndex(H_Id)));
            peopleHobby.setH_field(c.getString(c.getColumnIndex(H_field)));
            peopleHobby.setH_Sport(c.getString(c.getColumnIndex(H_Sport)));
            peopleHobby.setH_Hate(c.getString(c.getColumnIndex(H_Hate)));
            peopleHobby.setH_Remark(c.getString(c.getColumnIndex(H_Remark)));
            peopleHobbys.add(peopleHobby);
        }
        c.close();
        return peopleHobbys;
    }
    public PeopleHobby queryById(int id){
        Cursor c=queryAll();
        PeopleHobby peopleHobby = new PeopleHobby();
        while(c.moveToNext()){
            if(id==c.getInt(c.getColumnIndex(H_Id))) {

                peopleHobby.setH_Id(c.getInt(c.getColumnIndex(H_Id)));
                peopleHobby.setH_field(c.getString(c.getColumnIndex(H_field)));
                peopleHobby.setH_Sport(c.getString(c.getColumnIndex(H_Sport)));
                peopleHobby.setH_Hate(c.getString(c.getColumnIndex(H_Hate)));
                peopleHobby.setH_Remark(c.getString(c.getColumnIndex(H_Remark)));

            }
        }
        c.close();
        return peopleHobby;
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
    public int query_HID(){

        Cursor curcor=queryAll();
        if(curcor.getCount()==0){
            return 0;
        }
        curcor.moveToLast();
        return curcor.getInt(curcor.getColumnIndex(H_Id));
    }
}
