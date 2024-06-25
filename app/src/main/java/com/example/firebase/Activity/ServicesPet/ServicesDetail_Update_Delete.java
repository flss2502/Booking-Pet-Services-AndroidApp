package com.example.firebase.Activity.ServicesPet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.Activity.User.UserDetail_UpdateActivity;
import com.example.firebase.Api.ServicesPetApi;
import com.example.firebase.Model.Services;
import com.example.firebase.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicesDetail_Update_Delete extends AppCompatActivity {

    private EditText edtServiceName, edtDescription, edtPrice, edtServiceId;
    private static final String TAG = "ServicesDetailActivity";


    private Button btnCallApi, btnUpdateService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_detail);

        edtServiceName = findViewById(R.id.edt_service_name);
        edtDescription = findViewById(R.id.edt_description);
        edtPrice = findViewById(R.id.edt_price);
        edtServiceId = findViewById(R.id.edt_serviceID);

        btnCallApi = findViewById(R.id.btn_call_api_service);
        btnUpdateService = findViewById(R.id.btn_Update_api_service);

        btnCallApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                clickCallApiServices();
            }

            private void clickCallApiServices() {
                String servicesIdInput = edtServiceId.getText().toString();

                if (servicesIdInput.isEmpty()) {
                    Toast.makeText(ServicesDetail_Update_Delete.this, "Please enter a user ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    long servicesId = Long.parseLong(servicesIdInput);
                    Log.d(TAG, "Calling API with userId: " + servicesId);

                    ServicesPetApi.servicesPetApi.getServicesPet(servicesId).enqueue(new Callback<Services>() {
                        @Override
                        public void onResponse(Call<Services> call, Response<Services> response) {
                            Toast.makeText(ServicesDetail_Update_Delete
                                    .this, "Call api Success", Toast.LENGTH_SHORT).show();

                            Services services = response.body();
                            double price = services.getPrice();


                            if (services != null) {
                                edtServiceName.setText(services.getServiceName());
                                edtDescription.setText(services.getDescription());
                                edtPrice.setText(String.format("%.2f", price));                            }
                        }

                        @Override
                        public void onFailure(Call<Services> call, Throwable t) {
                            Toast.makeText(ServicesDetail_Update_Delete.this, "Call api Error", Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (NumberFormatException e) {
                    Toast.makeText(ServicesDetail_Update_Delete.this, "Invalid user ID", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUpdateService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                apiUpdate();
            }

            private void apiUpdate() {
                String serviceName = edtServiceName.getText().toString();
                String description = edtDescription.getText().toString();
                Double price = Double.parseDouble(edtPrice.getText().toString());
                Long serviceId = Long.parseLong(edtServiceId.getText().toString());

                Services services = new Services(serviceId,serviceName,description,price);

                ServicesPetApi servicesPetApi = ServicesPetApi.servicesPetApi;
                Call<Services> call = servicesPetApi.updateServices(serviceId, services);

                call.enqueue(new Callback<Services>() {
                    @Override
                    public void onResponse(Call<Services> call, Response<Services> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ServicesDetail_Update_Delete.this, "User updated successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ServicesDetail_Update_Delete.this, "Failed to update user!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Services> call, Throwable t) {
                        Toast.makeText(ServicesDetail_Update_Delete.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
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