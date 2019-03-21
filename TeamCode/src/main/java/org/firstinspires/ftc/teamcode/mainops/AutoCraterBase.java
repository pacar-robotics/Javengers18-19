package org.firstinspires.ftc.teamcode.mainops;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.libraries.AutoLib;
import org.firstinspires.ftc.teamcode.libraries.Constants;

/*
 * Title: AutoBlueCraterBase
 * Date Created: 11/23/2018
 * Date Modified: 2/22/2019
 * Author: Rahul, Poorvi, Varnika
 * Type: Main
 * Description: Starts on blue crater latcher
 */

@Autonomous(group = "Main")
public class AutoCraterBase extends LinearOpMode {
    private AutoLib autoLib;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        autoLib.landOnGround();

        // Tensorflow
        Constants.GoldObjectPosition gold = autoLib.readGoldObjectPosition();

        if (gold == Constants.GoldObjectPosition.LEFT) {
            telemetry.addData("pos", "Left");
            autoLib.calcMove(10, .8f);
            autoLib.calcTurn(38, .8f);
            autoLib.calcMove(60, .8f);
            autoLib.calcMove(-28, .8f);
            autoLib.calcTurn(-185, .8f);
            autoLib.calcMove(-67, .8f);
            autoLib.calcTurn(96, .8f);
            autoLib.calcMove(-105, .8f);
            autoLib.moveScoringArm();
            autoLib.depositMarker();
            Thread.sleep(1500);
            autoLib.calcMove(100, .8f);
            autoLib.setPositionintakeMinerals();
            autoLib.moveLinearSlideToDepot(1000);

        } else if (gold == Constants.GoldObjectPosition.RIGHT) {
            telemetry.addData("pos", "Right");
            autoLib.calcMove(10, .8f);
            autoLib.calcTurn(-45, .8f);
            autoLib.calcMove(60, .8f);
            autoLib.calcMove(-28, .8f);
            autoLib.calcTurn(-79, .8f);
            autoLib.calcMove(-105, .8f);
            autoLib.calcTurn(56, .8f);
            autoLib.calcMove(-95, .5f);
            autoLib.moveScoringArm();
            autoLib.depositMarker();
            Thread.sleep(1500);
            autoLib.calcMove(120, .8f);
            autoLib.setPositionintakeMinerals();
            autoLib.moveLinearSlideToDepot(1000);
        } else if (gold == Constants.GoldObjectPosition.CENTER) {
            telemetry.addData("pos", "Center");
            telemetry.update();
            autoLib.calcMove(10, .8f);
            autoLib.calcTurn(-5, .8f);
            autoLib.intakeMinerals();
            autoLib.calcMove(49, .8f);
            autoLib.calcMove(-19, .8f);
            autoLib.stopintake();
            autoLib.calcTurn(-115, .8f);
            autoLib.calcMove(-92, .8f);
            autoLib.calcTurn(60, .8f);
            autoLib.calcMove(-106, .8f);
            autoLib.moveScoringArm();
            autoLib.depositMarker();
            Thread.sleep(1500);
            autoLib.calcTurn(6,.8f);
            autoLib.calcMove(129, .8f);
            autoLib.setPositionintakeMinerals();
            autoLib.moveLinearSlideToDepot(1000);

        } else {
            telemetry.addData("pos", "Nothing");
            autoLib.calcMove(10, .8f);
            autoLib.calcTurn(-45, .8f);
            autoLib.calcMove(60, .8f);
            autoLib.calcMove(-28, .8f);
            autoLib.calcTurn(-79, .8f);
            autoLib.calcMove(-105, .8f);
            autoLib.calcTurn(58, .8f);
            autoLib.calcMove(-95, .5f);
            autoLib.moveScoringArm();
            autoLib.depositMarker();
            Thread.sleep(1500);
            autoLib.calcMove(117, .8f);
            autoLib.setPositionintakeMinerals();
            autoLib.moveLinearSlideToDepot(1000);


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
