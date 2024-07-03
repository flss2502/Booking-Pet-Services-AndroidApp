//package com.example.firebase.repository;
//
//import android.util.Log;
//
//import com.example.firebase.api.PetApiService;
//import com.example.firebase.retrofit.ApiClient;
//
//import java.util.ArrayList;
//import java.util.Map;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class PetRepository {
//
//    private PetApiService petApiService;
//    public static PetApiService getPetApiServices(){
//        return ApiClient.getClient().create(PetApiService.class);
//    }
//
//    public PetRepository(){
//        petApiService = ApiClient.getClient().create(PetApiService.class);
//    }
//
//    public void getLastPetId(final OnLastPetIdCallback callback){
//
//        Call<Map<String, Pet>> call = petApiService.getAllPet();
//
//        call.enqueue(new Callback<Map<String, Pet>>() {
//            @Override
//            public void onResponse(Call<Map<String, Pet>> call, Response<Map<String, Pet>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    Map<String, Pet> petMap = response.body();
//                    ArrayList<Pet> petArrayList = new ArrayList<>(petMap.values());
//
//                    if (!petArrayList.isEmpty()) {
//                        Pet lastPet = petArrayList.get(petArrayList.size() - 1);
//                        long lastPetId = lastPet.getPetId();
//
//                        callback.onLastPetIdReceived(lastPetId);
//                    } else {
//                        callback.onLastPetIdError("No services found");
//                    }
//                } else {
//                    callback.onLastPetIdError("Failed to get data");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Map<String, Pet>> call, Throwable t) {
//                callback.onLastPetIdError("Error: " + t.getMessage());
//                Log.e("ServicesPetRepository", "Error fetching services", t);
//            }
//        });
//    }
//
//    public interface OnLastPetIdCallback {
//        void onLastPetIdReceived(long lastPetId);
//
//        void onLastPetIdError(String error);
//    }
//}
