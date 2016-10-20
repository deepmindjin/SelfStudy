package com.hongseokandrewjang.android.listexcercise;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<ListData> data = new ArrayList<>();
    public static int position = -1;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listVIew);
        setData();
        ListAdapter adapter = new ListAdapter(this, data);
        listView.setAdapter(adapter);

    }

    private void setData(){
        for(int i=0;i<100;i++){
            ListData datum = new ListData();
            datum.artist    = "ARTIST"+i;
            datum.title     = "TITLE" + i + " "+i;
            data.add(datum);
        }
    }
}
