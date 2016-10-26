package com.hongseokandrewjang.android.banthing;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by HongSeokAndrewJang on 2016-10-13.
 */

public class ChickenStore implements Serializable{
    public int no;
    public String name;
    public String branch;
    public int minPrice;
    public int deliveryFee;
    public int mainMenuImage;
    public ArrayList<Menu> menus;

    public class Menu{
        public String name;
        public int price;
        public int menuImage;
    }
}
