package org.firstinspires.ftc.teamcode.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DrivetrainWheel {
    private DcMotor leftWheel = null;
    private DcMotor rightWheel = null;
    // constructing object with hardwareMap
    public DrivetrainWheel(HardwareMap hardwareMap){
        leftWheel  = hardwareMap.get(DcMotor.class, "left_wheel");
        rightWheel = hardwareMap.get(DcMotor.class, "right_wheel");

        // Wheels are mounted opposite so one must be reversed
        leftWheel.setDirection(DcMotor.Direction.FORWARD);
        rightWheel.setDirection(DcMotor.Direction.REVERSE);

        // Set wheels to run with encoders
        leftWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    // Get the wheel values
    public DcMotor getLeftWheel(){
        return leftWheel;
    }

    public DcMotor getRightWheel(){
        return rightWheel;
    }

    // Set power function
    public void setPower(double power){
        //ranges from -1 to 1
        leftWheel.setPower(power);
        rightWheel.setPower(power);
    }

    // Update Position Function
    public void updateWheel(double desiredFeet, DcMotor wheel){
        // Update the position that the motors should move to
        int tickMovement = DrivetrainCalculations.getFeetToTicks(desiredFeet);
        int finalPosition = wheel.getCurrentPosition() + tickMovement;

        // Move to updated position
        wheel.setTargetPosition(finalPosition);
    }

    public void moveWheelsFeetPower(double desiredFeet){
        setPower(0.2);

        // Move to Target Position
        updateWheel(desiredFeet, leftWheel);
        updateWheel(desiredFeet, rightWheel);
    }

    public void turnAngle(double desiredAngle){
        setPower(0.1);
        double desiredFeet = DrivetrainCalculations.getAngleToFeet(desiredAngle);
        updateWheel(desiredFeet, leftWheel);
        updateWheel(desiredFeet, rightWheel);
    }


}
