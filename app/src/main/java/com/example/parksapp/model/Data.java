package com.example.parksapp.model;

import java.util.List;

public class Data
{
    private String id;

    private String url;

    private String fullName;

    private String parkCode;

    private String description;

    private String latitude;

    private String longitude;

    private String latLong;

    private List<Activities> activities;

    private List<Topics> topics;

    private String states;

    private Contacts contacts;

    private List<EntranceFees> entranceFees;

    private List<EntrancePasses> entrancePasses;

    private List<String> fees;

    private String directionsInfo;

    private String directionsUrl;

    private List<OperatingHours> operatingHours;

    private List<Addresses> addresses;

    private List<Images> images;

    private String weatherInfo;

    private String name;

    private String designation;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public String getFullName(){
        return this.fullName;
    }
    public void setParkCode(String parkCode){
        this.parkCode = parkCode;
    }
    public String getParkCode(){
        return this.parkCode;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setLatitude(String latitude){
        this.latitude = latitude;
    }
    public String getLatitude(){
        return this.latitude;
    }
    public void setLongitude(String longitude){
        this.longitude = longitude;
    }
    public String getLongitude(){
        return this.longitude;
    }
    public void setLatLong(String latLong){
        this.latLong = latLong;
    }
    public String getLatLong(){
        return this.latLong;
    }
    public void setActivities(List<Activities> activities){
        this.activities = activities;
    }
    public List<Activities> getActivities(){
        return this.activities;
    }
    public void setTopics(List<Topics> topics){
        this.topics = topics;
    }
    public List<Topics> getTopics(){
        return this.topics;
    }
    public void setStates(String states){
        this.states = states;
    }
    public String getStates(){
        return this.states;
    }
    public void setContacts(Contacts contacts){
        this.contacts = contacts;
    }
    public Contacts getContacts(){
        return this.contacts;
    }
    public void setEntranceFees(List<EntranceFees> entranceFees){
        this.entranceFees = entranceFees;
    }
    public List<EntranceFees> getEntranceFees(){
        return this.entranceFees;
    }
    public void setEntrancePasses(List<EntrancePasses> entrancePasses){
        this.entrancePasses = entrancePasses;
    }
    public List<EntrancePasses> getEntrancePasses(){
        return this.entrancePasses;
    }
    public void setFees(List<String> fees){
        this.fees = fees;
    }
    public List<String> getFees(){
        return this.fees;
    }
    public void setDirectionsInfo(String directionsInfo){
        this.directionsInfo = directionsInfo;
    }
    public String getDirectionsInfo(){
        return this.directionsInfo;
    }
    public void setDirectionsUrl(String directionsUrl){
        this.directionsUrl = directionsUrl;
    }
    public String getDirectionsUrl(){
        return this.directionsUrl;
    }
    public void setOperatingHours(List<OperatingHours> operatingHours){
        this.operatingHours = operatingHours;
    }
    public List<OperatingHours> getOperatingHours(){
        return this.operatingHours;
    }
    public void setAddresses(List<Addresses> addresses){
        this.addresses = addresses;
    }
    public List<Addresses> getAddresses(){
        return this.addresses;
    }
    public void setImages(List<Images> images){
        this.images = images;
    }
    public List<Images> getImages(){
        return this.images;
    }
    public void setWeatherInfo(String weatherInfo){
        this.weatherInfo = weatherInfo;
    }
    public String getWeatherInfo(){
        return this.weatherInfo;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setDesignation(String designation){
        this.designation = designation;
    }
    public String getDesignation(){
        return this.designation;
    }
}

