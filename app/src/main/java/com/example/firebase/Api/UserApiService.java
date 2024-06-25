package com.example.firebase.Api;

import com.example.firebase.Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface UserApiService {

    Gson gson  = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

      UserApiService userApiService = new Retrofit.Builder()
              .baseUrl("https://android-decd6-default-rtdb.firebaseio.com/")
              .addConverterFactory(GsonConverterFactory.create(gson))
              .build()
              .create(UserApiService.class);


    @GET("users/{userId}.json")
    Call<User> getUser(@Path("userId") Long userId);

    @GET("users.json")
    Call<Map<String, User>>  getAllUsers();

    @POST("users.json")
    Call<User> createUser(@Body User user);

    @PATCH("users/{userId}.json")
    Call<User> updateUser(@Path("userId") Long userId, @Body User user);

}
