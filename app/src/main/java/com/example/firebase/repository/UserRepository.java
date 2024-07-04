package com.example.firebase.repository;

import android.util.Log;

import com.example.firebase.api.UserApiService;
import com.example.firebase.model.Services;
import com.example.firebase.model.User;
import com.example.firebase.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private UserApiService userApiService;
    public static UserApiService getUserApiServices(){
        return ApiClient.getClient().create(UserApiService.class);
    }

    public UserRepository(){
        userApiService = ApiClient.getClient().create(UserApiService.class);
    }


    public void getLastUserId(final UserRepository.OnLastUserIdCallback callback) {

        Call<Map<String, User>> call = userApiService.getAllUsers();

        call.enqueue(new Callback<Map<String, User>>() {
            @Override
            public void onResponse(Call<Map<String, User>> call, Response<Map<String, User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Map<String, User> userMap = response.body();
                    ArrayList<User> userList = new ArrayList<>(userMap.values());

                    if (!userList.isEmpty()) {
                        User lastUser = userList.get(userList.size() - 1);
                        long lastUserId = lastUser.getUserId();

                        callback.onLastUserIdReceived(lastUserId);
                    } else {
                        callback.onLastUserIdError("No user found");
                    }
                } else {
                    callback.onLastUserIdError("Failed to get data");
                }
            }

            @Override
            public void onFailure(Call<Map<String, User>> call, Throwable t) {
                callback.onLastUserIdError("Error: " + t.getMessage());
                Log.e("UserRepository", "Error fetching user", t);
            }
        });
    }

    public interface OnLastUserIdCallback {
        void onLastUserIdReceived(long lastUserId);

        void onLastUserIdError(String error);
    }
}
