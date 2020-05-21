package org.firstinspires.ftc.teamcode.odometry;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;

public class OdometryMap {
    // Declare class Instances
    double radiansAngle;
    double wheelDeltaX;
    double wheelDeltaY;
    double previousCoordinateX;
    double previousCoordinateY;
    DcMotor wheelOdometry1 = null;
    DcMotor wheelOdometry2 = null;
    BNO055IMU gyroscope = null;
    public OdometryMap(HardwareMap hardwareMap){
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


}
