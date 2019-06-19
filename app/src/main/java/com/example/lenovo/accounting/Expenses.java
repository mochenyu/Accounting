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


/**
 * A simple {@link Fragment} subclass.
 */
public class Expenses extends Fragment {
    TextView expenses, income;
    EditText inp1, inp2;
    TextView remarks1, remarks2;

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
                        String input = inp1.getText().toString();
                        float r1 = 0;
                        if (input.length() > 0) {
                            r1 = Float.parseFloat(input);
                        } else {
                            //提示用户输入内容
                            Toast.makeText(getActivity(), "请输入金额", Toast.LENGTH_SHORT).show();
                        }
                            showNum(r1);
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
