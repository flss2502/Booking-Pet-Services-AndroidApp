package com.example.firebase;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static String baseURL ="https://android-decd6-default-rtdb.firebaseio.com/";
    private static Retrofit retrofit;


    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
