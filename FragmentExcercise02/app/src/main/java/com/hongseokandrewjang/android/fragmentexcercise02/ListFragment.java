package com.hongseokandrewjang.android.fragmentexcercise02;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListFragment extends Fragment {

    ListView listView;

    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = (ListView)view.findViewById(R.id.listViewOnFragment);
        ListAdapter adapter = new ListAdapter(getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("클릭은 됨",position + "번 째");
                MainActivity main = (MainActivity)getActivity();
                main.position = position;
                main.onListFragmentInteraction(MainActivity.datas.get(position));
            }
        });
        return view;
    }

}



