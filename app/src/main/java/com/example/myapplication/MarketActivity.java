package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static android.media.CamcorderProfile.get;

public class MarketActivity extends AppCompatActivity {

    public LinkedList<Item> list = new LinkedList<Item>();
    public LinkedList<Integer> imageList = new LinkedList<Integer>();
    Food apple = new Food("Apple", 1, -5);
    Food pineapple = new Food("Poisoned Pineapple", 2, 10);
    Equipment hammer = new Equipment("Hammer", 10, 10);
    Equipment screw = new Equipment("Screw", 4, 3);
    Equipment umbrella = new Equipment("Umbrella", 5, 4);
    Equipment speaker = new Equipment("Speaker", 6, 1);
    Equipment bottle = new Equipment("Bottle", 7, 6);
    Equipment pen = new Equipment("Pen", 8, 3);
    Equipment pencil = new Equipment("Pencil", 9, 3);
    Equipment headphone = new Equipment("Headphone", 10, 2);
    private TextView item;
    private TextView value;
    private TextView massOrhealth;
    private ImageView imageOne;
    int number = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        list.add(apple);
        list.add(pineapple);
        list.add(hammer);
        list.add(screw);
        list.add(umbrella);
        list.add(speaker);
        list.add(bottle);
        list.add(pen);
        list.add(pencil);
        list.add(headphone);
        imageList.add(R.drawable.apple);
        imageList.add(R.drawable.pineapple);
        imageList.add(R.drawable.hammer);
        imageList.add(R.drawable.screw);
        imageList.add(R.drawable.umbrella);
        imageList.add(R.drawable.speaker);
        imageList.add(R.drawable.bottle);
        imageList.add(R.drawable.pen);
        imageList.add(R.drawable.pencil);
        imageList.add(R.drawable.headphone);

        final Button previous = (Button) findViewById(R.id.previous);
        Button next = (Button) findViewById(R.id.next);
        Button leave = (Button) findViewById(R.id.leave);
        Button buy = (Button) findViewById(R.id.buy);
        Button sell = (Button) findViewById(R.id.sell);
        item = (TextView) findViewById(R.id.item);
        value = (TextView) findViewById(R.id.value);
        massOrhealth = (TextView) findViewById(R.id.massOrhealth);
        imageOne = (ImageView) findViewById(R.id.imageOne);

        item.setText("Item:" + list.get(number).description);
        value.setText("Value: " + list.get(number).value);
        if (list.get(number) instanceof Food) {
            massOrhealth.setText("Health: " + ((Food) list.get(number)).health);
        } else if (list.get(number) instanceof Equipment) {
            massOrhealth.setText("Mass: " + ((Equipment) list.get(number)).mass);
        }
        imageOne.setImageResource(imageList.get(number));

        previous.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                number--;
                item.setText("Item:" + list.get(number).description);
                value.setText("Value: " + list.get(number).value);
                if (list.get(number) instanceof Food) {
                    massOrhealth.setText("Health: " + ((Food) list.get(number)).health);
                } else if (list.get(number) instanceof Equipment) {
                    massOrhealth.setText("Mass: " + ((Equipment) list.get(number)).mass);
                }
                imageOne.setImageResource(imageList.get(number));

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                number++;
                item.setText("Item:" + list.get(number).description);
                value.setText("Value: " + list.get(number).value);
                if (list.get(number) instanceof Food) {
                    massOrhealth.setText("Health: " + ((Food) list.get(number)).health);
                } else if (list.get(number) instanceof Equipment) {
                    massOrhealth.setText("Mass: " + ((Equipment) list.get(number)).mass);
                }
                imageOne.setImageResource(imageList.get(number));
            }

        });

        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leave();
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.setCash(MainActivity.decreaseCash(list.get(number).value));
                if (list.get(number) instanceof Equipment) {
                    MainActivity.addEquipment(((Equipment) list.get(number)).mass, list.get(number));
                } else if (list.get(number) instanceof Food) {
                    MainActivity.addHealth(((Food) list.get(number)).health);
                }
                //need a remove function to remove all the items bought by player from the list
            }
        });

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.sellEquipment(list.get(number));
            }
        });

    }

    public void leave() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

}