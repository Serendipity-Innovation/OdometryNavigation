package org.firstinspires.ftc.teamcode.drivetrain;

public class DrivetrainCalculations {

    public static int getFeetToTicks(double desiredFeet){
        // Setting up the ratios
        double wheelCircumfrence = 0.104; //units in feet from CAD
        int ticksPerRotation = 1120;

        // Calculating the ratios
        double rotations = desiredFeet / wheelCircumfrence;
        double ticks = rotations * ticksPerRotation;
        return (int) ticks;
    }

    public static double getAngleToFeet(double desiredAngle){
        double radianAngle = Math.toRadians(desiredAngle);
        double wheelToCenterDistance = 0.5; // note that this is radius measured in feet
        double desiredFeet = radianAngle * wheelToCenterDistance; // same as r * theta
        return desiredFeet;
    }
}
