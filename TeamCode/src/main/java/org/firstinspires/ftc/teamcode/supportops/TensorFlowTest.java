package org.firstinspires.ftc.teamcode.supportops;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.libraries.AutoLib;

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
