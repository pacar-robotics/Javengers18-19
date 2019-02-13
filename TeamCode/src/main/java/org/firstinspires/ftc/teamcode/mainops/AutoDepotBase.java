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
        autoLib.landOnGround();
//        autoLib.calcMove(5,3f );
//

        // Tensorflow
        Constants.GoldObjectPosition gold = autoLib.readGoldObjectPosition();
        telemetry.addData("Detecting Mineral", "Left Mineral");
        telemetry.update();
        if (gold == Constants.GoldObjectPosition.LEFT) {
            telemetry.addData("pos", "Left");
            autoLib.calcTurn(40, .9f);
            autoLib.calcMove(100, .1f,Constants.Direction.FORWARD);
            autoLib.calcTurn(70, .1f);
            autoLib.calcMove(80, .1f,Constants.Direction.FORWARD);
//            autoLib.setServoAngle();
//            autoLib.depositMarker();
            autoLib.calcTurn(28,.1f);
            autoLib.calcMove(195,.1f,Constants.Direction.BACKWARD);
            autoLib.calcMove(10,.1f,Constants.Direction.BACKWARD);
           autoLib.calcMove(60,.1f,Constants.Direction.BACKWARD);
            autoLib.calcTurn(100,.1f);
            autoLib.calcMove(150,.1f,Constants.Direction.FORWARD);
            autoLib.calcTurn(35,.1f);
            autoLib.calcMove(40,.1f,Constants.Direction.FORWARD);
        } else if (gold == Constants.GoldObjectPosition.RIGHT) {
            telemetry.addData("pos", "Right");
            autoLib.calcTurn(40, .1f);
            autoLib.calcMove(100, .1f,Constants.Direction.FORWARD);
            //autoLib.calcMove(-5,5f);
            autoLib.calcTurn(70, .1f);
            autoLib.calcMove(68,.1f,Constants.Direction.FORWARD);
            autoLib.calcTurn(5,.1f);
//            autoLib.setServoAngle();
//            autoLib.depositMarker();
            autoLib.calcTurn(35,.1f);
            autoLib.calcMove(168, .1f,Constants.Direction.BACKWARD);


        } else if (gold == Constants.GoldObjectPosition.CENTER) {
            telemetry.addData("pos", "Center");
            autoLib.calcMove(120, .1f,Constants.Direction.FORWARD);
//            autoLib.setServoAngle();
//            autoLib.depositMarker();
            autoLib.calcTurn(73, .1f);
            autoLib.calcMove(83, .1f,Constants.Direction.BACKWARD);
            autoLib.calcTurn(-20, .1f);
            autoLib.calcMove(140, .1f,Constants.Direction.BACKWARD);
            autoLib.calcMove(5, .1f,Constants.Direction.BACKWARD);

        } else {
            telemetry.addData("pos", "Nothing");
            autoLib.calcMove(125, .1f,Constants.Direction.FORWARD);
//            autoLib.setServoAngle();
//            autoLib.depositMarker();
            autoLib.calcTurn(-110, .5f);
            autoLib.calcMove(200,.1f,Constants.Direction.LEFT);
//            autoLib.calcMove(83, .1f,Constants.Direction.BACKWARD);
//            autoLib.calcTurn(15, .1f);
//            autoLib.calcMove(140, .1f,Constants.Direction.BACKWARD);
//            autoLib.calcMove(5, .1f,Constants.Direction.BACKWARD);
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