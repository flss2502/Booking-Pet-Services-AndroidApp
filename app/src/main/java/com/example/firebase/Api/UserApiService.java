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



    String USERS = "users";

    @GET(USERS + "/{userId}.json")
    Call<User> getUser(@Path("userId") Long userId);

    @GET(USERS + ".json")
    Call<Map<String, User>>  getAllUsers();

    @POST(USERS + ".json")
    Call<User> createUser(@Body User user);

    @PATCH(USERS+ "/{userId}.json")
    Call<User> updateUser(@Path("userId") Long userId, @Body User user);

}
