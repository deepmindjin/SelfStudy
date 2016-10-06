package com.hongseokandrewjang.android.listexcercise03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by 장홍석 on 2016-10-06.
 */

public class songListAdapter extends BaseAdapter{
    Context context;
    LayoutInflater inflater;

    public songListAdapter(Context context){
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return ListMain.data.size();
    }

    @Override
    public Object getItem(int position) {
        return ListMain.data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.item_layout, null);
        }
        TextView title = (TextView)convertView.findViewById(R.id.title_in_item);
        TextView artist = (TextView)convertView.findViewById(R.id.artist_in_item);

        title.setText(ListMain.data.get(position).title);
        artist.setText(ListMain.data.get(position).artist);

        return convertView;
    }
}
