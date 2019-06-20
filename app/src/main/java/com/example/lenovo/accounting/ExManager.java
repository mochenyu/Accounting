package com.example.lenovo.accounting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
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
        db.insert(TBNAME,null,values);
        db.close();
    }

    //添加所有数据
    public void addAll(List<AccountExItem> list){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (AccountExItem item : list){
            ContentValues values = new ContentValues();
            values.put("CurMark",item.getCurMark());
            values.put("CurEx",item.getCurEx());
            db.insert(TBNAME,null,values);
        }
        db.close();
    }

    //删除一条数据
    public void delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME,"ID=?",new String[]{String.valueOf(id)});
        db.close();
    }

    //更新数据
    public void update(AccountExItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CurMark",item.getCurMark());
        values.put("CurEx",item.getCurEx());
        db.update(TBNAME,values,"ID=?",new String[]{String.valueOf(item.getId())});
        db.close();
    }

    //删除所有数据
    public void deleteAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME,null,null);
        db.close();
    }

    //查询数据
    public AccountExItem findById(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME,null,"ID=?",new String[]{String.valueOf(id)},null,null,null);
        AccountExItem ExItem = null;
        if (cursor!=null && cursor.moveToFirst()){
            ExItem = new AccountExItem();
            ExItem.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            ExItem.setCurMark(cursor.getString(cursor.getColumnIndex("CURMARK")));
            ExItem.setCurEx(cursor.getString(cursor.getColumnIndex("CUREX")));
            cursor.close();//关闭光标
        }
        db.close();
        return  ExItem;
    }

    public List<AccountExItem> listAll(){
        List<AccountExItem> ExList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME,null,null,null,null,null,null);
        if (cursor!=null){
            ExList = new ArrayList<AccountExItem>();
            while (cursor.moveToNext()){
                AccountExItem item = new AccountExItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setCurMark(cursor.getString(cursor.getColumnIndex("CURMARK")));
                item.setCurEx(cursor.getString(cursor.getColumnIndex("CUREX")));
                ExList.add(item);
            }
            cursor.close();
        }
        db.close();
        return ExList;
    }
}
