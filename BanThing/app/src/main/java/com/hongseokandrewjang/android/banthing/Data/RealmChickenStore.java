package com.hongseokandrewjang.android.banthing.Data;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 * Created by HongSeok on 2016-11-08.
 */

public class RealmChickenStore  extends RealmObject implements RealmModel{

    private RealmList<RealmMENU> MENU;

    private String NAME;

    public RealmList<RealmMENU> getMENU() {
        return MENU;
    }

    public void setMENU(RealmList<RealmMENU> MENU) {
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
