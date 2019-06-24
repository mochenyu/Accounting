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

    //删除一条数据
    public void delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME,"ID=?",new String[]{String.valueOf(id)});
        db.close();
    }

    //删除所有数据
    public void deleteAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME,null,null);
        db.close();
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

    public List<HashMap<String, String>> getDataAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);
        List<HashMap<String, String>> listItems = null;
        if (cursor != null) {
            listItems = new ArrayList<HashMap<String, String>>();
            while (cursor.moveToNext()) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("ItemMarks", cursor.getString(cursor.getColumnIndex("CURMARK")));
                map.put("ItemEx", "-"+cursor.getString(cursor.getColumnIndex("CUREX")));
                map.put("ItemDate", cursor.getString(cursor.getColumnIndex("CURDATE")));
                listItems.add(map);
            }
            cursor.close();
        }
        db.close();
        return listItems;
    }
}
