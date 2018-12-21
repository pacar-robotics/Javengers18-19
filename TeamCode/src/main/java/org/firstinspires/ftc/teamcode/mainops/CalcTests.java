package org.firstinspires.ftc.teamcode.mainops;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.libraries.AutoLib;
import org.firstinspires.ftc.teamcode.libraries.Constants;

@Autonomous
public class CalcTests extends LinearOpMode {
    AutoLib autoLib;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();

        autoLib.calcMove(20, .2f, Constants.Direction.RIGHT);
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
