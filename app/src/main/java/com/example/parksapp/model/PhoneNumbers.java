package com.example.parksapp.model;

public class PhoneNumbers
{
    private String phoneNumber;

    private String description;

    private String extension;

    private String type;

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setExtension(String extension){
        this.extension = extension;
    }
    public String getExtension(){
        return this.extension;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
}
