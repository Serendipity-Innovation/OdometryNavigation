package org.firstinspires.ftc.teamcode.odometry;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;

public class OdometryCalculations {
    DcMotor wheelOdometry1 = null;
    DcMotor wheelOdometry2 = null;
    BNO055IMU gyroscope = null;
    public OdometryCalculations(HardwareMap hardwareMap){
        wheelOdometry1 = hardwareMap.get(DcMotor.class, "wheelOdometry1");
        wheelOdometry2 = hardwareMap.get(DcMotor.class, "wheelOdometry2");
        gyroscope = hardwareMap.get(BNO055IMU.class, "gyroscope");

        wheelOdometry1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelOdometry2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        wheelOdometry1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wheelOdometry2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        gyroscope.initialize(parameters);
    }

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
