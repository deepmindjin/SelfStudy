package com.hongseokandrewjang.android.fragmentexcercise03;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Fragment2 extends Fragment{

    private OnFragmentInteractionListener mListener;

    public Fragment2() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mListener = (OnFragmentInteractionListener)getActivity();
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        Button btn = (Button)view.findViewById(R.id.goToOneBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction("FRAGMENT2");
            }
        });

        return view;
    }
}
