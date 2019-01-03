package org.firstinspires.ftc.teamcode.libraries;

/*
 * Title: Constants
 * Date Created: 10/14/2018
 * Date Modified: 1/1/2019
 * Author: Rahul, Poorvi, Varnika, Sarvesh, Sachin, Shivani
 * Type: Library
 * Description: This will contain all of the constants we will use in any of our programs.
 */

public class Constants {

    //********** Gamepad Tolerance Constants **********//
    static final float GAMEPAD_JOYSTICK_TOLERANCE = .05f;
    static final float GAMEPAD_TRIGGER_TOLERANCE = .05f;

    //********** DcMotor Indexes **********//
    static final int MOTOR_LEFT_WHEEL = 0;
    static final int MOTOR_RIGHT_WHEEL = 1;
    static final int MOTOR_LATCHER = 2;
    static final int MOTOR_LINEAR_SLIDE = 3;
    static final int MOTOR_SCORING = 5;

    //********** Servo Indexes **********//
    static final int SERVO_LATCHER = 0;
    static final int SERVO_INTAKE = 1;
    static final int SERVO_INTAKE_ANGLE = 2;
    static final int SERVO_DEPOSIT = 3;
    static final int SERVO_DEPOSIT_ANGLE = 4;

    //********** Touch Sensor Indexes **********//
    static final int TOUCH_LATCHER_TOP = 0;
    static final int TOUCH_LATCHER_BOTTOM = 1;
    static final int TOUCH_SCORING_TOP = 2;
    static final int TOUCH_SCORING_BOTTOM = 3;

    //********** Servo Positions **********//
    static final float SERVO_LATCHER_POS_GRAB = 0.6f;
    static final float SERVO_LATCHER_POS_REST = 0.05f;
    static final float SERVO_INTAKE_SPEED = .95f;
    static final float SERVO_OUTTAKE_SPEED = .05f;
    static final float SERVO_INTAKE_ANGLE_POS_INTAKE = .42f;
    static final float SERVO_INTAKE_ANGLE_POS_DEPOSIT = .42f;   // TODO: Measure later


    //********** Servo Deltas **********//
    static final float SERVO_INTAKE_ANGLE_DELTA = .01f;
    static final float SERVO_INTAKE_ANGLE_DELAY = .1f;    // Seconds

    //********** CalcMove Constants **********//
    static final float WHEEL_DIAMETER = 10.16f; // Centimeters
    static final float GOBILDA_MOTOR_ENCODER_COUNTS_PER_REVOLUTION = 1425.2f;
    static final float ENCODER_MARGIN = 50f;
    static final float TRACK_DISTANCE = 20.5f;

    //********** Encoder Constants **********//
    static final int LINEAR_SLIDE_DEPOT_ENCODER_COUNT_FORWARD = -10000;   // TODO: Measure later
    static final int LINEAR_SLIDE_DEPOT_ENCODER_COUNT_REVERSE = 10000;   // TODO: Measure later
    //********** Driving Multipliers **********//
    static final float LATCHING_DRIVE_FACTOR = 0.5f;

    //********** TensorFlow **********//
    static final String VUFORIA_KEY = "AXKAgZb/////AAABmUS4ua80h0IOpsYtCk/GLTBlnQxMgwAqHFWy/jk/+c1LzWYsECeqyVFo8lr8efZWyITbeMxf4IhhNmROQ3O13t8pqcHIOhDPfp82HmA54VHiYCt3n3xpcz/xi0Vs1mh9/xe0L1coIN1cL8EnkLHVkziy8jhQV5piuKMYeqxhY0tg0OekmPBb41Bw+LE8a76/dNJjAW/uCjefuWo0pBYETldIdqnyjeRP6hmfJbo9LbSOT70zmH4fFbMKJiYnz543nF9/SpxjkNnrgjijP7f9FV98Mdhgr7f6uyqMV37oFIQIhkCh5ro737KfXQyvWfPDedbEDFy3Z2duM9JzYJA8ybFKozEtjUeB82XQgaPeDNwS";
    static final float TENSOR_READING_TIME = 2f;   // Seconds

    public enum GoldObjectPosition {LEFT, CENTER, RIGHT}
}

