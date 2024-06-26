package com.example.firebase.repository;

import android.util.Log;

import com.example.firebase.api.ServicesPetApiService;
import com.example.firebase.retrofit.ApiClient;
import com.example.firebase.model.Services;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicesPetRepository {
    private ServicesPetApiService servicesPetApiService;

    public static ServicesPetApiService getServicesPetApi(){
        return ApiClient.getClient().create(ServicesPetApiService.class);
    }

    public ServicesPetRepository() {
        servicesPetApiService = ApiClient.getClient().create(ServicesPetApiService.class);

    }

    public void getLastServiceId(final OnLastServiceIdCallback callback) {

        Call<Map<String, Services>> call = servicesPetApiService.getAllServicesPet();

        call.enqueue(new Callback<Map<String, Services>>() {
            @Override
            public void onResponse(Call<Map<String, Services>> call, Response<Map<String, Services>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Map<String, Services> servicesMap = response.body();
                    ArrayList<Services> servicesList = new ArrayList<>(servicesMap.values());

                    if (!servicesList.isEmpty()) {
                        Services lastService = servicesList.get(servicesList.size() - 1);
                        long lastServiceId = lastService.getServiceId();

                        callback.onLastServiceIdReceived(lastServiceId);
                    } else {
                        callback.onLastServiceIdError("No services found");
                    }
                } else {
                    callback.onLastServiceIdError("Failed to get data");
                }
            }

            @Override
            public void onFailure(Call<Map<String, Services>> call, Throwable t) {
                callback.onLastServiceIdError("Error: " + t.getMessage());
                Log.e("ServicesPetRepository", "Error fetching services", t);
            }
        });
    }

    public interface OnLastServiceIdCallback {
        void onLastServiceIdReceived(long lastServiceId);

        void onLastServiceIdError(String error);
    }
}