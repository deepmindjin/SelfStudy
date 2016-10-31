package com.hongseokandrewjang.android.remote_retrofitexcercise01;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by HongSeokAndrewJang on 2016-10-28.
 */

public interface ISeoulSilverShelter {
    @GET("{key}/json/{serviceName}/{begin}/{end}/")
    Call<Shelter> getShelter(@Path("key")String key, @Path("serviceName")String serviceName, @Path("begin")int begin
    , @Path("end")int end);
}
