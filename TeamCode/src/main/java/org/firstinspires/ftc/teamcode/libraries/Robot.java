package org.firstinspires.ftc.teamcode.libraries;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_BACK_LEFT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_BACK_RIGHT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_FRONT_LEFT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_FRONT_RIGHT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_INTAKE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_INTAKE_SLIDE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_LATCHER;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_SCORING_SLIDE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_INTAKE_ANGLE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_LATCHER;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TOUCH_INTAKE_SLIDE_BOTTOM;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TOUCH_INTAKE_SLIDE_TOP;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TOUCH_LATCHER_BOTTOM;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TOUCH_LATCHER_TOP;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TOUCH_SCORING_BOTTOM;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TOUCH_SCORING_TOP;

/*
 * Title: Robot
 * Date Created: 10/14/2018
 * Date Modified: 2/12/2019
 * Author: Rahul, Poorvi, Varnika, Sarvesh, Sachin, Shivani
 * Type: Library
 * Description: This is the base library for any main op to be based off. It will contain all the
 *              motors, servos, and sensors.
 */

public class Robot {

    private LinearOpMode opMode;

    // Motors
    private DcMotor[] dcMotors = new DcMotor[8];

    // Servos
    private Servo[] servos = new Servo[2];

    // Sensors
    private RevTouchSensor[] touchSensors = new RevTouchSensor[6];

    Robot(LinearOpMode opMode) {
        this.opMode = opMode;

        initDcMotors();
        initServos();
        initSensors();
    }

    private void initDcMotors() {
        //Naming our motors
        dcMotors[MOTOR_FRONT_LEFT_WHEEL] = opMode.hardwareMap.get(DcMotor.class, "frontLeftWheel");
        dcMotors[MOTOR_FRONT_RIGHT_WHEEL] = opMode.hardwareMap.get(DcMotor.class, "frontRightWheel");
        dcMotors[MOTOR_BACK_LEFT_WHEEL] = opMode.hardwareMap.get(DcMotor.class, "backLeftWheel");
        dcMotors[MOTOR_BACK_RIGHT_WHEEL] = opMode.hardwareMap.get(DcMotor.class, "backRightWheel");
        dcMotors[MOTOR_LATCHER] = opMode.hardwareMap.get(DcMotor.class, "latcher");
        dcMotors[MOTOR_SCORING_SLIDE] = opMode.hardwareMap.get(DcMotor.class, "scoring");
        dcMotors[MOTOR_INTAKE_SLIDE] = opMode.hardwareMap.get(DcMotor.class, "intakeSlide");
        dcMotors[MOTOR_INTAKE] = opMode.hardwareMap.get(DcMotor.class, "intakeRollers");

        dcMotors[MOTOR_FRONT_RIGHT_WHEEL].setDirection(DcMotorSimple.Direction.REVERSE);
        dcMotors[MOTOR_BACK_RIGHT_WHEEL].setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void initServos() {
        servos[SERVO_LATCHER] = opMode.hardwareMap.get(Servo.class, "servoLatcher");
        servos[SERVO_INTAKE_ANGLE] = opMode.hardwareMap.get(Servo.class, "servoIntakeAngle");
    }

    private void initSensors() {
        touchSensors[TOUCH_LATCHER_TOP] = opMode.hardwareMap.get(RevTouchSensor.class, "touchLatcherTop");
        touchSensors[TOUCH_LATCHER_BOTTOM] = opMode.hardwareMap.get(RevTouchSensor.class, "touchLatcherBottom");
        touchSensors[TOUCH_SCORING_TOP] = opMode.hardwareMap.get(RevTouchSensor.class, "touchScoringTop");
        touchSensors[TOUCH_SCORING_BOTTOM] = opMode.hardwareMap.get(RevTouchSensor.class, "touchScoringBottom");
        touchSensors[TOUCH_INTAKE_SLIDE_TOP] = opMode.hardwareMap.get(RevTouchSensor.class, "touchSlideTop");
        touchSensors[TOUCH_INTAKE_SLIDE_BOTTOM] = opMode.hardwareMap.get(RevTouchSensor.class, "touchSlideBottom");
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
                // This makes sure the servo positions are between 0 and 1
                Range.clip(servos[index].getPosition() + delta, 0, 1));
    }

    float getServoPosition(int index) {
        return (float) servos[index].getPosition();
    }

    boolean isTouchSensorPressed(int index) {
        return touchSensors[index].isPressed();
    }
}