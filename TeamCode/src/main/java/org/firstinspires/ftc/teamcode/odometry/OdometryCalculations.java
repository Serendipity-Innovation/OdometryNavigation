package org.firstinspires.ftc.teamcode.odometry;

import java.util.ArrayList;

public class OdometryCalculations {
    // Declare class Instances
    double radiansAngle;
    double wheelDeltaX;
    double wheelDeltaY;
    double previousCoordinateX = 0;
    double previousCoordinateY = 0;
    double radiusWheel1 = 1;
    double radiusWheel2 = 1;

    // Getter functions
    public double getRadiansAngle(){
        return radiansAngle;
    }

    public double getNewRadiansAngle(double newRadiansAngle){
        radiansAngle = newRadiansAngle;
        return radiansAngle;
    }

    public double getWheelDeltaX(){
        return wheelDeltaX;
    }

    public double getNewWheelDeltaX(double newWheelDeltaX){
        wheelDeltaX = newWheelDeltaX;
        return wheelDeltaX;
    }

    public double getWheelDeltaY(){
        return wheelDeltaY;
    }

    public double getNewWheelDeltaY(double newWheelDeltaY) {
        wheelDeltaY = newWheelDeltaY;
        return wheelDeltaY;
    }

    public double getPreviousCoordinateX(){
        return previousCoordinateX;
    }

    public double getNewPreviousCoordinateX(double newPreviousCoordinateX) {
        previousCoordinateX = newPreviousCoordinateX;
        return previousCoordinateX;
    }

    public double getPreviousCoordinateY(){
        return previousCoordinateY;
    }

    public double getNewPreviousCoordinateY(double newPreviousCoordinateY) {
        previousCoordinateY = newPreviousCoordinateY;
        return previousCoordinateY;
    }


    // Local(intrinsic) Wheel Displacement
    public double getDeltaX(){
        double arcDelta = Math.toRadians(radiansAngle) * radiusWheel1;
        double deltaX = wheelDeltaX - arcDelta;
        return deltaX;
    }

    public double getDeltaY(){
        double arcDelta = Math.toRadians(radiansAngle) * radiusWheel2;
        double deltaY = wheelDeltaY - arcDelta;
        return deltaY;
    }

    // Add local wheel displacement to previous coordinate to get local coordinate
    public double getLocalCoordinate(double previousCoordinate, double delta){
        double localCoordinate = previousCoordinate + delta;
        return localCoordinate;
    }

    // Change the localCoordinate into a global Coordinate by rotating the 2D point by theta
    // Formulas derived from rotating a 2D point formula about arbitrary point
    public double getGlobalX(double localCoordinateX, double localCoordinateY){
        double globalX = ((localCoordinateX - previousCoordinateX) * Math.cos(radiansAngle)) -
                ((localCoordinateY - previousCoordinateY) * Math.sin(radiansAngle)) + previousCoordinateX;
        return globalX;
    }

    public double getGlobalY(double localCoordinateX, double localCoordinateY) {
        double globalY = ((localCoordinateX - previousCoordinateX) * Math.sin(radiansAngle)) +
                ((localCoordinateY - previousCoordinateY) * Math.cos(radiansAngle)) + previousCoordinateY;
        return globalY;
    }

    public ArrayList getGlobalPositionAndResetPreviousPosition(){
        // Get local coordinates
        double deltaX = getDeltaX();
        double localCoordinateX = getLocalCoordinate(previousCoordinateX, deltaX);

        double deltaY = getDeltaY();
        double localCoordinateY = getLocalCoordinate(previousCoordinateY, deltaY);

        // Add global coordinates to a list
        double globalX = getGlobalX(localCoordinateX, localCoordinateY);
        double globalY = getGlobalY(localCoordinateX, localCoordinateY);

        // Reset the previousCoordinates to now be the current globalCoordiantes
        getNewPreviousCoordinateX(globalX);
        getNewPreviousCoordinateY(globalY);

        ArrayList globalCoordinates = new ArrayList();
        globalCoordinates.add(globalX);
        globalCoordinates.add(globalY);
        return globalCoordinates;
    }

    /* // older method
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
    */

}
