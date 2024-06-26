package com.example.firebase.activity.pet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.api.PetApiService;
import com.example.firebase.model.Pet;
import com.example.firebase.R;
import com.example.firebase.repository.PetRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPetActivity extends AppCompatActivity {

    private EditText edtPetName, edtTypePet, edtUserId;

    private Button btnCreatePet;
    private PetApiService petApiService;
    private PetRepository petRepository;
    private Long lastIdPetcheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_pet);

        edtPetName = findViewById(R.id.edt_pet_name);
        edtTypePet = findViewById(R.id.edt_pet_type);
        edtUserId = findViewById(R.id.edt_userId);

        btnCreatePet = findViewById(R.id.btn_add_pet);

        petApiService = PetRepository.getPetApiServices();
        petRepository = new PetRepository();

        petRepository.getLastPetId(new PetRepository.OnLastPetIdCallback() {
            @Override
            public void onLastPetIdReceived(long lastPetId) {
                lastIdPetcheck = lastPetId;
            }

            @Override
            public void onLastPetIdError(String error) {
                Log.e("AddPet", "Faild" + error);
            }
        });


        btnCreatePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPet();
            }

            private void addPet() {
                String petName = edtPetName.getText().toString();
                String type = edtTypePet.getText().toString();
                Long userId = Long.parseLong(edtUserId.getText().toString());


                if (lastIdPetcheck == null) {
                    lastIdPetcheck = 1L;
                } else {
                    lastIdPetcheck = lastIdPetcheck + 1L;
                }

                Pet pet = new Pet(lastIdPetcheck, userId, petName,type);

                Call<Pet> call = petApiService.createPet(pet);

                call.enqueue(new Callback<Pet>() {
                    @Override
                    public void onResponse(Call<Pet> call, Response<Pet> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(AddPetActivity.this, "Pet created successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddPetActivity.this, "Failed to create Pet!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Pet> call, Throwable t) {
                        Toast.makeText(AddPetActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


    }
}