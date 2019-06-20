package com.example.lenovo.accounting;


import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Expenses extends Fragment {
    TextView expenses;
    EditText inp1;
    TextView remarks1;

    public Expenses() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View expen = inflater.inflate(R.layout.fragment_expenses, container,
                false);
        inp1 = expen.findViewById(R.id.inp1);
        expenses = expen.findViewById(R.id.expenses);
        remarks1 = expen.findViewById(R.id.mark1);
        expen.findViewById(R.id.btn1).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //获取用户输入
                        String input1 = inp1.getText().toString();
                        float r1 = 0;
                        if (input1.length() > 0) {
                            r1 = Float.parseFloat(input1);
                        } else {
                            //提示用户输入内容
                            Toast.makeText(getActivity(), "请输入金额", Toast.LENGTH_SHORT).show();
                        }
                        showNum(r1);
                        String Date = (new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                        String marks1 = remarks1.toString();
                        List<AccountExItem> account1 = new ArrayList<AccountExItem>();
                        account1.add(new AccountExItem(marks1,input1,Date));
                    }

                    private void showNum(float f) {
                        Log.i("show", "i=" + f);
                        String oldNum = (String) expenses.getText();
                        float newNum = Float.parseFloat(oldNum) + f;
                        expenses.setText("" + newNum);
                        inp1.setText("");
                    }
                });
        return expen;
    }


}
