package org.firstinspires.ftc.teamcode.mainops;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.libraries.TeleLib;

/*
 * Title: MainTeleOp
 * Date Created: 10/14/2018
 * Date Modified: 11/11/2018
 * Author: Rahul, Sarvesh, Sachin, Shivani
 * Type: Main
 * Description: This is the main teleop program we will use
 */

@TeleOp(group = "Main")
public class MainTeleOp extends LinearOpMode {

    @SuppressWarnings("RedundantThrows")
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        TeleLib teleLib = new TeleLib(this);

        telemetry.addData("Status", "Ready");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            teleLib.processGamepadDrive();
            teleLib.processLatcher();
            teleLib.processLatcherServo();
            idle();
        }
    }
}
