package com.tips.zy.tips.AddPeople.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tips.zy.tips.AddPeople.Entity.PeopleInfo;
import com.tips.zy.tips.AddPeople.Entity.PeopleInfoAll;

import java.util.ArrayList;
import java.util.List;

import zuo.biao.library.util.StringUtil;

/**
 * Created by zy on 2017/3/31.
 */

public class PeopleInfoHelper extends SQLiteOpenHelper{
    private static final String TAG="PeopleInfoHelper";
    private static final int TABLE_VERSION=1;
    public static final String TABLE_NAME = "PeopleInfo";
    public static final String P_Id="P_Id";
    public static final String P_Icon="P_Icon";
    public static final String P_Name="P_Name";
    public static final String P_Gender="P_Gender";
    public static final String P_Phone="P_Phone";
    public static final String P_Mail="P_Mail";
    public static final String P_Address="P_Address";
    public static final String P_National="P_National";
    public static final String P_Religion="P_Religion";
    public static final String P_Degree="P_Degree";
    public static final String P_BirthDay="P_BirthDay";
    public static final String P_Remark="P_Remark";

    public PeopleInfoHelper(Context context){
        super(context, TABLE_NAME, null, TABLE_VERSION);
    }
    public PeopleInfoHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public PeopleInfoHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + P_Id + " INTEGER primary key autoincrement, "
                + P_Icon + " text, " + P_Name + " text, " + P_Gender + " text, " + P_Phone + " text,"+P_Mail + " text," +
                P_Address + " text, " + P_National + " text, " + P_Religion + " text, " + P_Degree + " text,"+P_BirthDay +
                " text,"+ P_Remark + " text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /*
    *根据Curcor获取最上边的一条数据
    *
     */
    public PeopleInfo getValue(Cursor curcor){
        PeopleInfo value=null;
        if(curcor==null){
            value=new PeopleInfo();
            if(curcor.moveToFirst()){
                value.setP_Id(curcor.getInt(curcor.getColumnIndex(P_Id)));
                value.setP_Icon(curcor.getString(curcor.getColumnIndex(P_Icon)));
                value.setP_Name(curcor.getString(curcor.getColumnIndex(P_Name)));
                value.setP_Gender(curcor.getString(curcor.getColumnIndex(P_Gender)));
                value.setP_Phone(curcor.getString(curcor.getColumnIndex(P_Phone)));
                value.setP_Mail(curcor.getString(curcor.getColumnIndex(P_Mail)));
                value.setP_Address(curcor.getString(curcor.getColumnIndex(P_Address)));
                value.setP_National(curcor.getString(curcor.getColumnIndex(P_National)));
                value.setP_Religion(curcor.getString(curcor.getColumnIndex(P_Religion)));
                value.setP_Degree(curcor.getString(curcor.getColumnIndex(P_Degree)));
                value.setP_BirthDay(curcor.getString(curcor.getColumnIndex(P_BirthDay)));
                value.setP_Remark(curcor.getString(curcor.getColumnIndex(P_Remark)));
            }
        }
        return value;
    }
    /*
   *根据Curcor获取Curcor所有数据
   *
    */
    public List<PeopleInfo> getValueList(Cursor curcor){
        PeopleInfo value=null;
        List<PeopleInfo> list=null;
        if(curcor==null){
            list=new ArrayList<PeopleInfo>();

            while(curcor.moveToNext()){
                value=new PeopleInfo();
                value.setP_Id(curcor.getInt(curcor.getColumnIndex(P_Id)));
                value.setP_Icon(curcor.getString(curcor.getColumnIndex(P_Icon)));
                value.setP_Name(curcor.getString(curcor.getColumnIndex(P_Name)));
                value.setP_Gender(curcor.getString(curcor.getColumnIndex(P_Gender)));
                value.setP_Phone(curcor.getString(curcor.getColumnIndex(P_Phone)));
                value.setP_Mail(curcor.getString(curcor.getColumnIndex(P_Mail)));
                value.setP_Address(curcor.getString(curcor.getColumnIndex(P_Address)));
                value.setP_National(curcor.getString(curcor.getColumnIndex(P_National)));
                value.setP_Religion(curcor.getString(curcor.getColumnIndex(P_Religion)));
                value.setP_Degree(curcor.getString(curcor.getColumnIndex(P_Degree)));
                value.setP_BirthDay(curcor.getString(curcor.getColumnIndex(P_BirthDay)));
                value.setP_Remark(curcor.getString(curcor.getColumnIndex(P_Remark)));
                list.add(value);
            }
        }
        return list;
    }

