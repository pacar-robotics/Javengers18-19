package org.firstinspires.ftc.teamcode.testops;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/*
 * Title: Latcher
 * Date Created: 11/11/2018
 * Date Modified: 11/11/2018
 * Author: Rahul
 * Type: TestOp
 * Description: Tests the latcher motor and servo
 */

@TeleOp(group = "TestOps")
public class TestLatcher extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        DcMotor latcher = hardwareMap.get(DcMotor.class, "latcher");
        Servo latcherServo = hardwareMap.get(Servo.class, "latcherServo");

        telemetry.addData("Status", "Ready");
        telemetry.addData("Controls", "Triggers to move motor, a to toggle latcher");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.left_trigger > .05f) {
                // Moves latcher up
                latcher.setPower(gamepad1.left_trigger);
            } else if (gamepad1.right_trigger > .05f) {
                // Moves latcher down
                latcher.setPower(-gamepad1.right_trigger);
            } else {
                // Stops latcher movement
                latcher.setPower(0);
            }


            if (gamepad1.a) {
                if (latcherServo.getPosition() == .5f) {
                    latcherServo.setPosition(.05f);
                } else {
                    latcherServo.setPosition(.5f);
                }
            }
        }
    }
}
