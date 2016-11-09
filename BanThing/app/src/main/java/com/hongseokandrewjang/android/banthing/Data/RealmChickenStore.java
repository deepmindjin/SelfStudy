package com.hongseokandrewjang.android.banthing.Data;

import java.util.ArrayList;

import io.realm.RealmModel;

/**
 * Created by HongSeok on 2016-11-08.
 */

public class RealmChickenStore  implements RealmModel{

    private ArrayList<RealmMENU> MENU;

    private String NAME;

    public ArrayList<RealmMENU> getMENU() {
        return MENU;
    }

    public void setMENU(ArrayList<RealmMENU> MENU) {
        this.MENU = MENU;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getLOGO() {
        return LOGO;
    }

    public void setLOGO(String LOGO) {
        this.LOGO = LOGO;
    }

    public Long getDELIVERY_FEE() {
        return DELIVERY_FEE;
    }

    public void setDELIVERY_FEE(Long DELIVERY_FEE) {
        this.DELIVERY_FEE = DELIVERY_FEE;
    }

    public String getBRANCH() {
        return BRANCH;
    }

    public void setBRANCH(String BRANCH) {
        this.BRANCH = BRANCH;
    }

    private String LOGO;

    private Long DELIVERY_FEE;

    private String BRANCH;



}