    /*
    *插入数据
     */
    public void put(int id,PeopleInfo peopleInfo){
        ContentValues values=new ContentValues();
        //values.put(P_Id,peopleInfo.getP_Id());
        values.put(P_Icon,peopleInfo.getP_Icon());
        values.put(P_Name,peopleInfo.getP_Name());
        values.put(P_Gender,peopleInfo.getP_Gender());
        values.put(P_Phone,peopleInfo.getP_Phone());
        values.put(P_Mail,peopleInfo.getP_Mail());
        values.put(P_Address,peopleInfo.getP_Address());
        values.put(P_National,peopleInfo.getP_National());
        values.put(P_Religion,peopleInfo.getP_Religion());
        values.put(P_Degree,peopleInfo.getP_Degree());
        values.put(P_BirthDay,peopleInfo.getP_BirthDay());
        values.put(P_Remark,peopleInfo.getP_Remark());
        put(P_Id,""+id,values);
    }
    /**插入数据
     * @param column - 列名称
     * @param value - 筛选数据的条件值
     * @param values - 一行键值对数据
     */
    public void put(String column, String value, ContentValues values) {
        PeopleInfo peopleInfo = get(column, value);

        if (peopleInfo != null && String.valueOf(peopleInfo.getP_Id())!=null) {//数据存在且有效
            update(column, value, values);
        } else {
            insert(values);
        }
    }
    /**
     * 获取单个数据
     */
    public PeopleInfo get(int id){
        return getValue(query(id));
    }
    /**
     * 获取单个数据
     */
    public PeopleInfo get(String column,String value){
        return getValue(query(column,value));
    }
    /**
     * 获取数据列表
     */
    public List<PeopleInfo> getList(String column,String value){
        return getValueList(query(column,value));
    }
    /**
     * 获取所有数据
     */
    public List<PeopleInfo> getAll(){
        return getList(null, null);
    }
    /**
     * 插入数据
     */
    public long insert(ContentValues values){
        SQLiteDatabase db=this.getWritableDatabase();
        try{
            return db.insert(TABLE_NAME,null,values);

        }catch(Exception e){
            Log.d("insert",e.getMessage());
        }
        return -1;
    }
    /**
     * 更新数据
     */
    public int update(int id,ContentValues values){
        return update(P_Id,""+id,values);
    }
    /**
     * 更新数据
     */
    public int update(String column,String value,ContentValues values){
        SQLiteDatabase db=this.getWritableDatabase();
        try{
            return db.update(TABLE_NAME,values,getSelection(column),getSelectionArgs(column,value));
        }catch(Exception e){
            Log.d("update",e.getMessage());
        }
        return -1;
    }
    /**删除数据
     * @param id
     * @return
     */
    public int delete(int id) {
        return delete(P_Id, "" + id);
    }
    /**删除数据
     * @param column
     * @param value
     * @return
     */
    public int delete(String column, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            return db.delete(TABLE_NAME, getSelection(column), getSelectionArgs(column, value));
        } catch (Exception e) {
            zuo.biao.library.util.Log.e(TAG, "update   try { return db.delete(.... } catch (Exception e) {\n " + e.getMessage());
        }
        return 0;
    }

    /**查询所有数据
     * @return
     */
    public Cursor queryAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            return db.query(TABLE_NAME, null, null, null, null, null, null);
        } catch (Exception e) {
            zuo.biao.library.util.Log.e(TAG, "queryAll  try { return db.query(...} catch (Exception e) {\n" + e.getMessage());
        }
        return null;
    }
    /**查询单个数据
     * @param id
     * @return
     */
    public Cursor query(int id) {
        return query(P_Id, "" + id);
    }
    /**查询单个数据
     * @param column
     * @param value
     * @return
     */
    public Cursor query(String column, String value) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            return db.query(TABLE_NAME, null, getSelection(column), getSelectionArgs(column, value), null, null, null);
        } catch (Exception e) {
            zuo.biao.library.util.Log.e(TAG, "query  try { return db.query(...} catch (Exception e) {\n" + e.getMessage());
        }
        return null;
    }



    /**获取过滤条件类型
     * @param column
     * @return StringUtil.isNotEmpty(column, false) ? column + " = ?" : null
     */
    private String getSelection(String column) {
        return StringUtil.isNotEmpty(column, false) ? column + " = ?" : null;
    }
    /**获取过滤条件值
     * @param column
     * @return StringUtil.isNotEmpty(column, false) ? new String[]{value} : nul
     */
    private String[] getSelectionArgs(String column, String value) {
        return StringUtil.isNotEmpty(column, false) ? new String[]{value} : null;
    }

    public void addPeopleInfo(List<PeopleInfo> peopleInfos){
        SQLiteDatabase db=this.getWritableDatabase();
        //db.beginTransaction();
        try{
            for(PeopleInfo peopleInfo:peopleInfos){

                db.execSQL("INSERT INTO "+TABLE_NAME +" VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        new Object[]{peopleInfo.getP_Icon(),peopleInfo.getP_Name(),
                                peopleInfo.getP_Gender(),peopleInfo.getP_Phone(),
                                peopleInfo.getP_Mail(),peopleInfo.getP_Address(),
                                peopleInfo.getP_National(),peopleInfo.getP_Religion(),
                                peopleInfo.getP_Degree(),peopleInfo.getP_BirthDay(),
                                peopleInfo.getP_Remark()});
            }
            //db.setTransactionSuccessful();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<PeopleInfo> query(){
        ArrayList<PeopleInfo> peopleInfos=new ArrayList<PeopleInfo>();
        Cursor curcor=queryAll();
        while(curcor.moveToNext()){
            PeopleInfo peopleInfo=new PeopleInfo();
            peopleInfo.setP_Id(curcor.getInt(curcor.getColumnIndex(P_Id)));
            peopleInfo.setP_Icon(curcor.getString(curcor.getColumnIndex(P_Icon)));
            peopleInfo.setP_Name(curcor.getString(curcor.getColumnIndex(P_Name)));
            peopleInfo.setP_Gender(curcor.getString(curcor.getColumnIndex(P_Gender)));
            peopleInfo.setP_Phone(curcor.getString(curcor.getColumnIndex(P_Phone)));
            peopleInfo.setP_Mail(curcor.getString(curcor.getColumnIndex(P_Mail)));
            peopleInfo.setP_Address(curcor.getString(curcor.getColumnIndex(P_Address)));
            peopleInfo.setP_National(curcor.getString(curcor.getColumnIndex(P_National)));
            peopleInfo.setP_Religion(curcor.getString(curcor.getColumnIndex(P_Religion)));
            peopleInfo.setP_Degree(curcor.getString(curcor.getColumnIndex(P_Degree)));
            peopleInfo.setP_BirthDay(curcor.getString(curcor.getColumnIndex(P_BirthDay)));
            peopleInfo.setP_Remark(curcor.getString(curcor.getColumnIndex(P_Remark)));

            zuo.biao.library.util.Log.d("helperpeopleInfoAll",peopleInfo.toString());
            peopleInfos.add(peopleInfo);
        }
        curcor.close();
        return peopleInfos;
    }
    public int query_PID(){

        Cursor curcor=queryAll();
        if(curcor.getCount()==0){
            return 0;
        }
        curcor.moveToLast();
        return curcor.getInt(curcor.getColumnIndex(P_Id));
    }
    public PeopleInfo queryById(int id){
        //ArrayList<PeopleInfo> peopleInfos=new ArrayList<PeopleInfo>();
        PeopleInfo peopleInfo=new PeopleInfo();
        Cursor curcor=queryAll();
        while(curcor.moveToNext()){

            if(curcor.getInt(curcor.getColumnIndex(P_Id))==id) {

                peopleInfo.setP_Id(curcor.getInt(curcor.getColumnIndex(P_Id)));
                peopleInfo.setP_Icon(curcor.getString(curcor.getColumnIndex(P_Icon)));
                peopleInfo.setP_Name(curcor.getString(curcor.getColumnIndex(P_Name)));
                peopleInfo.setP_Gender(curcor.getString(curcor.getColumnIndex(P_Gender)));
                peopleInfo.setP_Phone(curcor.getString(curcor.getColumnIndex(P_Phone)));
                peopleInfo.setP_Mail(curcor.getString(curcor.getColumnIndex(P_Mail)));
                peopleInfo.setP_Address(curcor.getString(curcor.getColumnIndex(P_Address)));
                peopleInfo.setP_National(curcor.getString(curcor.getColumnIndex(P_National)));
                peopleInfo.setP_Religion(curcor.getString(curcor.getColumnIndex(P_Religion)));
                peopleInfo.setP_Degree(curcor.getString(curcor.getColumnIndex(P_Degree)));
                peopleInfo.setP_BirthDay(curcor.getString(curcor.getColumnIndex(P_BirthDay)));
                peopleInfo.setP_Remark(curcor.getString(curcor.getColumnIndex(P_Remark)));

                zuo.biao.library.util.Log.d("helperpeopleInfoAll", peopleInfo.toString());
                //peopleInfos.add(peopleInfo);
            }
        }
        curcor.close();
        return peopleInfo;
    }

}
