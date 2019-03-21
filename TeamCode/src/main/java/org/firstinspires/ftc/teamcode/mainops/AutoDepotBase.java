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

        if (gold == Constants.GoldObjectPosition.LEFT) {
            telemetry.addData("pos", "Left");
            telemetry.update();
            autoLib.calcMove(5, .2f);
            autoLib.calcTurn(35, .2f);
            autoLib.calcMove(75, .6f);
            autoLib.calcTurn(150, .6f);
            autoLib.calcMove(-70, .6f);
            autoLib.moveScoringArm();
            autoLib.depositMarker();
            Thread.sleep(1500);
            autoLib.calcTurn(-30, .6f);
            autoLib.calcMove(150, .6f);
            autoLib.setPositionintakeMinerals();
            autoLib.moveLinearSlideToDepot(1300);
        } else if (gold == Constants.GoldObjectPosition.RIGHT) {
            telemetry.addData("pos", "Right");
            telemetry.update();
            autoLib.calcMove(5, .2f);
            autoLib.calcTurn(-44, .2f);
            autoLib.intakeMinerals();
            autoLib.calcMove(75, .6f);
            autoLib.stopintake();
            autoLib.calcTurn(90, .6f);
            autoLib.calcMove(70, .6f);
            autoLib.calcTurn(150, .6f);
            autoLib.moveScoringArm();
            autoLib.depositMarker();
            Thread.sleep(1500);
            autoLib.calcTurn(-55, .6f);
            autoLib.calcMove(140, .6f);
            autoLib.setPositionintakeMinerals();
            autoLib.moveLinearSlideToDepot(1100);
        } else if (gold == Constants.GoldObjectPosition.CENTER) {
            telemetry.addData("pos", "Center");
            telemetry.update();
            autoLib.calcMove(5,.2f);
            autoLib.calcTurn(-9, .2f);
            autoLib.intakeMinerals();
            autoLib.calcMove(120, .6f);
            autoLib.stopintake();
            autoLib.calcMove(-5,.6f);
            autoLib.calcTurn(215, .6f);
            autoLib.moveScoringArm();
            autoLib.depositMarker();
            Thread.sleep(1500);
            autoLib.calcTurn(-80, .6f);
            autoLib.calcMove(50, .6f);
            autoLib.calcTurn(20, .6f);
            autoLib.calcMove(105, .4f);
            autoLib.setPositionintakeMinerals();
            autoLib.moveLinearSlideToDepot(1100);
        } else {
            telemetry.addData("pos", "Nothing");
            telemetry.update();
            autoLib.calcMove(5, .2f);
            autoLib.calcTurn(35, .2f);
            autoLib.calcMove(75, .6f);
            autoLib.calcTurn(150, .6f);
            autoLib.calcMove(-70, .6f);
            Thread.sleep(1500);
            autoLib.moveScoringArm();
            autoLib.depositMarker();
            autoLib.calcTurn(-30, .6f);
            autoLib.calcMove(155, .6f);
            autoLib.setPositionintakeMinerals();
            autoLib.moveLinearSlideToDepot(1100);
        }
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