package com.hongseokandrewjang.android.banthing.com.hongseokandrewjang.android.banthing.domain;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class ChickenStoreDataSetting {
    ArrayList<ChickenStore> stores = new ArrayList<>();
    Context context;
    public ChickenStoreDataSetting(Context context){
        this.context = context;
        init();
    }

    public ArrayList<ChickenStore> getData(Context context){
        init();
        stores = DataUtil.selectAll(context, DataUtil.selectCount(context));
        for(ChickenStore store : stores){
            store.image = context.getResources().getIdentifier("chicken1.jpg", "drawable", "com.hongseokandrewjang.android.banthing.com.hongseokandrewjang.android.banthing");
            Log.i("image","이미지의 주소는 : "+store.image);
        }
        return stores;
    }
    private  void init(){
        File file = new File(DataUtil.getFullpath(context,DataUtil.DB_NAME));
        if(!file.exists()) {
            DataUtil.assetToDisk(context, DataUtil.DB_NAME);
        }
    }

}
