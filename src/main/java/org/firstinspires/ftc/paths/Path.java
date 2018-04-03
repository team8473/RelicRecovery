package org.firstinspires.ftc.paths;

import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.ball;

public class Path {

    private static final double ROBOT_WIDTH = 17.2;

    private static double leftDistance;
    private static double rightDistance;
    private static double leftSpeed;
    private static double rightSpeed;

    private WaypointDrive waypoint = new WaypointDrive();

    void addLeftTurn(double angle, double radius, double speed) {
        double distance = convertDistance(angle, radius);

        double innerScale = innerScale(radius);

        leftDistance =  (distance * Math.abs(innerScale));
        leftSpeed =     (speed * innerScale);
        rightDistance = (distance);
        rightSpeed =    (speed);

        waypoint.drive(leftSpeed, rightSpeed, leftDistance, rightDistance);
    }

    void addRightTurn(double angle, double radius, double speed) {
        double distance = convertDistance(angle, radius);

        double innerScale = innerScale(radius);

        rightDistance = (distance * Math.abs(innerScale));
        rightSpeed = (speed * innerScale);
        leftDistance = (distance);
        leftSpeed = (speed);

        waypoint.drive(leftSpeed, rightSpeed, leftDistance, rightDistance);
    }

    void addStraight(double distance, double speed) {
        leftDistance = distance;
        rightDistance = distance;
        leftSpeed = speed;
        rightSpeed = speed;

        switch(ball){
            case IS_RED_BALL:
                waypoint.drive(leftSpeed, rightSpeed, leftDistance - 4, rightDistance - 4);
                break;
            default:
                waypoint.drive(leftSpeed, rightSpeed, leftDistance, rightDistance);
                break;
        }
    }

    private double convertDistance(double angle, double radius) {
        angle *= Math.PI / 180.0; //convert to radians
        return radius * angle;
    }

    private double innerScale(double radius) {
        radius = Math.abs(radius);
        double innerRadius = radius - ROBOT_WIDTH;
        if (radius > 0.0) innerRadius /= radius;
        return innerRadius;
    }
}
