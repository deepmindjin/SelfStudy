package com.hongseokandrewjang.android.banthing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Runnable run;
        ImageView imageView = (ImageView)findViewById(R.id.login_logo);
        run = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(run,3000);
    }
}
