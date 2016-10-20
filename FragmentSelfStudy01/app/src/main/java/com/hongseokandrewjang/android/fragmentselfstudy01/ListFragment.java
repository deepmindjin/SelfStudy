package com.hongseokandrewjang.android.fragmentselfstudy01;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class ListFragment extends Fragment {

    public static ArrayList<AlbumData> data = new ArrayList<>();


    public ListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        setData();

        ListView listViewInFragment = (ListView)view.findViewById(R.id.listViewInFragment);
        FragmentListAdapter adapter = new FragmentListAdapter(ListFragment.this);
        listViewInFragment.setAdapter(adapter);





        return view;
    }

    private void setData(){
        for(int i=0;i<100;i++){
            AlbumData datum = new AlbumData();
            datum.artist = i + "번 째 artist 입니다";
            datum.title = "제목은 " + i + " 입니다.";
            data.add(datum);
        }
    }

}
