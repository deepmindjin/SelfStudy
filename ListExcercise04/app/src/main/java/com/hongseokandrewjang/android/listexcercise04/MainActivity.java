package com.hongseokandrewjang.android.listexcercise04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<MusicData> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setData();
        ListView listView = (ListView)findViewById(R.id.listView);
        ListAdapter adapter = new ListAdapter(data, this);
        listView.setAdapter(adapter);
    }

    private void setData(){
        for(int i=0;i<100;i++) {
            MusicData datum = new MusicData();
            datum.num = (i+1);
            datum.title = "아티스트" +i;
            data.add(datum);
        }
    }


}

class MusicData{
    String title;
    int num;
}
