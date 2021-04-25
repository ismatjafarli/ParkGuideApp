package com.example.parksapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.parksapp.R;
import com.example.parksapp.databinding.CustomInfoWindowBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindow implements GoogleMap.InfoWindowAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    CustomInfoWindowBinding binding;


    public CustomInfoWindow(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_info_window, null, true);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        binding.parkName.setText(marker.getTitle());
        binding.parkStates.setText(marker.getSnippet());
        return binding.getRoot();
    }
}
