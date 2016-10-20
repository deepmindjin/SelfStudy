package com.hongseokandrewjang.android.sqlitebasic_bbs;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DataUtil {

    public final static String DB_NAME = "sqlite.db";

    // 데이터 manipulation
    public static void insert(Context context, BbsData data){
        SQLiteDatabase db = null;
        try{
            // 1. db를 연결한다
            db = openDatabase(context, DB_NAME);
            // 2. 쿼리를 만든다
            String query = "insert into bbs(name, title, contents) values('"+data.name+"','"+data.title+"','"+data.contents+"')";
            // 3. 쿼리를 실행한다
            db.execSQL(query);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (db != null) {
                    db.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static BbsData select(Context context, int bbsno){
        BbsData data = null;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try{
            // 1. db를 연결한다
            db = openDatabase(context, DB_NAME);
            // 2. 쿼리를 만든다
            String query = "select * from bbs where no="+bbsno;
            // 3. 쿼리를 실행한다
            cursor = db.rawQuery(query,null);
            // 4. 반복문을 통해 값을 datas에 담아준다
            if (cursor.moveToNext()) {
                data = new BbsData();
                int index = cursor.getColumnIndex("no");
                data.no = cursor.getInt(index);
                index = cursor.getColumnIndex("title");
                data.title = cursor.getString(index);
                index = cursor.getColumnIndex("contents");
                data.contents = cursor.getString(index);
                index = cursor.getColumnIndex("name");
                data.name = cursor.getString(index);
                index = cursor.getColumnIndex("ndate");
                data.ndate = cursor.getString(index);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (db != null) {
                    db.close();
                }
                if (cursor != null) {
                    cursor.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return data;
    }

    public static ArrayList<BbsData> selectAll(Context context, int count){
        return selectAllByWord(context,count, "");
    }

    public static ArrayList<BbsData> selectAllByWord(Context context, int count, String word){
        ArrayList<BbsData> datas = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try{
            // 1. db를 연결한다
            db = openDatabase(context, DB_NAME);
            // 2. 쿼리를 만든다
            // 2,1 조건절을 만든다
            String where = "";
            String query;
            if(!word.equals("")) {
                query = "select * from bbs where " + where + "order by no desc limit "+ count;
            }else{
                query = "select * from bbs order by no desc limit "+count;
            }
            // 3. 쿼리를 실행한다
            cursor = db.rawQuery(query,null);
            // 4. 반복문을 통해 값을 datas에 담아준다
            while (cursor.moveToNext()){
                BbsData data = new BbsData();
                int index = cursor.getColumnIndex("no");
                data.no = cursor.getInt(index);
                index = cursor.getColumnIndex("title");
                data.title = cursor.getString(index);
                datas.add(data);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (db != null) {
                    db.close();
                }
                if (cursor != null) {
                    cursor.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return datas;
    }

    public static void update(Context context, BbsData data){
        SQLiteDatabase db = null;
        try{
            // 1. db를 연결한다
            db = openDatabase(context, DB_NAME);
            // 2. 쿼리를 만든다
            String query = "update bbs set name = '"+data.name
                    +"', title = '"+data.title
                    +"', contents = '"+data.contents
                    +"', ndate = 'CURRENT_TIMESTAMP' where no = "+data.no+"";
            // 3. 쿼리를 실행한다
            db.execSQL(query);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (db != null) {
                    db.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void delete(Context context, int bbsno){
        SQLiteDatabase db = null;
        try{
            // 1. db를 연결한다
            db = openDatabase(context, DB_NAME);
            // 2. 쿼리를 만든다
            String query = "select * from bbs where no="+bbsno;
            // 3. 쿼리를 실행한다
            db.execSQL(query);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (db != null) {
                    db.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    // DB에서 전부를 가져오는 함수
    public static Integer selectCount(Context context){
        int count = 0;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try{
            // 1. db를 연결한다
            db = openDatabase(context, DB_NAME);
            // 2. 쿼리를 만든다
            String query = "select count(*) from bbs ";
            // 3. 쿼리를 실행한다
            cursor = db.rawQuery(query,null);
            // 4. 반복문을 통해 값을 datas에 담아준다
            if (cursor.moveToNext()){
                count = cursor.getInt(0);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (db != null) {
                    db.close();
                }
                if (cursor != null) {
                    cursor.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return count;
    }

    // 데이터 베이스를 정의하는 method
    public static SQLiteDatabase openDatabase(Context context, String dbFileName){
        return SQLiteDatabase.openDatabase(getFullpath(context, dbFileName),null,0);
    }

    // 파일 이름을 입력하면 내장 디렉토리에 있는 파일의 전체 경로를 리턴해준다
    public static String getFullpath(Context context, String fileName){
        return context.getFilesDir().getAbsolutePath()+File.separator+fileName;
    }

    public static void assetToDisk(Context context, String fileName){
        InputStream is = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            // 외부에서 작성된 sqlite db파일 사용하기
            // (Asset은 폰의 하드에 들어가긴하는데 바로 접근이 불가능한 asset 영역에 따로 존재한다)
            // 1. assets 에 담아둔 파일을 internal 혹은 external 공간으로 복사하기위해 읽어온다
            AssetManager manager = context.getAssets();
            // asset 에 파일이 없으면 exception 이 발생하여 아래 로직이 실행되지 않는다.
            is = manager.open(fileName);
            bis = new BufferedInputStream(is);
            // 2. 저장할 위치에 파일이 없으면 생성한다.
            String targetFile = getFullpath(context, fileName);
            File file = new File(targetFile);
            if(!file.exists()){
                file.createNewFile();
            }

            // 3. outputStream을 생성해서 파일 내용을 쓴다
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            // 읽어올 데이터를 담아줄 변수
            int read = -1; // 모두 읽어오면 -1이 리턴된다
            // 한번에 읽을 버퍼의 크기를 지정
            byte buffer[] = new byte[1024];
            // 더 이상 읽어올 데이터가 없을 때 까지 buffer 단위로 읽어서 쓴다
            while((read = bis.read(buffer, 0, 1024)) != -1){
                bos.write(buffer, 0, read);
            }
            // 남아있는 데이터를 buffer에서 써준다
            bos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (bos != null) bos.close();
                if (fos != null) fos.close();
                if (bis != null) bis.close();
                if (is != null) is.close();
            }catch (Exception e){

            }
        }
    }

















}



