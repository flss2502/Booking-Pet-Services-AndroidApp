package com.example.firebase.activity.servicesPet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.api.ServicesPetApiService;
import com.example.firebase.R;
import com.example.firebase.repository.ServicesPetRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddServicesActivity extends AppCompatActivity {

    private EditText edtServiceName, edtDescription, edtPrice;
    private Button btnCreateServices;

    private ServicesPetApiService servicesPetApiService;
    private ServicesPetRepository servicesPetRepository;
    private Long lastIdService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_services);

        edtServiceName = findViewById(R.id.edt_serviceName);
        edtDescription = findViewById(R.id.edt_description);
        edtPrice = findViewById(R.id.edt_price);
        btnCreateServices = findViewById(R.id.btn_add_services);

        servicesPetApiService = ServicesPetRepository.getServicesPetApi();
        servicesPetRepository = new ServicesPetRepository();

        servicesPetRepository.getLastServiceId(new ServicesPetRepository.OnLastServiceIdCallback() {
            @Override
            public void onLastServiceIdReceived(long lastId) {
                Log.d("AddServicesActivity", "Last serviceId: " + lastId);

                    lastIdService = lastId;

            }

            @Override
            public void onLastServiceIdError(String error) {
                Log.e("AddServicesActivity", "Failed to get last serviceId: " + error);
            }
        });

        btnCreateServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addServices();
            }

            private void addServices() {


                String serviceName = edtServiceName.getText().toString();
                String description = edtDescription.getText().toString();
                Double price = Double.parseDouble(edtPrice.getText().toString());

                if (lastIdService == null) {
                    lastIdService = 1L;
                }else{
                    lastIdService = lastIdService + 1L;
                }

                Services services = new Services(lastIdService, serviceName, description, price);


                Call<Services> call = servicesPetApiService.createServices(services);

                call.enqueue(new Callback<Services>() {
                    @Override
                    public void onResponse(Call<Services> call, Response<Services> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(AddServicesActivity.this, "Services created successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddServicesActivity.this, "Failed to create services!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Services> call, Throwable t) {
                        Toast.makeText(AddServicesActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }


}