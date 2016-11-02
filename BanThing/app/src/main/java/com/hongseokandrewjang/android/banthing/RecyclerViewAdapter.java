package com.hongseokandrewjang.android.banthing;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by HongSeokAndrewJang on 2016-11-03.
 */


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private Context mContext;
    private ArrayList<ChickenStore> chickenStores = new ArrayList<>();
    private OnFragmentInteractionListener mListener;

    public RecyclerViewAdapter(Context context, ArrayList<ChickenStore> chickenStores) {
        mContext = context;
        this.chickenStores = chickenStores;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, final int position) {
        mListener = (OnFragmentInteractionListener)mContext;

        ChickenStore store = MainActivity.getStore(position);
        holder.storeName_in_main_list.setText(store.getNAME()+" - "+store.getBRANCH());
        holder.delivery_fee_in_main_list.setText("배달비 : "+store.getDELIVERY_FEE()+"");
        Glide.with(mContext)
                .load(store.getLOGO())
                .bitmapTransform(new CropCircleTransformation(mContext))
                .into(holder.mainLogo_in_main_list);
        String price = "";
        if (store.getMENU().get(0).getMENU_PRICE()!=null) {
            price = (int) (store.getMENU().get(0).getMENU_PRICE() / 2) + "";
        }else{
            price = "없습니다!";
        }
        holder.mainMenuPrice_in_main_list.setText("가격 : "+price+" ~");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(MainListFragment.TAG,position);
                Log.e(MainListFragment.TAG,"클릭됨");
            }
        });
    }

    @Override
    public int getItemCount() {
        return chickenStores.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mainLogo_in_main_list;
        TextView mainMenuPrice_in_main_list;
        TextView delivery_fee_in_main_list;
        TextView storeName_in_main_list;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            mainLogo_in_main_list = (ImageView)itemView.findViewById(R.id.image_on_main_list);
            mainMenuPrice_in_main_list = (TextView) itemView.findViewById(R.id.price_in_main_list);
            delivery_fee_in_main_list = (TextView) itemView.findViewById(R.id.deliveryFee_in_main_list);
            storeName_in_main_list = (TextView) itemView.findViewById(R.id.storeName_in_main_list);
            cardView = (CardView)itemView.findViewById(R.id.card_view);
        }
    }
}
