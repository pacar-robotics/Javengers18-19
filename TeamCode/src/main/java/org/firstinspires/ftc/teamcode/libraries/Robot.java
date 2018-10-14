package org.firstinspires.ftc.teamcode.libraries;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import static org.firstinspires.ftc.teamcode.libraries.Constants.LEFT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.RIGHT_WHEEL;

public class Robot {

    private LinearOpMode opMode;

    private DcMotor[] dcMotors = new DcMotor[2];

    Robot(LinearOpMode opMode) {
        this.opMode = opMode;

        initDcMotor();
    }

    private void initDcMotor() {
        dcMotors[LEFT_WHEEL] = opMode.hardwareMap.get(DcMotor.class, "leftWheel");
        dcMotors[RIGHT_WHEEL] = opMode.hardwareMap.get(DcMotor.class, "rightWheel");

        dcMotors[LEFT_WHEEL].setDirection(DcMotorSimple.Direction.REVERSE);
    }

    void setDcMotorPower(int index, float power) {
        dcMotors[index].setPower(power);
    }
}
