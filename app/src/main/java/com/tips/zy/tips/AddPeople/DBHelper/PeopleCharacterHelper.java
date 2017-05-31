package com.tips.zy.tips.AddPeople.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tips.zy.tips.AddPeople.Entity.PeopleCharacter;
import com.tips.zy.tips.AddPeople.Entity.PeopleHobby;

import java.util.ArrayList;
import java.util.List;

import zuo.biao.library.util.Log;

/**
 * Created by zy on 2017/5/21.
 */

public class PeopleCharacterHelper extends SQLiteOpenHelper{

    private static final String TAG="PeopleCharacterHelper";
    public static final int TABLE_VERSION=1;
    public static final String TABLE_NAME = "PeopleCharacter";
    public static final String C_Id="C_Id";
    public static final String C_Character="C_Character";
    public static final String C_Remark="C_Remark";
    public PeopleCharacterHelper(Context context){
        super(context, TABLE_NAME, null, TABLE_VERSION);
    }
    public PeopleCharacterHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, TABLE_NAME, null, TABLE_VERSION);
    }

    public PeopleCharacterHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql="CREATE TABLE "+TABLE_NAME+" ("+
                C_Id+ " INTEGER primary key autoincrement, "+
                C_Character+ " text, "+C_Remark +" text )";
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
    public void addPeopleCharacter(List<PeopleCharacter> peopleCharacters){
        SQLiteDatabase db=this.getWritableDatabase();
        //db.beginTransaction();
        try{
            //int id=1;
            for(PeopleCharacter peopleCharacter:peopleCharacters){

                db.execSQL("INSERT INTO "+TABLE_NAME +" VALUES(null, ?, ?)",
                        new Object[]{peopleCharacter.getC_Character(),
                                peopleCharacter.getC_Remark()});

                //Log.d("id",id+"");
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
    public void updatePeopleCharacter(PeopleCharacter peopleCharacter){
        ContentValues cv=new ContentValues();
        cv.put(C_Character,peopleCharacter.getC_Character());
        cv.put(C_Remark,peopleCharacter.getC_Remark());
        SQLiteDatabase db=this.getReadableDatabase();
        db.update(TABLE_NAME,cv,"C_Id = ?",new String[]{String.valueOf(peopleCharacter.getC_Id())});
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
    public List<PeopleCharacter> query(){
        ArrayList<PeopleCharacter> peopleCharacters=new ArrayList<PeopleCharacter>();
        Cursor c=queryAll();
        while(c.moveToNext()){
            PeopleCharacter peopleCharacter=new PeopleCharacter();
            peopleCharacter.setC_Id(c.getInt(c.getColumnIndex(C_Id)));
            peopleCharacter.setC_Character(c.getString(c.getColumnIndex(C_Character)));
            peopleCharacter.setC_Remark(c.getString(c.getColumnIndex(C_Remark)));
            Log.d("helperpeopleCharacter",peopleCharacter.toString());
            peopleCharacters.add(peopleCharacter);
        }
        c.close();
        return peopleCharacters;
    }

    /**
     * 通过id 查询PeopleCharactor
     * @param id
     * @return
     */
    public PeopleCharacter queryById(int id){
        PeopleCharacter peopleCharacter=new PeopleCharacter();
        Cursor c=queryAll();
        while(c.moveToNext()){
            if(id==c.getInt(c.getColumnIndex(C_Id))) {
                peopleCharacter.setC_Id(c.getInt(c.getColumnIndex(C_Id)));
                peopleCharacter.setC_Character(c.getString(c.getColumnIndex(C_Character)));
                peopleCharacter.setC_Remark(c.getString(c.getColumnIndex(C_Remark)));
                Log.d("helperpeopleCharacter", peopleCharacter.toString());
            }
        }
        c.close();
        return peopleCharacter;
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
    public int query_CID(){

        Cursor curcor=queryAll();
        if(curcor.getCount()==0){
            return 0;
        }
        curcor.moveToLast();
        return curcor.getInt(curcor.getColumnIndex(C_Id));
    }
}
