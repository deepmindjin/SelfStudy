package com.hongseokandrewjang.android.banthing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference storesInDatabase;

    private final String TAG = "LOGIN - GET FIRST DATA";

    private final String DATABASE_ADDRESS = "https://banthing-4382c.firebaseio.com/";
    private final String STORAGE_ADDRESS = "gs://banthing-4382c.appspot.com chevron_right/images";
    ArrayList<Map<String, ChickenStore>> mChickenStores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.i(TAG,"시작됨");
        startFirebase();
        goToMainPage();
    }

    private void startFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        storesInDatabase = firebaseDatabase.getReference("CHICKENSTORE");
        Log.i(TAG,"열었음");
        storesInDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot stores) {
                for(DataSnapshot store : stores.getChildren()){
                    String storeId = store.getKey();
                    HashMap<String, ChickenStore> data = new HashMap<String, ChickenStore>();
                    ChickenStore chickenStore = store.getValue(ChickenStore.class);
                    data.put(storeId,chickenStore);
                    mChickenStores.add(data);
                    Log.i(TAG,data.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void goToMainPage(){
        Runnable goToMainAfter5sec = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Login.this, MainActivity.class);
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(goToMainAfter5sec, 3000);
    }
}
