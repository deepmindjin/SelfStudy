package com.hongseokandrewjang.android.fragmentexcercise02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import static com.hongseokandrewjang.android.fragmentexcercise02.MainActivity.datas;

public class ListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;

    public ListAdapter(Context context){
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        TextView number = (TextView)convertView.findViewById(R.id.numberViewOnList);
        TextView title = (TextView)convertView.findViewById(R.id.titleViewOnList);

        number.setText(datas.get(position).num);
        title.setText(datas.get(position).title);

        return convertView;
    }
}
