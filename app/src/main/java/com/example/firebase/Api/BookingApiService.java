package com.example.firebase.api;

import com.example.firebase.model.Booking;
import com.example.firebase.model.Pet;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookingApiService {

    String BOOKINGS = "bookings";

    @GET(BOOKINGS + ".json")
    Call<Map<String, Booking>> getBookingById(
            @Query("orderBy") String orderBy,
            @Query("equalTo") Long bookingId
    );

    @GET(BOOKINGS + ".json")
    Call<Map<String, Booking>> getAllBooking();
    @POST(BOOKINGS + ".json")
    Call<Pet> createBooking(@Body Booking booking);

    @PATCH(BOOKINGS + "/{bookingId}.json")
    Call<Pet> updateBooking(@Path("bookingId") Long bookingId, @Body Booking booking);

    @DELETE(BOOKINGS + "/{key}.json")
    Call<Void> deleteBookingByFirebaseKey(@Path("key") String firebaseKey);

}
