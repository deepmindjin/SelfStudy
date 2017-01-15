package com.example.hongseok.freeletics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class DetailExcercise extends AppCompatActivity {

    ImageView excercise;
    String excerciseName;
    url


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_excercise);

        excercise = (ImageView)findViewById(R.id.activity_detail_excercise);


    }
}
