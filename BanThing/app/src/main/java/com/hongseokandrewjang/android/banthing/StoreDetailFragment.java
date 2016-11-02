package com.hongseokandrewjang.android.banthing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by HongSeokAndrewJang on 2016-11-03.
 */

public class StoreDetailFragment extends Fragment {
    public static final String TAG = "StoreDetailFragment";

    private static ChickenStore mChickenStore;
    private RecyclerView recyclerView;
    private OnFragmentInteractionListener mListener;
    private Button btnChangePosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mListener = (OnFragmentInteractionListener)getActivity();
        View view = inflater.inflate(R.layout.fragment_store_detail, container, false);

        return view;
    }

    public static void setChickenStore(int position){
        mChickenStore = MainActivity.getStore(position);
    }
}
