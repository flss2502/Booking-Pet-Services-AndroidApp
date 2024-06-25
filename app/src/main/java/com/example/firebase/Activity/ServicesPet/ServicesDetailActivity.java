package com.example.firebase.Activity.ServicesPet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.firebase.Activity.User.UserDetail_UpdateActivity;
import com.example.firebase.Api.ServicesPetApi;
import com.example.firebase.Api.UserApiService;
import com.example.firebase.Model.Services;
import com.example.firebase.Model.User;
import com.example.firebase.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicesDetailActivity extends AppCompatActivity {

    private EditText edtServiceName, edtDescription, edtPrice, edtServiceId;
    private static final String TAG = "ServicesDetailActivity";


    private Button btnCallApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_detail);

        edtServiceName = findViewById(R.id.edt_service_name);
        edtDescription = findViewById(R.id.edt_description);
        edtPrice = findViewById(R.id.edt_price);
        edtServiceId = findViewById(R.id.edt_serviceID);

        btnCallApi = findViewById(R.id.btn_call_api_service);

        btnCallApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                clickCallApiServices();
            }

            private void clickCallApiServices() {
                String servicesIdInput = edtServiceId.getText().toString();

                if (servicesIdInput.isEmpty()) {
                    Toast.makeText(ServicesDetailActivity.this, "Please enter a user ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    long servicesId = Long.parseLong(servicesIdInput);
                    Log.d(TAG, "Calling API with userId: " + servicesId);

                    ServicesPetApi.servicesPetApi.getServicesPet(servicesId).enqueue(new Callback<Services>() {
                        @Override
                        public void onResponse(Call<Services> call, Response<Services> response) {
                            Toast.makeText(ServicesDetailActivity
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
                            Toast.makeText(ServicesDetailActivity.this, "Call api Error", Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (NumberFormatException e) {
                    Toast.makeText(ServicesDetailActivity.this, "Invalid user ID", Toast.LENGTH_SHORT).show();
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