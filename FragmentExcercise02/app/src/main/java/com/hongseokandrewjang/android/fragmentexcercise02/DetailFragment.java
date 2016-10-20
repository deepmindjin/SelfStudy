package com.hongseokandrewjang.android.fragmentexcercise02;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    TextView title;
    TextView content;

    public DetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        title = (TextView)view.findViewById(R.id.titleViewOnDetail);
        content = (TextView)view.findViewById(R.id.contentViewOnDetail);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(MainActivity.position > -1)
            setData();
    }

    public void setData(){
        title.setText(MainActivity.datas.get(MainActivity.position).title);
        content.setText(MainActivity.datas.get(MainActivity.position).content);
    }

}
