package org.firstinspires.ftc.teamcode.libraries;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import static org.firstinspires.ftc.teamcode.libraries.Constants.LEFT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.RIGHT_WHEEL;

/*
 * Title: TeleLib
 * Date Created: 10/14/2018
 * Date Modified: 10/14/2018
 * Author: Rahul
 * Type: Library
 * Description: This will contain the methods for TeleOp, and other TeleOp-related programs.
 */

public class TeleLib {
    private Robot robot;
    private LinearOpMode opMode;

    public TeleLib(LinearOpMode opMode) {
        robot = new Robot(opMode);
        this.opMode = opMode;

        opMode.gamepad1.setJoystickDeadzone(.1f);
    }

    public void processGamepadDrive() {
        robot.setDcMotorPower(LEFT_WHEEL, -opMode.gamepad1.left_stick_y);
        robot.setDcMotorPower(RIGHT_WHEEL, -opMode.gamepad1.right_stick_y);
    }
}
