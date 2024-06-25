package com.example.firebase.Activity.ServicesPet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.firebase.Activity.User.AddUserActivity;
import com.example.firebase.Api.ServicesPetApi;
import com.example.firebase.Model.Services;
import com.example.firebase.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddServicesActivity extends AppCompatActivity {

    private EditText edtServiceName, edtDescription, edtPrice, edtServiceId;
    private Button btnCreateServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_services);

        edtServiceName = findViewById(R.id.edt_serviceName);
        edtDescription = findViewById(R.id.edt_description);
        edtPrice = findViewById(R.id.edt_price);
        edtServiceId = findViewById(R.id.edt_services_id);
        btnCreateServices = findViewById(R.id.btn_add_services);

    btnCreateServices.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addServices();
        }

        private void addServices() {
            String serviceName = edtServiceName.getText().toString();
            String description = edtDescription.getText().toString();
            Double price = Double.parseDouble(edtPrice.getText().toString());
            Long serviceId = Long.parseLong(edtServiceId.getText().toString());


            Services services = new Services(serviceId, serviceName, description,price);

            ServicesPetApi servicesPetApi = ServicesPetApi.servicesPetApi;
            Call<Services> call = servicesPetApi.createServices(services);

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