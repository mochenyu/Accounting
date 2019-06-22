package com.example.lenovo.accounting;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView expenses, income;
    EditText inp1, inp2;
    TextView mark1, mark2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.viewpager);
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myAdapter);

        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);//将当前标题与页面绑定

        expenses = findViewById(R.id.income);
        inp1 = findViewById(R.id.inp2);
        mark1 = findViewById(R.id.mark2);
    }

    public void onClick(View btn) {

    }

    public void openOne(View btn) {
        //打开计算器
        Intent mIntent = new Intent();
        mIntent.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");
        startActivity(mIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.accounting,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //打开列表窗口
        if (item.getItemId()==R.id.item1){
            Intent accout1 = new Intent(this,Account1Activity.class);
            startActivity(accout1);
        }else if (item.getItemId()==R.id.item2){
            Intent accout2 = new Intent(this,Account2Activity.class);
            startActivity(accout2);
        }
        return super.onOptionsItemSelected(item);
    }
}

