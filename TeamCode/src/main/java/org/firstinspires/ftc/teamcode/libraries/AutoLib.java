package org.firstinspires.ftc.teamcode.libraries;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;
import static org.firstinspires.ftc.teamcode.libraries.Constants.ENCODER_MARGIN;
import static org.firstinspires.ftc.teamcode.libraries.Constants.GOBILDA_MOTOR_ENCODER_COUNTS_PER_REVOLUTION;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_LATCHER;
import static org.firstinspires.ftc.teamcode.libraries.Constants.LATCHER_SERVO_REST;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_LEFT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_RIGHT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TRACK_DISTANCE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.WHEEL_DIAMETER;

/*
 * Title: AutoLib
 * Date Created: 10/28/2018
 * Date Modified: 11/24/2018
 * Author: Rahul, Poorvi, Varnika
 * Type: Library
 * Description: This will contain the methods for Autonomous, and other autonomous-related programs.
 */

public class AutoLib {
    private Robot robot;
    private LinearOpMode opMode;

    public AutoLib(LinearOpMode opMode) {
        robot = new Robot(opMode);
        this.opMode = opMode;
    }

    public void calcMove(float centimeters, float power) {
        // Calculate how many encoder counts to move
        final int targetPosition = (int) ((centimeters / (Math.PI * WHEEL_DIAMETER)) *
                GOBILDA_MOTOR_ENCODER_COUNTS_PER_REVOLUTION);

        prepMotorsForCalcMove(targetPosition, targetPosition);

        robot.setDcMotorPower(MOTOR_LEFT_WHEEL, power);
        robot.setDcMotorPower(MOTOR_RIGHT_WHEEL, power);

        // Stays in this while loop until the motors are done moving to their positionp
        while (areBaseMotorsBusy() &&
                (Math.abs(targetPosition - robot.getDcMotorPosition(MOTOR_LEFT_WHEEL)) >= ENCODER_MARGIN)) {
            opMode.idle();
        }

        robot.setDcMotorPower(MOTOR_LEFT_WHEEL, 0);
        robot.setDcMotorPower(MOTOR_RIGHT_WHEEL, 0);
    }

    public void calcTurn(int degrees, float power) {
        int leftTargetPosition = (int) (2 * ((TRACK_DISTANCE) * degrees
                * GOBILDA_MOTOR_ENCODER_COUNTS_PER_REVOLUTION) /
                (WHEEL_DIAMETER * 360));
        int rightTargetPosition = -leftTargetPosition;

        float leftPower;
        float rightPower;

        // Will switch powers if turning the other way
        if (degrees < 0) {
            leftPower = power;
            rightPower = -power;
        } else {
            leftPower = -power;
            rightPower = power;
        }

        prepMotorsForCalcMove(leftTargetPosition, rightTargetPosition);

        robot.setDcMotorPower(MOTOR_LEFT_WHEEL, leftPower);
        robot.setDcMotorPower(MOTOR_RIGHT_WHEEL, rightPower);

        // Stays in this while loop until the motors are done moving to their position
        while (areBaseMotorsBusy() &&
                (Math.abs(leftTargetPosition - robot.getDcMotorPosition(MOTOR_LEFT_WHEEL)) >= ENCODER_MARGIN)) {
            opMode.idle();
        }

        robot.setDcMotorPower(MOTOR_LEFT_WHEEL, 0);
        robot.setDcMotorPower(MOTOR_RIGHT_WHEEL, 0);
    }

    private void prepMotorsForCalcMove(int leftTargetPosition, int rightTargetPosition) {
        robot.setDcMotorMode(MOTOR_LEFT_WHEEL, STOP_AND_RESET_ENCODER);
        robot.setDcMotorMode(MOTOR_RIGHT_WHEEL, STOP_AND_RESET_ENCODER);

        robot.setDcMotorMode(MOTOR_LEFT_WHEEL, RUN_TO_POSITION);
        robot.setDcMotorMode(MOTOR_RIGHT_WHEEL, RUN_TO_POSITION);

        robot.setDcMotorTargetPosition(MOTOR_LEFT_WHEEL, leftTargetPosition);
        robot.setDcMotorTargetPosition(MOTOR_RIGHT_WHEEL, rightTargetPosition);
    }

    private boolean areBaseMotorsBusy() {
        return robot.isMotorBusy(MOTOR_LEFT_WHEEL) || robot.isMotorBusy(MOTOR_RIGHT_WHEEL);
    }

    public void landOnGround() throws InterruptedException {
        robot.setDcMotorPower(MOTOR_LATCHER, 0.5f);
        // The motor will stop when it detects that it's on the ground
        while (robot.getGroundDistanceCenti() >= 5.2) {
            opMode.telemetry.addData("groundSensor", robot.getGroundDistanceCenti());
            opMode.telemetry.update();
            opMode.idle();
        }

        // Waiting for the latcher to raise enough so it can unlatch
        Thread.sleep(1000);
        robot.setDcMotorPower(MOTOR_LATCHER, 0f);

        robot.setLatcherServoPosition(LATCHER_SERVO_REST);
    }


    // SupportOp Methods
    public void moveLatcherToBottom() {
        robot.setDcMotorPower(MOTOR_LATCHER, -.2f);
        while (!robot.isLatcherTouchBottomPressed()) {
            opMode.idle();
        }
        robot.setDcMotorPower(MOTOR_LATCHER, 0);
    }
}
