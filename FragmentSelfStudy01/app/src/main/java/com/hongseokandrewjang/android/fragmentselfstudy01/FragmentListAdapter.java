package com.hongseokandrewjang.android.fragmentselfstudy01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 장홍석 on 2016-10-07.
 */

public class FragmentListAdapter extends BaseAdapter{
    Context context;
    LayoutInflater inflater;


    public FragmentListAdapter(Context context){
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return ListFragment.data.size();
    }

    @Override
    public Object getItem(int position) {
        return ListFragment.data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.fragement_list_item, null);
        }
        TextView titleTextView = (TextView)convertView.findViewById(R.id.titleTextView);
        TextView artistTextView = (TextView)convertView.findViewById(R.id.artistTextView);
        TextView numberTextView = (TextView)convertView.findViewById(R.id.numberTextView);

        titleTextView.setText(ListFragment.data.get(position).title);
        artistTextView.setText(ListFragment.data.get(position).artist);
        titleTextView.setText(ListFragment.position+"");

        return convertView;
    }
}
