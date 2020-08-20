package com.example.myapplication;

import java.io.Serializable;

abstract class Item implements Serializable {
    String description;
    int value;
    boolean bought = false;
}

class Equipment extends Item{
    double mass;
    public Equipment(String name, int worth,double weight){
        description = name;
        value = worth;
        mass = weight;
    }
}

class Food extends Item{
    double health;
    public Food(String name, int worth,double Health){
        description = name;
        value = worth;
        health = Health;
    }
}
