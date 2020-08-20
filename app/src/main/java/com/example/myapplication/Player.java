package com.example.myapplication;

import java.io.Serializable;
import java.util.LinkedList;

//Serializable 是为了让player object可以在intent被pass过来
public class Player implements Serializable {
    private int rowLocation;
    private int colLocation;
    int cash;
    double health;
    double equipmentMass;
    LinkedList<Item> equipment = new LinkedList<Item>();

    public Player(){
        cash = 100;
        health = 100.0;
        equipmentMass = 0.0;
    }
    public void healthDecrease(){
        if(this.health > 0){
            this.health = Math.max(0.0,health-5.0-(equipmentMass/2.0));
        }
    }

    public void cashDecrease(Item item){
        this.cash = cash - item.value;
    }

    public double showHealth(){
        return health;
    }

    public int showCash(){
        return cash;
    }

    public double showMass(){
        return equipmentMass;
    }

    public boolean healthStatus(){
        return this.health > 0;
    }

    public void restart(){
        this.health = 100.00;
        this.cash = 100;
        this.equipmentMass = 0.0;
    }
}