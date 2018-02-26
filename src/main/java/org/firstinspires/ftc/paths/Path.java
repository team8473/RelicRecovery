package org.firstinspires.ftc.paths;

import org.firstinspires.ftc.paths.WaypointDrive;

public class Path {
    private static final double ROBOT_WIDTH = 17.2;
    private static final double WHEEL_DIAMETER = 4;
    private static final double CIRCUMFRENCE = Math.PI * WHEEL_DIAMETER;

    static double leftDistance1, leftDistance2, leftDistance3;
    static double rightDistance1, rightDistance2, rightDistance3;
    static double leftSpeed1, leftSpeed2, leftSpeed3;
    static double rightSpeed1, rightSpeed2, rightSpeed3;

    WaypointDrive waypoint = new WaypointDrive();

    public double convertDistance(double distance) {
        distance = Math.abs(distance);
        return distance / CIRCUMFRENCE;
    }

    public void addLeftTurn(double angle, double radius, double speed) {
        double distance = convertDistance(angle, radius);

        double innerScale = innerScale(radius);

        leftDistance3 = (distance * Math.abs(innerScale));
        leftSpeed3 = (speed * innerScale);
        rightDistance3 = (distance);
        rightSpeed3 = (speed);

        waypoint.drive(leftSpeed3, rightSpeed3, leftDistance3, rightDistance3, 10);
    }

    public void addRightTurn(double angle, double radius, double speed) {
        double distance = convertDistance(angle, radius);

        double innerScale = innerScale(radius);

        rightDistance2 = (distance * Math.abs(innerScale));
        rightSpeed2 = (speed * innerScale);
        leftDistance2 = (distance);
        leftSpeed2 = (speed);

        waypoint.drive(leftSpeed2, rightSpeed2, leftDistance2, rightDistance2, 10);
    }

    public void addStraight(double distance, double speed) {
        leftDistance1 = distance;
        rightDistance1 = distance;
        leftSpeed1 = speed;
        rightSpeed1 = speed;

        waypoint.drive(leftSpeed1, rightSpeed1, leftDistance1, rightDistance1, 10);
    }

    public double convertDistance(double angle, double radius) {
        angle *= Math.PI / 180.0; //convert to radians
        double distance = radius * angle;
        return distance;
    }

    public double innerScale(double radius) {
        radius = Math.abs(radius);
        double innerRadius = radius - ROBOT_WIDTH;
        if (radius > 0.0) innerRadius /= radius;
        return innerRadius;
    }
}
