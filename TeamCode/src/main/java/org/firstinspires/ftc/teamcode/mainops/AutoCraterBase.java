package org.firstinspires.ftc.teamcode.mainops;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.libraries.AutoLib;
import org.firstinspires.ftc.teamcode.libraries.Constants;

import static org.firstinspires.ftc.robotcore.internal.opmode.OnBotJavaManager.initialize;

/*
 * Title: AutoBlueCraterBase
 * Date Created: 11/23/2018
 * Date Modified: 11/26/2018
 * Author: Rahul, Poorvi, Varnika
 * Type: Main
 * Description: Starts on blue crater latcher
 */

@Autonomous(group = "Main")
public class AutoCraterBase extends LinearOpMode {
    private AutoLib autoLib;

    @SuppressWarnings("RedundantThrows")
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        autoLib.landOnGround();
        autoLib.calcMove(5, .3f);

        // Tensorflow
        Constants.GoldObjectPosition gold = autoLib.readGoldObjectPosition();

        if (gold == Constants.GoldObjectPosition.LEFT) {
            telemetry.addData("pos", "Left");
            telemetry.update();
            autoLib.calcTurn(-45, 5f);
            autoLib.calcMove(73, 9f);
            autoLib.calcMove(-20, 9f);
            autoLib.calcTurn(-75, 5f);
            autoLib.calcMove(110, 9f);
            autoLib.calcTurn(-40, 5f);
            autoLib.calcMove(107, 9f);
            autoLib.setServoAngle();
            //autoLib.depositMarker();
            autoLib.calcTurn(-10, 5f);
            autoLib.calcMove(-175, 9f);
        } else if (gold == Constants.GoldObjectPosition.RIGHT) {
            telemetry.addData("pos", "Right");
            telemetry.update();
            autoLib.calcTurn(39, 5f);
            autoLib.calcMove(78, 9f);
            autoLib.calcMove(-41, 9f);
            autoLib.calcTurn(-135, 5f);
            autoLib.calcMove(145, 9f);
            autoLib.calcTurn(-57, 5f);
            autoLib.calcMove(130, 9f);
            autoLib.setServoAngle();
            //autoLib.depositMarker();
            autoLib.calcTurn(-10, 5f);
            autoLib.calcMove(-169, 8f);
        } else if (gold == Constants.GoldObjectPosition.CENTER) {
            telemetry.addData("pos", "Center");
            telemetry.update();
            autoLib.calcMove(75, 9f);
            autoLib.calcMove(-40, 9f);
            autoLib.calcTurn(-100, .7f);
            autoLib.calcMove(125, 9f);
            autoLib.calcTurn(-55, .7f);
            autoLib.calcMove(127, 9f);
            autoLib.setServoAngle();
            //autoLib.depositMarker();
            autoLib.calcTurn(-7, .7f);
            autoLib.calcMove(-185, 9f);
            autoLib.calcMove(-8,.3f);

        } else {
            telemetry.addData("pos", "Nothing");
            telemetry.update();
            autoLib.calcMove(75, 9f);
            autoLib.calcMove(-40, 9f);
            autoLib.calcTurn(-100, 5f);
            autoLib.calcMove(110, 9f);
            autoLib.calcTurn(-47, 5f);
            autoLib.calcMove(127, 9f);
            autoLib.setServoAngle();
            //autoLib.depositMarker();
            autoLib.calcTurn(-15, 5f);
            autoLib.calcMove(-165, 9f);


        }
        telemetry.update();

//        autoLib.calcTurn(-80,.2f);
//        autoLib.calcMove(200,.2f);
//        autoLib.moveLinearSlideToDepot();
//        autoLib.calcMove(200,-.2f);
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
