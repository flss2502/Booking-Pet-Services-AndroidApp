package com.example.firebase.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.R;
import com.example.firebase.model.Booking;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private ArrayList<Booking> bookingList;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public BookingAdapter(ArrayList<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookingList.get(position);

        if (booking != null) {
            holder.tvBookingId.setText("Booking Id: " + booking.getBookingId());
            holder.tvUserId.setText("User Id: " + booking.getUserId());
            holder.tvPetId.setText("Pet Id: " + booking.getPetId());
            holder.tvServiceId.setText("Service Id: " + booking.getServiceId());
            holder.tvStartDate.setText("Start Date: " + dateFormat.format(booking.getStartDate()));
            holder.tvEndDate.setText("End Date: " + dateFormat.format(booking.getEndDate()));
            holder.tvStatus.setText("Status: " + booking.getStatus());
        } else {
            holder.tvBookingId.setText("Booking Id: N/A");
            holder.tvUserId.setText("User Id: N/A");
            holder.tvPetId.setText("Pet Id: N/A");
            holder.tvServiceId.setText("Service Id: N/A");
            holder.tvStartDate.setText("Start Date: N/A");
            holder.tvEndDate.setText("End Date: N/A");
            holder.tvStatus.setText("Status: N/A");
        }
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {

        private TextView tvBookingId, tvUserId, tvPetId, tvServiceId, tvStartDate, tvEndDate, tvStatus;

        public BookingViewHolder(View itemView) {
            super(itemView);
            tvBookingId = itemView.findViewById(R.id.tv_bookingId);
            tvUserId = itemView.findViewById(R.id.tv_userId);
            tvPetId = itemView.findViewById(R.id.tv_petId);
            tvServiceId = itemView.findViewById(R.id.tv_serviceId);
            tvStartDate = itemView.findViewById(R.id.tv_startDate);
            tvEndDate = itemView.findViewById(R.id.tv_endDate);
            tvStatus = itemView.findViewById(R.id.tv_status);
        }
    }
}
