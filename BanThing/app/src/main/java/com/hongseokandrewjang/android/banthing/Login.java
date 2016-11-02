package com.hongseokandrewjang.android.banthing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference storeRef;

    private final String TAG = "LOGIN";
    public static final String CHICKEN_STORE_DATA = "Get data from firebase";

    private final String DATABASE_ADDRESS = "https://banthing-4382c.firebaseio.com/";
    private final String STORAGE_ADDRESS = "gs://banthing-4382c.appspot.com chevron_right/images";

    public static ArrayList<ChickenStore> sChickenStores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        startFirebase();
    }

    private void startFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        storeRef = firebaseDatabase.getReference("CHICKENSTORE");
        Log.e(TAG,"Database 열었음");
        storeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot stores) {
                for(DataSnapshot store : stores.getChildren()){
                    ChickenStore chickenStore = store.getValue(ChickenStore.class);
                    sChickenStores.add(chickenStore);
                }
                goToMainPage();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    private void goToMainPage() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        intent.putExtra(CHICKEN_STORE_DATA,sChickenStores);
        startActivity(intent);
        finish();
    }
}
