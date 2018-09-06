package org.firstinspires.ftc.TeamCodeRelicRecovery.paths;

import java.util.List;

import org.firstinspires.ftc.math.RigidTransform2d;
import org.firstinspires.ftc.math.Rotation2d;
import org.firstinspires.ftc.math.Translation2d;

public class PathBuilder {

    private static final double kEpsilon = 1E-9;
    private static final double kReallyBigNumber = 1E9;

    public static Path buildPathFromWaypoints(List<Waypoint> w) {
        Path p = new Path();
        if (w.size() < 2)
            throw new Error("Path must contain at least 2 waypoints");
        int i = 0;
        if (w.size() > 2) {
            do {
                new Arc(getPoint(w, i), getPoint(w, i + 1), getPoint(w, i + 2)).addToPath(p);
                i++;
            } while (i < w.size() - 2);
        }
        new Line(w.get(w.size() - 2), w.get(w.size() - 1)).addToPath(p);

        return p;
    }

    private static Waypoint getPoint(List<Waypoint> w, int i) {
        if (i > w.size())
            return w.get(w.size() - 1);
        return w.get(i);
    }

    /**
     * A waypoint along a path. Contains a position, radius (for creating curved paths), and speed. The information from
     * these waypoints is used by the PathBuilder class to generate Paths. Waypoints also contain an optional marker
     * that is used by the WaitForPathMarkerAction.
     *
     * @see PathBuilder
     */

    public static class Waypoint {
        Translation2d position;
        double radius;
        double speed;
        String marker;

        public Waypoint(Waypoint other) {
            this(other.position.x(), other.position.y(), other.radius, other.speed, other.marker);
        }

        public Waypoint(double x, double y, double r, double s) {
            position = new Translation2d(x, y);
            radius = r;
            speed = s;
        }

        public Waypoint(Translation2d pos, double r, double s) {
            position = pos;
            radius = r;
            speed = s;
        }

        public Waypoint(double x, double y, double r, double s, String m) {
            position = new Translation2d(x, y);
            radius = r;
            speed = s;
            marker = m;
        }
    }

    /**
     * A Line object is formed by two Waypoints. Contains a start and end position, slope, and speed.
     */
    static class Line {
        Waypoint a;
        Waypoint b;
        Translation2d start;
        Translation2d end;
        Translation2d slope;
        double speed;

        public Line(Waypoint a, Waypoint b) {
            this.a = a;
            this.b = b;
            slope = new Translation2d(a.position, b.position);
            speed = b.speed;
            start = a.position.translateBy(slope.scale(a.radius / slope.norm()));
            end = b.position.translateBy(slope.scale(-b.radius / slope.norm()));
        }

        private void addToPath(Path p) {
            double pathLength = new Translation2d(end, start).norm();
            if (pathLength > kEpsilon) {
                p.addStraight(pathLength, speed);
            }
        }
    }

    /**
     * An Arc object is formed by two Lines that share a common Waypoint. Contains a center position, radius, and speed.
     */
    static class Arc {
        Line a;
        Line b;
        Translation2d center;
        double radius;
        double speed;

        public Arc(Waypoint a, Waypoint b, Waypoint c) {
            this(new Line(a, b), new Line(b, c), b.position);
        }

        public Arc(Line a, Line b, Translation2d center) {
            this.a = a;
            this.b = b;
            this.speed = (a.speed + b.speed) / 2;
            this.center = center;
            this.radius = new Translation2d(center, a.end).norm();
        }

        private void addToPath(Path p) {
            a.addToPath(p);
            if (radius > kEpsilon && radius < kReallyBigNumber) {
                double direction = Translation2d.cross(a.slope, b.slope);
                double angle = Translation2d.getAngle(a.slope, b.slope).getDegrees();

                if(direction < 0) {
                    p.addRightTurn(angle, radius, speed);
                }
                else if(direction > 0) {
                    p.addLeftTurn(angle, radius, speed);
                }
            }
        }

        private static Translation2d intersect(Line l1, Line l2) {
            final RigidTransform2d lineA = new RigidTransform2d(l1.end, new Rotation2d(l1.slope, true).normal());
            final RigidTransform2d lineB = new RigidTransform2d(l2.start, new Rotation2d(l2.slope, true).normal());
            return lineA.intersection(lineB);
        }
    }
}