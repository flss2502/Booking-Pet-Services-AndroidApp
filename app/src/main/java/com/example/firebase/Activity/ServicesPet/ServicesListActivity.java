package com.example.firebase.Activity.ServicesPet;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.Adapter.ServicesAdapter;
import com.example.firebase.Api.ServicesPetApi;
import com.example.firebase.Model.Services;
import com.example.firebase.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicesListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ServicesAdapter servicesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_list);
        recyclerView = findViewById(R.id.recycler_view_services_pet);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        callApi();
    }

    private void callApi() {
        Call<List<Services>> call = ServicesPetApi.servicesPetApi.getAllServicesPet();

        call.enqueue(new Callback<List<Services>>() {
            @Override
            public void onResponse(Call<List<Services>> call, Response<List<Services>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Services> servicesList = response.body();

                    // Filter out null elements from the list
                    List<Services> filteredList = new ArrayList<>();
                    for (Services service : servicesList) {
                        if (service != null) {
                            filteredList.add(service);
                        }
                    }

                    // Create adapter with filtered list
                    servicesAdapter = new ServicesAdapter(filteredList);
                    recyclerView.setAdapter(servicesAdapter);

                    Toast.makeText(ServicesListActivity.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ServicesListActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Services>> call, Throwable t) {
                Toast.makeText(ServicesListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
