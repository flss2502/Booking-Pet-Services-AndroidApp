package com.example.firebase.activity.user;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.model.Role;
import com.example.firebase.api.UserApiService;
import com.example.firebase.model.User;
import com.example.firebase.R;
import com.example.firebase.repository.UserRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUserActivity extends AppCompatActivity {

    private EditText edtFullName, edtMail, edtUserId, edtPassword, edtAddress, edtPhone, edtRole;

    private Button btnCreateUser;
    private DatabaseReference mDatabase;
    private UserApiService userApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        edtFullName = findViewById(R.id.edt_full_name);
        edtMail = findViewById(R.id.edt_mail);
        edtUserId = findViewById(R.id.edt_userId);
        edtAddress = findViewById(R.id.edt_address);
        edtPassword = findViewById(R.id.edt_password);
        edtPhone = findViewById(R.id.edt_phone);
        edtRole = findViewById(R.id.edt_role_user);

        btnCreateUser = findViewById(R.id.btn_create_user);

        userApiService = UserRepository.getUserApiServices();



        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }



    private void createUser() {
        String fullName = edtFullName.getText().toString();
        String email = edtMail.getText().toString();
        Long userId = Long.parseLong(edtUserId.getText().toString());
        String address = edtAddress.getText().toString();
        String phone = edtPhone.getText().toString();
        String password = edtPassword.getText().toString();
        String role1 = edtRole.getText().toString();


        Role role = null;
        try {
            role = Role.valueOf(role1.toLowerCase());
        } catch (IllegalArgumentException e) {
            Toast.makeText(this, "Invalid role: " + role1, Toast.LENGTH_SHORT).show();
            return;
        }

        // Create User object
        User user = new User(userId, fullName, email, password, phone, address, role);

        // Call API

        Call<User> call = userApiService.createUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddUserActivity.this, "User created successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddUserActivity.this, "Failed to create user!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(AddUserActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("AddUserActivity", "onFailure: ", t);
            }
        });
    }
}
