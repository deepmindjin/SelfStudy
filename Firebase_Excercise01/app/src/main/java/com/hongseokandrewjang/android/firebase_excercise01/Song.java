package com.hongseokandrewjang.android.firebase_excercise01;

import java.util.HashMap;

/**
 * Created by HongSeokAndrewJang on 2016-10-30.
 */

public class Song {
    String artist;
    String title;
    HashMap<String, Song_Info> infos = new HashMap<>();

    public static class Song_Info{
        String date;

        public String getDate() {
            return date;
        }

        public String getSize() {
            return size;
        }

        public Song_Info() {

        }

        String size;
    }

    public Song() {
    }

    public HashMap<String, Song_Info> getInfos() {
        return infos;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }
}
