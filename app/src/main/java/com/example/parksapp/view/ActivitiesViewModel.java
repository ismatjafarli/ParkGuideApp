package com.example.parksapp.view;

import androidx.lifecycle.ViewModel;

import java.util.List;

public class ActivitiesViewModel extends ViewModel {
    public String getActivities(List<String> list) {
        StringBuilder finalText = new StringBuilder();
        for (String text: list) {
            finalText.append(text).append(" | ");
        }
        return finalText.toString();
    }
}
