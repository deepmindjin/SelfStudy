package com.hongseokandrewjang.android.fragmentexcercise02;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<ListItem> datas = new ArrayList<>();
    public static int position = -1;
    DetailFragment detailFragment;
    ListFragment listFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager manager = getSupportFragmentManager();
        setDatas();

        int stackCount = manager.getBackStackEntryCount();
        if(stackCount > 0) {
            manager.popBackStack();
        }

        setContentView(R.layout.activity_main);

        if(position > -1){
            goDetail();
        }

    }

    public void goDetail(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        detailFragment = new DetailFragment();
        transaction.replace(R.id.fragmentOnMain, detailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void setFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentOnMain, listFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void onListFragmentInteraction(ListItem item) {
        goDetail();
    }

    private void setDatas(){
        for(int i=0;i<100;i++){
            ListItem item = new ListItem();
            item.content = "CONTENT, CONTENT, CONTENT, CONTENT, CONTENT, CONTENT";
            item.num = (i+1)+"";
            item.title = "곡의 제목은 "+i*30+"입니다.";
            datas.add(item);
        }
    }

}
