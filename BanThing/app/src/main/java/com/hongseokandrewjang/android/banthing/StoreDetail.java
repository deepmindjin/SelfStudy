package com.hongseokandrewjang.android.banthing;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StoreDetail extends AppCompatActivity {

    public static final String STORE_DETAIL = "STORE";

    private ArrayList<ChickenStore.Menu> mMenus;
    private GridView storeMenues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);

        storeMenues = (GridView)findViewById(R.id.store_detail_grid_view);
        MenuAdapter adapter = new MenuAdapter(this,getLayoutInflater());
        storeMenues.setAdapter(adapter);

    }

    private class MenuAdapter extends BaseAdapter{
        Context mContext;
        LayoutInflater mInflater;

        public MenuAdapter(Context context, LayoutInflater inflater) {
            mContext = context;
            mInflater = inflater;
        }

        @Override
        public int getCount() {
            return mMenus.size();
        }

        @Override
        public ChickenStore.Menu getItem(int position) {
            return mMenus.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ChickenStore.Menu menu = mMenus.get(position);

            TextView menu_price = (TextView)convertView.findViewById(R.id.store_detail_menu_price);
            TextView menu_name = (TextView)convertView.findViewById(R.id.store_detail_menu_name);
            ImageView menu_image = (ImageView)convertView.findViewById(R.id.store_detail_menu_picture);

            menu_price.setText(menu.price);
            menu_name.setText(menu.name);
            menu_image.setImageResource(menu.menuImage);

            return convertView;
        }
    }
}
