package com.example.firebase.repository;

import com.example.firebase.api.UserApiService;
import com.example.firebase.retrofit.ApiClient;

public class UserRepository {
    public static UserApiService getUserApiServices(){
        return ApiClient.getClient().create(UserApiService.class);
    }
}
