package org.firstinspires.ftc.teamcode.odometry;

import java.util.ArrayList;

public class OdometryCalculationsRetired {
    // Getting the distance the robot moved
    public double getXCoordinate(int wheelOdometryMovement, double angle){
        double xCoordinate = wheelOdometryMovement * Math.cos(angle);
        return xCoordinate;
    }

    public double getYCoordinate(int wheelOdometryMovement, double angle){
        double yCoordinate = wheelOdometryMovement * Math.sin(angle);
        return yCoordinate;
    }

    // Getting the delta x and delta y
    public double getDeltaX(double xCoordinate1, double xCoordinate2){
        double deltaX = xCoordinate1 + xCoordinate2;
        return deltaX;
    }

    public double getDeltaY(double yCoordinate1, double yCoordinate2){
        double deltaY = yCoordinate1 + yCoordinate2;
        return deltaY;
    }

    // Add that value ot the original X and Y to get new X and Y
    public double getNewCoordinate(double delta, double baseCoordinate){
        double newCoordinate = delta + baseCoordinate;
        return newCoordinate;
    }

    public ArrayList getNewPosition(int wheelMovement1, int wheelMovement2, double angle,
                                    double baseCoordinateX, double baseCoordinateY){
        // Get all coordinates
        double xCoordinate1 = getXCoordinate(wheelMovement1, angle);
        double xCoordinate2 = getXCoordinate(wheelMovement2, angle);
        double yCoordinate1 = getYCoordinate(wheelMovement1,angle);
        double yCoordinate2 = getYCoordinate(wheelMovement2, angle);

        // Get deltas
        double deltaX = getDeltaX(xCoordinate1, xCoordinate2);
        double deltaY = getDeltaY(yCoordinate1, yCoordinate2);

        // Update original point
        double newCoordinateX = getNewCoordinate(deltaX, baseCoordinateX);
        double newCoordinateY = getNewCoordinate(deltaY, baseCoordinateY);

        // Return new coordinates as a list
        ArrayList newCoordinates = new ArrayList();
        newCoordinates.add(newCoordinateX);
        newCoordinates.add(newCoordinateY);

        return newCoordinates;
    }

}
