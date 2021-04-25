package com.example.parksapp.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.parksapp.model.Root;
import com.example.parksapp.repository.ParkRepository;

public class ParkViewModel extends AndroidViewModel {
    private final ParkRepository repository;

    public ParkViewModel(@NonNull Application application) {
        super(application);
        repository = new ParkRepository(application);
    }

    public LiveData<Root> getPark(String id) {
        return repository.getPark(id);
    }
}
