package com.hongseokandrewjang.android.banthing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hongseokandrewjang.android.banthing.Fragments.MenuDetailFragment;

public class SignIn extends AppCompatActivity {
    public static String TAG = "SignIn";

    private Button btnNoSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.layout_rightin, R.anim.layout_rightout);
        setContentView(R.layout.activity_signin);

        btnNoSignIn = (Button)findViewById(R.id.SignIn_btnNoSignIn);
        btnNoSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total_price = getIntent().getIntExtra(MenuDetailFragment.TOTAL_PRICE,0);
                Intent intent = Purchase.goToPurchase(SignIn.this);
                intent.putExtra(MenuDetailFragment.TOTAL_PRICE,total_price);
                startActivity(intent);
            }
        });
    }

    public static Intent goToSignIn(Activity activity){
        Intent intent = new Intent(activity, SignIn.class);
        return intent;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.layout_rightin, R.anim.layout_rightout);
    }
}
