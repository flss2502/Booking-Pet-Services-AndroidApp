package com.example.firebase.Api;

import com.example.firebase.Model.Services;
import com.example.firebase.Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServicesPetApi {

    Gson gson  = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ServicesPetApi servicesPetApi = new Retrofit.Builder()
            .baseUrl("https://android-decd6-default-rtdb.firebaseio.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ServicesPetApi.class);

    @GET("services/{serviceId}.json")
    Call<Services> getServicesPet(@Path("serviceId") Long serviceId);


    @GET("services.json")
    Call<List<Services>> getAllServicesPet();

    @POST("services.json")
    Call<Services> createServices(@Body Services services);

    @PATCH("services/{serviceId}.json")
    Call<Services> updateServices(@Path("serviceId") Long serviceId, @Body Services services);

}
