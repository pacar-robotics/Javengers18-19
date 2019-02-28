package org.firstinspires.ftc.teamcode.mainops;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.libraries.AutoLib;
import org.firstinspires.ftc.teamcode.libraries.Constants;


/*
 * Title: AutoBlueDepotBase
 * Date Created: 11/3/2018
 * Date Modified: 2/22/2019
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
        autoLib.landOnGround();
        // Tensorflow
         Constants.GoldObjectPosition gold = autoLib.readGoldObjectPosition();
        telemetry.addData("Detecting Mineral", "Left Mineral");
        telemetry.update();
        if (gold == Constants.GoldObjectPosition.LEFT) {
            telemetry.addData("pos", "Left");
            telemetry.update();
            autoLib.calcTurn(40, .9f);
            autoLib.calcMove(100, .1f);
            autoLib.calcTurn(70, .1f);
            autoLib.calcMove(80, .1f);
            autoLib.calcTurn(28,.1f);
            autoLib.calcMove(195,.1f);
            autoLib.calcMove(10,.1f);
           autoLib.calcMove(60,.1f);
            autoLib.calcTurn(100,.1f);
            autoLib.calcMove(150,.1f);
            autoLib.calcTurn(35,.1f);
            autoLib.calcMove(40,.1f);

        } else if (gold == Constants.GoldObjectPosition.RIGHT) {
            telemetry.addData("pos", "Right");
            telemetry.update();
            telemetry.addData("status","just detected");
            telemetry.update();
            autoLib.calcMove(5,.2f);
            autoLib.calcTurn(-45, .1f);
            autoLib.calcMove(45, .1f);
            autoLib.setPositionintakeMinerals();
            autoLib.intakeMinerals();
            autoLib.calcTurn(30,.2f);
            autoLib.calcMove(50,.2f);
            autoLib.calcTurn(180,.2f);
            autoLib.moveScoringArm();
            autoLib.depositMarker();
            autoLib.calcTurn(30,.2f);
            autoLib.calcMove(168, .1f);
            autoLib.setPositionintakeMinerals();
            autoLib.moveLinearSlideToDepot(900);


        } else if (gold == Constants.GoldObjectPosition.CENTER) {
            telemetry.addData("pos", "Center");
            telemetry.update();
            autoLib.calcMove(120, .1f);
            autoLib.calcTurn(185,.2f);
            autoLib.depositMarker();
            autoLib.calcTurn(-35,.2f);
            autoLib.calcMove(30, .1f);
            autoLib.calcTurn(30,.2f);
            autoLib.calcMove(120,.2f);
            autoLib.setPositionintakeMinerals();
            autoLib.moveLinearSlideToDepot(900);

        } else {
            telemetry.addData("pos", "Nothing");
            telemetry.update();
            autoLib.calcMove(5,.2f);
            autoLib.calcTurn(-50, .1f);
            autoLib.calcMove(90, .1f);
            autoLib.calcTurn(100, .1f);
            autoLib.calcMove(60,.1f);
            autoLib.calcTurn(180,.1f);
            autoLib.depositMarker();
            autoLib.calcMove(168, .1f);


        }
        telemetry.update();

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