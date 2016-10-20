package com.hongseokandrewjang.android.recyclerviewexcercise02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewMain extends AppCompatActivity {

    ArrayList<MusicData> data = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_main);

        setData();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, R.layout.recycler_item,data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void setData(){
        for(int i=0;i<100;i++){
            MusicData musicData = new MusicData();
            musicData.artist = "artist number"+i;
            musicData.title = "TITLE"+i+"o";
            data.add(musicData);
        }
    }
}
