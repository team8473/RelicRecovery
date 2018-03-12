package org.firstinspires.ftc.paths;

import java.util.ArrayList;
import org.firstinspires.ftc.paths.PathBuilder.Waypoint;
import org.firstinspires.ftc.math.RigidTransform2d;
import org.firstinspires.ftc.math.Rotation2d;
import org.firstinspires.ftc.math.Translation2d;

public class RedLeft implements PathContainer {
    
    @Override
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<>();
        sWaypoints.add(new Waypoint(24,49,0,0));
        sWaypoints.add(new Waypoint(24,75,0,0.5));
        sWaypoints.add(new Waypoint(10,75,0,0.5));

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
	// WAYPOINT_DATA: [{"position":{"x":24,"y":49},"speed":0,"radius":0,"comment":""},{"position":{"x":24,"y":75},"speed":0,"radius":0,"comment":""},{"position":{"x":10,"y":75},"speed":60,"radius":0,"comment":""}]
	// IS_REVERSED: false
	// FILE_NAME: RedLeft
}