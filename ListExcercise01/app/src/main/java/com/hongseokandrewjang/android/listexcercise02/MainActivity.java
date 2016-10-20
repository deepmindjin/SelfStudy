package com.hongseokandrewjang.android.listexcercise02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<AlbumData> albums = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setAlbums();
        ListView listView = (ListView)findViewById(R.id.listViewOnMain);
        AlbumListAdapter adapter = new AlbumListAdapter(this);
        listView.setAdapter(adapter);
    }

    private void setAlbums(){
        for(int i=0;i<100;i++){
            AlbumData album = new AlbumData();
            album.title = "Song number "+i;
            album.number = (i+1)+"";
            albums.add(album);
        }
    }
}
