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
//        autoLib.calcMove(55,1f);
//        autoLib.moveLinearSlide(-15000,1f);
//        autoLib.moveLinearSlide(15000,1f);
//        autoLib.calcMove(-53,1f);
        autoLib.calcMove(5, 1f);
//        autoLib.calcTurn(15, .3f);

        // Tensorflow
        Constants.GoldObjectPosition gold = autoLib.readGoldObjectPosition();
        telemetry.addData("Detecting Mineral","Left Mineral");
        telemetry.update();
        if (gold == Constants.GoldObjectPosition.LEFT) {
            telemetry.addData("pos", "Left");
            autoLib.calcTurn(-40, 2f);
            autoLib.calcMove(100, 2f);
            autoLib.calcTurn(70,.3f);
            autoLib.calcMove(80, 2f);
            autoLib.setServoAngle();
            autoLib.depositMarker();
            autoLib.calcTurn(35,.3f);
            autoLib.calcMove(-190,4f);
//            autoLib.calcMove(-60,.5f);
//            autoLib.calcTurn(100,1f);
//            autoLib.calcMove(150,1f);
//            autoLib.calcTurn(35,1f);
//            autoLib.calcMove(40,1f);
        } else if (gold == Constants.GoldObjectPosition.RIGHT) {
            telemetry.addData("pos", "Right");
            autoLib.calcTurn(25, 1f);
            autoLib.calcMove(78, 1f);
            autoLib.calcMove(-28, 1f);
            autoLib.calcTurn(70, 1f);
            autoLib.calcMove(134, .6f);
            autoLib.setServoAngle();
            autoLib.depositMarker();

        } else if (gold == Constants.GoldObjectPosition.CENTER) {
            telemetry.addData("pos", "Center");
            autoLib.calcMove(155, 3f);
            autoLib.setServoAngle();
            autoLib.depositMarker();
            autoLib.calcTurn(73,3f );
            autoLib.calcMove(-83,4f);
            autoLib.calcTurn(-15,4f);
            autoLib.calcMove(-145,4f);

        } else {
            telemetry.addData("pos", "Nothing");
            autoLib.calcMove(155, .3f);
            autoLib.setServoAngle();
            autoLib.depositMarker();
            autoLib.calcTurn(73,.3f );
            autoLib.calcMove(-83,.3f);
            autoLib.calcTurn(-15,.3f);
            autoLib.calcMove(-145,.3f);
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
