package org.firstinspires.ftc.teamcode.supportops;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.libraries.AutoLib;

/*
 * Title: TensorFlow Test
 * Date Created: 12/23/2018
 * Date Modified: 12/23/2018
 * Author: Rahul, Poorvi, Varnika=
 * Type: Testing
 * Description: This tests only the TensorFlow object detection
 */

@Autonomous(name = "TensorFlow Test", group = "Support")
public class TensorFlowTest extends LinearOpMode {
    private AutoLib autoLib;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();

        telemetry.addData("Object Detected", autoLib.readGoldObjectPosition());
        telemetry.update();
        Thread.sleep(5000);
    }

    private void initialize() {
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        autoLib = new AutoLib(this);

        telemetry.addData("Status", "Ready");
        telemetry.update();
        waitForStart();

        telemetry.addData("Status", "Running");
        telemetry.update();
    }
}
