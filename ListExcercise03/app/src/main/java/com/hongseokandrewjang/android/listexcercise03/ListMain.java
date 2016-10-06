package com.hongseokandrewjang.android.listexcercise03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListMain extends AppCompatActivity {

    public static ArrayList<songData> data = new ArrayList<>();
    public int position = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main);

        setData();
        ListView listView = (ListView)findViewById(R.id.listViewOnMain);
        songListAdapter adapter = new songListAdapter(ListMain.this);
        listView.setAdapter(adapter);

    }

    private void setData(){
        for(int i=0;i<100;i++){
            songData datum = new songData();
            datum.artist = i+"라는 가수입니다";
            datum.title = i + "번 째 곡입니다";
            data.add(datum);
        }
    }
}
