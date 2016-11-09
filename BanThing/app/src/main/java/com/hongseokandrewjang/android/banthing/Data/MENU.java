package com.hongseokandrewjang.android.banthing.Data;

import java.io.Serializable;

/**
 * Created by HongSeok on 2016-11-08.
 */

public class MENU implements Serializable {
    public MENU() {
    }

    private String MENU_NAME;

    private Long MENU_PRICE;

    private String MENU_IMAGE;

    public String getMENU_NAME() {
        return MENU_NAME;
    }

    public void setMENU_NAME(String MENU_NAME) {
        this.MENU_NAME = MENU_NAME;
    }

    public Long getMENU_PRICE() {
        return MENU_PRICE;
    }

    public void setMENU_PRICE(Long MENU_PRICE) {
        this.MENU_PRICE = MENU_PRICE;
    }

    public String getMENU_IMAGE() {
        return MENU_IMAGE;
    }

    public void setMENU_IMAGE(String MENU_IMAGE) {
        this.MENU_IMAGE = MENU_IMAGE;
    }

    @Override
    public String toString() {
        return "[MENU_NAME = " + MENU_NAME + ", MENU_PRICE = " + MENU_PRICE + ", MENU_IMAGE = " + MENU_IMAGE + "]";
    }
}
