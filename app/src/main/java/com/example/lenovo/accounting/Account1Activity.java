package com.example.lenovo.accounting;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Account1Activity extends ListActivity implements AdapterView.OnItemLongClickListener {
    public  final String TAG = "Account1Activity" ;//存放文字、图片信息
    private SimpleAdapter listItemAdapter;//适配器
    private List<HashMap<String,String>> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();
        this.setListAdapter(listItemAdapter);
        getListView().setOnItemLongClickListener(this);
    }

    private void initListView() {
        listItems = new ArrayList<HashMap<String, String>>();
        ExManager manager = new ExManager(this);
        listItems = manager.getDataAll();

        //生成适配器的Item和动态数组对应元素
        listItemAdapter = new SimpleAdapter(this, listItems,//listItems数据源
                R.layout.activity_account1,//listItem的XML布局实现
                new String[]{"ItemMarks", "ItemEx", "ItemDate"},
                new int[]{R.id.itemMarks, R.id.itemEx, R.id.itemDate});
    }
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        Log.i(TAG, "onItemLongClick: 长按列表项position="+position);
            //删除操作
            Log.i(TAG, "onItemLongClick: size="+listItems.size());
            //构造对话框进行确认操作
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示").setMessage("请确认是否删除当前数据").setPositiveButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.i(TAG, "onClick: 对话框事件处理");
                    listItems.remove(position);
                    listItemAdapter.notifyDataSetChanged();
                }
            }).setNegativeButton("否",null);
            builder.create().show();
            return true;
        }
    }
