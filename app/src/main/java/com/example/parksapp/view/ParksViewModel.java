package com.example.parksapp.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.parksapp.model.Root;
import com.example.parksapp.repository.ParksRepository;

public class ParksViewModel extends AndroidViewModel {
    private final ParksRepository repository;
    public ParksViewModel(@NonNull Application application) {
        super(application);
        repository = new ParksRepository(application);
    }

    public LiveData<Root> getParks() {
        return repository.getParks();
    }

}
