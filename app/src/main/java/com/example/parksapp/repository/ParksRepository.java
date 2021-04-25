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

public class ParksRepository {
    private static final String TAG = "Testing";
    private final MutableLiveData<Root> liveData = new MutableLiveData<>();
    Application application;

    public ParksRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<Root> getParks() {
        ApiService apiService = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);

        Call<Root> call = apiService.getParks( "6OAS3gvpgfKjWKFN2oQNlTg9aUgZYg6id2geBgEW");
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if(!response.isSuccessful()){
                    return;
                }
                //Log.d(TAG, "onResponse: " + response.body().getData().get(0));
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
