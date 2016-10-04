package com.hongseokandrewjang.android.sachunsung;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    Runnable runBasic;
    Runnable runImage1;
    Runnable runImage2;
    Runnable runImage3;
    Runnable toLogin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView mainImageBasic = new ImageView(MainActivity.this);
        mainImageBasic.setImageResource(R.drawable.sachunsungmainbasic);
        setContentView(mainImageBasic);

        runBasic = new Runnable() {
            @Override
            public void run() {
                ImageView mainImageBasic = new ImageView(MainActivity.this);
                mainImageBasic.setImageResource(R.drawable.sachunsungmainbasic);
                setContentView(mainImageBasic);
            }
        };

        runImage1 = new Runnable() {
            @Override
            public void run() {
                ImageView mainImage1 = new ImageView(MainActivity.this);
                mainImage1.setImageResource(R.drawable.sachunsungmain);
                setContentView(mainImage1);
            }
        };

        runImage2 = new Runnable() {
            @Override
            public void run() {
                ImageView mainImage2 = new ImageView(MainActivity.this);
                mainImage2.setImageResource(R.drawable.sachunsungmain2);
                setContentView(mainImage2);
            }
        };

        runImage3 = new Runnable() {
            @Override
            public void run() {
                ImageView mainImage3 = new ImageView(MainActivity.this);
                mainImage3.setImageResource(R.drawable.sachunsungmain3);
                setContentView(mainImage3);
            }
        };

        toLogin = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        };

        handler = new Handler();

        handler.postDelayed(runImage1,1000);
        handler.removeCallbacks(runImage1);
        handler.postDelayed(runBasic,1000);
        handler.removeCallbacks(runBasic);
        handler.postDelayed(runImage2,1000);
        handler.removeCallbacks(runImage1);
        handler.postDelayed(runBasic,1000);
        handler.removeCallbacks(runBasic);
        handler.postDelayed(runImage3,1000);
        handler.removeCallbacks(runImage1);
        handler.postDelayed(toLogin,1000);
        }
    }

