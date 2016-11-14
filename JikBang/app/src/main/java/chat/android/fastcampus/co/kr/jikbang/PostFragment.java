package chat.android.fastcampus.co.kr.jikbang;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Map;

import static chat.android.fastcampus.co.kr.jikbang.R.id.imageView;

public class PostFragment extends Fragment {

    private OnFragmentInteractionListener listener;
    FloatingActionButton btnPost;
    EditText roomTitle,roomDeposit,roomCount,roomSize, roomPrice, monthlyRent;
    ImageView photo;
    Map map;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_post, container, false);

        // 기본 셋팅
        listener = (OnFragmentInteractionListener)getActivity();
        btnPost = (FloatingActionButton)view.findViewById(R.id.floatingActionButton);
        roomTitle = (EditText)view.findViewById(R.id.et_RoomTitle);
        roomDeposit = (EditText)view.findViewById(R.id.et_Deposit);
        roomCount = (EditText)view.findViewById(R.id.et_Room_Number);
        monthlyRent = (EditText)view.findViewById(R.id.et_MonthyRent);
        roomSize = (EditText)view.findViewById(R.id.et_Room_Size);
        photo = (ImageView)view.findViewById(R.id.img_room);


        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    // firebase에 등록
                }
            }
        });


//        btnGallery = (Button) findViewById(R.id.btnGallery);
//        btnGallery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK
//                        , MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent.setType("image/*"); // 이미지만 필터링
//                startActivityForResult(Intent.createChooser(intent,"Select Picture"),REQ_CODE_GALLERY);
//            }
//        });
//        btnCamera = (Button) findViewById(R.id.btnCamera);
//        btnCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent,REQ_CODE_CAMERA);
//            }
//        });



        return view;
    }

    private boolean validate(){
        if (roomTitle.getText().length()!=0){
            if (roomCount.getText().length()!=0){
                if (roomPrice.getText().length()!=0){
                    if (roomDeposit.getText().length()!=0){
                        if (roomSize.getText().length()!=0){
                            return  true;
                        }
                    }
                }
            }
        }
        Toast.makeText(getActivity(), "내용을 모두 입력해 주세요",Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (null != data.getData()) {
                Bitmap image = null;
                Uri uri = data.getData();
                switch (requestCode) {
                    case REQ_CODE_GALLERY:
                        imageView.setImageURI(uri);
                        break;
                    case REQ_CODE_CAMERA:
                        String imgPath = getRealPathFromURI(uri);
                        Log.i("image","imgPath="+imgPath);
                        image = getThumbnailImage(imgPath);
                        imageView.setImageBitmap(image);
                        //imageView.setImageURI(uri);
                        break;
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Bitmap getThumbnailImage(String imgPath) {
        Bitmap image = null;
        try {
            // 이미지 축소
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 3; // 1/3로 축소

            image = BitmapFactory.decodeFile(imgPath,options);

            Log.i("image","instance="+image);
            int exifDegree = exifOrientationToDegrees(imgPath);
            Log.i("image","exifDegree="+exifDegree);
            image = rotateImage(image, exifDegree);

        }catch (Exception e){
            Log.e("Thumbnail Error",e.toString());
            e.printStackTrace();
        }
        return image;
    }


    /**
     * EXIF정보를 회전각도로 변환하는 메서드
     * @param imgPath 이미지 경로
     * @return 실제 각도
     */
    public int exifOrientationToDegrees(String imgPath){
        int degree = 0;
        ExifInterface exif = null;

        try{
            exif = new ExifInterface(imgPath);
        }catch (IOException e){
            Log.e("exifOrientation", "cannot read exif");
            e.printStackTrace();
        }

        if (exif != null){
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
            if (orientation != -1){
                // We only recognize a subset of orientation tag values.
                switch(orientation){
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }
            }
        }
        return degree;
    }

    // 이미지 회전 함수
    public Bitmap rotateImage(Bitmap src, float degree) {

        // Matrix 객체 생성
        Matrix matrix = new Matrix();
        // 회전 각도 셋팅
        matrix.postRotate(degree);
        // 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(),src.getHeight(), matrix, true);
    }

    /**
     * 이미지를 회전시킵니다.
     * @param bitmap 비트맵 이미지
     * @param degrees 회전 각도
     * @return 회전된 이미지
     */
    public Bitmap rotate(Bitmap bitmap, int degrees){
        if(degrees != 0 && bitmap != null){
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);
            try{
                Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
                if(bitmap != converted){
                    bitmap.recycle();
                    bitmap = converted;
                }
            }catch(OutOfMemoryError ex){ // 메모리 부족
                ex.printStackTrace();
            }
        }
        return bitmap;
    }

    public String getRealPathFromURI(Uri contentUri){
        try{
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }catch (Exception e){
            e.printStackTrace();
            return contentUri.getPath();
        }
    }



}
