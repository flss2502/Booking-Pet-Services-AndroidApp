package com.example.firebase.activity.user;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.activity.servicesPet.ServicesDetail_Update_Delete;
import com.example.firebase.api.UserApiService;
import com.example.firebase.R;
import com.example.firebase.model.Services;
import com.example.firebase.model.User;
import com.example.firebase.repository.UserRepository;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetail_UpdateActivity extends AppCompatActivity {


    private EditText edtFullName, edtEmail, edtAddress, edtPhone;
    private Button btnCallApi, btnUpdateUser;
    private EditText edtUserId;

    private TextView tvPassword, tvRole;

    private static final String TAG = "UserDetailActivity";
    private UserApiService userApiService;
    private String firebaseKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        edtFullName = findViewById(R.id.edt_ten);
        edtEmail = findViewById(R.id.edt_email);
        btnCallApi = findViewById(R.id.btn_call_api);
        edtUserId = findViewById(R.id.edt_userId);
        edtAddress = findViewById(R.id.edt_address);
        edtPhone = findViewById(R.id.edt_phone);
        tvPassword = findViewById(R.id.tv_password);
        tvRole = findViewById(R.id.tv_role);
        btnUpdateUser = findViewById(R.id.btn_update_user);
        btnCallApi = findViewById(R.id.btn_call_api);


        userApiService = UserRepository.getUserApiServices();

        //Api get user by id
        btnCallApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hideKeyboard();
                clickCallApi();
            }

            private void clickCallApi() {

                String userIdInput = edtUserId.getText().toString();

                if (userIdInput.isEmpty()) {
                    Toast.makeText(UserDetail_UpdateActivity.this, "Please enter a user ID", Toast.LENGTH_SHORT).show();
                    return;
                }


                try {
                    long userId = Long.parseLong(userIdInput);
                    Log.d(TAG, "Calling API with userId: " + userId);


                    userApiService.getServicesByUserId("\"userId\"", userId).enqueue(new Callback<Map<String, User>>() {
                        @Override
                        public void onResponse(Call<Map<String, User>> call, Response<Map<String, User>> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                Map<String, User> userMap = response.body();
                                User user = null;

                                Toast.makeText(UserDetail_UpdateActivity.this, "Key: " + firebaseKey, Toast.LENGTH_SHORT).show();

                                // Iterate through the map to find the service
                                for (Map.Entry<String, User> entry : userMap.entrySet()) {
                                    user = entry.getValue();
                                    break;
                                }

                                if (user != null) {
                                    long UserId = user.getUserId();
                                    edtUserId.setText(String.valueOf(userId));
                                    edtEmail.setText(user.getEmail());
                                    edtAddress.setText(user.getAddress());
                                    edtFullName.setText(user.getFullName());
                                    edtPhone.setText(user.getPhone());
                                    Toast.makeText(UserDetail_UpdateActivity.this, "Call API Success", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(UserDetail_UpdateActivity.this, "Service not found", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(UserDetail_UpdateActivity.this, "Failed to fetch service", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Map<String, User>> call, Throwable t) {
                            Toast.makeText(UserDetail_UpdateActivity.this, "Call API Error", Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (NumberFormatException e) {
                    Toast.makeText(UserDetail_UpdateActivity.this, "Invalid user ID", Toast.LENGTH_SHORT).show();
                }
            }
        });


       // Api update user by id
        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                apiUpdate();
            }

            private void apiUpdate() {
                String fullName = edtFullName.getText().toString();
                String email = edtEmail.getText().toString();
                String address = edtAddress.getText().toString();
                String phone = edtPhone.getText().toString();
                Long userId = Long.parseLong(edtUserId.getText().toString());




                // Tạo đối tượng User
                User user = new User(userId, fullName, email, null, phone, address, null, 1, null, true);

                // Gọi API
                Call<User> call = userApiService.updateUser(userId, user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(UserDetail_UpdateActivity.this, "User updated successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UserDetail_UpdateActivity.this, "Failed to update user!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(UserDetail_UpdateActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("UpdateUserActivity", "onFailure: ", t);
                    }
                });
            }
        });



        edtUserId.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_GO || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    hideKeyboard();
                    btnCallApi.performClick();

                    return true;
                }
                return false;
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