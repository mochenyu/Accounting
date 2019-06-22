package com.example.lenovo.accounting;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
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
    //private List<String> listItems;
    private List<HashMap<String,String>> listItems;
    String date[] = {"wait......"};
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*//setContentView(R.layout.activity_my_list1);
        //this.setListAdapter(listItemAdapter);
        ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,date);
        setListAdapter(adapter);

        Thread t = new Thread(this);
        t.start();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what==7){
                    List<String> list2 = (List<String>) msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(Account1Activity.this,android.R.layout.simple_list_item_1,list2);
                    setListAdapter(adapter);
                }
            }
        };
        */
        initListView();
        this.setListAdapter(listItemAdapter);
        getListView().setOnItemLongClickListener(this);
    }

    private void initListView() {
        listItems = new ArrayList<HashMap<String,String>>();

        //生成适配器的Item和动态数组对应元素
        listItemAdapter = new SimpleAdapter(this, listItems,//listItems数据源
                R.layout.activity_account1,//listItem的XML布局实现
                new String[]{"ItemMarks", "ItemEx", "ItemDate"},
                new int[]{R.id.itemMarks, R.id.itemEx, R.id.itemDate});

        ExManager manager = new ExManager(this);
        listItems.add(manager.getData());

            /*map.put("ItemMarks",item.getCurMark());
            map.put("ItemEx",item.getCurEx());
            map.put("ItemDate",item.getCurDate());
            listItems.add(map);*/

        /*for (int i=1;i<100;i++){
            HashMap<String,String> map = new HashMap<String, String>();
            map.put("ItemMarks",i+"");
            map.put("ItemEx",i+"");
            map.put("ItemDate",i+"");
            listItems.add(map);
        }*/
    }
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
            Log.i(TAG, "onItemLongClick: 长按列表项position="+position);
            //删除操作
            //listItems.remove(position);
            //listItemAdapter.notifyDataSetChanged();
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

    /*@Override
    public void run() {
        listItems = new ArrayList<String>();
        ExManager manager = new ExManager(this);
        //HashMap<String, String> map = new HashMap<String, String>();
        for (AccountExItem item : manager.listAll()) {
            listItems.add(item.getCurMark() + " : -" + item.getCurEx() + "元 于" + item.getCurDate());
        }
        Message msg = handler.obtainMessage(7);
        msg.obj = listItems;
        handler.sendMessage(msg);*/
    }
