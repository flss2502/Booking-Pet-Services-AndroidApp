package com.example.firebase.api;

import com.example.firebase.model.Services;
import com.example.firebase.model.Tracking;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TrackingApiService {
    String TRACKING = "tracking";

    @GET(TRACKING + ".json")
    Call<Map<String, Tracking>> getServicesByTrackingId(
            @Query("orderBy") String orderBy,
            @Query("equalTo") Long trackingId
    );

    @GET(TRACKING + ".json")
    Call<Map<String, Tracking>> getAllTracking();
    @POST(TRACKING + ".json")
    Call<Tracking> createTracking(@Body Tracking tracking);

    @PATCH(TRACKING + "/{trackingId}.json")
    Call<Tracking> updateTracking(@Path("trackingId") Long trackingId, @Body Tracking tracking);

    @DELETE(TRACKING + "/{key}.json")
    Call<Void> deleteTrackingByFirebaseKey(@Path("key") String firebaseKey);

}
