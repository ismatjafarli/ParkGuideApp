package com.example.parksapp.fragnment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.parksapp.R;
import com.example.parksapp.adapter.CustomInfoWindow;
import com.example.parksapp.databinding.FragmentMapBinding;
import com.example.parksapp.model.Root;
import com.example.parksapp.view.ParksViewModel;
import com.example.parksapp.view.SearchViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class MapFragment extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener {
    FragmentMapBinding binding;
    private static final String TAG = "Testing";
    private GoogleMap mMap;
    ParksViewModel viewModel;
    SearchViewModel searchViewModel;

    public MapFragment() {
        // Required empty public constructor
    }


    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        else
        {
            Toast.makeText(getActivity(), "MapFragment is null, why?", Toast.LENGTH_LONG).show();
        }

        // Create instance of viewModel
        viewModel = ViewModelProviders.of(this).get(ParksViewModel.class);
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Bundle args = new Bundle();
        args.putString("id", Objects.requireNonNull(marker.getTag()).toString());
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setInfoWindowAdapter(new CustomInfoWindow(getContext()));
        mMap.setOnInfoWindowClickListener(this);
        String stateCode = binding.editText.getText().toString().trim();

        if(stateCode.isEmpty()) {
            getAllParks();
        }

        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editText = binding.editText.getText().toString().trim();
                if (!editText.isEmpty()) {
                    getSearchedParks(binding.editText.getText().toString().trim());
                } else {
                    getAllParks();
                }
            }
        });
    }

    private void getSearchedParks(String stateCode) {
        mMap.clear();
        searchViewModel.getSearch(stateCode).observe(this, new Observer<Root>() {
            @Override
            public void onChanged(Root root) {
                int total = Integer.parseInt(root.getTotal());
                LatLng first = null;
                if(total == 0) {
                    Snackbar snackbar = Snackbar
                            .make(binding.cardView, "There have not been found any parks!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {
                    Log.d(TAG, "onChangeddd: " + root.getTotal());
                    for (int i = 0; i < total; i++) {
//                  Log.d(TAG, "onChangeddd: " + root.getData().get(i).getFullName());
                        first = new LatLng(Double.parseDouble(root.getData().get(0).getLatitude()),
                                Double.parseDouble(root.getData().get(0).getLongitude()));
                        LatLng latLng = new LatLng(Double.parseDouble(root.getData().get(i).getLatitude()),
                                Double.parseDouble(root.getData().get(i).getLongitude()));

                        MarkerOptions markerOptions = new MarkerOptions()
                                .position(latLng)
                                .title(root.getData().get(i).getName())
                                .snippet(root.getData().get(i).getStates())
                                .icon(BitmapDescriptorFactory.defaultMarker(
                                        BitmapDescriptorFactory.HUE_GREEN
                                ));

                        Marker marker = mMap.addMarker(markerOptions);
                        marker.setTag(root.getData().get(i).getId());
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(first));
                }
            }
        });
        onMapReady(mMap);
    }

    private void getAllParks() {
        mMap.clear();
        viewModel.getParks().observe(this, new Observer<Root>() {
            @Override
            public void onChanged(Root root) {
                LatLng first = null;
                int total = Integer.parseInt(root.getLimit());
                for(int i = 0; i < total; i++) {
//                  Log.d(TAG, "onChangeddd: " + root.getData().get(i).getFullName());
                    first = new LatLng(Double.parseDouble(root.getData().get(0).getLatitude()),
                            Double.parseDouble(root.getData().get(0).getLongitude()));
                    LatLng latLng = new LatLng(Double.parseDouble(root.getData().get(i).getLatitude()),
                            Double.parseDouble(root.getData().get(i).getLongitude()));

                    MarkerOptions markerOptions = new MarkerOptions()
                            .position(latLng)
                            .title(root.getData().get(i).getName())
                            .snippet(root.getData().get(i).getStates())
                            .icon(BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_GREEN
                            ));

                    Marker marker = mMap.addMarker(markerOptions);
                    marker.setTag(root.getData().get(i).getId());
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLng(first));
            }
        });
    }
}