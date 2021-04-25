package com.example.parksapp.model;

import java.util.List;

public class OperatingHours
{
    private List<Exceptions> exceptions;

    private String description;

    private StandardHours standardHours;

    private String name;

    public void setExceptions(List<Exceptions> exceptions){
        this.exceptions = exceptions;
    }
    public List<Exceptions> getExceptions(){
        return this.exceptions;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setStandardHours(StandardHours standardHours){
        this.standardHours = standardHours;
    }
    public StandardHours getStandardHours(){
        return this.standardHours;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}

