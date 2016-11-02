package com.hongseokandrewjang.android.banthing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by HongSeokAndrewJang on 2016-11-03.
 */

public class MainListFragment extends Fragment {

    public static final String TAG = "MainListFragment";

    private ArrayList<ChickenStore> mChickenStores = new ArrayList<>();
    private RecyclerView recyclerView;
    private Button btnChangePosition;

    public MainListFragment() {
        mChickenStores = MainActivity.getChickenStores();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_list, container, false);
        Log.e(TAG,"List Fragment Opened");
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView_in_Main);
        btnChangePosition = (Button)view.findViewById(R.id.btnChangePosition);
        btnChangePosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "현재 위치로 변경합니다", Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), mChickenStores);
        Log.e(TAG,mChickenStores+"");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}

