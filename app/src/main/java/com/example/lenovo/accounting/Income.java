package com.example.lenovo.accounting;


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
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class Income extends Fragment {
    TextView income;
    EditText inp2;
    TextView remarks2;
    String marks2,input2,Date;
    AccountInItem item;
    Float newNum;

    public Income() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inco = inflater.inflate(R.layout.fragment_income, container,
                false);
        inp2 = inco.findViewById(R.id.inp2);
        income = inco.findViewById(R.id.income);
        remarks2 = inco.findViewById(R.id.mark2);

        InManager manager = new InManager(getActivity());
        income.setText(manager.NUM().toString());

        inco.findViewById(R.id.btn2).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //获取用户输入
                        input2 = inp2.getText().toString();
                        float r1 = 0;
                        if (input2.length() > 0) {
                            r1 = Float.parseFloat(input2);
                            marks2 = remarks2.getText().toString();
                            Date = (new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

                            InManager manager = new InManager(getActivity());
                            item = new AccountInItem(marks2,input2,Date);
                            manager.add(item);
                            Log.i("DBHelper", "onClick: 写入成功");
                        } else {
                            //提示用户输入内容
                            Toast.makeText(getActivity(), "请输入金额", Toast.LENGTH_SHORT).show();
                        }
                        showNum(r1);
                    }

                    private void showNum(float f) {
                        Log.i("show", "i=" + f);
                        String oldNum = (String) income.getText();
                        newNum = Float.parseFloat(oldNum) + f;
                        income.setText("" + newNum);
                        inp2.setText("");
                    }
                });
        return inco;
    }
}
