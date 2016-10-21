package com.hongseokandrewjang.android.getseoulrealestateprice;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> sigunguList;
    ArrayAdapter<Integer> yearList;
    Spinner spinnerYear;
    Spinner spinnerSigungu;
    Button btnCallData;

    TextView showRegion;
    TextView showAverage;


    private final static String[] SIGUNGU = {
            "종로구", "중구", "용산구", "성동구", "광진구", "동대문구", "중랑구", "성북구", "강북구", "도봉구", "노원구", "노원구", "은평구", "서대문구","마포구","양천구", "강서구", "구로구", "금천구", "영등포구", "동작구", "관악구", "서초구", "강남구", "송파구", "강도구"};
    private final static Integer[] YEARS = {2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCallData = (Button)findViewById(R.id.btnCallData);
        btnCallData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedYear = Integer.parseInt(spinnerYear.getItemAtPosition(spinnerYear.getSelectedItemPosition()).toString());
                String selectedGu = spinnerSigungu.getItemAtPosition(spinnerSigungu.getSelectedItemPosition()).toString();
                getData(selectedYear,selectedGu);
            }
        });

        spinnerYear = (Spinner)findViewById(R.id.spinnerYearChoice);
        spinnerYear.setPrompt("연도를 선택해 주세요");
        yearList = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,YEARS);
        spinnerYear.setAdapter(yearList);

        spinnerSigungu = (Spinner)findViewById(R.id.spinnerSigunguChoice);
        spinnerSigungu.setPrompt("구를 선택해 주세요");
        sigunguList = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,SIGUNGU);
        spinnerSigungu.setAdapter(sigunguList);

        showAverage = (TextView) findViewById(R.id.showAverage);
        showRegion = (TextView) findViewById(R.id.showRegion);
    }

    public void getData(int year, final String sigungu){
        final String webUri = "http://openapi.seoul.go.kr:8088/4b677653716465653239614e6e636a/json/IndividuallyPostedLandPriceService/1/100/"+ Uri.encode(sigungu)+"/%20/%20/%20/%20/"+year;

        new AsyncTask<Void, Void, String>(){
            int sum=0;
            ProgressDialog progress;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progress = new ProgressDialog(MainActivity.this);
                progress.setTitle("찾는중");
                progress.setMessage("- 자비스 가동중 -");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setCancelable(true);
                progress.show();
            }

            @Override
            protected String doInBackground(Void... params) {
                String result ="";
                Log.i("URL VALIDATION",webUri);
                try {result = Remote.getData(webUri);
                }catch (Exception e){}
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                StringBuffer sb = new StringBuffer();
                super.onPostExecute(s);
                try {
                    JSONObject json = new JSONObject(s);
                    Log.i("JSON",""+json);
                    JSONObject topObject = json.getJSONObject("IndividuallyPostedLandPriceService");
                    JSONArray rows = topObject.getJSONArray("row");
                    int rowsCount = rows.length();
                    for (int i = 0; i < rowsCount; i++) {
                        JSONObject result = (JSONObject) rows.get(i);
                        String JIGA = result.getString("JIGA");
                        sum = sum+Integer.parseInt(JIGA);
                        Log.i("JIGA","지가는 = "+JIGA);
                    }
                    showRegion.setText(sigungu+"의 평균 지가는");
                    showAverage.setText(sum/100+"원 입니다");
                    progress.dismiss();
                }catch (Exception e){}
            }
        }.execute();
    }
}
