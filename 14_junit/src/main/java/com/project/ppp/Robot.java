package com.project.ppp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Robot {

    private  String FILE_NAME = "route.txt";
    private  File FILE = new File(FILE_NAME);
    private  String GET_ABSOLUTE_PATH = FILE.getAbsolutePath();
    private  List<String> listClockWise;
    private  List<String> listAntiClockWise;
    private int x;
    private int y;
    private String direction;

    public Robot() {
        this.x = 0;
        this.y = 0;
        this.direction = "right";
        this.listClockWise = new ArrayList<>(Arrays.asList("right", "down", "left", "up"));
        this.listAntiClockWise = new ArrayList<>(Arrays.asList("right", "up", "left", "down"));
    }

    public void turnRight() {
        int count = 0;
        while (true) {
            if (listClockWise.get(count).equals(direction)) {
                count++;
                direction = listClockWise.get(count % 4);
                break;
            }
            count++;
        }
    }

    public void turnLeft() {
        int count = 0;
        while (true) {
            if (listAntiClockWise.get(count).equals(direction)) {
                count++;
                direction = listAntiClockWise.get(count % 4);
                break;
            }
            count++;
        }
    }

    public void stepForward() {
        switch (direction) {
            case ("right"):
                x += 1;
                break;
            case ("down"):
                y -= 1;
                break;
            case ("left"):
                x -= 1;
                break;
            case ("up"):
                y += 1;
                break;
        }
        String s = x + "." + y;
        printIntoFile(s);
    }

    public void printIntoFile(String s) {
        try {
            if (!FILE.exists()) {
                FILE.createNewFile();
            }
            PrintWriter out = new PrintWriter(new FileWriter(FILE, true));
            out.append(s);
            out.println();
            out.close();
        } catch (IOException e) {
            System.out.println("Could not write!");
        }
    }

    public String getDirection() {
        return direction;
    }

    public String getGET_ABSOLUTE_PATH() {
        return GET_ABSOLUTE_PATH;
    }

    public File getFILE() {
        return FILE;
    }

    public String getFILE_NAME() {
        return FILE_NAME;
    }
}
