package com.example.firebase.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.firebase.R;
import com.example.firebase.model.Services;

import java.util.ArrayList;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder> {

    private ArrayList<Services> servicesList;

    public ServicesAdapter(ArrayList<Services> servicesList){
        this.servicesList = servicesList;
    }

    @NonNull
    @Override
    public ServicesAdapter.ServicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_services, parent, false);
        return new ServicesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesViewHolder holder, int position) {
        Services services = servicesList.get(position);


        if (services != null) {
            holder.tvServicesName.setText(services.getServiceName());
            holder.tvDescription.setText(services.getDescription());
            holder.tvServiceId.setText(String.valueOf(services.getServiceId()));
            holder.tvPrice.setText(String.valueOf(services.getPrice()));
        } else {
            holder.tvServicesName.setText("Service Name N/A");
            holder.tvDescription.setText("Description N/A");
            holder.tvServiceId.setText(String.valueOf("N/A"));
            holder.tvPrice.setText(String.valueOf("N/A"));
        }
    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }

    public static class ServicesViewHolder extends RecyclerView.ViewHolder {

        private TextView tvServicesName, tvDescription,tvServiceId, tvPrice;

        public ServicesViewHolder(View itemView) {
            super(itemView);
            tvServicesName = itemView.findViewById(R.id.tv_services_name);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvServiceId = itemView.findViewById(R.id.tv_serviceId);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }
    }
}