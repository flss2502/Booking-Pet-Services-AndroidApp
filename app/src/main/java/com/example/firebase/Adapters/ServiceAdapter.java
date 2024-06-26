package com.example.firebase.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firebase.Model.Services;
import com.example.firebase.R;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private List<Services> servicesList;

    public ServiceAdapter(List<Services> servicesList) {
        this.servicesList = servicesList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Services service = servicesList.get(position);
        holder.serviceNameTextView.setText(service.getServiceName());
        holder.serviceDescriptionTextView.setText(service.getDescription());
        holder.servicePriceTextView.setText(String.format("$%.2f", service.getPrice()));

        // Load the image using Glide
        Glide.with(holder.itemView.getContext())
                .load(service.getServiceImageUrl())
                .into(holder.serviceImageView);
    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }

    static class ServiceViewHolder extends RecyclerView.ViewHolder {
        TextView serviceNameTextView, serviceDescriptionTextView, servicePriceTextView;
        ImageView serviceImageView;

        ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceNameTextView = itemView.findViewById(R.id.serviceNameTextView);
            serviceDescriptionTextView = itemView.findViewById(R.id.serviceDescriptionTextView);
            servicePriceTextView = itemView.findViewById(R.id.servicePriceTextView);
            serviceImageView = itemView.findViewById(R.id.serviceImage);
        }
    }
}
