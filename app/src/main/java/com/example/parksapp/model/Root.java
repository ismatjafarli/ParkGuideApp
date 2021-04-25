package com.example.parksapp.model;

import java.util.List;

public class Root
{
    private String total;

    private String limit;

    private String start;

    private List<Data> data;

    public void setTotal(String total){
        this.total = total;
    }
    public String getTotal(){
        return this.total;
    }
    public void setLimit(String limit){
        this.limit = limit;
    }
    public String getLimit(){
        return this.limit;
    }
    public void setStart(String start){
        this.start = start;
    }
    public String getStart(){
        return this.start;
    }
    public void setData(List<Data> data){
        this.data = data;
    }
    public List<Data> getData(){
        return this.data;
    }
}

