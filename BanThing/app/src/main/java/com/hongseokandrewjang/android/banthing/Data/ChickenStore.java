package com.hongseokandrewjang.android.banthing.Data;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by HongSeokAndrewJang on 2016-11-01.
 */

public class ChickenStore implements Serializable
{
    public ChickenStore() {
    }

    private List<MENU> MENU;

    private String NAME;

    private String LOGO;

    private Long DELIVERY_FEE;

    private String BRANCH;

    public List<MENU> getMENU ()
    {
        return MENU;
    }

    public void setMENU ( List<MENU> MENU)
    {
        this.MENU = MENU;
    }

    public String getNAME ()
    {
        return NAME;
    }

    public void setNAME (String NAME)
    {
        this.NAME = NAME;
    }

    public String getLOGO ()
    {
        return LOGO;
    }

    public void setLOGO (String LOGO)
    {
        this.LOGO = LOGO;
    }

    public Long getDELIVERY_FEE ()
    {
        return DELIVERY_FEE;
    }

    public void setDELIVERY_FEE (Long DELIVERY_FEE)
    {
        this.DELIVERY_FEE = DELIVERY_FEE;
    }

    public String getBRANCH ()
    {
        return BRANCH;
    }

    public void setBRANCH (String BRANCH)
    {
        this.BRANCH = BRANCH;
    }

    public static ChickenStore fromRealmData(RealmChickenStore realmChickenStore){
        Realm realm = Realm.getDefaultInstance();
        ChickenStore store = new ChickenStore();

        store.setBRANCH(realmChickenStore.getBRANCH());
        store.setNAME(realmChickenStore.getNAME());
        store.setDELIVERY_FEE(realmChickenStore.getDELIVERY_FEE());
        store.setLOGO(realmChickenStore.getLOGO());

        ArrayList<MENU> menus = new ArrayList<>();
        for (int i=0;i<realmChickenStore.getMENU().size();i++){
//        for (RealmMENU menu : realmChickenStore.getMENU()){
            RealmMENU menu = realmChickenStore.getMENU().get(i);
            com.hongseokandrewjang.android.banthing.Data.MENU storeMenu = new MENU();
            storeMenu.setMENU_IMAGE(menu.getMENU_IMAGE());
            storeMenu.setMENU_PRICE(menu.getMENU_PRICE());
            storeMenu.setMENU_NAME(menu.getMENU_NAME());
            Log.e("From Realm Data","메뉴 : "+storeMenu.getMENU_NAME());
            menus.add(storeMenu);
        }

        store.setMENU(menus);
        for (MENU menu : store.getMENU()){
            Log.e("Data output",store.getNAME()+"에서 "+menu.getMENU_NAME()+"을 팔고 있습니다");
        }

        return store;
    }

    public static RealmChickenStore ToRealmData(ChickenStore store){
        Realm realm = Realm.getDefaultInstance();
        RealmChickenStore realmChickenStore = new RealmChickenStore();

        realmChickenStore.setBRANCH(store.getBRANCH());
        realmChickenStore.setDELIVERY_FEE(store.getDELIVERY_FEE());
        realmChickenStore.setLOGO(store.getLOGO());
        realmChickenStore.setNAME(store.getNAME());

        RealmList<RealmMENU> menuList = new RealmList<>();

        for (MENU chickenMenu : store.getMENU()) {
            RealmMENU menu = new RealmMENU();
            menu.setMENU_IMAGE(chickenMenu.getMENU_IMAGE());
            menu.setMENU_NAME(chickenMenu.getMENU_NAME());
            menu.setMENU_PRICE(chickenMenu.getMENU_PRICE());
            menuList.add(menu);
        }
        realmChickenStore.setMENU(menuList);

        for (RealmMENU menu : realmChickenStore.getMENU()){
            Log.e("Data input",realmChickenStore.getNAME()+"에서 "+menu.getMENU_NAME()+"을 팔고 있습니다");
        }
        return  realmChickenStore;
    }
}

