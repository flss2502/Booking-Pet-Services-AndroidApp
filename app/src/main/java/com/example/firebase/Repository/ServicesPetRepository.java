package com.example.firebase.Repository;

import com.example.firebase.Api.ServicesPetApiService;
import com.example.firebase.ApiClient;

public class ServicesPetRepository {
    public static ServicesPetApiService getServicesPetApi(){
        return ApiClient.getClient().create(ServicesPetApiService.class);
    }
}
