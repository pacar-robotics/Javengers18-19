package org.firstinspires.ftc.teamcode.libraries;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;
import static org.firstinspires.ftc.teamcode.libraries.Constants.ENCODER_MARGIN;
import static org.firstinspires.ftc.teamcode.libraries.Constants.GOBILDA_MOTOR_ENCODER_COUNTS_PER_REVOLUTION;
import static org.firstinspires.ftc.teamcode.libraries.Constants.LATCHER;
import static org.firstinspires.ftc.teamcode.libraries.Constants.LATCHER_SERVO_REST;
import static org.firstinspires.ftc.teamcode.libraries.Constants.LEFT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.RIGHT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TRACK_DISTANCE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.WHEEL_DIAMETER;

/*
 * Title: AutoLib
 * Date Created: 10/28/2018
 * Date Modified: 11/4/2018
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

        robot.setDcMotorPower(LEFT_WHEEL, power);
        robot.setDcMotorPower(RIGHT_WHEEL, power);

        // Stays in this while loop until the motors are done moving to their positionp
        while (areBaseMotorsBusy() &&
                (Math.abs(targetPosition - robot.getDcMotorPosition(LEFT_WHEEL)) >= ENCODER_MARGIN)) {
            opMode.idle();
        }

        robot.setDcMotorPower(LEFT_WHEEL, 0);
        robot.setDcMotorPower(RIGHT_WHEEL, 0);
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

        robot.setDcMotorPower(LEFT_WHEEL, leftPower);
        robot.setDcMotorPower(RIGHT_WHEEL, rightPower);

        // Stays in this while loop until the motors are done moving to their position
        while (areBaseMotorsBusy() &&
                (Math.abs(leftTargetPosition - robot.getDcMotorPosition(LEFT_WHEEL)) >= ENCODER_MARGIN)) {
            opMode.idle();
        }

        robot.setDcMotorPower(LEFT_WHEEL, 0);
        robot.setDcMotorPower(RIGHT_WHEEL, 0);
    }

    private void prepMotorsForCalcMove(int leftTargetPosition, int rightTargetPosition) {
        robot.setDcMotorMode(LEFT_WHEEL, STOP_AND_RESET_ENCODER);
        robot.setDcMotorMode(RIGHT_WHEEL, STOP_AND_RESET_ENCODER);

        robot.setDcMotorMode(LEFT_WHEEL, RUN_TO_POSITION);
        robot.setDcMotorMode(RIGHT_WHEEL, RUN_TO_POSITION);

        robot.setDcMotorTargetPosition(LEFT_WHEEL, leftTargetPosition);
        robot.setDcMotorTargetPosition(RIGHT_WHEEL, rightTargetPosition);
    }

    private boolean areBaseMotorsBusy() {
        return robot.isMotorBusy(LEFT_WHEEL) || robot.isMotorBusy(RIGHT_WHEEL);
    }

    public void landOnGround() {
        robot.setDcMotorPower(LATCHER, 0.5f);
        // The motor will stop when it detects that it's on the ground
        while (robot.getGroundDistanceCenti() >= 5) {
            opMode.telemetry.addData("groundSensor", robot.getGroundDistanceCenti());
            opMode.telemetry.update();
            opMode.idle();
        }
        robot.setDcMotorPower(LATCHER, 0f);

        robot.setLatcherServoPosition(LATCHER_SERVO_REST);


    }
}
