package com.example.firebase.Repository;

import com.example.firebase.Api.UserApiService;
import com.example.firebase.ApiClient;

public class UserRepository {
    public static UserApiService getUserApiServices(){
        return ApiClient.getClient().create(UserApiService.class);
    }
}
