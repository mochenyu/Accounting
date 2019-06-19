package com.example.lenovo.accounting;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class AccountListActivity extends ListActivity {
    public  final String TAG = "AccountListActivity" ;
    String date[] = {"wait......"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list);父类中已包含布局，不需要填充
        ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,date);
        setListAdapter(adapter);
    }
}
