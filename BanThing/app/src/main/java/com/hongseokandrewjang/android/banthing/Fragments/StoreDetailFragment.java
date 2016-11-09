package com.hongseokandrewjang.android.banthing.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hongseokandrewjang.android.banthing.Data.ChickenStore;
import com.hongseokandrewjang.android.banthing.MainActivity;
import com.hongseokandrewjang.android.banthing.OnFragmentInteractionListener;
import com.hongseokandrewjang.android.banthing.R;
import com.hongseokandrewjang.android.banthing.RecyclerViewAdapter;

/**
 * Created by HongSeokAndrewJang on 2016-11-03.
 */

public class StoreDetailFragment extends Fragment {
    public static final String TAG = "StoreDetailFragment";

    private int currentPosition = 0;
    public static ChickenStore mChickenStore;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private OnFragmentInteractionListener mListener;
    private ImageView mainLogo;
    private TextView store_name;
    private TextView deliveryFee;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "들어옴");
        mListener = (OnFragmentInteractionListener)getActivity();
        View view = inflater.inflate(R.layout.fragment_store_detail, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview_in_detail);
        store_name = (TextView)view.findViewById(R.id.menu_detail_name);
        store_name = (TextView)view.findViewById(R.id.menu_detail_name);
        mainLogo = (ImageView)view.findViewById(R.id.menu_detail_image);

        Glide.with(getContext()).load(mChickenStore.getLOGO()).into(mainLogo);
        store_name.setText(mChickenStore.getNAME()+" - "+mChickenStore.getBRANCH());

        adapter = new RecyclerViewAdapter(StoreDetailFragment.this, getContext(),null);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        // Make back button go to mainList with animation
        goBack(view);

        return view;
    }

    private void goBack(View view){
        // Make back button go to mainList with animation
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK){
                    adapter.goToList();
                    return true;
                }
                return false;
            }
        });
    }

    public static void setCurrentChickenStore(int position){
        mChickenStore = MainActivity.getStore(position);
    }

    public ChickenStore getCurrentChickenStore(){
        return mChickenStore;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

}
