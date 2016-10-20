package com.hongseokandrewjang.android.sqlitebasic_bbs;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener{

    public final static int ACTION_NEW_WRITE = -1;
    public final static int ACTION_WRITE = 0;
    public final static int ACTION_SAVE = 1;
    public final static int ACTION_CANCEL = 2;
    public final static int ACTION_MODIFY = 3;
    public final static int ACTION_DETAIL = 4;
    public final static int ACTION_DELETE = 5;
    public static int LISTCOUNT = 10;

    public ArrayList<BbsData> datas = new ArrayList<>();
    ArrayList<Fragment> fragments = new ArrayList<>();
    SQLiteDatabase db;
    EditFragment editFragment;
    ListFragment listFragment;
    DetailFragment detailFragment;
    ViewPager pager = null;

    private  void init(){
        // DB 파일이 internal files 디렉토리에 있는지 검사
        File file = new File(DataUtil.getFullpath(this,DataUtil.DB_NAME));
        // 파일이 없을때만 DB 파일 생성
        if(!file.exists()) {
            DataUtil.assetToDisk(this, DataUtil.DB_NAME);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 앱 초기화 - DB 생성 등...
        init();
        setContentView(R.layout.activity_main);

        editFragment = new EditFragment();
        listFragment = new ListFragment();
        detailFragment = new DetailFragment();
        fragments.add(detailFragment);
        fragments.add(listFragment);
        fragments.add(editFragment);

        pager = (ViewPager)findViewById(R.id.viewPagerOnMain);
        CustomAdapter adapter = new CustomAdapter(getSupportFragmentManager(), fragments);
        pager.setAdapter(adapter);
        pager.setCurrentItem(1);
    }

    @Override
    public void onFragmentInteraction(int actionFlag, int position) {
        switch (actionFlag){
            case ACTION_WRITE:
                editFragment.position = -1;
                pager.setCurrentItem(2);
                editFragment.setView(editFragment.getContext(),ACTION_NEW_WRITE);
                break;
            case ACTION_SAVE:
                pager.setCurrentItem(1);
                refresh(listFragment, LISTCOUNT);
                break;
            case ACTION_CANCEL:
                pager.setCurrentItem(1);
                break;
            case ACTION_MODIFY:
                pager.setCurrentItem(2);
                editFragment.position = position;
                editFragment.setView(editFragment.getContext(), position);
                break;
            case ACTION_DETAIL:
                pager.setCurrentItem(0);
                detailFragment.setView(detailFragment.getView(),position);
                break;
            case ACTION_DELETE:
                refresh(listFragment, LISTCOUNT);
                pager.setCurrentItem(1);
        }
    }

    private void refresh(ListFragment listFragment, int count){
        listFragment.setList(count);
        listFragment.adapter.notifyDataSetChanged();
    }

    @Override
    public void addData(String title, String name, String contents) {
        if(db!=null) {
            db.execSQL("insert into bbs(name,title,contents) values('"+name+"','"+title+"','"+contents+"')");
        }
    }

    class CustomAdapter extends FragmentPagerAdapter {

        public CustomAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            fragments = list;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = fragments.get(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}





