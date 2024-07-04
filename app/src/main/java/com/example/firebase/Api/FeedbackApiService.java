package com.example.firebase.api;

import com.example.firebase.model.Feedback;
import com.example.firebase.model.Services;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FeedbackApiService {

    String FEEDBACK = "feedback";

    @GET(FEEDBACK + ".json")
    Call<Map<String, Feedback>> getServicesByFeedbackId(
            @Query("orderBy") String orderBy,
            @Query("equalTo") Long feedbackId
    );

    @GET(FEEDBACK + ".json")
    Call<Map<String, Feedback>> getAllFeedback();
    @POST(FEEDBACK + ".json")
    Call<Feedback> createFeedback(@Body Feedback feedback);

    @PATCH(FEEDBACK + "/{feedbackId}.json")
    Call<Feedback> updateFeedback(@Path("feedbackId") Long feedbackId, @Body Feedback feedback);

    @DELETE(FEEDBACK + "/{key}.json")
    Call<Void> deleteFeedbackByFirebaseKey(@Path("key") String firebaseKey);

}
