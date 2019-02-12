package org.firstinspires.ftc.teamcode.libraries;

/*
 * Title: Constants
 * Date Created: 10/14/2018
 * Date Modified: 2/12/2019
 * Author: Rahul, Poorvi, Varnika, Sarvesh, Sachin, Shivani
 * Type: Library
 * Description: This will contain all of the constants we will use in any of our programs.
 */

public class Constants {

    //********** Gamepad Tolerance Constants **********//
    static final float GAMEPAD_JOYSTICK_TOLERANCE = .05f;
    static final float GAMEPAD_TRIGGER_TOLERANCE = .05f;

    //********** DcMotor Indexes **********//
    static final int MOTOR_FRONT_LEFT_WHEEL = 0;
    static final int MOTOR_FRONT_RIGHT_WHEEL = 1;
    static final int MOTOR_BACK_LEFT_WHEEL = 2;
    static final int MOTOR_BACK_RIGHT_WHEEL = 3;
    static final int MOTOR_LATCHER = 4;
    static final int MOTOR_SCORING_SLIDE = 5;
    static final int MOTOR_INTAKE_SLIDE = 6;

    //********** Servo Indexes **********//
    static final int SERVO_LATCHER = 0;

    //********** Servo Positions **********//
    static final float SERVO_LATCHER_POS_LATCHED = 1;
    static final float SERVO_LATCHER_POS_REST = .71f;

    //********** Touch Sensor Indexes **********//
    static final int TOUCH_LATCHER_TOP = 0;
    static final int TOUCH_LATCHER_BOTTOM = 1;
    static final int TOUCH_SCORING_TOP = 2;
    static final int TOUCH_SCORING_BOTTOM = 3;
    static final int TOUCH_INTAKE_SLIDE_TOP = 4;
    static final int TOUCH_INTAKE_SLIDE_BOTTOM = 5;

    //********** Servo Deltas **********//
    // Used for slowly moving a servo like a motor
    static final float SERVO_INTAKE_ANGLE_DELTA = .01f;
    static final float SERVO_INTAKE_ANGLE_DELAY = .1f;    // Seconds

    //********** CalcMove Constants **********//
    static final float WHEEL_DIAMETER = 10.16f; // Centimeters
    static final float WHEEL_GEAR_RATIO = (2f / 3);
    static final float NEVEREST_40_REVOLUTION_ENCODER_COUNT = 1120f;
    static final float TRACK_DISTANCE = 23f;

    public enum Direction {FORWARD, BACKWARD, LEFT, RIGHT}

    //********** Encoder Constants **********//

    //********** Driving Multipliers **********//

    //********** TensorFlow **********//
    // Vuforia Key compatible with external camera
    static final String VUFORIA_KEY = "AXKAgZb/////AAABmUS4ua80h0IOpsYtCk/GLTBlnQxMgwAqHFWy/jk/+c1LzWYsECeqyVFo8lr8efZWyITbeMxf4IhhNmROQ3O13t8pqcHIOhDPfp82HmA54VHiYCt3n3xpcz/xi0Vs1mh9/xe0L1coIN1cL8EnkLHVkziy8jhQV5piuKMYeqxhY0tg0OekmPBb41Bw+LE8a76/dNJjAW/uCjefuWo0pBYETldIdqnyjeRP6hmfJbo9LbSOT70zmH4fFbMKJiYnz543nF9/SpxjkNnrgjijP7f9FV98Mdhgr7f6uyqMV37oFIQIhkCh5ro737KfXQyvWfPDedbEDFy3Z2duM9JzYJA8ybFKozEtjUeB82XQgaPeDNwS";
    static final float TENSOR_READING_TIME = 2f;

    public enum GoldObjectPosition {LEFT, CENTER, RIGHT}
}