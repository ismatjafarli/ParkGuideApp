package com.example.parksapp.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.parksapp.model.Root;
import com.example.parksapp.repository.SearchRepository;

public class SearchViewModel extends AndroidViewModel {
    private final SearchRepository repository;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        repository = new SearchRepository(application);
    }

    public LiveData<Root> getSearch(String stateCode) {
        return repository.getSearch(stateCode);
    }
}
