package com.example.lenovo.accounting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InManager {
    private DBHelper dbHelper;
    private String TBNAME;

    public InManager(Context context){
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME2;
    }

    //添加一条数据
    public void add(AccountInItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CurMark",item.getCurMark());
        values.put("CurIn",item.getCurIn());
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

    //添加所有数据
    public void addAll(List<AccountInItem> list){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (AccountInItem item : list){
            ContentValues values = new ContentValues();
            values.put("curMark",item.getCurMark());
            values.put("curIn",item.getCurIn());
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

    public Float NUM() {
        Float r = 0.0f;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                AccountInItem item = new AccountInItem();
                r = r + Float.parseFloat(cursor.getString(cursor.getColumnIndex("CURIN")));
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
                map.put("ItemIn", cursor.getString(cursor.getColumnIndex("CURIN")));
                map.put("ItemDate", cursor.getString(cursor.getColumnIndex("CURDATE")));
                listItems.add(map);
            }
            cursor.close();
        }
        db.close();
        return listItems;
    }
}
