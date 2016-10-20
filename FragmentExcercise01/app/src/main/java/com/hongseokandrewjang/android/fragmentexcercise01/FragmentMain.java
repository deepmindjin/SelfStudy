package com.hongseokandrewjang.android.fragmentexcercise01;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class FragmentMain extends AppCompatActivity {

    ArrayList<Fragment> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main);

        ViewPager pager = (ViewPager)findViewById(R.id.viewPager);
        ListFragment listFragment = new ListFragment();
        listFragment.setPager(pager);
        datas.add(listFragment);
        DetailFragment detailFragment = new DetailFragment();
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), datas);
        pager.setAdapter(adapter);
    }

    class PagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> datas = new ArrayList<>();

        public PagerAdapter(FragmentManager manager, ArrayList<Fragment> datas){
            super(manager);
            this.datas = datas;
        }

        @Override
        public Fragment getItem(int position) {
            return datas.get(position);
        }

        @Override
        public int getCount() {
            return datas.size();
        }
    }


}


