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
            autoLib.calcMove(10, .2f);
            autoLib.calcTurn(45, .2f);
            autoLib.calcMove(60, .2f);
            autoLib.calcMove(-28, .2f);
            autoLib.calcTurn(-190, .2f);
            autoLib.calcMove(-70, .2f);
            autoLib.calcTurn(-50, .2f);
            autoLib.calcMove(-75, .2f);
            autoLib.depositMarker();
            autoLib.calcMove(86, .2f);
            autoLib.setPositionintakeMinerals();
            autoLib.moveLinearSlideToDepot(1000);

        } else if (gold == Constants.GoldObjectPosition.RIGHT) {
            telemetry.addData("pos", "Right");
            autoLib.calcMove(10, .7f);
            autoLib.calcTurn(-45, .7f);
            autoLib.calcMove(60, .7f);
            autoLib.calcMove(-28, .7f);
            autoLib.calcTurn(-77, .7f);
            autoLib.calcMove(-99, .7f);
            autoLib.calcTurn(60, .7f);
            autoLib.calcMove(-84, .7f);
            autoLib.depositMarker();
            Thread.sleep(1000);
            autoLib.calcTurn(6, .7f);
            autoLib.calcMove(90, .7f);
            autoLib.setPositionintakeMinerals();
            autoLib.moveLinearSlideToDepot(900);

        } else if (gold == Constants.GoldObjectPosition.CENTER) {
            telemetry.addData("pos", "Center");
            telemetry.update();
            autoLib.calcMove(10, .4f);
            autoLib.calcTurn(-10, .4f);
            autoLib.calcMove(40, .4f);
            autoLib.calcMove(-28, .4f);
            autoLib.calcTurn(-120, .4f);
            autoLib.calcMove(-93, .4f);
            autoLib.calcTurn(85, .4f);
            autoLib.calcMove(-64, .4f);
            autoLib.depositMarker();
            Thread.sleep(1000);
            autoLib.calcMove(80, .4f);
            autoLib.setPositionintakeMinerals();
            autoLib.moveLinearSlideToDepot(1000);

        } else {
            telemetry.addData("pos", "Nothing");

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
