package org.firstinspires.ftc.teamcode.libraries;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.libraries.Constants.GAMEPAD_JOYSTICK_TOLERANCE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.GAMEPAD_TRIGGER_TOLERANCE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.INTAKE_SPEED;
import static org.firstinspires.ftc.teamcode.libraries.Constants.LATCHING_DRIVE_FACTOR;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_INTAKE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_LATCHER;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_LEFT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_LINEAR_SLIDE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_RIGHT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_SCORING;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_INTAKE_DELAY;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_INTAKE_DELTA;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_INTAKE_X;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_INTAKE_Y;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_LATCHER;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_LATCHER_POS_GRAB;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_LATCHER_POS_REST;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TOUCH_LATCHER_BOTTOM;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TOUCH_LATCHER_TOP;

/*
 * Title: TeleLib
 * Date Created: 10/14/2018
 * Date Modified: 11/24/2018
 * Author: Rahul, Sarvesh, Sachin, Shivani
 * Type: Library
 * Description: This will contain the methods for TeleOp, and other TeleOp-related programs.
 */

public class TeleLib {
    private Robot robot;
    private LinearOpMode opMode;

    private ElapsedTime elapsedTime;

    private boolean isLatchingDrive;

    public TeleLib(LinearOpMode opMode) {
        robot = new Robot(opMode);
        this.opMode = opMode;

        elapsedTime = new ElapsedTime();

        opMode.gamepad1.setJoystickDeadzone(GAMEPAD_JOYSTICK_TOLERANCE);
        opMode.gamepad2.setJoystickDeadzone(GAMEPAD_JOYSTICK_TOLERANCE);

        isLatchingDrive = false;
    }

    // Uses joysticks on gamepad 1 for tank drive
    public void processDrive() {
        if (isLatchingDrive) {
            latchingDrive();
        } else {
            defaultDrive();
        }

        changeDrive();
    }

    // Used for when intake is front
    private void defaultDrive() {
        // Values need to be reversed (up on joystick is -1)
        robot.setDcMotorPower(MOTOR_LEFT_WHEEL, -opMode.gamepad1.left_stick_y);
        robot.setDcMotorPower(MOTOR_RIGHT_WHEEL, -opMode.gamepad1.right_stick_y);
    }

    // Used for when latcher is front
    private void latchingDrive() {
        robot.setDcMotorPower(MOTOR_LEFT_WHEEL, opMode.gamepad1.right_stick_y * LATCHING_DRIVE_FACTOR);
        robot.setDcMotorPower(MOTOR_RIGHT_WHEEL, opMode.gamepad1.left_stick_y * LATCHING_DRIVE_FACTOR);
    }

    // Changes different drives
    private void changeDrive() {
        if (opMode.gamepad1.a) {
            isLatchingDrive = false;
        } else if (opMode.gamepad1.b) {
            isLatchingDrive = true;
        }
    }

    // Uses both triggers on gamepad 1
    public void processLatcher() {
        if (opMode.gamepad1.right_trigger > GAMEPAD_TRIGGER_TOLERANCE &&
                !robot.isTouchSensorPressed(TOUCH_LATCHER_TOP)) {
            // Moves latcher up
            robot.setDcMotorPower(MOTOR_LATCHER, opMode.gamepad1.right_trigger);
        } else if (opMode.gamepad1.left_trigger > GAMEPAD_TRIGGER_TOLERANCE &&
                !robot.isTouchSensorPressed(TOUCH_LATCHER_BOTTOM)) {
            // Moves latcher down
            robot.setDcMotorPower(MOTOR_LATCHER, -opMode.gamepad1.left_trigger);
        } else {
            // Stops latcher movement
            robot.setDcMotorPower(MOTOR_LATCHER, 0);
        }
    }

    // Uses both bumpers on gamepad 1
    public void processLatcherServo() {
        if (opMode.gamepad1.left_bumper) {
            robot.setServoPosition(SERVO_LATCHER, SERVO_LATCHER_POS_GRAB);
        } else if (opMode.gamepad1.right_bumper) {
            robot.setServoPosition(SERVO_LATCHER, SERVO_LATCHER_POS_REST);
        }
    }

    // Uses bumpers on gamepad 2
    public void processIntake() {
        if (opMode.gamepad2.y) {
            // Deposit
            robot.setDcMotorPower(MOTOR_INTAKE, INTAKE_SPEED);
        } else if (opMode.gamepad2.right_bumper) {
            // Collect
            robot.setDcMotorPower(MOTOR_INTAKE, -INTAKE_SPEED);
        } else if (opMode.gamepad2.left_bumper) {
            robot.setDcMotorPower(MOTOR_INTAKE, 0);
        }
    }

    // Uses triggers on gamepad 2
    public void processLinearSlide() {
        if (opMode.gamepad2.left_trigger > GAMEPAD_TRIGGER_TOLERANCE) {
            robot.setDcMotorPower(MOTOR_LINEAR_SLIDE, opMode.gamepad2.left_trigger);
        } else if (opMode.gamepad2.right_trigger > GAMEPAD_TRIGGER_TOLERANCE) {
            robot.setDcMotorPower(MOTOR_LINEAR_SLIDE, -opMode.gamepad2.right_trigger);
        } else {
            robot.setDcMotorPower(MOTOR_LINEAR_SLIDE, 0);
        }
    }

    // Uses dpad on gamepad 2
    public void processIntakePosition() {
        intakeXPosition();
        intakeYPosition();

        opMode.telemetry.addData("IntakeX", robot.getServoPosition(SERVO_INTAKE_X));
        opMode.telemetry.addData("IntakeY", robot.getServoPosition(SERVO_INTAKE_Y));
        opMode.telemetry.update();
    }

    private void intakeXPosition() {
        if ((opMode.gamepad2.dpad_up || opMode.gamepad2.dpad_down) &&
                (elapsedTime.seconds() > SERVO_INTAKE_DELAY)) {
            if (opMode.gamepad2.dpad_up) {
                robot.setDeltaServoPosition(SERVO_INTAKE_X, SERVO_INTAKE_DELTA);
            } else {
                robot.setDeltaServoPosition(SERVO_INTAKE_X, -SERVO_INTAKE_DELTA);
            }
            elapsedTime.reset();
        }
    }

    private void intakeYPosition() {
        if ((opMode.gamepad2.dpad_left || opMode.gamepad2.dpad_right) &&
                (elapsedTime.seconds() > SERVO_INTAKE_DELAY)) {
            if (opMode.gamepad2.dpad_left) {
                robot.setDeltaServoPosition(SERVO_INTAKE_Y, SERVO_INTAKE_DELTA);
            } else {
                robot.setDeltaServoPosition(SERVO_INTAKE_Y, -SERVO_INTAKE_DELTA);
            }
            elapsedTime.reset();
        }
    }

    // Uses right joystick on gamepad 2
    public void processScoring() {
        robot.setDcMotorPower(MOTOR_SCORING, opMode.gamepad2.right_stick_y);
    }
}
