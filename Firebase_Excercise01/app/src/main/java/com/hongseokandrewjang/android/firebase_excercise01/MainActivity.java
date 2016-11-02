package com.hongseokandrewjang.android.firebase_excercise01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText etArtist;
    EditText etTitle;
    Button btnPutData;
    RecyclerView mRecyclerView;
    ArrayList<Song> mSongs = new ArrayList<>();
    CustomAdapter adapter;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance();
        rootRef = firebaseDatabase.getReference("song");
        getSongFromDatabase();

        etArtist = (EditText) findViewById(R.id.etArtist);
        etTitle = (EditText) findViewById(R.id.etTtitle);
        btnPutData = (Button) findViewById(R.id.btnWriteSong);
        btnPutData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String artist = etArtist.getText().toString().trim();
                String title = etTitle.getText().toString().trim();
                Song song = new Song();
                song.title = title;
                song.artist = artist;

                Song.Song_Info infomation = new Song.Song_Info();
                infomation.date = "2016-11-01";
                infomation.size = song.title.toString().length()+"";

                Song.Song_Info infomation2 = new Song.Song_Info();
                infomation2.date = "2016-11-01";
                infomation2.size = song.title.toString().length()+"";

                HashMap<String, Song.Song_Info> map = new HashMap<>();
                map.put("info1",infomation);
                map.put("info2",infomation2);

                song.infos = map;
                addNewSong(song);
                etTitle.setText("");
                etArtist.setText("");
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void getSongFromDatabase(){
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mSongs = new ArrayList<>();
                for (DataSnapshot song_data : dataSnapshot.getChildren()){
                    Song song = song_data.getValue(Song.class);
                    mSongs.add(song);
                }
                updateUI();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void addNewSong(Song song) {
        rootRef.push().setValue(song);
        mSongs.add(song);
        updateUI();
    }

    public void updateUI(){
        adapter= new CustomAdapter(mSongs,R.layout.recycler_item);
        adapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(adapter);
    }


    public static class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
        ArrayList<Song> mSongs = new ArrayList<>();
        int itemLayout;

        public CustomAdapter(ArrayList<Song> songs, int itemLayout) {
            mSongs = songs;
            this.itemLayout = itemLayout;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Song song = mSongs.get(position);
            holder.artist.setText(song.artist);
            holder.title.setText(song.title);
        }

        @Override
        public int getItemCount() {
            return mSongs.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            TextView artist;

            public ViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.tvTitle);
                artist = (TextView) itemView.findViewById(R.id.tvArtist);
            }
        }
    }
}

