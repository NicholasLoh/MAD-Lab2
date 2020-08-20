package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
    private TextView life;
    private TextView cashField;
    private TextView massField;
    int number = 0;

    //Return a intent with extra values
    public static Intent getIntent(Context c, Player player) {
        Intent intent= new Intent(c, MarketActivity.class);
        intent.putExtra("player", player);
        return intent;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        Intent intent = getIntent();
        final Player player = (Player) intent.getSerializableExtra("player");
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

        life = (TextView) findViewById(R.id.life2);
        cashField = (TextView) findViewById(R.id.cash2);
        massField = (TextView) findViewById(R.id.mass2);

        item.setText("Item:" + list.get(number).description);
        value.setText("Value: " + list.get(number).value);

        //如果player不是null
        assert player != null;
        life.setText(Double.toString(player.showHealth()));
        cashField.setText(Integer.toString(player.showCash()));
        massField.setText(Double.toString(player.showMass()));

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
                leave(player);
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //直接改player obj
                player.cash = player.cash - list.get(number).value;

                if (list.get(number) instanceof Equipment) {
                    double Mass = ((Equipment) list.get(number)).mass;
                    Item item = list.get(number);
                    player.equipment.add(item);
                    player.equipmentMass = player.equipmentMass + Mass;
                    massField.setText(Double.toString(player.equipmentMass));
                } else if (list.get(number) instanceof Food) {
                    double health = ((Food) list.get(number)).health;
                    player.health = player.health + health;
                    life.setText(Double.toString(player.health));
                }
                cashField.setText(Integer.toString(player.cash));

                //need a remove function to remove all the items bought by player from the list
            }
        });

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Item item = list.get(number);
                if (player.equipment.contains(item)) {
                    player.cash = player.cash + ((item.value / 100) * 75);
                    player.equipmentMass = player.equipmentMass - ((Equipment) item).mass;
                    player.equipment.remove(item);
                }
                cashField.setText(Integer.toString(player.cash));
                massField.setText(Double.toString(player.equipmentMass));
            }
        });

    }

    //https://stackoverflow.com/questions/26703691/android-return-object-as-a-activity-result#:~:text=3%20Answers&text=You%20cannot%20return%20an%20object,resultIntent%20%3D%20new%20Intent()%3B%20resultIntent.
    public void leave(Player player) {
        Intent returnData = new Intent(this, MainActivity.class);

        returnData.putExtra("player", player );
        setResult(RESULT_OK, returnData);
        finish();
    }

}