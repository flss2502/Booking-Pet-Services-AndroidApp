package com.example.firebase.activity.pet;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.api.PetApiService;
import com.example.firebase.model.Pet;
import com.example.firebase.R;
import com.example.firebase.repository.PetRepository;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetDetail_Update_Delete extends AppCompatActivity {

    private EditText edtPetName, edtTypePet, edtUserId, edtPetId;
    private static final String TAG = "PetDetail_Update_Delete";
    private PetApiService petApiService;
    private String firebaseKey;

    private Button btnApiDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail_update_delete);

        edtPetId = findViewById(R.id.edt_petId);
        edtPetName = findViewById(R.id.edt_pet_name);
        edtTypePet = findViewById(R.id.edt_pet_type);
        edtUserId = findViewById(R.id.edt_userId);

        btnApiDetail = findViewById(R.id.btn_call_api_pet_detail);

        petApiService = PetRepository.getPetApiServices();

        btnApiDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                clickCallApiDetail();
            }

            private void clickCallApiDetail() {
                String strPetId = edtPetId.getText().toString();

                if(strPetId.isEmpty()){
                    Toast.makeText(PetDetail_Update_Delete.this, "Enter petId", Toast.LENGTH_SHORT).show();
                }

                try {
                    long petId = Long.parseLong(strPetId);

                    petApiService.getPetById("\"petId\"", petId).enqueue(new Callback<Map<String, Pet>>() {
                        @Override
                        public void onResponse(Call<Map<String, Pet>> call, Response<Map<String, Pet>> response) {

                            if(response.isSuccessful() && response.body() != null){
                                Map<String, Pet> petMap = response.body();
                                Pet pet = null;


                                for (Map.Entry<String, Pet> entry : petMap.entrySet()) {
                                    pet = entry.getValue();
                                    break;
                                }

                                if (pet != null) {

                                    long idPet = pet.getPetId();
                                    long idUser = pet.getUserId();

                                    edtPetName.setText(pet.getPetName());
                                    edtTypePet.setText(pet.getPetType());
                                    edtPetId.setText(String.valueOf(idPet));
                                    edtUserId.setText(String.valueOf(idUser));

                                    Toast.makeText(PetDetail_Update_Delete.this, "Call API Success", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(PetDetail_Update_Delete.this, "Pet not found", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(PetDetail_Update_Delete.this, "Failed to fetch pet", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Map<String, Pet>> call, Throwable t) {
                            Toast.makeText(PetDetail_Update_Delete.this, "Error", Toast.LENGTH_SHORT).show();;
                        }
                    });

                }catch(NumberFormatException e){

                }
            }
        });

    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}