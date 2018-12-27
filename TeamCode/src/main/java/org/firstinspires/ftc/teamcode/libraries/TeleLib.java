package org.firstinspires.ftc.teamcode.libraries;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.libraries.Constants.GAMEPAD_JOYSTICK_TOLERANCE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.GAMEPAD_TRIGGER_TOLERANCE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_BACK_LEFT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_BACK_RIGHT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_FRONT_LEFT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_FRONT_RIGHT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_LATCHER;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_LINEAR_SLIDE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_SCORING;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_INTAKE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_INTAKE_ANGLE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_INTAKE_ANGLE_DELAY;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_INTAKE_ANGLE_DELTA;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_INTAKE_ANGLE_POS_DEPOSIT;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_INTAKE_ANGLE_POS_INTAKE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_INTAKE_SPEED;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_LATCHER;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_LATCHER_POS_GRAB;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_LATCHER_POS_REST;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_OUTTAKE_SPEED;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TOUCH_LATCHER_BOTTOM;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TOUCH_LATCHER_TOP;

/*
 * Title: TeleLib
 * Date Created: 10/14/2018
 * Date Modified: 12/26/2018
 * Author: Rahul, Sarvesh, Sachin, Shivani
 * Type: Library
 * Description: This will contain the methods for TeleOp, and other TeleOp-related programs.
 */

public class TeleLib {
    private Robot robot;
    private LinearOpMode opMode;

    private ElapsedTime elapsedTime;

    public TeleLib(LinearOpMode opMode) {
        robot = new Robot(opMode);
        this.opMode = opMode;

        elapsedTime = new ElapsedTime();

        opMode.gamepad1.setJoystickDeadzone(GAMEPAD_JOYSTICK_TOLERANCE);
        opMode.gamepad2.setJoystickDeadzone(GAMEPAD_JOYSTICK_TOLERANCE);
    }

    // Uses joysticks on gamepad 1 for tank drive
    public void processDrive() {
        // https://ftcforum.usfirst.org/forum/ftc-technology/android-studio/6361-mecanum-wheels-drive-code-example
        float r = (float) Math.hypot(opMode.gamepad1.left_stick_x, -opMode.gamepad1.left_stick_y);
        float robotAngle = (float) (Math.atan2(-opMode.gamepad1.left_stick_y, opMode.gamepad1.left_stick_x) - Math.PI / 4);
        float rightX = opMode.gamepad1.right_stick_x;

        robot.setDcMotorPower(MOTOR_FRONT_LEFT_WHEEL, (float) (r * Math.cos(robotAngle) + rightX));
        robot.setDcMotorPower(MOTOR_FRONT_RIGHT_WHEEL, (float) (r * Math.sin(robotAngle) - rightX));
        robot.setDcMotorPower(MOTOR_BACK_LEFT_WHEEL, (float) (r * Math.sin(robotAngle) + rightX));
        robot.setDcMotorPower(MOTOR_BACK_RIGHT_WHEEL, (float) (r * Math.cos(robotAngle) - rightX));
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
            robot.setServoPosition(SERVO_INTAKE, SERVO_OUTTAKE_SPEED);
        } else if (opMode.gamepad2.right_bumper) {
            // Collect
            robot.setServoPosition(SERVO_INTAKE, SERVO_INTAKE_SPEED);
        } else if (opMode.gamepad2.left_bumper) {
            robot.setServoPosition(SERVO_INTAKE, .5f);
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

    public void processAutomaticIntakePosition() {
        if (opMode.gamepad2.x) {
            robot.setServoPosition(SERVO_INTAKE_ANGLE, SERVO_INTAKE_ANGLE_POS_INTAKE);
        } else if (opMode.gamepad2.b) {
            robot.setServoPosition(SERVO_INTAKE_ANGLE, SERVO_INTAKE_ANGLE_POS_DEPOSIT);
        }
    }

    // Uses dpad on gamepad 2
    public void processManualIntakePosition() {
        if ((opMode.gamepad2.dpad_up || opMode.gamepad2.dpad_down) &&
                (elapsedTime.seconds() > SERVO_INTAKE_ANGLE_DELAY)) {
            if (opMode.gamepad2.dpad_down) {
                robot.setDeltaServoPosition(SERVO_INTAKE_ANGLE, SERVO_INTAKE_ANGLE_DELTA);
            } else {
                robot.setDeltaServoPosition(SERVO_INTAKE_ANGLE, -SERVO_INTAKE_ANGLE_DELTA);
            }
            elapsedTime.reset();
        }
    }

    // Uses right joystick on gamepad 2
    public void processScoring() {
        robot.setDcMotorPower(MOTOR_SCORING, opMode.gamepad2.right_stick_y);
    }
}
