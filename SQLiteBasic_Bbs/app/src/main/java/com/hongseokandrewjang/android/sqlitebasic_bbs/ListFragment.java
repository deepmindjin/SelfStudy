package com.hongseokandrewjang.android.sqlitebasic_bbs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    public ListAdapter adapter;
    public ArrayList<BbsData> datas = new ArrayList<>();
    public final static int ACTION_WRITE = 0;
    public final static int ACTION_DETAIL = 4;
    public int position = -1;
    public int totalCount = 0;

    // 메인액티비티와 소통하는 Listener
    private OnFragmentInteractionListener mListener;

    public ListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        final ListView listView = (ListView)view.findViewById(R.id.listVIew);
        adapter = new ListAdapter(inflater);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.onFragmentInteraction(ACTION_DETAIL,position+1);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // 리스트뷰의 스크롤 이벤트에서 마지막 아이템을 체크하는 로직
                if(totalItemCount == firstVisibleItem + visibleItemCount){
                    // 현재 리스트에 있는 데이터 개수가 실제 database에 있는 데이터 개수보다 작을때만 리스트를 갱신한다
                    if(totalItemCount < totalCount){
                        setList(totalItemCount+MainActivity.LISTCOUNT);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "삭제됩니다", Toast.LENGTH_SHORT).show();

                return true;
            }
        });
        listView.setAdapter(adapter);
        EditText searchInput = (EditText)view.findViewById(R.id.searchInput);
        final String word = searchInput.getText().toString();
        Button btnSearch = (Button)view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setListByWord(totalCount, word);
            }
        });
        Button btnWrite = (Button)view.findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(ACTION_WRITE,position);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 메인 Activity가 OnFragmentInteractionListener를 구현했는지 확인
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            // 만약 구현하지 않았으면 MainActivity와 통신할 방법이 없으므로 앱을 죽인다
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        setList(MainActivity.LISTCOUNT);
    }

    public void setList(int count){
        totalCount = DataUtil.selectCount(getContext());
        datas = DataUtil.selectAll(getContext(), count);
    }

    public void setListByWord(int count, String word){
        totalCount = DataUtil.selectCount(getContext());
        datas = DataUtil.selectAllByWord(getContext(), count, word);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    // Adapter부분
    public class ListAdapter extends BaseAdapter{
        LayoutInflater inflater;

        public ListAdapter(LayoutInflater inflater){
            this.inflater = inflater;
        }
        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = inflater.inflate(R.layout.listitem_layout, null);
            }
            TextView no = (TextView)convertView.findViewById(R.id.item_id_View);
            no.setText(datas.get(position).no+"");
            TextView title = (TextView)convertView.findViewById(R.id.item_title_view);
            title.setText(datas.get(position).title);

            return convertView;
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }
    }

}


