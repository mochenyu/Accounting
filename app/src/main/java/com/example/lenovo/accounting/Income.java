package com.example.lenovo.accounting;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Income extends Fragment {
    TextView income,remarks2,total2;
    EditText inp2;
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
        total2 = inco.findViewById(R.id.total2);

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
        ExManager manager1 = new ExManager(getActivity());
        InManager manager2 = new InManager(getActivity());
        income.setText(manager2.NUM().toString());
        Float val = manager2.NUM()-manager1.NUM();
        total2.setText(val.toString());

        inco.findViewById(R.id.btn4).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //打开计算器
                        PackageInfo pak = getAllApps(getActivity(), "Calculator","calculator");//大小写
                        if(pak != null){
                            Intent intent = new Intent();
                            intent = getActivity().getPackageManager().getLaunchIntentForPackage(pak.packageName);
                            startActivity(intent);
                        }else {
                            Toast.makeText(getActivity(), "未找到计算器", Toast.LENGTH_SHORT).show();
                        }
                    }

                    public PackageInfo getAllApps(Context context, String app_flag_1, String app_flag_2) {
                        PackageManager pManager = context.getPackageManager();
                        //获取手机内所有应用
                        List<PackageInfo> packlist = pManager.getInstalledPackages(0);
                        for (int i = 0; i < packlist.size(); i++) {
                            PackageInfo pak = (PackageInfo) packlist.get(i);
                            if(pak.packageName.contains(app_flag_1)||pak.packageName.contains(app_flag_2)){
                                return pak;
                            }
                        }
                        return null;
                    }
                });

        inp2.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                //删除.后面超过两位的数字
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        inp2.setText(s);
                        inp2.setSelection(s.length());
                    }
                }

                //如果.在起始位置,则起始位置自动补0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    inp2.setText(s);
                    inp2.setSelection(2);
                }

                //如果起始位置为0并且第二位跟的不是".",则无法后续输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        inp2.setText(s.subSequence(0, 1));
                        inp2.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        return inco;
    }
}
