package com.example.firebase.activity.servicesPet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.example.firebase.R;

public class ServicesPetActivity extends AppCompatActivity {

    private Button btnListAll;
    private Button btnListServices, btnAddServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_services_pet);

        btnListAll = findViewById(R.id.btn_list_services);
        btnListServices = findViewById(R.id.btn_list_detail_update_delete);
        btnAddServices = findViewById(R.id.btn_create_services);

        btnListAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServicesPetActivity.this, ServicesListActivity.class);
                startActivity(intent);
            }
        });


        btnListServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServicesPetActivity.this, ServicesDetail_Update_Delete.class);
                startActivity(intent);
            }
        });

        btnAddServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServicesPetActivity.this, AddServicesActivity.class);
                startActivity(intent);
            }
        });

    }
}