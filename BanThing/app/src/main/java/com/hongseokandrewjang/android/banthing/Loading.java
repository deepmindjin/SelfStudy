package com.hongseokandrewjang.android.banthing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hongseokandrewjang.android.banthing.Data.ChickenStore;

import java.util.ArrayList;

import io.realm.Realm;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Loading extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference storeRef;

    private final String TAG = "LOGIN";
    public static final String CHICKEN_STORE_DATA = "Get data from firebase";

    private final String DATABASE_ADDRESS = "https://banthing-4382c.firebaseio.com/";
    private final String STORAGE_ADDRESS = "gs://banthing-4382c.appspot.com chevron_right/images";

    public static ArrayList<ChickenStore> sChickenStores = new ArrayList<>();
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        // Set Facebook Sign in API
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplicationContext());

        startFirebase();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        realm.close();
    }

    private void startFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        storeRef = firebaseDatabase.getReference("CHICKENSTORE");
        Log.e(TAG, "Database 열었음");
        storeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot stores) {
                Log.e(TAG, "Data 가져오기 시작");
                for (DataSnapshot store : stores.getChildren()) {
                    ChickenStore chickenStore = store.getValue(ChickenStore.class);
                    sChickenStores.add(chickenStore);
                }
                Log.e(TAG, "Data 가져오기 끝");
                goToMainPage();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void goToMainPage() {
        Intent intent = new Intent(Loading.this, MainActivity.class);
        intent.putExtra(CHICKEN_STORE_DATA,sChickenStores);
        startActivity(intent);
        finish();
    }

    private void setRealm(){
        // Initiate realm
        Realm.init(Loading.this);
        realm = Realm.getDefaultInstance();

//        realm.beginTransaction();
//        RealmResults<ChickenStore> realmStores = realm.where(ChickenStore.class).findAll();
//

    }
}
