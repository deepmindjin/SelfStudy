package com.hongseokandrewjang.android.banthing;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hongseokandrewjang.android.banthing.Data.ChickenStore;
import com.hongseokandrewjang.android.banthing.Data.MENU;
import com.hongseokandrewjang.android.banthing.Fragments.MainListFragment;
import com.hongseokandrewjang.android.banthing.Fragments.MenuDetailFragment;
import com.hongseokandrewjang.android.banthing.Fragments.StoreDetailFragment;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by HongSeokAndrewJang on 2016-11-03.
 */


/*
    There are two recyclerviews in MainListFragment and StoreDetailFragment
    So i made a simple RecyclerView adapter and split it by inner fragment check
    So this adapter basically have 2 different flow
 */
public class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    Fragment fragment;
    public static ArrayList<ChickenStore> chickenStores = new ArrayList<>();
    private OnFragmentInteractionListener mListener;
    private Context mContext;

    public RecyclerViewAdapter(Fragment fragment, Context context, @Nullable ArrayList<ChickenStore> chickenStores) {
        mContext = context;
        this.fragment = fragment;
        // Detail fragment doesn't have store list, so bring basic store list from mainactivity
        if (chickenStores!=null) {
            this.chickenStores = chickenStores;
        }else{
            this.chickenStores = MainActivity.getChickenStores();
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (fragment instanceof MainListFragment) {
            view = LayoutInflater.from(mContext).inflate(R.layout.main_list_item, parent,false);
        }else if(fragment instanceof StoreDetailFragment){
            view = LayoutInflater.from(mContext).inflate(R.layout.store_menu_item,parent,false);
        }
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, final int position) {
        mListener = (OnFragmentInteractionListener)mContext;

        if (fragment instanceof MainListFragment) {
            ChickenStore store = MainActivity.getStore(position);
            holder.tvName.setText(store.getNAME() + " - " + store.getBRANCH());
            holder.delivery_fee_in_main_list.setText("배달비 : " + store.getDELIVERY_FEE() + "");
            Glide.with(mContext)
                    .load(store.getLOGO())
                    .bitmapTransform(new CropCircleTransformation(mContext))
                    .into(holder.imageView);
            String price = "";
            if (store.getMENU().get(0).getMENU_PRICE() != 0) {
                price = (int) (store.getMENU().get(0).getMENU_PRICE() / 2) + "";
            } else {
                price = "없습니다!";
            }
            holder.tvPrice.setText("가격 : " + price + " ~");
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onFragmentInteraction(StoreDetailFragment.TAG,position);
                }
            });
        }else if(fragment instanceof StoreDetailFragment){
            ChickenStore store = MainActivity.getStore(((StoreDetailFragment) fragment).getCurrentPosition());
            List<MENU> menus = store.getMENU();
            MENU menu = menus.get(position);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onFragmentInteraction(MenuDetailFragment.TAG,position);
                }
            });
            holder.tvName.setText(menu.getMENU_NAME());
            holder.tvPrice.setText(menu.getMENU_PRICE()+"원");
            Glide.with(mContext)
                    .load(menu.getMENU_IMAGE())
                    .into(holder.imageView);
        }
    }

    public void goToSelectedDetail(int selected){
        mListener.onFragmentInteraction(StoreDetailFragment.TAG,selected);
        Log.e("CLICKED","int : "+selected);
    }

    public void goToList(){
        mListener.onFragmentInteraction(MainListFragment.TAG,0);
    }

    @Override
    public int getItemCount() {
        int result = 0;
        // Two fragments uses different data for recyclerview
        if (fragment instanceof MainListFragment) {
            result =  chickenStores.size();
        }else if(fragment instanceof StoreDetailFragment){
            result = MainActivity.getChickenStores().get(((StoreDetailFragment) fragment).getCurrentPosition()).getMENU().size();
        }
        return result;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        // In Here, imageView, tvPrice, tvName is used in both fragment
        ImageView imageView = null;
        TextView tvPrice = null;
        TextView delivery_fee_in_main_list = null;
        TextView tvName = null;
        CardView cardView = null;

        public ViewHolder(View itemView) {
            super(itemView);
            if(fragment instanceof MainListFragment) {
                imageView = (ImageView) itemView.findViewById(R.id.image_on_main_list);
                tvPrice = (TextView) itemView.findViewById(R.id.price_in_main_list);
                delivery_fee_in_main_list = (TextView) itemView.findViewById(R.id.deliveryFee_in_main_list);
                tvName = (TextView) itemView.findViewById(R.id.storeName_in_main_list);
                cardView = (CardView) itemView.findViewById(R.id.card_view);
            }else if (fragment instanceof StoreDetailFragment){
                cardView = (CardView)itemView.findViewById(R.id.store_detail_cardview);
                imageView = (ImageView)itemView.findViewById(R.id.store_detail_menu_picture);
                tvPrice = (TextView)itemView.findViewById(R.id.store_detail_menu_price);
                tvName = (TextView)itemView.findViewById(R.id.store_detail_menu_name);
            }
        }
    }
}
