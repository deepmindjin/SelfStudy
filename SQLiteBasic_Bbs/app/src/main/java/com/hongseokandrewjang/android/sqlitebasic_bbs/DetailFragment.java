package com.hongseokandrewjang.android.sqlitebasic_bbs;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    public final static int ACTION_CANCEL = 2;
    public final static int ACTION_MODIFY = 3;
    public int position = 1;
    TextView no;
    TextView title;
    TextView name;
    TextView date;
    TextView contents;
    ImageView imageView;
    Button imageButton;

    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mListener = (OnFragmentInteractionListener) getActivity();
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        setView(view, position);
        imageView = (ImageView)view.findViewById(R.id.imageViewInDetail);

        Button btnCancel = (Button) view.findViewById(R.id.btnCancelInDetail);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(ACTION_CANCEL,position);
            }
        });

        Button btnModify = (Button) view.findViewById(R.id.btnModifyInDetail);
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(ACTION_MODIFY,position);
            }
        });

        imageButton = (Button)view.findViewById(R.id.btnImageInDetail);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);  // 이미지를 호출하는 action Intent
                intent.putExtra("imagePath",MediaStore.Images.Media._ID);
                startActivityForResult(intent, REQ_CODE_IMAGE);         // 결과값을 넘겨받기 위해 호출
            }
        });
        return view;
    }
    private static final int REQ_CODE_IMAGE = 99;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_IMAGE){
            Uri mediaImage = data.getData();    // 이미지 Uri
            String selections[] = {MediaStore.Images.Media.DATA}; // 실제 이미지 패스 데이터
            Cursor cursor = getContext().getContentResolver().query(mediaImage,selections ,null,null,null);
            if(cursor.moveToNext()){
                String imagePath = cursor.getString(0);
                imageView.setImageBitmap(BitmapFactory.decodeFile(imagePath));
            }
        }
    }

    public void setView(View view, int position){
        if(position!=-1) {
            this.position = DataUtil.selectCount(getContext()) - position + 1;
            BbsData data = DataUtil.select(getContext(), this.position);
            no = (TextView) view.findViewById(R.id.noInDetail);
            no.setText("no : " + data.no + "");
            title = (TextView) view.findViewById(R.id.titleInDetail);
            title.setText("제목 : " + data.title);
            name = (TextView) view.findViewById(R.id.nameInDetail);
            name.setText("작성자 : " + data.name);
            date = (TextView) view.findViewById(R.id.dateInDetail);
            date.setText("작성일자 : " + data.ndate);
            contents = (TextView) view.findViewById(R.id.contentsInDetail);
            contents.setText(data.contents);
        }else{

        }
    }

}
