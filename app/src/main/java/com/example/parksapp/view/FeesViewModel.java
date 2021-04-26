package com.example.parksapp.view;

import androidx.lifecycle.ViewModel;

import com.example.parksapp.model.EntranceFees;

import java.util.List;

public class FeesViewModel extends ViewModel {
    public String getFees (List<EntranceFees> feesList){
        StringBuilder finalText = new StringBuilder();
        for(EntranceFees fees: feesList) {
            finalText.append(fees.getTitle()).append("\n")
                    .append(fees.getDescription()).append("\n")
                    .append(fees.getCost()).append("\n")
                    .append("\n\n");
        }
        return finalText.toString();
    }
}
