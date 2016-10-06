package com.hongseokandrewjang.android.listexcercise02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by 장홍석 on 2016-10-06.
 */

public class AlbumListAdapter extends BaseAdapter{
    Context context;
    LayoutInflater inflater;

    public AlbumListAdapter(Context context){
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return MainActivity.albums.size();
    }

    @Override
    public Object getItem(int position) {
        return MainActivity.albums.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_layout, null);
        }
        TextView number = (TextView)convertView.findViewById(R.id.numberVIewOnItem);
        TextView title = (TextView)convertView.findViewById(R.id.titleViewOnItem);

        number.setText(MainActivity.albums.get(position).number);
        title.setText(MainActivity.albums.get(position).title);
        return convertView;
    }
}
