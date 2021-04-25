package com.example.parksapp.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.parksapp.model.Data;
import com.example.parksapp.model.Root;
import com.example.parksapp.service.ApiService;
import com.example.parksapp.service.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRepository {
    private static final String TAG = "Testing";
    private final MutableLiveData<Root> liveData = new MutableLiveData<>();
    private Data data;
    Application application;

    public SearchRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<Root> getSearch(String stateCode) {
        ApiService apiService = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);

        Call<Root> call = apiService.getSearch(stateCode, "6OAS3gvpgfKjWKFN2oQNlTg9aUgZYg6id2geBgEW");
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if(!response.isSuccessful()) {
                    return;
                }
                Log.d(TAG, "onResponseer: " + response.body().getTotal());
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return liveData;
    }
}
