package com.example.firebase.Api;

import com.example.firebase.Model.Pet;
import com.example.firebase.Model.Services;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PetApiService {

    String PET = "pets";

    @GET(PET + ".json")
    Call<Map<String, Pet>> getPetById(
            @Query("orderBy") String orderBy,
            @Query("equalTo") Long serviceId
    );

    @GET(PET + ".json")
    Call<Map<String, Pet>> getAllPet();
    @POST(PET + ".json")
    Call<Pet> createPet(@Body Pet pet);

    @PATCH(PET + "/{petId}.json")
    Call<Pet> updatePet(@Path("petId") Long petId, @Body Pet pet);

    @DELETE(PET + "/{key}.json")
    Call<Void> deletePetByFirebaseKey(@Path("key") String firebaseKey);


}
