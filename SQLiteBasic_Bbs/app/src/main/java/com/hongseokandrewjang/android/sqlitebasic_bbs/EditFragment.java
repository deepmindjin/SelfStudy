package com.hongseokandrewjang.android.sqlitebasic_bbs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EditFragment extends Fragment {

    public final static int ACTION_SAVE = 1;
    public final static int ACTION_CANCEL = 2;
    private OnFragmentInteractionListener mListener;
    public int position = -1;
    EditText inputTitle;
    EditText inputName;
    EditText inputContents;

    public EditFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        inputTitle = (EditText)view.findViewById(R.id.putTitle);
        inputName = (EditText)view.findViewById(R.id.putName);
        inputContents = (EditText)view.findViewById(R.id.putContents);

        Button btnCancel = (Button)view.findViewById(R.id.btnCancelInEdit);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = -1;
                mListener.onFragmentInteraction(ACTION_CANCEL,position);
            }
        });

        Button btnSave = (Button)view.findViewById(R.id.btnSaveInEdit);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = inputTitle.getText().toString();
                String name = inputName.getText().toString();
                String contents = inputContents.getText().toString();
                BbsData data = new BbsData();
                data.title = title;
                data.name = name;
                data.contents = contents;
                if (position == -1) {
                    insert(getContext(), data);
                }else{
                    update(getContext(), data, position);
                }
                mListener.onFragmentInteraction(ACTION_SAVE,position);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void setView(Context context, int bbsno){
        if (bbsno!=-1) {
            position = bbsno;
            BbsData data = DataUtil.select(context, bbsno);
            inputTitle.setText(data.title);
            inputName.setText(data.name);
            inputContents.setText(data.contents);
        }else{
            inputTitle.setText("");
            inputName.setText("");
            inputContents.setText("");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void insert(Context context, BbsData data){
        DataUtil.insert(context, data);
    }

    public void update(Context context, BbsData data, int position){
        data.no = position;
        DataUtil.update(context, data);
    }
}
