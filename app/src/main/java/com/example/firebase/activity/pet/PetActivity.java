




package com.example.firebase.activity.pet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.R;

public class PetActivity extends AppCompatActivity {

    private Button btnListAllPet;
    private Button btnDetail_Update_Delete, btnCreatePet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pet);

        btnListAllPet = findViewById(R.id.bnt_list_all_pet);
        btnDetail_Update_Delete = findViewById(R.id.btn_detail_update_delete);
        btnCreatePet = findViewById(R.id.btn_create_pet);

        btnDetail_Update_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetActivity.this, PetDetail_Update_Delete.class);
                startActivity(intent);
            }
        });

        btnListAllPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetActivity.this, PetListActivity.class);
                startActivity(intent);
            }
        });

        btnCreatePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetActivity.this, AddPetActivity.class);
                startActivity(intent);
            }
        });

    }
}