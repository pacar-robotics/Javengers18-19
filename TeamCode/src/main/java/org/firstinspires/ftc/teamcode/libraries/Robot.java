package org.firstinspires.ftc.teamcode.libraries;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import static org.firstinspires.ftc.teamcode.libraries.Constants.LATCHER;
import static org.firstinspires.ftc.teamcode.libraries.Constants.LATCHER_SERVO_REST;
import static org.firstinspires.ftc.teamcode.libraries.Constants.LEFT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.RIGHT_WHEEL;

/*
 * Title: Robot
 * Date Created: 10/14/2018
 * Date Modified: 11/11/2018
 * Author: Rahul, Poorvi, Varnika, Sarvesh, Sachin, Shivani
 * Type: Library
 * Description: This is the base library for any main op to be based off. It will contain all the
 *              motors, servos, and sensors.
 */

public class Robot {

    private LinearOpMode opMode;

    // Motors
    private DcMotor[] dcMotors = new DcMotor[3];

    // Servos
    private Servo latcherServo;

    // Sensors
    private Rev2mDistanceSensor groundSensor;
    private RevTouchSensor latcherTouchTop;
    private RevTouchSensor latcherTouchBottom;

    Robot(LinearOpMode opMode) {
        this.opMode = opMode;

        initDcMotors();
        initServos();
        initSensors();
    }

    private void initDcMotors() {
        dcMotors[LEFT_WHEEL] = opMode.hardwareMap.get(DcMotor.class, "leftWheel");
        dcMotors[RIGHT_WHEEL] = opMode.hardwareMap.get(DcMotor.class, "rightWheel");
        dcMotors[LATCHER] = opMode.hardwareMap.get(DcMotor.class, "latcher");

        dcMotors[RIGHT_WHEEL].setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void initServos() {
        latcherServo = opMode.hardwareMap.get(Servo.class, "latcherServo");
        latcherServo.setPosition(LATCHER_SERVO_REST);
    }

    private void initSensors() {
        groundSensor = opMode.hardwareMap.get(Rev2mDistanceSensor.class, "groundSensor");

        latcherTouchTop = opMode.hardwareMap.get(RevTouchSensor.class, "latcherTouchTop");
        latcherTouchBottom = opMode.hardwareMap.get(RevTouchSensor.class, "latcherTouchBottom");
    }

    // Motor methods
    void setDcMotorPower(int index, float power) {
        dcMotors[index].setPower(power);
    }

    void setDcMotorMode(int index, DcMotor.RunMode runMode) {
        dcMotors[index].setMode(runMode);
    }

    int getDcMotorPosition(int index) {
        return dcMotors[index].getCurrentPosition();
    }

    void setDcMotorTargetPosition(int index, int targetPosition) {
        dcMotors[index].setTargetPosition(targetPosition);
    }

    boolean isMotorBusy(int index) {
        return dcMotors[index].isBusy();
    }

    // Servo methods
    void setLatcherServoPosition(float position) {
        latcherServo.setPosition(position);
    }

    float getLatcherServoPosition() {
        return (float) latcherServo.getPosition();
    }

    // Sensor methods
    double getGroundDistanceCenti() {
        return (groundSensor.getDistance(DistanceUnit.METER) * 100);
    }

    boolean isLatcherTouchTopPressed() {
        return latcherTouchTop.isPressed();
    }

    boolean isLatcherTouchBottomPressed() {
        return latcherTouchBottom.isPressed();
    }
}
