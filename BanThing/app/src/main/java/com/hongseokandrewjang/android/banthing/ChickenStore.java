package com.hongseokandrewjang.android.banthing;

import java.io.Serializable;
import java.util.List;


public class ChickenStore implements Serializable{

    public ChickenStore(){
    }
    private String BRANCH;
    private Long DELIVERY_FEE;
    private List<Menu> MENU;
    private String NAME;

    public String getBRANCH() {
        return BRANCH;
    }

    public Long getDELIVERY_FEE() {
        return DELIVERY_FEE;
    }

    public List<Menu> getMENU() {
        return MENU;
    }

    public String getNAME() {
        return NAME;
    }

    public static class Menu{
        public Menu() {
        }
        private String MENU_IMAGE;
        private String MENU_NAME;
        private Long MENU_PRICE;

        public Long getMENU_PRICE() {
            return MENU_PRICE;
        }

        public String getMENU_IMAGE() {
            return MENU_IMAGE;
        }

        public String getMENU_NAME() {
            return MENU_NAME;
        }
    }
}
