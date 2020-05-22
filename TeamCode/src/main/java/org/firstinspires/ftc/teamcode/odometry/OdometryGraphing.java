package org.firstinspires.ftc.teamcode.odometry;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OdometryGraphing {
    // Declare class Instances
    DcMotor wheelOdometry1 = null;
    DcMotor wheelOdometry2 = null;
    BNO055IMU gyroscope = null;
    Orientation angles;
    public OdometryGraphing(HardwareMap hardwareMap){
        wheelOdometry1 = hardwareMap.get(DcMotor.class, "wheelOdometry1");
        wheelOdometry2 = hardwareMap.get(DcMotor.class, "wheelOdometry2");
        gyroscope = hardwareMap.get(BNO055IMU.class, "gyroscope");

        wheelOdometry1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelOdometry2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        wheelOdometry1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wheelOdometry2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        gyroscope.initialize(parameters);
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        angles = gyroscope.getAngularOrientation(
                AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.RADIANS);
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
    }

    // Getter Functions
    public DcMotor getWheelOdometry1(){
        return wheelOdometry1;
    }

    public DcMotor getWheelOdometry2(){
        return wheelOdometry2;
    }

    public BNO055IMU getGyroscope(){
        return gyroscope;
    }

    // Initialize class instances to get the coordinates based off of the wheel movements and IMU
    double theta = angles.thirdAngle;

    double previousWheelRightPosition = 0;
    double leftWheelTurn = wheelOdometry1.getCurrentPosition() - previousWheelRightPosition;

    double previousWheelLeftPosition = 0;
    double rightWheelTurn = wheelOdometry2.getCurrentPosition() - previousWheelLeftPosition;

    OdometryCalculationsParallel colsomWheels = new OdometryCalculationsParallel(theta, leftWheelTurn, rightWheelTurn);
    DataFiles odometryDataFile = new DataFiles();

    public void graphData(boolean isDetectGerms) throws IOException {
        // Parse the list for global x, global y
        ArrayList<Double> globalPosition = new ArrayList();
        globalPosition = colsomWheels.getGlobalCoordinates();
        double globalX = globalPosition.get(0);
        double globalY = globalPosition.get(1);

        // make the file.txt with the positions
        odometryDataFile.writeGraphDataFileAndUpdateVersion(globalX, globalY, isDetectGerms);
    }
}
