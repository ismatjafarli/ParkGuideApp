package com.example.parksapp.model;

public class EntranceFees
{
    private String cost;

    private String description;

    private String title;

    public void setCost(String cost){
        this.cost = cost;
    }
    public String getCost(){
        return this.cost;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
}
