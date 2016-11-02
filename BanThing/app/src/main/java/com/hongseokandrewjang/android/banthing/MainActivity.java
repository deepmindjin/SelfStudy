package com.hongseokandrewjang.android.banthing;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  OnFragmentInteractionListener{

    private static ArrayList<ChickenStore> mChickenStores = new ArrayList<>();


    private FragmentManager manager;

    MainListFragment mMainListFragment;
    StoreDetailFragment mStoreDetailFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Handle fragment stack exception
        manager = getSupportFragmentManager();
        int stackCount = manager.getBackStackEntryCount();
        if(stackCount > 0) {
            manager.popBackStack();
        }

        setContentView(R.layout.activity_main);
        setChickenStoreDataToList();
        mMainListFragment = new MainListFragment();
        mStoreDetailFragment = new StoreDetailFragment();
        setFragment();
    }

    private void setChickenStoreDataToList(){
        mChickenStores = (ArrayList<ChickenStore>)getIntent().getSerializableExtra(Login.CHICKEN_STORE_DATA);
    }

    private void setFragment(){
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_ground_in_main, mMainListFragment)
                .addToBackStack(null)
                .commit();
    }

    public void goList(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_ground_in_main, mMainListFragment)
                .addToBackStack(null)
                .commit();
    }

    public void goDetail(int position){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_ground_in_main, mStoreDetailFragment)
                .addToBackStack(null)
                .commit();
        mStoreDetailFragment.setChickenStore(position);
    }

    @Override
    public void onFragmentInteraction(String str,int position) {
        if (str.equals(MainListFragment.TAG)){
            goDetail(position);
        }else if(str.equals(StoreDetailFragment.TAG)){
            goList();
        }
    }

    public static ArrayList<ChickenStore> getChickenStores() {
        return mChickenStores;
    }

    public static ChickenStore getStore(int position){
        return mChickenStores.get(position);
    }
}

