package com.example.lenovo.accounting;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        expenses = findViewById(R.id.expenses);
        inp1 = findViewById(R.id.inp1);
        mark1 = findViewById(R.id.mark1);
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
            Intent accout1 = new Intent(this,AccountListActivity.class);
            startActivity(accout1);
        }else if (item.getItemId()==R.id.item2){

        }
        return super.onOptionsItemSelected(item);
    }

    //传递参数
    /*Intent config = new Intent(this,CfgActivity.class);
        config.putExtra("expenses_key","expenses");
        Log.i("", "openOne: expenses_key="+expenses);
    startActivityForResult(config,1);*/

//另一个页面获取传递的数据
    /*Intent intent = getIntent();
    float expenses2 = intent.getFloatExtra("expenses_key",0.0f);
        Log.i(TAG, "onCreate: expenses2="+expenses2);

        dollarText = findViewById(R.id.dollar_rate);
     //显示数据到控件
        dollarText.setText(String.valueOf(dollar2));

        public void save(View btn){
        //获取新的值
        float newDollar = Float.parseFloat(dollarText.getText().toString)
        }
        Intent intent = getIntent();
        Bundle bdl = new Bundle();
        bdl.putDouble("new_dollarRate",newdollar);
        intent.putExtras(bdl);
        setResult(2,intent);
        //返回调用页面
        finish();//结束当前页面回到上一个页面

        //处理带回数据
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==2){
            Bundle bundle = data.getExtras();
            dollarRate = bundle.getFloat("new_dollarRate",1/6.7f);
            Log.i(TAG, "onActivityResult: dollarRate="+dollarRate);
            //将新设置的汇率写到sp里
            SharedPreferences sharedPreferences = getSharedPreferences("myrate",Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putFloat("dollar_rate",dollarRate);
            editor.commit();
            Log.i(TAG, "onActivityResult: 数据已保存");
        }
    }
        */
}

