package com.hongseokandrewjang.android.banthing;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.hongseokandrewjang.android.banthing.AnimationClasses.BackTwiceForEndApp.BackTwice;
import com.hongseokandrewjang.android.banthing.Data.ChickenStore;
import com.hongseokandrewjang.android.banthing.Fragments.MainListFragment;
import com.hongseokandrewjang.android.banthing.Fragments.MenuDetailFragment;
import com.hongseokandrewjang.android.banthing.Fragments.StoreDetailFragment;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class MainActivity extends AppCompatActivity implements  OnFragmentInteractionListener{

    private static ArrayList<ChickenStore> mChickenStores = new ArrayList<>();
    // Show where to go
    private Boolean checkFromMenu = false;
    private boolean signedIn = false;

    // Need twice to end application
    private BackTwice backPressCloseHandler;

    private FragmentManager manager;
    private MainListFragment mMainListFragment;
    private StoreDetailFragment mStoreDetailFragment;
    private MenuDetailFragment menuDetailFragment;

    private static int currentStorePosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get

        // Handle fragment stack exception
        manager = getSupportFragmentManager();
        int stackCount = manager.getBackStackEntryCount();
        if(stackCount > 0) {
            manager.popBackStack();
        }

        // Initiate view and make fragment instances
        setContentView(R.layout.activity_main);
        setChickenStoreDataToList();
        mMainListFragment = new MainListFragment();
        mStoreDetailFragment = new StoreDetailFragment();
        menuDetailFragment = new MenuDetailFragment();

        // Show MainListView to screen
        setFragment();

        // Need click back button twice for closing application
        backPressCloseHandler = new BackTwice(this);
    }

    // Get Store data from Loading Activity
    private void setChickenStoreDataToList(){
        mChickenStores = (ArrayList<ChickenStore>)getIntent().getSerializableExtra(Loading.CHICKEN_STORE_DATA);
    }

    // Make MainListViewFragment stick to first screen
    private void setFragment(){
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_ground_in_main, mMainListFragment)
                .commit();
    }

    // Handle request from detail fragment going list fragment
    public void goList(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction
                .setCustomAnimations(R.anim.layout_rightin,R.anim.layout_rightout)
                .replace(R.id.fragment_ground_in_main, mMainListFragment)
                .commit();
    }

    // Handle request from list fragment going detail fragment
    public void goDetail(int position){
        Log.e("goDetail","detail로 가는중, position : "+position);
        mStoreDetailFragment = new StoreDetailFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        mStoreDetailFragment.setCurrentChickenStore(position);
        Log.e("goDetail"," detail position 설정중, position : "+position);
        mStoreDetailFragment.setCurrentPosition(position);
        if (!checkFromMenu) {
            transaction
                    .setCustomAnimations(R.anim.layout_leftin, R.anim.layout_leftout)
                    .replace(R.id.fragment_ground_in_main, mStoreDetailFragment)
                    .commit();
        }else{
            transaction
                    .setCustomAnimations(R.anim.layout_rightin,R.anim.layout_rightout)
                    .replace(R.id.fragment_ground_in_main, mStoreDetailFragment)
                    .commit();
        }
        Log.e("goDetail","건너감");
    }

    public void goMenuDetail(int position){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        menuDetailFragment.setCurrentStore(mStoreDetailFragment.getCurrentChickenStore());
        menuDetailFragment.setCurrentMenuPosition(position);
        transaction
                .setCustomAnimations(R.anim.layout_leftin,R.anim.layout_leftout)
                .replace(R.id.fragment_ground_in_main, menuDetailFragment)
                .commit();
    }

    // Handle move between list fragment and detail fragment
    @Override
    public void onFragmentInteraction(String str,int position) {
        if (str.equals(StoreDetailFragment.TAG)){
            Log.e("goDetail","position : "+position);
            goDetail(currentStorePosition);
        }else if(str.equals(MainListFragment.TAG)){
            if (checkFromMenu){
                Log.e("from Menu","ㅎㅎ");
                checkFromMenu = false;
            }else {
                Log.e("goList", "position : " + position);
                goList();
            }
        }else if(str.equals(MenuDetailFragment.TAG)) {
            checkFromMenu = true;
            goMenuDetail(position);
        }else if (str.equals(SignIn.TAG)){
            if (signedIn){
                Purchase.goToPurchase(this);
            }else{
                SignIn.goToSignIn(this);
            }
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }

    // Getter Section Below
    public static ArrayList<ChickenStore> getChickenStores() {
        return mChickenStores;
    }

    public static ChickenStore getStore(int position){
        return mChickenStores.get(position);
    }

    public static int getStorePosition(){
        return currentStorePosition;
    }

    public static void setStorePosition(int position){
        currentStorePosition = position;
    }

}


