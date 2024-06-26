package com.example.firebase.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


import com.example.firebase.R;

public class UserActivity extends AppCompatActivity {

    private Button btnUserDetail;
    private Button btnUsersList;
    private Button btnCreateUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btnUserDetail = findViewById(R.id.buttonGetUserById);
        btnUsersList = findViewById(R.id.buttonGetAllUsers);
        btnCreateUser = findViewById(R.id.buttonCreateUser);

        btnUserDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, UserDetail_UpdateActivity.class);
                startActivity(intent);
            }
        });

        btnUsersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, UsersListActivity.class);
                startActivity(intent);
            }
        });

        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });

    }
}