package com.hongseokandrewjang.android.banthing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Purchase extends AppCompatActivity {
    public static String TAG = "Purchase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
    }

    public static Intent goToPurchase(Activity activity){
        return new Intent(activity,Purchase.class);
    }
}
