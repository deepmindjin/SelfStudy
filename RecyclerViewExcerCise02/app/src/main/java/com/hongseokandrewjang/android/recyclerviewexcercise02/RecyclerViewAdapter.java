package com.hongseokandrewjang.android.recyclerviewexcercise02;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HongSeokAndrewJang on 2016-10-07.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    ArrayList<MusicData> datas;
    Context context;
    int itemlayout;

    public RecyclerViewAdapter(Context context, int itemLayout, ArrayList<MusicData> datas){
        this.context = context;
        this.datas = datas;
        this.itemlayout = itemLayout;
    }
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemlayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        MusicData data = datas.get(position);
        holder.artist.setText(data.artist);
        holder.title.setText(data.title);
        setAnimation(holder.cardView, position);
    }

    int lastPosition = -1;
    public void setAnimation(View view,int position){
        if(position>lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            view.startAnimation(animation);
            lastPosition = position;
        }
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        CardView cardView;
        TextView artist;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
            title = (TextView)itemView.findViewById(R.id.titleTextView);
            artist = (TextView)itemView.findViewById(R.id.artistTextView);
        }


    }
}
