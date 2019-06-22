package com.example.lenovo.accounting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExManager {
    private DBHelper dbHelper;
    private String TBNAME;

    public ExManager(Context context){
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME1;
    }

    //添加一条数据
    public void add(AccountExItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CurMark",item.getCurMark());
        values.put("CurEx",item.getCurEx());
        values.put("CurDate",item.getCurDate());
        db.insert(TBNAME,null,values);
        db.close();
    }

    public void add2(HashMap<String,String> map){

    }

    //删除一条数据
    public void delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME,"ID=?",new String[]{String.valueOf(id)});
        db.close();
    }

    //添加所有数据
    public void addAll(List<AccountExItem> list){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (AccountExItem item : list){
            ContentValues values = new ContentValues();
            values.put("curMark",item.getCurMark());
            values.put("curEx",item.getCurEx());
            values.put("curDate",item.getCurDate());
            db.insert(TBNAME,null,values);
        }
        db.close();
    }

    //删除所有数据
    public void deleteAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME,null,null);
        db.close();
    }

    public List<AccountExItem> listAll(){
        List<AccountExItem> rateList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME,null,null,null,null,null,null);
        if (cursor!=null){
            rateList = new ArrayList<AccountExItem>();
            while (cursor.moveToNext()){
                AccountExItem item = new AccountExItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setCurMark(cursor.getString(cursor.getColumnIndex("CURMARK")));
                item.setCurEx(cursor.getString(cursor.getColumnIndex("CUREX")));
                item.setCurDate(cursor.getString(cursor.getColumnIndex("CURDATE")));
                rateList.add(item);
            }
            cursor.close();
        }
        db.close();
        return rateList;
    }

    public Float NUM() {
        Float r = 0.0f;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                AccountExItem item = new AccountExItem();
                r = r + Float.parseFloat(cursor.getString(cursor.getColumnIndex("CUREX")));
            }
            cursor.close();
        }
        db.close();
        return r;
    }

    public HashMap<String, String> getData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);
        HashMap<String, String> map = null;
        if (cursor != null) {
            while (cursor.moveToNext()) {
                map = new HashMap<String, String>();
                map.put("ItemMarks", cursor.getString(cursor.getColumnIndex("CURMARK")));
                map.put("ItemEx", cursor.getString(cursor.getColumnIndex("CUREX")));
                map.put("ItemDate", cursor.getString(cursor.getColumnIndex("CURDATE")));
            }
            cursor.close();
        }
        db.close();
        return map;
    }
    /*public List<HashMap<String, String>> getData(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);
        List<HashMap<String,String>> listitems = null;
        if (cursor != null) {
            listitems = new ArrayList<HashMap<String, String>>();
            while (cursor.moveToNext()) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("ItemMarks", cursor.getString(cursor.getColumnIndex("CURMARK")));
                map.put("ItemEx", cursor.getString(cursor.getColumnIndex("CUREX")));
                map.put("ItemDate", cursor.getString(cursor.getColumnIndex("CURDATE")));
                listitems.add(map);
            }
            cursor.close();
        }
        db.close();
        return listitems;
    }*/
}
