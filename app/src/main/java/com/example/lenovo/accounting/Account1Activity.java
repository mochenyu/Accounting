package com.example.lenovo.accounting;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Account1Activity extends ListActivity {
    public  final String TAG = "Account1Activity" ;
    Handler handler;
    private List<HashMap<String,String>> listItems;//存放文字、图片信息
    private SimpleAdapter listItemAdapter;//适配器
    TextView itemMarks,itemDate,itemEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();
        this.setListAdapter(listItemAdapter);

        itemMarks = findViewById(R.id.itemMarks);
        itemEx = findViewById(R.id.itemEx);
        itemDate = findViewById(R.id.itemDate);
    }

    private void initListView(){
        listItems = new ArrayList<HashMap<String,String>>();
        for (int i=1;i<10;i++){
            HashMap<String,String> map = new HashMap<String, String>();
            map.put("ItemMarks","Rate:"+i);//备注
            map.put("ItemEx",""+i);//金额
            map.put("ItemDate",""+i);//日期
            listItems.add(map);
        }
        //生成适配器的Item和动态数组对应元素
        listItemAdapter = new SimpleAdapter(this,listItems,//listItems数据源
                R.layout.activity_account1,//listItem的XML布局实现
                new String[] {"ItemMarks","ItemEx","ItemDate"},
                new int[] {R.id.itemMarks,R.id.itemEx,R.id.itemDate});
    }
}
