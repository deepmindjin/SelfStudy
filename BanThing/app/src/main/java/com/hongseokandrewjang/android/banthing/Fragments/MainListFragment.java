package com.hongseokandrewjang.android.banthing.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hongseokandrewjang.android.banthing.Data.ChickenStore;
import com.hongseokandrewjang.android.banthing.MainActivity;
import com.hongseokandrewjang.android.banthing.R;
import com.hongseokandrewjang.android.banthing.RecyclerViewAdapter;

import java.util.ArrayList;

/**
 * Created by HongSeokAndrewJang on 2016-11-03.
 */

public class MainListFragment extends Fragment {

    public static final String TAG = "MainListFragment";

    private ArrayList<ChickenStore> mChickenStores = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private Button btnChangePosition;
    private Toolbar toolbar;

    public MainListFragment() {
        mChickenStores = MainActivity.getChickenStores();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main_list, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView_in_Main);
        adapter = new RecyclerViewAdapter(MainListFragment.this,getContext(), mChickenStores);

        // Set current position change button
        btnChangePosition = (Button)view.findViewById(R.id.btnChangePosition);
        btnChangePosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "현재 위치로 변경합니다", Toast.LENGTH_SHORT).show();
            }
        });

        // Set Toolbar
        toolbar = (Toolbar)view.findViewById(R.id.toolbar_in_list);
        ((AppCompatActivity)getContext()).getSupportActionBar();
        setCustomActionbar();

//        // Make the cards in recyclerview slide
//        SwipeDismissListViewTouchListener.DismissCallbacks dismissCallbacks = new SwipeDismissListViewTouchListener.DismissCallbacks() {
//            @Override
//            public boolean canDismiss(int position) {
//                return true;
//            }
//
//            @Override
//            public void onDismiss(RecyclerView recyclerView, int[] reverseSortedPositions) {
//                final int selected = reverseSortedPositions[reverseSortedPositions.length-1];
//                MainActivity.setStorePosition(selected);
//                adapter.goToSelectedDetail(selected);
//            }
//        };
//        SwipeDismissListViewTouchListener touchListener = new SwipeDismissListViewTouchListener(recyclerView,dismissCallbacks);
//        recyclerView.setNestedScrollingEnabled(true);
//        recyclerView.setOnTouchListener(touchListener);

        // Set adapter to recyclerview
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    private void setCustomActionbar(){
        toolbar.setTitle("치킨집들");
        toolbar.setCollapsible(true);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setActivated(true);
        toolbar.canShowOverflowMenu();
        toolbar.getContentInsetLeft();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_in_list,menu);
    }
}

