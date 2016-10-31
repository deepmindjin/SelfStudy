package com.hongseokandrewjang.android.remote_retrofitexcercise01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String baseUrl = "http://openapi.seoul.go.kr:8088/";
    private final String key = "4b677653716465653239614e6e636a";
    private final String serviceName = "GbSeniorCenter";
    private final int begin = 1;
    private final int end = 10;

    TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView)findViewById(R.id.textView);

        String url = baseUrl+key+"/json/"+serviceName+"/"+begin+"/"+end;

        Retrofit client  = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build();

        ISeoulSilverShelter iSeoulSilverShelter = client.create(ISeoulSilverShelter.class);
        Call<Shelter> seoulSilverShelterCall = iSeoulSilverShelter.getShelter(key,serviceName,begin,end);
        seoulSilverShelterCall.enqueue(new Callback<Shelter>() {
            @Override
            public void onResponse(Call<Shelter> call, Response<Shelter> response) {
                if (response.isSuccessful()){
                    StringBuffer sb = new StringBuffer();
                    Shelter shelter = response.body();
                    for(Shelter.Row row : shelter.getGbSeniorCenter().getRow()){
                        String senior_center = row.getSENIOR_CENTER();
                        sb.append(senior_center+"\n");
                    }
                    String result = sb.toString();
                    mTextView.setText(result);
                }
            }
            @Override
            public void onFailure(Call<Shelter> call, Throwable t) {

            }
        });
    }
}
