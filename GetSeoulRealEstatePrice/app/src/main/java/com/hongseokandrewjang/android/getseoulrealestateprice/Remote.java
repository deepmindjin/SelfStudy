package com.hongseokandrewjang.android.getseoulrealestateprice;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by HongSeokAndrewJang on 2016-10-21.
 */

public class Remote {

    private static final String TAG = "REMOTE";

    public static String getData(String webUrl) throws IOException{
        StringBuffer result = new StringBuffer();
        URL url = new URL(webUrl);
        Log.i(TAG,""+url);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        Log.i(TAG,""+responseCode);
        if (responseCode==HttpURLConnection.HTTP_OK){
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String dataLine = null;
            while((dataLine=br.readLine())!=null){
                result.append(dataLine);
            }
            br.close();
        }else{
            Log.e(TAG,"HTTP error code = "+responseCode);
        }
        Log.i(TAG,""+result.toString());
        return result.toString();
    }
}
