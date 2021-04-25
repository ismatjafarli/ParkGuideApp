package com.example.parksapp.view;

import androidx.lifecycle.ViewModel;

import java.util.List;

public class TopicsViewModel extends ViewModel {
    public String getTopics(List<String> list) {
        StringBuilder finalText = new StringBuilder();
        for (String text: list) {
            finalText.append(text).append("\n");
        }
        return finalText.toString();
    }
}
