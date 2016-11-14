package com.hongseokandrewjang.android.banthing.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hongseokandrewjang.android.banthing.Data.ChickenStore;
import com.hongseokandrewjang.android.banthing.Data.MENU;
import com.hongseokandrewjang.android.banthing.MainActivity;
import com.hongseokandrewjang.android.banthing.OnFragmentInteractionListener;
import com.hongseokandrewjang.android.banthing.R;
import com.hongseokandrewjang.android.banthing.SignIn;

/**
 * Created by HongSeok on 2016-11-07.
 */

public class MenuDetailFragment extends Fragment{
    private ChickenStore currentStore;
    private MENU currentMenu;
    private int menu_position;
    private OnFragmentInteractionListener mListener;

    public static final String TAG = "MenuDetailFragment";
    public static final String TOTAL_PRICE = "TOTAL_PRICE";
    private Toolbar toolbar;
    private ImageView menu_image;
    private TextView menu_name, menu_price, show_total_price;
    private LinearLayout addBeerLayout, addCokeLayout;
    private CheckedTextView addon_beer,  addon_Big_Coke;
    private Button btnGoBack, btnGoToPurchase;
    private int totalPrice = 0;
    private final int cokePrice = 1500;
    private final int beerPrice = 4000;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mListener = (OnFragmentInteractionListener)getActivity();
        View view = inflater.inflate(R.layout.fragment_menu_detail, container, false);
        // Find  which menu to show
        currentMenu = currentStore.getMENU().get(menu_position);

        // Declare  basic widgets
        totalPrice = currentMenu.getMENU_PRICE().intValue();
        menu_image = (ImageView)view.findViewById(R.id.menu_detail_image);
        menu_name = (TextView)view.findViewById(R.id.menu_detail_name);
        menu_price = (TextView)view.findViewById(R.id.menu_detail_price);
        show_total_price = (TextView)view.findViewById(R.id.menu_detail_total_price);
        addon_beer = (CheckedTextView)view.findViewById(R.id.addon_beer);
        addon_Big_Coke = (CheckedTextView)view.findViewById(R.id.addon_Big_Coke);
        addBeerLayout = (LinearLayout)view.findViewById(R.id.add_beer_check_linear);
        addCokeLayout = (LinearLayout)view.findViewById(R.id.addon_Big_Coke_linear);
        btnGoBack = (Button)view.findViewById(R.id.menu_detail_btnGoBack);
        btnGoToPurchase = (Button)view.findViewById(R.id.menu_detail_btnGoToPurchase);

        // Add click listener for checking and changing total price
        addBeerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addon_beer.setChecked(!addon_beer.isChecked());
                if (!addon_beer.isChecked()){
                    totalPrice = totalPrice - beerPrice;
                    show_total_price.setText(totalPrice+" 원");
                }else{
                    totalPrice = totalPrice + beerPrice;
                    show_total_price.setText(totalPrice+" 원");
                }
            }
        });
        addCokeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addon_Big_Coke.setChecked(!addon_Big_Coke.isChecked());
                if (!addon_Big_Coke.isChecked()){
                    totalPrice = totalPrice - cokePrice;
                    show_total_price.setText(totalPrice+" 원");
                }else{
                    totalPrice = totalPrice + cokePrice;
                    show_total_price.setText(totalPrice+" 원");
                }
            }
        });

        // Set buttons click listener
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(StoreDetailFragment.TAG, MainActivity.getStorePosition());
            }
        });
        btnGoToPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = SignIn.goToSignIn(getActivity());
                intent.putExtra(TOTAL_PRICE,totalPrice);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.layout_leftin,R.anim.layout_leftout);
            }
        });

        // Set inputs
        menu_name.setText(currentMenu.getMENU_NAME());
        menu_price.setText(currentMenu.getMENU_PRICE()+"원");
        show_total_price.setText(totalPrice+" 원");
        Glide.with(getActivity())
                .load(currentMenu.getMENU_IMAGE())
                .into(menu_image);

        // Make back button go to store detail with animation
        goBack(view);
        return view;
    }

    private void goBack(View view){
        // Make back button go to mainList with animation
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK){
                    mListener.onFragmentInteraction(StoreDetailFragment.TAG, MainActivity.getStorePosition());
                    return true;
                }
                return false;
            }
        });
    }

    public void setCurrentStore(ChickenStore store){
        currentStore = store;
    }

    public void setCurrentMenuPosition(int position){
        menu_position = position;
    }
}
