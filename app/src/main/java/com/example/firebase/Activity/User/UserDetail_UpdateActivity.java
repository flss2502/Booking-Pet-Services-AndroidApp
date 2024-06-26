package com.example.firebase.Activity.User;

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

import com.example.firebase.Api.UserApiService;
import com.example.firebase.Model.Role;
import com.example.firebase.Model.User;
import com.example.firebase.R;
import com.example.firebase.Repository.UserRepository;

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


                    Call<User> call = userApiService.getUser(userId);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Toast.makeText(UserDetail_UpdateActivity.this, "Call api Success", Toast.LENGTH_SHORT).show();

                            User user = response.body();

                            if (user != null) {
                                edtFullName.setText(user.getFullName());
                                edtEmail.setText(user.getEmail());
                                edtAddress.setText(user.getAddress());
                                edtPhone.setText(user.getPhone());
                                tvPassword.setText(user.getPassword());
                                tvRole.setText(user.getRole().toString());
                            }

                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(UserDetail_UpdateActivity.this, "Call api Error", Toast.LENGTH_SHORT).show();
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
                User user = new User(userId, fullName, email, null, phone, address, null);

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