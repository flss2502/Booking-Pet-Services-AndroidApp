package com.example.firebase.activity.pet;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.adapter.PetAdapter;
import com.example.firebase.api.PetApiService;
import com.example.firebase.R;
import com.example.firebase.model.Pet;
import com.example.firebase.repository.PetRepository;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PetListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PetAdapter petAdapter;
    private PetApiService petApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pet_list);

        recyclerView = findViewById(R.id.recycler_view_pet);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        petApiService = PetRepository.getPetApiServices();

        ApiGetAll();

    }

    private void ApiGetAll() {
        Call<Map<String, Pet>> call = petApiService.getAllPet();

        call.enqueue(new Callback<Map<String, Pet>>() {
            @Override
            public void onResponse(Call<Map<String, Pet>> call, Response<Map<String, Pet>> response) {
                if(response.isSuccessful() && response.body() != null){

                    Map<String, Pet> petMap = response.body();

                    ArrayList<Pet> petArrayList = new ArrayList<>(petMap.values());

                petAdapter = new PetAdapter(petArrayList);
                recyclerView.setAdapter(petAdapter);
                    Toast.makeText(PetListActivity.this, "List All Pet Success", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(PetListActivity.this, "List Pet Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, Pet>> call, Throwable t) {
                Toast.makeText(PetListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("PetListActivity", "Error pet", t);
            }
        });
    }
}