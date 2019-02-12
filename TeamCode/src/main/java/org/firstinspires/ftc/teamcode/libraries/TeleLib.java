package org.firstinspires.ftc.teamcode.libraries;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import static org.firstinspires.ftc.teamcode.libraries.Constants.GAMEPAD_JOYSTICK_TOLERANCE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.GAMEPAD_TRIGGER_TOLERANCE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_BACK_LEFT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_BACK_RIGHT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_FRONT_LEFT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_FRONT_RIGHT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_LATCHER;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TOUCH_LATCHER_BOTTOM;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TOUCH_LATCHER_TOP;

/*
 * Title: TeleLib
 * Date Created: 10/14/2018
 * Date Modified: 2/12/2019
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

    // Uses joysticks on gamepad 1 for tank drive
    public void processDrive() {
        // https://ftcforum.usfirst.org/forum/ftc-technology/android-studio/6361-mecanum-wheels-drive-code-example
        float r = (float) Math.hypot(opMode.gamepad1.left_stick_x, -opMode.gamepad1.left_stick_y);
        float robotAngle = (float) (Math.atan2(-opMode.gamepad1.left_stick_y, -opMode.gamepad1.left_stick_x) - Math.PI / 4);
        float rightX = opMode.gamepad1.right_stick_x;

        robot.setDcMotorPower(MOTOR_FRONT_LEFT_WHEEL, (float) (r * Math.cos(robotAngle) + rightX));
        robot.setDcMotorPower(MOTOR_FRONT_RIGHT_WHEEL, (float) (r * Math.sin(robotAngle) - rightX));
        robot.setDcMotorPower(MOTOR_BACK_LEFT_WHEEL, (float) (r * Math.sin(robotAngle) + rightX));
        robot.setDcMotorPower(MOTOR_BACK_RIGHT_WHEEL, (float) (r * Math.cos(robotAngle) - rightX));
    }

    public void processLatcher() {
        if (opMode.gamepad1.right_bumper && !robot.isTouchSensorPressed(TOUCH_LATCHER_TOP)) {
            // Extend
            robot.setDcMotorPower(MOTOR_LATCHER, .6f);
        } else if (opMode.gamepad1.left_bumper && !robot.isTouchSensorPressed(TOUCH_LATCHER_BOTTOM)) {
            // Retract
            robot.setDcMotorPower(MOTOR_LATCHER, -.6f);
        } else {
            robot.setDcMotorPower(MOTOR_LATCHER, 0);
        }
    }
}