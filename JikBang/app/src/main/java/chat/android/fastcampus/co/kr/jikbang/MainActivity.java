package chat.android.fastcampus.co.kr.jikbang;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    static final int FRAGMENT_COUNT = 3;
    HomeFragment home;
    MapFragment map;
    PostFragment post;
    Fragment settings;

    ViewPager pager;

    FirebaseDatabase firebase;
    DatabaseReference rootRef;
    DatabaseReference roomsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Firebase
        firebase = FirebaseDatabase.getInstance();
        rootRef = firebase.getReference();
        roomsRef = firebase.getReference("rooms");


        home = new HomeFragment();
        map = new MapFragment();
        post = new PostFragment();

        TabLayout tab = (TabLayout) findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("Home"));
        tab.addTab(tab.newTab().setText("Map"));
        tab.addTab(tab.newTab().setText("Post"));

        pager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        // 페이저가 변경되었을때 탭을 변경해주는 리스너
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

        // 탭에 페이저를 연결해주는 리스너
        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));
    }

    @Override
    public void onFragmentInteraction(Fragment fragment) {
        Toast.makeText(this, "서브 프래그먼트에서 클릭됨", Toast.LENGTH_SHORT).show();
    }

    @Override
    public DatabaseReference getRoomReference() {
        return roomsRef;
    }

    class PagerAdapter extends FragmentStatePagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return FRAGMENT_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch(position){
                case 0: fragment = home; break;
                case 1: fragment = map; break;
                case 2: fragment = post; break;
            }
            return fragment;
        }
    }
}
