package com.example.parksapp.service;

import com.example.parksapp.model.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("parks")
    Call<Root> getParks(
            @Query("api_key") String apiKey);

    @GET("parks")
    Call<Root> getPark(
            @Query("id") String id,
            @Query("api_key") String apiKey);

    @GET("parks")
    Call<Root> getSearch(
            @Query("stateCode") String stateCode,
            @Query("api_key") String apiKey);
}
