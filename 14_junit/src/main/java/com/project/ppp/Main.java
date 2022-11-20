package com.project.ppp;

public class Main {
    public static void main(String[] args) {
        Robot robot = new Robot();
        Program program = new Program(robot);
        program.stepForward();
    }
}
