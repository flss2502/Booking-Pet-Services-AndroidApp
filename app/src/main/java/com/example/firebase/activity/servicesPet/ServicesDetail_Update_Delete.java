package com.example.firebase.activity.servicesPet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.api.ServicesPetApiService;
import com.example.firebase.R;
import com.example.firebase.model.Services;
import com.example.firebase.repository.ServicesPetRepository;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicesDetail_Update_Delete extends AppCompatActivity {

    private EditText edtServiceName, edtDescription, edtPrice, edtServiceId;
    private static final String TAG = "ServicesDetailActivity";
    private ServicesPetApiService servicesPetApiService;
    private String firebaseKey;


    private Button btnCallApi, btnUpdateService, btnDeleteService;

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
        btnDeleteService = findViewById(R.id.btn_Delele_api_service);

        servicesPetApiService = ServicesPetRepository.getServicesPetApi();

        btnCallApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                clickCallApiServices();
            }

            private void clickCallApiServices() {
                String servicesIdInput = edtServiceId.getText().toString();

                if (servicesIdInput.isEmpty()) {
                    Toast.makeText(ServicesDetail_Update_Delete.this, "Please enter a service ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    long servicesId = Long.parseLong(servicesIdInput);
                    Log.d(TAG, "Calling API with serviceId: " + servicesId);

                    servicesPetApiService.getServicesByServiceId("\"serviceId\"", servicesId).enqueue(new Callback<Map<String, Services>>() {
                        @Override
                        public void onResponse(Call<Map<String, Services>> call, Response<Map<String, Services>> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                Map<String, Services> servicesMap = response.body();
                                Services service = null;

                                Toast.makeText(ServicesDetail_Update_Delete.this, "Key: " + firebaseKey, Toast.LENGTH_SHORT).show();

                                // Iterate through the map to find the service
                                for (Map.Entry<String, Services> entry : servicesMap.entrySet()) {
                                    service = entry.getValue();
                                    break;
                                }

                                if (service != null) {
                                    double price = service.getPrice();
                                    edtServiceName.setText(service.getServiceName());
                                    edtDescription.setText(service.getDescription());
                                    edtPrice.setText(String.format("%.2f", price));
                                    Toast.makeText(ServicesDetail_Update_Delete.this, "Call API Success", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ServicesDetail_Update_Delete.this, "Service not found", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(ServicesDetail_Update_Delete.this, "Failed to fetch service", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Map<String, Services>> call, Throwable t) {
                            Toast.makeText(ServicesDetail_Update_Delete.this, "Call API Error", Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (NumberFormatException e) {
                    Toast.makeText(ServicesDetail_Update_Delete.this, "Invalid service ID", Toast.LENGTH_SHORT).show();
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

                Services services = new Services(serviceId, serviceName, description, price, null);


                Call<Services> call = servicesPetApiService.updateServices(serviceId, services);

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

        btnDeleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                clickDeleteApiServices();
            }

            private void clickDeleteApiServices() {





                    servicesPetApiService.deleteServiceByFirebaseKey(firebaseKey).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {


                                Toast.makeText(ServicesDetail_Update_Delete.this, "Delete Success", Toast.LENGTH_SHORT).show();
                                Log.d("Delete" + firebaseKey, "Delete Success" + firebaseKey); // Log success message
                            } else {
                                Toast.makeText(ServicesDetail_Update_Delete.this, "Failed to Delete service", Toast.LENGTH_SHORT).show();
                                Log.e("Delete", "Failed to Delete service"); // Log failure message

                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(ServicesDetail_Update_Delete.this, "Error", Toast.LENGTH_SHORT).show();
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