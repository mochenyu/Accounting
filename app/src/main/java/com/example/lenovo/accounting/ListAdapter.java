package com.example.lenovo.accounting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListAdapter extends ArrayAdapter {
    public ListAdapter(Context context, int resource, ArrayList<HashMap<String,String>> list) {
        super(context, resource,list);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = convertView;
        if (itemView==null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_account1,parent,false);
        }
        Map<String,String> map = (Map<String, String>)getItem(position);
        TextView itemMarks = itemView.findViewById(R.id.itemMarks);
        TextView itemEx = itemView.findViewById(R.id.itemEx);
        TextView itemDate = itemView.findViewById(R.id.itemDate);

        itemMarks.setText("title:"+map.get("ItemMarks"));
        itemEx.setText("detail:"+map.get("ItemEx"));
        itemDate.setText("detail:"+map.get("ItemDate"));

        return itemView;
    }
}
