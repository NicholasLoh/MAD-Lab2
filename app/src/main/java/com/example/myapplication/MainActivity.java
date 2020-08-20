package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView grid;
    private static TextView life;
    private static TextView cash;
    private static TextView mass;
    private TextView status;
    private TextView place;
    GameMap location = new GameMap();
    public static Player player = new Player();

    @SuppressLint("SetTextI18n")
    public void show() {
        if ((location.grid[location.currRow][location.currColumn].town)) {
            place.setText("Town");
        } else {
            place.setText("Wilderness");
        }
        if (player.healthStatus()) {
            status.setText("Win");
            double showHealth = player.showHealth();
            int showCash = player.showCash();
            double showMass = player.showMass();
            life.setText(Double.toString(showHealth));
            cash.setText(Integer.toString(showCash));
            mass.setText(Double.toString(showMass));


        } else {
            status.setText("Lose");
            double showHealth = player.showHealth();
            int showCash = player.showCash();
            double showMass = player.showMass();
            life.setText(Double.toString(showHealth));
            cash.setText(Integer.toString(showCash));
            mass.setText(Double.toString(showMass));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button north = (Button) findViewById(R.id.north);
        Button south = (Button) findViewById(R.id.south);
        Button east = (Button) findViewById(R.id.east);
        Button west = (Button) findViewById(R.id.west);
        Button restart = (Button) findViewById(R.id.restart);
        Button option = (Button) findViewById(R.id.option);
        grid = (TextView) findViewById(R.id.grid);
        life = (TextView) findViewById(R.id.life);
        cash = (TextView) findViewById(R.id.cash);
        mass = (TextView) findViewById(R.id.mass);
        status = (TextView) findViewById(R.id.status);
        place = (TextView) findViewById(R.id.place);

        north.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (player.healthStatus()) {

                    player.healthDecrease();

                    location.changeLocation(location, 'N');
                    String locationStatus = "Current Location: (" + location.currRow + ", " + location.currColumn + ")";
                    show();
                    grid.setText(locationStatus);

                } else {
                    status.setText("Lose");
                }
            }
        });

        south.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (player.healthStatus()) {
                    if (!(location.currColumn == 0)) {
                        player.healthDecrease();
                    }
                    show();
                    location.changeLocation(location, 'S');
                    String locationStatus = "Current Location: (" + location.currRow + ", " + location.currColumn + ")";
                    grid.setText(locationStatus);

                } else {
                    status.setText("Lose");
                }
            }
        });

        east.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (player.healthStatus()) {
                    player.healthDecrease();

                    location.changeLocation(location, 'E');
                    String locationStatus = "Current Location: (" + location.currRow + ", " + location.currColumn + ")";
                    show();
                    grid.setText(locationStatus);

                } else {
                    status.setText("Lose");
                }
            }
        });

        west.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (player.healthStatus()) {
                    if (!(location.currRow == 0)) {
                        player.healthDecrease();
                    }
                    show();
                    location.changeLocation(location, 'W');
                    String locationStatus = "Current Location: (" + location.currRow + ", " + location.currColumn + ")";
                    grid.setText(locationStatus);

                } else {
                    status.setText("Lose");
                }
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                player.restart();
                location.restart();

                status.setText("Win");
                show();
                String locationStatus = "Current Location: (" + location.currRow + ", " + location.currColumn + ")";
                grid.setText(locationStatus);

            }
        });

        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((location.grid[location.currRow][location.currColumn]).town) {
                    openMarket();
                } else {
                    openWild();
                }
            }
        });

    }

    public void openMarket() {
        Intent intent = new Intent(this, MarketActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivityForResult(intent, 1);
    }

    public void openWild() {
        Intent intent = new Intent(this, WildernessActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    @SuppressLint("SetTextI18n")
    public static void addEquipment(double Mass, Item item) {
        player.equipment.add(item);
        player.equipmentMass = player.equipmentMass + Mass;
        mass.setText(Double.toString(player.equipmentMass));
    }

    @SuppressLint("SetTextI18n")
    public static void addHealth(double health) {
        player.health = player.health + health;
        life.setText(Double.toString(player.health));
    }

    @SuppressLint("SetTextI18n")
    public static void sellEquipment(Item item) {
        if (player.equipment.contains(item)) {
            player.cash = player.cash + ((item.value / 100) * 75);
            player.equipmentMass = player.equipmentMass - ((Equipment) item).mass;
            player.equipment.remove(item);
        }
        cash.setText(Integer.toString(player.cash));
        mass.setText(Double.toString(player.equipmentMass));
    }

    public static int decreaseCash(int money) {
        player.cash = player.cash - money;
        return player.cash;
    }

    @SuppressLint("SetTextI18n")
    public static void setCash(int money) {
        player.cash = money;
        cash.setText(Integer.toString(player.cash));
    }

    @SuppressLint("SetTextI18n")
    public static void setHealth(double Health) {
        player.health = Health;
        life.setText(Double.toString(Health));
    }

    @SuppressLint("SetTextI18n")
    public static void setMass(double Mass) {
        player.equipmentMass = Mass;
        mass.setText(Double.toString(Mass));
    }

}