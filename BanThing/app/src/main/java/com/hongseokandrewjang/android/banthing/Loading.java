package com.hongseokandrewjang.android.banthing;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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
import com.hongseokandrewjang.android.banthing.Data.RealmChickenStore;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

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
        AppEventsLogger.activateApp(getApplicationContext()); // I know it's deprecated but facebook suggest this

        // Check internet is available
        // If it is available, connect to the firebase and get the data from
        // if it's not, uses inner data that was set previously
        if (isInternetAvailable()) {
            Log.e(TAG,"인터넷 연결됨");
            startFirebase();
        }else{
            Log.e(TAG,"인터넷 연결 안됨");
            getDataFromRealm();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void startFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        storeRef = firebaseDatabase.getReference("CHICKENSTORE");
        Log.e(TAG, "Firebase Database 열었음");
        storeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot stores) {
                Log.e(TAG, "Data 가져오기 시작");
                for (DataSnapshot store : stores.getChildren()) {
                    ChickenStore chickenStore = store.getValue(ChickenStore.class);
                    sChickenStores.add(chickenStore);
                }
                Log.e(TAG, "Data 가져오기 끝");

                // Setting realm data and going to the main page should be done Thread safely
                setRealm();
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
        Log.e(TAG, "Login Activity finish");
    }

    private boolean isInternetAvailable(){
        boolean result = false;
        ConnectivityManager manager = (ConnectivityManager)this
                .getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo!=null){
            result = true;
        }
        return result;
    }

    private void setRealm(){
        // Initiate realm
        Realm.init(Loading.this);
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        // Delete previous data for internet is connected and firebase is working
        realm.deleteAll();

        for (ChickenStore store : sChickenStores){
            RealmChickenStore realmChickenStore = ChickenStore.ToRealmData(store);
            realm.copyToRealm(realmChickenStore);
        }
        realm.commitTransaction();
        realm.close();
    }

    private void getDataFromRealm(){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                Realm.init(Loading.this);
                realm = Realm.getDefaultInstance();

                // Set the query finding all the chickenstore data from inner data base
                RealmQuery<RealmChickenStore> query = realm.where(RealmChickenStore.class);
                RealmResults<RealmChickenStore> results = query.findAll();
                for (RealmChickenStore store : results){
                    ChickenStore chickenStore = ChickenStore.fromRealmData(store);
                    sChickenStores.add(chickenStore);
                }
                realm.close();

                // Need time to show the Logo
                try {Thread.sleep(3000);}
                catch (InterruptedException e) {e.printStackTrace();}

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                goToMainPage();
                super.onPostExecute(aVoid);

            }
        }.execute();

    }
}
