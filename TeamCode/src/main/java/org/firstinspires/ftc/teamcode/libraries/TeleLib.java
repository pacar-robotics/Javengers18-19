package org.firstinspires.ftc.teamcode.libraries;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import static org.firstinspires.ftc.teamcode.libraries.Constants.GAMEPAD_JOYSTICK_TOLERANCE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.GAMEPAD_TRIGGER_TOLERANCE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.LATCHER;
import static org.firstinspires.ftc.teamcode.libraries.Constants.LATCHER_SERVO_GRAB;
import static org.firstinspires.ftc.teamcode.libraries.Constants.LATCHER_SERVO_REST;
import static org.firstinspires.ftc.teamcode.libraries.Constants.LATCHING_DRIVE_FACTOR;
import static org.firstinspires.ftc.teamcode.libraries.Constants.LEFT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.RIGHT_WHEEL;

/*
 * Title: TeleLib
 * Date Created: 10/14/2018
 * Date Modified: 11/18/2018
 * Author: Rahul, Sarvesh, Sachin, Shivani
 * Type: Library
 * Description: This will contain the methods for TeleOp, and other TeleOp-related programs.
 */

public class TeleLib {
    private Robot robot;
    private LinearOpMode opMode;

    public TeleLib(LinearOpMode opMode) {
        robot = new Robot(opMode);
        this.opMode = opMode;

        opMode.gamepad1.setJoystickDeadzone(GAMEPAD_JOYSTICK_TOLERANCE);
        opMode.gamepad2.setJoystickDeadzone(GAMEPAD_JOYSTICK_TOLERANCE);
    }

    // Uses both joysticks to control the wheels (tank drive)
    public void processGamepadDrive() {
        // Values need to be reversed (up on joystick is -1)
        robot.setDcMotorPower(LEFT_WHEEL, -opMode.gamepad1.left_stick_y);
        robot.setDcMotorPower(RIGHT_WHEEL, -opMode.gamepad1.right_stick_y);
    }

    // Uses both triggers on controllers to control the latcher
    public void processLatcher() {
        if (opMode.gamepad2.left_trigger > GAMEPAD_TRIGGER_TOLERANCE &&
                !robot.isLatcherTouchTopPressed()) {
            // Moves latcher up
            robot.setDcMotorPower(LATCHER, opMode.gamepad2.left_trigger);
        } else if (opMode.gamepad2.right_trigger > GAMEPAD_TRIGGER_TOLERANCE &&
                !robot.isLatcherTouchBottomPressed()) {
            // Moves latcher down
            robot.setDcMotorPower(LATCHER, -opMode.gamepad2.right_trigger);
        } else {
            // Stops latcher movement
            robot.setDcMotorPower(LATCHER, 0);
        }
    }

    public void processLatcherServo() {
        if (opMode.gamepad2.left_bumper) {
            robot.setLatcherServoPosition(LATCHER_SERVO_GRAB);
        } else if (opMode.gamepad2.right_bumper) {
            robot.setLatcherServoPosition(LATCHER_SERVO_REST);
        }
    }

    public void processLatchingDrive() {
        robot.setDcMotorPower(LEFT_WHEEL, opMode.gamepad2.right_stick_y * LATCHING_DRIVE_FACTOR);
        robot.setDcMotorPower(RIGHT_WHEEL, opMode.gamepad2.left_stick_y * LATCHING_DRIVE_FACTOR);
    }
}
