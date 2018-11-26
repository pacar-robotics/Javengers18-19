package org.firstinspires.ftc.teamcode.mainops;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.libraries.AutoLib;
import org.firstinspires.ftc.teamcode.libraries.Constants;

/*
 * Title: AutoBlueDepotBase
 * Date Created: 11/3/2018
 * Date Modified: 11/26/2018
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

        autoLib.moveLinearSlideToDepot();

        //Blue depot base parking near the robot picture
//        autoLib.landOnGround();
//        autoLib.calcMove(5, .3f);
//        autoLib.calcTurn(15, .3f);
//
//        // Tensorflow
//        Constants.GoldObjectPosition gold = autoLib.readGoldObjectPosition();
//        telemetry.addData("Detecting Mineral","Left Mineral");
//        telemetry.update();
//        if (gold == Constants.GoldObjectPosition.LEFT) {
//            telemetry.addData("pos", "Left");
//            autoLib.calcTurn(-50, .3f);
//            autoLib.calcMove(83, .5f);
//            autoLib.calcMove(-45, .3f);
//            autoLib.calcTurn(137,.3f);
//            autoLib.calcMove(150,.4f);
//            autoLib.calcTurn(35,.3f);
//            autoLib.calcMove(40,.3f);
//        } else if (gold == Constants.GoldObjectPosition.RIGHT) {
//            telemetry.addData("pos", "Right");
//            autoLib.calcTurn(25, .3f);
//            autoLib.calcMove(78, .5f);
//            autoLib.calcMove(-34, .3f);
//            autoLib.calcTurn(70, .2f);
//            Thread.sleep(100);
//            autoLib.calcMove(134, .5f);
//
//        } else if (gold == Constants.GoldObjectPosition.CENTER) {
//            telemetry.addData("pos", "Center");
//            autoLib.calcTurn(-15, .3f);
//            autoLib.calcMove(75, .5f);
//            autoLib.moveLinearSlideToDepot();
//            Thread.sleep(100);
//            autoLib.calcMove(-40, .3f);
//            autoLib.calcTurn(107,.3f );
//            autoLib.calcMove(-155,.5f);
//
//        } else {
//            telemetry.addData("pos", "Nothing");
//        }
//        telemetry.update();

//       autoLib.moveLinearSlideToDepot();
//        autoLib.calcTurn(75, .2f);
//        Thread.sleep(100);
//        autoLib.calcMove(124, .5f);
//        autoLib.calcTurn(40, .5f);
//        autoLib.calcMove(68, .5f);
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
