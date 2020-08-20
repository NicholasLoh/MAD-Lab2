package com.example.myapplication;

public class GameMap {
    Area[][] grid;
    int currColumn = 0;
    int currRow = 0;

    public GameMap() {
        grid = new Area[20][20];
        Area a = new Area();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                grid[i][j] = a;
            }
        }
        Area area = new Area();
        area.town = true;
        grid[1][1] = area;
        grid[2][2] = area;
        grid[5][2] = area;
    }

    public void changeLocation(GameMap map, char c) {
        switch (c) {
            case 'N':
                map.currColumn++;
                break;

            case 'S':
                if (map.currColumn > 0) {
                    map.currColumn--;
                }
                break;

            case 'E':
                map.currRow++;
                break;

            case 'W':
                if (map.currRow > 0) {
                    map.currRow--;
                }
                break;
        }
    }

    public void restart() {
        this.currRow = 0;
        this.currColumn = 0;
    }


}
