package com.hongseokandrewjang.android.fragmentexcercise03;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Fragment1 extends Fragment {

    private OnFragmentInteractionListener mListener;
    private final String TAG = "BLANK";


    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mListener = (OnFragmentInteractionListener)getActivity();
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        Button btn = (Button)view.findViewById(R.id.goToTwoBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(TAG);
            }
        });
        return view;
    }


}

interface OnFragmentInteractionListener {
    void onFragmentInteraction(String str);
}
