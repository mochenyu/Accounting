package com.example.lenovo.accounting;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
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
public class Expenses extends Fragment {
    TextView expenses,remarks1,total1;
    EditText inp1;
    String marks1,input1,Date;
    AccountExItem item;
    Float newNum;

    public Expenses() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View expen = inflater.inflate(R.layout.fragment_expenses, container,
                false);
        inp1 = expen.findViewById(R.id.inp1);
        expenses = expen.findViewById(R.id.expenses);
        remarks1 = expen.findViewById(R.id.mark1);
        total1 = expen.findViewById(R.id.total1);

        expen.findViewById(R.id.btn1).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //获取用户输入
                        input1 = inp1.getText().toString();
                        float r1 = 0;
                        if (input1.length() > 0) {
                            r1 = Float.parseFloat(input1);
                            marks1 = remarks1.getText().toString();
                            Date = (new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

                            ExManager manager = new ExManager(getActivity());
                            item = new AccountExItem(marks1,input1,Date);
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
                        String oldNum = (String) expenses.getText();
                        newNum = Float.parseFloat(oldNum) + f;
                        expenses.setText("" + newNum);
                        inp1.setText("");
                        remarks1.setText("");
                    }
                });
        ExManager manager1 = new ExManager(getActivity());
        InManager manager2 = new InManager(getActivity());
        expenses.setText(manager1.NUM().toString());
        Float val = manager2.NUM()-manager1.NUM();
        total1.setText(val.toString());

        expen.findViewById(R.id.btn3).setOnClickListener(
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

        inp1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                //删除.后面超过两位的数字
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        inp1.setText(s);
                        inp1.setSelection(s.length());
                    }
                }

                //如果.在起始位置,则起始位置自动补0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    inp1.setText(s);
                    inp1.setSelection(2);
                }

                //如果起始位置为0并且第二位跟的不是".",则无法后续输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        inp1.setText(s.subSequence(0, 1));
                        inp1.setSelection(1);
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

        return expen;
    }
}
