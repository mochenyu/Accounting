package com.example.lenovo.accounting;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class Account2Activity extends ListActivity implements AdapterView.OnItemLongClickListener,Runnable{
    public  final String TAG = "Account2Activity" ;//存放文字、图片信息
    private SimpleAdapter listItemAdapter;//适配器
    private List<String> listItems;
    String date[] = {"wait......"};
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_account2);
        //this.setListAdapter(listItemAdapter);
        android.widget.ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,date);
        setListAdapter(adapter);
        getListView().setOnItemLongClickListener(this);

        Thread t = new Thread(this);
        t.start();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what==7){
                    List<String> list2 = (List<String>) msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(Account2Activity.this,android.R.layout.simple_list_item_1,list2);
                    setListAdapter(adapter);
                }
            }
        };
        //initListView();
    }

    /*private void initListView(){
        listItems = new ArrayList<String>();
        ExManager manager = new ExManager(this);
        HashMap<String,String> map = new HashMap<String, String>();
        for (AccountExItem item : manager.listAll()){
            listItems.add(item.getCurMark()+"-->"+item.getCurEx()+"-->"+item.getCurDate());
        }*/
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
    //生成适配器的Item和动态数组对应元素
        /*listItemAdapter = new SimpleAdapter(this,listItems,//listItems数据源
                R.layout.activity_account1,//listItem的XML布局实现
                new String[] {"ItemMarks","ItemEx","ItemDate"},
                new int[] {R.id.itemMarks,R.id.itemEx,R.id.itemDate});*/

    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        Log.i(TAG, "onItemLongClick: 长按列表项position="+position);
        //删除操作
        Log.i(TAG, "onItemLongClick: size="+listItems.size());
        //构造对话框进行确认操作
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("请确认是否删除该条记录").setPositiveButton("是", new DialogInterface.OnClickListener() {
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

    @Override
    public void run() {
        listItems = new ArrayList<String>();
        InManager manager = new InManager(this);
        //HashMap<String, String> map = new HashMap<String, String>();
        for (AccountInItem item : manager.listAll()) {
            listItems.add(item.getCurMark() + " : " + item.getCurIn() + "元 于" + item.getCurDate());
        }
        Message msg = handler.obtainMessage(7);
        msg.obj = listItems;
        handler.sendMessage(msg);
    }
}
