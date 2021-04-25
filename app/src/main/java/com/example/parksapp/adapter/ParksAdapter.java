package com.example.parksapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parksapp.R;
import com.example.parksapp.databinding.ParkRowBinding;
import com.example.parksapp.model.Data;

import java.util.List;

public class ParksAdapter extends RecyclerView.Adapter<ParksAdapter.ViewHolder> {
    private final Context context;
    private final List<Data> dataList;
    private final OnParkClickListener parkClickListener;

    public ParksAdapter(Context context, List<Data> dataList, OnParkClickListener parkClickListener) {
        this.context = context;
        this.dataList = dataList;
        this.parkClickListener = parkClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ParkRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.park_row, parent, false);
        return new ViewHolder(binding, parkClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data data = dataList.get(position);
        holder.binding.setParkData(data);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ParkRowBinding binding;
        OnParkClickListener onParkClickListener;

        public ViewHolder(@NonNull  ParkRowBinding binding, OnParkClickListener onParkClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.onParkClickListener = onParkClickListener;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Data currentData = dataList.get(getAdapterPosition());
            parkClickListener.onParkClicked(currentData);
        }
    }
}
