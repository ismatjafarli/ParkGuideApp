package com.example.parksapp;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import com.example.parksapp.adapter.CustomInfoWindow;
import com.example.parksapp.databinding.ActivityMapsBinding;
import com.example.parksapp.fragnment.DetailsFragment;
import com.example.parksapp.fragnment.ParksFragment;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener {
    private static final String TAG = "Testing";
    private GoogleMap mMap;
    ActivityMapsBinding binding;
    ParksViewModel viewModel;
    SearchViewModel searchViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_maps);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_maps);

        //hide keyboard



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Create instance of viewModel
        viewModel = ViewModelProviders.of(this).get(ParksViewModel.class);
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        //Log.d(TAG, "onCreate: "+ viewModel.getParks());

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int id = item.getItemId();
                if (id == R.id.map_nav) {
                    binding.editText.getText().clear();
                    binding.cardView.setVisibility(View.VISIBLE);
                    mMap.clear();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.map, mapFragment)
                            .commit();
                    mapFragment.getMapAsync(MapsActivity.this);
                    return true;
                } else if (id == R.id.parks_nav) {
                    binding.cardView.setVisibility(View.GONE);
                    selectedFragment = ParksFragment.newInstance();
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.map, selectedFragment)
                        .commit();
                return true;
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        mMap.setInfoWindowAdapter(new CustomInfoWindow(getApplicationContext()));
        mMap.setOnInfoWindowClickListener(this);
        String stateCode = binding.editText.getText().toString().trim();

        if(stateCode.isEmpty()) {
            getAllParks();
        }

        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
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

    @Override
    public void onInfoWindowClick(Marker marker) {
        binding.cardView.setVisibility(View.GONE);
        Bundle args = new Bundle();
        args.putString("id", Objects.requireNonNull(marker.getTag()).toString());
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.map, fragment, null)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }


}