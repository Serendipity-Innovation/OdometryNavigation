package org.firstinspires.ftc.teamcode.attachments;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ShadowCaster {
    DcMotor linearActuator = null;
    Servo uvCover = null;
    public ShadowCaster(HardwareMap hardwareMap){
        linearActuator  = hardwareMap.get(DcMotor.class, "linearActuator");
        uvCover = hardwareMap.get(Servo.class, "right_wheel");

        // Wheels are mounted opposite so one must be reversed

        // Set wheels to run with encoders
        linearActuator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearActuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void uncoverLight() {
        double positionOpen = 0;
        uvCover.setPosition(positionOpen);
    }

    public void coverLight(){
        double positionClose = 0.5;
        uvCover.setPosition(positionClose);
    }

    public void moveLinearActuatorUp(int movementTicks)  {
        int currentPosition = linearActuator.getCurrentPosition();
        // Moving up
        int desiredUpPosition = currentPosition + movementTicks;
        linearActuator.setTargetPosition(desiredUpPosition);

        while (linearActuator.isBusy()){ }
    }

    public void moveLinearActuatorDown (int movementTicks){
        int currentPosition = linearActuator.getCurrentPosition();
        // Moving Down
        int desiredDownPosition = currentPosition - movementTicks;
        linearActuator.setTargetPosition(desiredDownPosition);

        while (linearActuator.isBusy()){ }
    }
}
