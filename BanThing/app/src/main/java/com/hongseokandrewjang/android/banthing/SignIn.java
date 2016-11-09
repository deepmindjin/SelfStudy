package com.hongseokandrewjang.android.banthing;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SignIn extends AppCompatActivity {
    public static String TAG = "SignIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }

    public static Intent goToSignIn(Activity activity){
        Intent intent = new Intent(activity, SignIn.class);
        return intent;
    }
}
