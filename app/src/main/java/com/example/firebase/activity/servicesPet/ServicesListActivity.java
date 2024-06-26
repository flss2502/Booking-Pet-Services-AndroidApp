package com.example.firebase.activity.servicesPet;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.adapter.ServicesAdapter;
import com.example.firebase.api.ServicesPetApiService;
import com.example.firebase.model.Services;
import com.example.firebase.R;
import com.example.firebase.repository.ServicesPetRepository;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicesListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ServicesAdapter servicesAdapter;
    private ServicesPetApiService servicesPetApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_list);
        recyclerView = findViewById(R.id.recycler_view_services_pet);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        servicesPetApiService = ServicesPetRepository.getServicesPetApi();

        callApi();
    }

    private void callApi() {
        Call<Map<String,Services>> call = servicesPetApiService.getAllServicesPet();

        call.enqueue(new Callback<Map<String,Services>>() {
            @Override
            public void onResponse(Call<Map<String,Services>> call, Response<Map<String,Services>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    Map<String, Services> servicesMap = response.body();

                    ArrayList<Services> servicesList = new ArrayList<>(servicesMap.values());


                    // Create adapter with filtered list
                    servicesAdapter = new ServicesAdapter(servicesList);
                    recyclerView.setAdapter(servicesAdapter);

                    Toast.makeText(ServicesListActivity.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ServicesListActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Map<String,Services>> call, Throwable t) {
                Toast.makeText(ServicesListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("AddServicesActivity", "Error services", t);
            }
        });
    }
}
