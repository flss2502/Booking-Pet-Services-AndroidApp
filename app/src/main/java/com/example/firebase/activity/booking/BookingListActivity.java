package com.example.firebase.activity.booking;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.R;
import com.example.firebase.adapter.BookingAdapter;
import com.example.firebase.api.BookingApiService;

public class BookingListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookingAdapter bookingAdapter;
    private BookingApiService bookingApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);

        recyclerView = findViewById(R.id.recycler_view_booking);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}