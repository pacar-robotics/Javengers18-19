package org.firstinspires.ftc.teamcode.libraries;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_LATCHER;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_LEFT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_LINEAR_SLIDE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_RIGHT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_SCORING;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_INTAKE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_INTAKE_ANGLE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_INTAKE_ANGLE_POS_INTAKE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_LATCHER;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_LATCHER_POS_GRAB;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TOUCH_LATCHER_BOTTOM;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TOUCH_LATCHER_TOP;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TOUCH_SLIDE_BOTTOM;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TOUCH_SLIDE_TOP;

/*
 * Title: Robot
 * Date Created: 10/14/2018
 * Date Modified: 11/27/2018
 * Author: Rahul, Poorvi, Varnika, Sarvesh, Sachin, Shivani
 * Type: Library
 * Description: This is the base library for any main op to be based off. It will contain all the
 *              motors, servos, and sensors.
 */

public class Robot {

    private LinearOpMode opMode;

    // Motors
    private DcMotor[] dcMotors = new DcMotor[6];

    // Servos
    private Servo[] servos = new Servo[3];

    // Sensors
    private Rev2mDistanceSensor groundSensor;
    private RevTouchSensor[] touchSensors = new RevTouchSensor[4];

    Robot(LinearOpMode opMode) {
        this.opMode = opMode;

        initDcMotors();
        initServos();
        initSensors();
    }

    private void initDcMotors() {
        dcMotors[MOTOR_LEFT_WHEEL] = opMode.hardwareMap.get(DcMotor.class, "leftWheel");
        dcMotors[MOTOR_RIGHT_WHEEL] = opMode.hardwareMap.get(DcMotor.class, "rightWheel");
        dcMotors[MOTOR_LATCHER] = opMode.hardwareMap.get(DcMotor.class, "latcher");
        dcMotors[MOTOR_LINEAR_SLIDE] = opMode.hardwareMap.get(DcMotor.class, "linearSlide");
        dcMotors[MOTOR_SCORING] = opMode.hardwareMap.get(DcMotor.class, "scoring");

        dcMotors[MOTOR_LEFT_WHEEL].setDirection(DcMotorSimple.Direction.REVERSE);
        dcMotors[MOTOR_LATCHER].setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void initServos() {
        servos[SERVO_LATCHER] = opMode.hardwareMap.get(Servo.class, "latcherServo");
        servos[SERVO_INTAKE] = opMode.hardwareMap.get(Servo.class, "intakeServo");
        servos[SERVO_INTAKE_ANGLE] = opMode.hardwareMap.get(Servo.class, "intakeServoAngle");

        servos[SERVO_LATCHER].setPosition(SERVO_LATCHER_POS_GRAB);
        servos[SERVO_INTAKE_ANGLE].setPosition(SERVO_INTAKE_ANGLE_POS_INTAKE);
    }

    private void initSensors() {
        groundSensor = opMode.hardwareMap.get(Rev2mDistanceSensor.class, "groundSensor");

        touchSensors[TOUCH_LATCHER_TOP] = opMode.hardwareMap.get(RevTouchSensor.class, "latcherTouchTop");
        touchSensors[TOUCH_LATCHER_BOTTOM] = opMode.hardwareMap.get(RevTouchSensor.class, "latcherTouchBottom");
        touchSensors[TOUCH_SLIDE_TOP] = opMode.hardwareMap.get(RevTouchSensor.class, "slideTouchTop");
        touchSensors[TOUCH_SLIDE_BOTTOM] = opMode.hardwareMap.get(RevTouchSensor.class, "slideTouchBottom");
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
    void setServoPosition(int index, float position) {
        servos[index].setPosition(position);
    }

    void setDeltaServoPosition(int index, float delta) {
        servos[index].setPosition(
                Range.clip(servos[index].getPosition() + delta, 0, 1));
    }

    float getServoPosition(int index) {
        return (float) servos[index].getPosition();
    }

    // Sensor methods
    double getGroundDistanceCenti() {
        return (groundSensor.getDistance(DistanceUnit.METER) * 100);
    }

    boolean isTouchSensorPressed(int index) {
        return touchSensors[index].isPressed();
    }
}
