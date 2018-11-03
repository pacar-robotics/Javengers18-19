package org.firstinspires.ftc.teamcode.libraries;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import static org.firstinspires.ftc.teamcode.libraries.Constants.LATCHER;
import static org.firstinspires.ftc.teamcode.libraries.Constants.LEFT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.RIGHT_WHEEL;

/*
 * Title: Robot
 * Date Created: 10/14/2018
 * Date Modified: 11/3/2018
 * Author: Rahul
 * Type: Library
 * Description: This is the base library for any main op to be based off. It will contain all the
 *              motors, servos, and sensors.
 */

public class Robot {

    private LinearOpMode opMode;

    private DcMotor[] dcMotors = new DcMotor[3];

    Robot(LinearOpMode opMode) {
        this.opMode = opMode;

        initDcMotor();
    }

    private void initDcMotor() {
        dcMotors[LEFT_WHEEL] = opMode.hardwareMap.get(DcMotor.class, "leftWheel");
        dcMotors[RIGHT_WHEEL] = opMode.hardwareMap.get(DcMotor.class, "rightWheel");
        dcMotors[LATCHER] = opMode.hardwareMap.get(DcMotor.class, "latcher");

        dcMotors[LEFT_WHEEL].setDirection(DcMotorSimple.Direction.REVERSE);
    }

    void setDcMotorPower(int index, float power) {
        dcMotors[index].setPower(power);
    }
}
