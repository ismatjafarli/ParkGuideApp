package com.example.parksapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parksapp.R;
import com.example.parksapp.databinding.ImageCardBinding;
import com.example.parksapp.model.Images;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {
    private Context context;
    private List<Images> imageList;

    public ViewPagerAdapter(Context context, List<Images> images) {
        this.context = context;
        this.imageList = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageCardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.image_card, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Images images = imageList.get(position);
        Picasso.get()
                .load(images.getUrl())
                .fit()
                .placeholder(android.R.drawable.stat_sys_download)
                .into(holder.binding.imageView);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageCardBinding binding;

        public ViewHolder(@NonNull ImageCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
