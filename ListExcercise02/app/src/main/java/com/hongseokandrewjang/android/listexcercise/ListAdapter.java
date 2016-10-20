package com.hongseokandrewjang.android.listexcercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class ListAdapter extends BaseAdapter{

    Context context;
    ArrayList<ListData> data;
    LayoutInflater inflater;

    public ListAdapter(Context context, ArrayList<ListData> data){
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return MainActivity.data.size();
    }

    @Override
    public Object getItem(int position) {
        return MainActivity.data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.itemlayout, null);
        }
        TextView artist = (TextView)convertView.findViewById(R.id.artist_textview);
        TextView title = (TextView)convertView.findViewById(R.id.title_textview);

        artist.setText(MainActivity.data.get(position).artist);
        title.setText(MainActivity.data.get(position).title);
        return convertView;
    }
}
