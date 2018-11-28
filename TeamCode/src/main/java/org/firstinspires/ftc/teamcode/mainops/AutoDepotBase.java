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

//        Blue depot base parking near the robot picture
        autoLib.landOnGround();
        autoLib.calcMove(55,1f);
        autoLib.moveLinearSlide(-15000,1f);
        autoLib.moveLinearSlide(15000,1f);
        autoLib.calcMove(-53,1f);
        autoLib.calcMove(5, 1f);
        autoLib.calcTurn(15, 1f);

        // Tensorflow
        Constants.GoldObjectPosition gold = autoLib.readGoldObjectPosition();
        telemetry.addData("Detecting Mineral","Left Mineral");
        telemetry.update();
        if (gold == Constants.GoldObjectPosition.LEFT) {
            telemetry.addData("pos", "Left");
            autoLib.calcTurn(-50, 1f);
            autoLib.calcMove(83, 1f);
            autoLib.calcMove(-40, 1f);
            autoLib.calcTurn(137,1f);
            autoLib.calcMove(150,1f);
            autoLib.calcTurn(35,1f);
            autoLib.calcMove(40,1f);
        } else if (gold == Constants.GoldObjectPosition.RIGHT) {
            telemetry.addData("pos", "Right");
            autoLib.calcTurn(25, 1f);
            autoLib.calcMove(78, 1f);
            autoLib.calcMove(-28, 1f);
            autoLib.calcTurn(70, 1f);
            Thread.sleep(100);
            autoLib.calcMove(134, .6f);

        } else if (gold == Constants.GoldObjectPosition.CENTER) {
            telemetry.addData("pos", "Center");
            autoLib.calcTurn(-15, 1f);
            autoLib.calcMove(75, 1f);
            Thread.sleep(100);
            autoLib.calcMove(-40, 1f);
            autoLib.calcTurn(107,1f );
            autoLib.calcMove(-155,1f);

        } else {
            telemetry.addData("pos", "Nothing");
        }
        telemetry.update();

     /*  autoLib.moveLinearSlideToDepot();
        autoLib.calcTurn(75, .2f);
        Thread.sleep(100);
        autoLib.calcMove(124, .5f);
        autoLib.calcTurn(40, .5f);
        autoLib.calcMove(68, .5f);*/
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
