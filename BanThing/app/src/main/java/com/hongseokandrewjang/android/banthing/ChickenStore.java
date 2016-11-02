package com.hongseokandrewjang.android.banthing;

import java.io.Serializable;
import java.util.List;

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

    @Override
    public String toString()
    {
        return "[MENU = "+MENU+", NAME = "+NAME+", LOGO = "+LOGO+", DELIVERY_FEE = "+DELIVERY_FEE+", BRANCH = "+BRANCH+"]";
    }

}

class MENU implements Serializable
{
    public MENU() {
    }

    private String MENU_NAME;

    private Long MENU_PRICE;

    private String MENU_IMAGE;

    public String getMENU_NAME ()
    {
        return MENU_NAME;
    }

    public void setMENU_NAME (String MENU_NAME)
    {
        this.MENU_NAME = MENU_NAME;
    }

    public Long getMENU_PRICE ()
    {
        return MENU_PRICE;
    }

    public void setMENU_PRICE (Long MENU_PRICE)
    {
        this.MENU_PRICE = MENU_PRICE;
    }

    public String getMENU_IMAGE ()
    {
        return MENU_IMAGE;
    }

    public void setMENU_IMAGE (String MENU_IMAGE)
    {
        this.MENU_IMAGE = MENU_IMAGE;
    }

    @Override
    public String toString()
    {
        return "[MENU_NAME = "+MENU_NAME+", MENU_PRICE = "+MENU_PRICE+", MENU_IMAGE = "+MENU_IMAGE+"]";
    }
}
