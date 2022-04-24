package com.njkol.rxjava.dto;

public class Car{
    public Integer price;
    public String year;
    public String make;
    public String model;

    public  enum Type{
        ALL_ELECTRIC,
        ALL_DIESEL,
        ALL_PETROL
    }

    public Type type;

    public Car(Type type, Integer price, String year, String make, String model){
        this.type = type;
        this.price= price;
        this.year = year;
        this.make = make;
        this.model = model;
    }

}