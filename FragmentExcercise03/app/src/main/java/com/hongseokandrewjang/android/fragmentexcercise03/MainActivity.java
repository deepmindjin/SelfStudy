package com.hongseokandrewjang.android.fragmentexcercise03;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener{

    Fragment1 fragment1 = new Fragment1();
    Fragment2 fragment2 = new Fragment2();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragment();
    }

    public void setFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentOnMain, fragment1);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void goToOne(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentOnMain, fragment1);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void goToTwo(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentOnMain, fragment2);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(String str) {
        if (str.equals("BLANK")){
            goToTwo();
        }else if(str.equals("FRAGMENT2")){
            goToOne();
        }
    }
}
