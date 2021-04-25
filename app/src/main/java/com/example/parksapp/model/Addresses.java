package com.example.parksapp.model;

public class Addresses
{
    private String postalCode;

    private String city;

    private String stateCode;

    private String line1;

    private String type;

    private String line3;

    private String line2;

    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }
    public String getPostalCode(){
        return this.postalCode;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getCity(){
        return this.city;
    }
    public void setStateCode(String stateCode){
        this.stateCode = stateCode;
    }
    public String getStateCode(){
        return this.stateCode;
    }
    public void setLine1(String line1){
        this.line1 = line1;
    }
    public String getLine1(){
        return this.line1;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setLine3(String line3){
        this.line3 = line3;
    }
    public String getLine3(){
        return this.line3;
    }
    public void setLine2(String line2){
        this.line2 = line2;
    }
    public String getLine2(){
        return this.line2;
    }
}
