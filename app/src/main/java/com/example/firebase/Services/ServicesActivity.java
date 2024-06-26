package com.example.firebase.Services;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.Adapters.ServiceAdapter;
import com.example.firebase.Model.Services;
import com.example.firebase.R;

import java.util.ArrayList;
import java.util.List;
public class ServicesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ServiceAdapter serviceAdapter;
    private List<Services> servicesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data
        servicesList = new ArrayList<>();
        servicesList.add(new Services(1,"https://www.flaticon.com/free-icon/animals_1719853?term=pet+walking&page=1&position=20&origin=search&related_id=1719853", "Walk", "A walk in your neighborhood", 15.0));
        servicesList.add(new Services(2,"https://www.flaticon.com/free-icon/pet-house_10754017?term=pet+boarding&page=1&position=26&origin=search&related_id=10754017", "Pet boarding", "Temporary residence with another person", 25.0));
        servicesList.add(new Services(3,"https://www.flaticon.com/free-icon/pet-care_8182996?term=pet+care&page=1&position=9&origin=search&related_id=8182996" ,"Day nanny", "Caring for a pet in your home in your absence", 20.0));

        serviceAdapter = new ServiceAdapter(servicesList);
        recyclerView.setAdapter(serviceAdapter);
    }
}