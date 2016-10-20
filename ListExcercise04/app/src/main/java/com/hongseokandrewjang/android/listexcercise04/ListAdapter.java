package com.hongseokandrewjang.android.listexcercise04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter{
    ArrayList<MusicData> data;
    Context context;
    LayoutInflater inflater;

    public ListAdapter(ArrayList<MusicData> data, Context context){
        this.data = data;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.listitem, null);
        }
        TextView number = (TextView)convertView.findViewById(R.id.numberView);
        TextView title = (TextView)convertView.findViewById(R.id.titleView);

        number.setText(data.get(position).num+"");
        title.setText(data.get(position).title);

        return convertView;
    }
}
