package com.tips.zy.tips.AddPeople.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tips.zy.tips.AddPeople.Entity.PeopleCharacter;
import com.tips.zy.tips.AddPeople.Entity.PeopleInfoAll;

import java.util.ArrayList;
import java.util.List;

import zuo.biao.library.util.Log;

/**
 * Created by zy on 2017/5/29.
 */

public class PeopleInfoAllHelper extends SQLiteOpenHelper {

    private static final String TAG="PeopleALLInfoHelper";
    public static final int TABLE_VERSION=1;
    public static final String TABLE_NAME = "PeopleALLInfo";
    public static final String User_Name="User_Name";
    public static final String G_Name="G_Name";
    public static final String P_Id="P_Id";
    public static final String W_Id="W_Id";
    public static final String H_Id="H_Id";
    public static final String C_Id="C_Id";


    public PeopleInfoAllHelper(Context context) {
        super(context,TABLE_NAME,null,TABLE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE "+TABLE_NAME+" ("+
                User_Name+ " text, "+
                G_Name+ " text, "+P_Id +" Integer primary key, "+ W_Id +" Integer, "
                + H_Id +" Integer, " + C_Id + " Integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }


    /**
     *添加peopleCharacter
     */
    public void addPeopeInfoAll(List<PeopleInfoAll> peopleInfoAlls){
        SQLiteDatabase db=this.getWritableDatabase();
        //db.beginTransaction();
        try{
            for(PeopleInfoAll peopleInfoAll:peopleInfoAlls){

                db.execSQL("INSERT INTO "+TABLE_NAME +" VALUES(?, ?, ?, ?, ?, ?)",
                        new Object[]{peopleInfoAll.getUser_Name(),
                                peopleInfoAll.getG_Name(),peopleInfoAll.getP_Id(),
                        peopleInfoAll.getW_Id(),peopleInfoAll.getH_Id(),
                        peopleInfoAll.getC_Id()});
            }
            //db.setTransactionSuccessful();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 更新peopleCharacter数据
     * @return
     */
    public void updatePeopleInfoAll(PeopleInfoAll peopleInfoAll){
        ContentValues cv=new ContentValues();
        cv.put(User_Name,peopleInfoAll.getUser_Name());
        cv.put(G_Name,peopleInfoAll.getG_Name());
        cv.put(P_Id,peopleInfoAll.getP_Id());
        cv.put(W_Id,peopleInfoAll.getW_Id());
        cv.put(H_Id,peopleInfoAll.getH_Id());
        cv.put(C_Id,peopleInfoAll.getC_Id());
        SQLiteDatabase db=this.getReadableDatabase();
        db.update(TABLE_NAME,cv,"P_Id = ?",new String[]{String.valueOf(peopleInfoAll.getP_Id())});
    }

    /**
     * 删除peopleCharacter信息
     * @param peopleCharacter
     */
    public void deletePeopleHobby(PeopleCharacter peopleCharacter){
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete(TABLE_NAME,"C_Id=?",new String[]{String.valueOf(peopleCharacter.getC_Id())});
    }

    /**
     *将查询数据转换为实体返回
     * @return
     */
    public List<PeopleInfoAll> query(){
        ArrayList<PeopleInfoAll> peopleInfoAlls=new ArrayList<PeopleInfoAll>();
        Cursor c=queryTheCursor();
        while(c.moveToNext()){
            PeopleInfoAll peopleInfoAll=new PeopleInfoAll();
            peopleInfoAll.setUser_Name(c.getString(c.getColumnIndex(User_Name)));
            peopleInfoAll.setG_Name(c.getString(c.getColumnIndex(G_Name)));
            peopleInfoAll.setP_Id(c.getInt(c.getColumnIndex(P_Id)));
            peopleInfoAll.setW_Id(c.getInt(c.getColumnIndex(W_Id)));
            peopleInfoAll.setH_Id(c.getInt(c.getColumnIndex(H_Id)));
            peopleInfoAll.setC_Id(c.getInt(c.getColumnIndex(C_Id)));

            Log.d("helperpeopleInfoAll",peopleInfoAll.toString());
            peopleInfoAlls.add(peopleInfoAll);
        }
        c.close();
        return peopleInfoAlls;
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
        return db.rawQuery("SELECT * FROM "+TABLE_NAME+" order by "+G_Name,null);
    }
    public List<String> queryG_Name(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT "+G_Name+" FROM "+TABLE_NAME+" group by "+G_Name,null);
        List<String> G_Names=new ArrayList<>();
        while(cursor.moveToNext()){
            G_Names.add(cursor.getString(cursor.getColumnIndex(G_Name)));
        }
        return G_Names;
    }
    public List<PeopleInfoAll> queryByG_Name(String G_N){
        ArrayList<PeopleInfoAll> peopleInfoAlls=new ArrayList<PeopleInfoAll>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM "+TABLE_NAME+" where "+G_Name +" = "+"'"+G_N+"'",null);

        while(c.moveToNext()){
            PeopleInfoAll peopleInfoAll=new PeopleInfoAll();
            peopleInfoAll.setUser_Name(c.getString(c.getColumnIndex(User_Name)));
            peopleInfoAll.setG_Name(c.getString(c.getColumnIndex(G_Name)));
            peopleInfoAll.setP_Id(c.getInt(c.getColumnIndex(P_Id)));
            peopleInfoAll.setW_Id(c.getInt(c.getColumnIndex(W_Id)));
            peopleInfoAll.setH_Id(c.getInt(c.getColumnIndex(H_Id)));
            peopleInfoAll.setC_Id(c.getInt(c.getColumnIndex(C_Id)));

            Log.d("helperpeopleInfoAll",peopleInfoAll.toString());
            peopleInfoAlls.add(peopleInfoAll);
        }
        c.close();
        return peopleInfoAlls;
    }
    public List<PeopleInfoAll> queryByP_Id(int id){
        ArrayList<PeopleInfoAll> peopleInfoAlls=new ArrayList<PeopleInfoAll>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM "+TABLE_NAME+" where "+P_Id +" = "+"'"+id+"'",null);

        while(c.moveToNext()){
            PeopleInfoAll peopleInfoAll=new PeopleInfoAll();
            peopleInfoAll.setUser_Name(c.getString(c.getColumnIndex(User_Name)));
            peopleInfoAll.setG_Name(c.getString(c.getColumnIndex(G_Name)));
            peopleInfoAll.setP_Id(c.getInt(c.getColumnIndex(P_Id)));
            peopleInfoAll.setW_Id(c.getInt(c.getColumnIndex(W_Id)));
            peopleInfoAll.setH_Id(c.getInt(c.getColumnIndex(H_Id)));
            peopleInfoAll.setC_Id(c.getInt(c.getColumnIndex(C_Id)));

            Log.d("helperpeopleInfoAll",peopleInfoAll.toString());
            peopleInfoAlls.add(peopleInfoAll);
        }
        c.close();
        return peopleInfoAlls;
    }
}
