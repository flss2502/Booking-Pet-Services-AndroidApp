package com.example.firebase.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.R;
import com.example.firebase.model.Pet;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder>{

    private ArrayList<Pet> petsArraylist;

    public PetAdapter(ArrayList<Pet> petsArraylist){
        this.petsArraylist= petsArraylist;
    }
    @NonNull
    @Override
    public PetAdapter.PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pet, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetAdapter.PetViewHolder holder, int position) {
        Pet pet = petsArraylist.get(position);

        if(pet != null){
            holder.tvPetName.setText(pet.getPetName());
            holder.tvType.setText(pet.getPetType());
            holder.tvPetId.setText(String.valueOf(pet.getPetId()));
            holder.tvUserId.setText(String.valueOf(pet.getUserId()));

        }else{
            holder.tvPetName.setText("N/A");
            holder.tvType.setText("N/A");
            holder.tvPetId.setText(String.valueOf("N/A"));
            holder.tvUserId.setText(String.valueOf("N/A"));
        }
    }

    @Override
    public int getItemCount() {
        return petsArraylist.size();
    }

    public class PetViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPetName, tvPetId, tvType, tvUserId;
        public PetViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPetName = itemView.findViewById(R.id.tv_pet_name);
            tvPetId = itemView.findViewById(R.id.tv_petId);
            tvType = itemView.findViewById(R.id.tv_pet_type);
            tvUserId = itemView.findViewById(R.id.tv_userId);
        }
    }
}
