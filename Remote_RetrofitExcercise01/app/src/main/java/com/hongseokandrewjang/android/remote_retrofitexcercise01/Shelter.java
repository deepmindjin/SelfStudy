package com.hongseokandrewjang.android.remote_retrofitexcercise01;

/**
 * Created by HongSeokAndrewJang on 2016-10-28.
 */

public class Shelter
{
    private GbSeniorCenter GbSeniorCenter;

    public GbSeniorCenter getGbSeniorCenter ()
    {
        return GbSeniorCenter;
    }

    public void setGbSeniorCenter (GbSeniorCenter GbSeniorCenter)
    {
        this.GbSeniorCenter = GbSeniorCenter;
    }


    public class GbSeniorCenter
    {
        private RESULT RESULT;

        private String list_total_count;

        private Row[] row;

        public RESULT getRESULT ()
        {
            return RESULT;
        }

        public void setRESULT (RESULT RESULT)
        {
            this.RESULT = RESULT;
        }

        public String getList_total_count ()
        {
            return list_total_count;
        }

        public void setList_total_count (String list_total_count)
        {
            this.list_total_count = list_total_count;
        }

        public Row[] getRow ()
        {
            return row;
        }

        public void setRow (Row[] row)
        {
            this.row = row;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [RESULT = "+RESULT+", list_total_count = "+list_total_count+", row = "+row+"]";
        }
    }

    public class Row
    {
        private String DONG_NM;

        private String GB;

        private String TEL_NO;

        private String SENIOR_CENTER;

        private String ROAD_ADDR;

        private String NO;

        public String getDONG_NM ()
        {
            return DONG_NM;
        }

        public void setDONG_NM (String DONG_NM)
        {
            this.DONG_NM = DONG_NM;
        }

        public String getGB ()
        {
            return GB;
        }

        public void setGB (String GB)
        {
            this.GB = GB;
        }

        public String getTEL_NO ()
        {
            return TEL_NO;
        }

        public void setTEL_NO (String TEL_NO)
        {
            this.TEL_NO = TEL_NO;
        }

        public String getSENIOR_CENTER ()
        {
            return SENIOR_CENTER;
        }

        public void setSENIOR_CENTER (String SENIOR_CENTER)
        {
            this.SENIOR_CENTER = SENIOR_CENTER;
        }

        public String getROAD_ADDR ()
        {
            return ROAD_ADDR;
        }

        public void setROAD_ADDR (String ROAD_ADDR)
        {
            this.ROAD_ADDR = ROAD_ADDR;
        }

        public String getNO ()
        {
            return NO;
        }

        public void setNO (String NO)
        {
            this.NO = NO;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [DONG_NM = "+DONG_NM+", GB = "+GB+", TEL_NO = "+TEL_NO+", SENIOR_CENTER = "+SENIOR_CENTER+", ROAD_ADDR = "+ROAD_ADDR+", NO = "+NO+"]";
        }
    }

    @Override
    public String toString()
    {
        return "ClassPojo [GbSeniorCenter = "+GbSeniorCenter+"]";
    }

    public class RESULT
    {
        private String MESSAGE;

        private String CODE;

        public String getMESSAGE ()
        {
            return MESSAGE;
        }

        public void setMESSAGE (String MESSAGE)
        {
            this.MESSAGE = MESSAGE;
        }

        public String getCODE ()
        {
            return CODE;
        }

        public void setCODE (String CODE)
        {
            this.CODE = CODE;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [MESSAGE = "+MESSAGE+", CODE = "+CODE+"]";
        }
    }
}
