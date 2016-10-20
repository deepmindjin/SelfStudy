package com.hongseokandrewjang.android.banthing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hongseokandrewjang.android.banthing.com.hongseokandrewjang.android.banthing.domain.ChickenStore;
import com.hongseokandrewjang.android.banthing.com.hongseokandrewjang.android.banthing.domain.ChickenStoreDataSetting;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ChickenStore> stores = new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChickenStoreDataSetting setting = new ChickenStoreDataSetting(this);
        stores = setting.getData(this);

        listView = (ListView)findViewById(R.id.listView_On_Main);
        MainListAdapter adapter = new MainListAdapter(this.getLayoutInflater(), stores);
        listView.setAdapter(adapter);
    }
}

class MainListAdapter extends BaseAdapter{
    ArrayList<ChickenStore> stores = new ArrayList<>();
    LayoutInflater inflater;

    MainListAdapter(LayoutInflater inflater, ArrayList<ChickenStore> stores){
        this.inflater = inflater;
        this.stores = stores;
    }

    @Override
    public int getCount() {
        return stores.size();
    }

    @Override
    public Object getItem(int position) {
        return stores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        final View view = convertView;
        ChickenStore store = stores.get(position);
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.main_list_item, null);
        }
//        RelativeLayout item_layout = (RelativeLayout)convertView.findViewById(R.id.list_item_view);
//        item_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(view.getContext(), StoreDetail.class);
//                intent.getAction();
//            }
//        });
        ImageView image = (ImageView)convertView.findViewById(R.id.image_on_main_list);
        image.setImageResource(R.drawable.chicken4);
        TextView name = (TextView)convertView.findViewById(R.id.chickenStoreName);
        name.setText(store.name +" "+store.branch);
        TextView minPrice = (TextView)convertView.findViewById(R.id.chickenStoreMinPrice);
        minPrice.setText("가격 : "+store.minPrice+"원 ~");
        TextView deliveryFee = (TextView)convertView.findViewById(R.id.chickenStoreDeliveryFee);
        deliveryFee.setText("배달팁 : "+store.deliveryFee+"원");
        return convertView;
    }
}