package com.project.ppp;

public class Program {

    private Robot robot;

    public Program(Robot robot) {
        this.robot = robot;
    }

    public int stepForward() {
        robot.turnLeft();
        robot.stepForward();
        robot.stepForward();
        robot.turnRight();
        robot.stepForward();
        robot.turnLeft();
        robot.turnRight();
        robot.turnRight();
        robot.turnRight();
        robot.stepForward();
        robot.stepForward();
        robot.stepForward();
        return 1;
    }
}
