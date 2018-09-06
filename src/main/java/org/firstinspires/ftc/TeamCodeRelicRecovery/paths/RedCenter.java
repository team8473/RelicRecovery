package org.firstinspires.ftc.TeamCodeRelicRecovery.paths;

import java.util.ArrayList;

import org.firstinspires.ftc.TeamCodeRelicRecovery.paths.PathBuilder.Waypoint;
import org.firstinspires.ftc.math.RigidTransform2d;
import org.firstinspires.ftc.math.Rotation2d;
import org.firstinspires.ftc.math.Translation2d;

public class RedCenter implements PathContainer {

    @Override
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(24,49,0,0));
        sWaypoints.add(new Waypoint(24,85,0,12));
        sWaypoints.add(new Waypoint(10,85,0,12));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }

    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(24, 49), Rotation2d.fromDegrees(180.0));
    }

    @Override
    public boolean isReversed() {
        return false;
    }
    // WAYPOINT_DATA: [{"position":{"x":24,"y":49},"speed":0,"radius":0,"comment":""},{"position":{"x":24,"y":85},"speed":0,"radius":0,"comment":""},{"position":{"x":10,"y":85},"speed":0,"radius":0,"comment":""}]
    // IS_REVERSED: false
    // FILE_NAME: RedCenter
}