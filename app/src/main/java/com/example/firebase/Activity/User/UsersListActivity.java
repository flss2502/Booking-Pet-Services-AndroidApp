package com.example.firebase.Activity.User;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.Adapter.UserAdapter;
import com.example.firebase.Api.UserApiService;
import com.example.firebase.Model.User;
import com.example.firebase.R;
import com.example.firebase.Repository.UserRepository;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersListActivity extends AppCompatActivity {

    private static final String TAG = "UsersListActivity";
    private RecyclerView recyclerView;
    private UserApiService userApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_users_list);

        recyclerView = findViewById(R.id.recycler_view_users);

        userApiService = UserRepository.getUserApiServices();

        // Call API to fetch users
        callApi();
    }

    private void callApi() {
        Call<Map<String, User>> call = userApiService.getAllUsers();

        call.enqueue(new Callback<Map<String, User>>() {
            @Override
            public void onResponse(Call<Map<String, User>> call, Response<Map<String, User>> response) {
                if (response.isSuccessful()) {
                    Map<String, User> map = response.body();
                    ArrayList<User> list = new ArrayList<>(map.values());

                    // Tạo adapter mới và gán vào RecyclerView
                    UserAdapter userAdapter = new UserAdapter(list);
                    recyclerView.setAdapter(userAdapter);

                    Toast.makeText(UsersListActivity.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UsersListActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, User>> call, Throwable t) {
                Toast.makeText(UsersListActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
