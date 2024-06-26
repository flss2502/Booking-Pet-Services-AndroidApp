package com.example.firebase.Api;

import com.example.firebase.Model.Services;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServicesPetApiService {

    String SERVICES = "services";

    @GET(SERVICES + ".json")
    Call<Map<String, Services>> getServicesByServiceId(
            @Query("orderBy") String orderBy,
            @Query("equalTo") Long serviceId
    );

    @GET(SERVICES + ".json")
    Call<Map<String, Services>> getAllServicesPet();
    @POST(SERVICES + ".json")
    Call<Services> createServices(@Body Services services);

    @PATCH(SERVICES + "/{serviceId}.json")
    Call<Services> updateServices(@Path("serviceId") Long serviceId, @Body Services services);

    @DELETE(SERVICES + "/{key}.json")
    Call<Void> deleteServiceByFirebaseKey(@Path("key") String firebaseKey);


}
