package org.firstinspires.ftc.teamcode.odometry;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class OdometryCalculationsParallel {
    // Declare class Instances
    double theta;
    double leftWheelTurn;
    double rightWheelTurn;
    double trackWidth = 1;

    // Automatically update in updatePosition Function
    double previousCoordinateX = 0;
    double previousCoordinateY = 0;

    // Getter functions
    public double getTheta(){
        return theta;
    }

    public double getNewTheta(double newTheta){
        theta = newTheta;
        return theta;
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

    public double getLeftWheelTurn(){
        return leftWheelTurn;
    }

    public double getNewLeftWheelTurn(double newLeftWheelTurn){
        leftWheelTurn = newLeftWheelTurn;
        return leftWheelTurn;
    }

    public double getRightWheelTurn(){
        return rightWheelTurn;
    }

    public double getNewRightWheelTurn(double newRightWheelTurn) {
        rightWheelTurn = newRightWheelTurn;
        return rightWheelTurn;
    }

    // constructor call
    public OdometryCalculationsParallel(double inputTheta, double inputLeftWheelTurn,
                                        double inputRightWheelTurn){
        theta = getNewTheta(inputTheta);
        leftWheelTurn = getNewLeftWheelTurn(inputLeftWheelTurn);
        rightWheelTurn = getNewRightWheelTurn(inputRightWheelTurn);
    }

    // Getting the radii
    public double getPhi(){
        double phi = (leftWheelTurn - rightWheelTurn) / trackWidth;
        return phi;
    }
    public double getRadiusLeft(double phi){
        double radiusLeft = leftWheelTurn/phi;
        return radiusLeft;
    }

    public double getRadiusRight(double phi){
        double radiusRight = rightWheelTurn/phi;
        return radiusRight;
    }

    public double getRadiusCenter(double radiusLeft, double radiusRight) {
        double radiusCenter = (radiusLeft + radiusRight)/2;
        return radiusCenter;
    }

    // Calculate P of x and P of Y (aka center of rotation)
    public double getCenterRotationX(double radiusCenter){
        double centerOfRotationX = previousCoordinateX - (radiusCenter * Math.cos(theta));
        return centerOfRotationX;
    }

    public double getCenterRotationY(double radiusCenter){
        double centerOfRotationY = previousCoordinateY - (radiusCenter * Math.sin(theta));
        return centerOfRotationY;
    }

    // Calculate new global X and Y coordinates
    public double getGlobalX(double centerOfRotationX, double phi, double radiusCenter){
        double globalX = centerOfRotationX + (radiusCenter * Math.cos(phi + theta));
        return globalX;
    }

    public double getGlobalY(double centerOfRotationY, double phi, double radiusCenter){
        double globalY = centerOfRotationY + (radiusCenter * Math.sin(phi + theta));
        return globalY;
    }

    // Get the new global x and y position and update it to the class instances
    public ArrayList<Double> getGlobalCoordinates () {
        // Get radii
        double phi = getPhi();
        double radiusLeft = getRadiusLeft(phi);
        double radiusRight = getRadiusRight(phi);
        double radiusCenter = getRadiusCenter(radiusLeft, radiusRight);

        // Get center of rotations
        double centerOfRotationX = getCenterRotationX(phi);
        double centerOfRotationY = getCenterRotationY(phi);

        // Get new Coordinates
        double globalX = getGlobalX(centerOfRotationX, phi, radiusCenter);
        double globalY = getGlobalY(centerOfRotationY, phi, radiusCenter);
        double newTheta = theta + phi;

        // Return in a list
        ArrayList<Double> globalPosition = new ArrayList();
        globalPosition.add(globalX);
        globalPosition.add(globalY);
        globalPosition.add(newTheta);
        return globalPosition;
    }

    public void updatePosition(int newLeftWheelTurn, int newRightWheelTurn) {
        // Parse the global position
        ArrayList<Double> globalPosition = new ArrayList<Double>();
        globalPosition = getGlobalCoordinates();
        double newGlobalX = globalPosition.get(0);
        double newGlobalY = globalPosition.get(1);
        double newTheta = globalPosition.get(2);

        // Update all the values
        theta = newTheta;
        previousCoordinateX = newGlobalX;
        previousCoordinateY = newGlobalY;
        leftWheelTurn = newLeftWheelTurn;
        rightWheelTurn = newRightWheelTurn;
    }




}
