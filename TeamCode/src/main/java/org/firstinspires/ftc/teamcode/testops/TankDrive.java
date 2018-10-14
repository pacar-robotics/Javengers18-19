package org.firstinspires.ftc.teamcode.testops;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/*
 * Title: TankDrive
 * Date Created: 10/13/2018
 * Date Modified: 10/13/2018
 * Author: Rahul
 * Type: TeleOp
 * Description: Left joystick moves left wheel, right joystick moves right wheel
 */

@SuppressWarnings("unused")
@TeleOp(group = "TestOps")
public class TankDrive extends LinearOpMode {
    @SuppressWarnings("RedundantThrows")
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor leftWheel = hardwareMap.get(DcMotor.class, "leftWheel");
        DcMotor rightWheel = hardwareMap.get(DcMotor.class, "rightWheel");

        leftWheel.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        // This while loop updates the motor powers with the joystick values
        while (opModeIsActive()) {
            leftWheel.setPower(-gamepad1.left_stick_y);
            rightWheel.setPower(-gamepad1.right_stick_y);
            idle();
        }
    }
}
