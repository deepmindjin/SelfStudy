package com.hongseokandrewjang.android.banthing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hongseokandrewjang.android.banthing.Fragments.MenuDetailFragment;

public class Purchase extends AppCompatActivity {
    public static String TAG = "Purchase";
    private int total_price = 0;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        total_price = getIntent().getIntExtra(MenuDetailFragment.TOTAL_PRICE,0);
        textView = (TextView)findViewById(R.id.test_on_purchase);
        textView.setText(total_price+"원");
    }

    public static Intent goToPurchase(Activity activity){
        return new Intent(activity,Purchase.class);
    }
}
