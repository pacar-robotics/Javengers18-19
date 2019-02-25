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
            autoLib.calcTurn(40, .1f);
            telemetry.addData("status","just turned");
            telemetry.update();
            autoLib.calcMove(100, .1f);
            autoLib.calcMove(-5,5f);
            autoLib.calcTurn(70, .1f);
            autoLib.calcMove(68,.1f);
            autoLib.calcTurn(5,.1f);
            autoLib.calcTurn(35,.1f);
            autoLib.calcMove(168, .1f);


        } else if (gold == Constants.GoldObjectPosition.CENTER) {
            telemetry.addData("pos", "Center");
            telemetry.update();
            autoLib.calcMove(115, .1f);
            autoLib.intakeMinerals();
            autoLib.calcTurn(315, .1f);
            autoLib.calcMove(83, .1f);
            autoLib.calcTurn(-20, .1f);
            autoLib.calcMove(50, .1f);
            autoLib.moveLinearSlideToDepot(900);

        } else {
            telemetry.addData("pos", "Nothing");
            telemetry.update();
            autoLib.calcMove(125, .1f);
            autoLib.calcTurn(-50, .5f);

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