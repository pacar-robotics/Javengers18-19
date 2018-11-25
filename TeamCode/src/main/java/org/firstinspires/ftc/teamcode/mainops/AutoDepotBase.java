package org.firstinspires.ftc.teamcode.mainops;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.libraries.AutoLib;
import org.firstinspires.ftc.teamcode.libraries.Constants;

/*
 * Title: AutoBlueDepotBase
 * Date Created: 11/3/2018
 * Date Modified: 11/23/2018
 * Author: Rahul, Poorvi, Varnika
 * Type: Main
 * Description: Starts on blue depot latcher
 */

@Autonomous(group = "Main")
public class AutoDepotBase extends LinearOpMode {
    private AutoLib autoLib;

    @SuppressWarnings("RedundantThrows")
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();

        //Blue depot base parking near the robot picture
        autoLib.landOnGround();
        Thread.sleep(100);

        //tensorflow
//        Constants.GoldObjectPosition gold = autoLib.readGoldObjectPosition();
//
//        if (gold == Constants.GoldObjectPosition.LEFT) {
//
//        } else if (gold == Constants.GoldObjectPosition.RIGHT) {
//
//        } else if (gold == Constants.GoldObjectPosition.CENTER) {
//
//        } else {
//
//        }



        if (false) { // Left

        } else if (false) {    // Right

        } else if (true) {   // Center
            telemetry.addData("Status", "Inside Center");
            telemetry.update();
            autoLib.calcMove(80,.2f );
            Thread.sleep(2000);
            telemetry.addData("Status", "Moved 80 CM");
            telemetry.update();
            Thread.sleep(100);
        }

        // Marker depot dropoff

//        autoLib.calcTurn(80, .2f);
//        Thread.sleep(100);
//        autoLib.calcMove(87, .5f);
//        autoLib.calcTurn(40, .5f);
//        autoLib.calcMove(68, .5f);
//        autoLib.calcTurn(90, .2f);

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
