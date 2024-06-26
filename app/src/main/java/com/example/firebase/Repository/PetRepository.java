package com.example.firebase.Repository;

import com.example.firebase.Api.PetApiService;
import com.example.firebase.ApiClient;

public class PetRepository {
    public static PetApiService getPetApiServices(){
        return ApiClient.getClient().create(PetApiService.class);
    }
}
