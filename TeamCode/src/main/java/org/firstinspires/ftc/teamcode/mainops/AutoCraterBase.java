package org.firstinspires.ftc.teamcode.mainops;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.libraries.AutoLib;
import org.firstinspires.ftc.teamcode.libraries.Constants;

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

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();

//        autoLib.landOnGround();
        autoLib.calcMove(25, .3f);

        // Tensorflow
        Constants.GoldObjectPosition gold = autoLib.readGoldObjectPosition();

        if (gold == Constants.GoldObjectPosition.LEFT) {
            telemetry.addData("pos", "Left");
            autoLib.calcTurn(-45, 5f);
            autoLib.calcMove(73, 9f );
            autoLib.calcMove(-20,9f);
            autoLib.calcTurn(-70,5f);
            autoLib.calcMove(110,9f);
            autoLib.calcTurn(-40,5f);
            autoLib.calcMove(110,9f);
            autoLib.setServoAngle();
            autoLib.depositMarker();
            autoLib.calcTurn(-5,5f);
            autoLib.calcMove(-185,9f);
        } else if (gold == Constants.GoldObjectPosition.RIGHT) {
            telemetry.addData("pos", "Right");
            autoLib.calcTurn(30,5f);
            autoLib.calcMove(75,9f);
            autoLib.calcMove(-41,9f);
            autoLib.calcTurn(-130,5f);
            autoLib.calcMove(143,9f);
            autoLib.calcTurn(-57,5f);
            autoLib.calcMove(130,9f);
            autoLib.setServoAngle();
            autoLib.depositMarker();
            autoLib.calcTurn(-5,5f);
            autoLib.calcMove(-178,8f);
        } else if (gold == Constants.GoldObjectPosition.CENTER) {
            telemetry.addData("pos", "Center");
            autoLib.calcMove(75,9f);
            autoLib.calcMove(-40,9f);
            autoLib.calcTurn(-100,5f);
            autoLib.calcMove(110,9f);
            autoLib.calcTurn(-47,5f);
            autoLib.calcMove(127,9f);
            autoLib.setServoAngle();
            autoLib.depositMarker();
            autoLib.calcTurn(-15,5f);
            autoLib.calcMove(-165,9f);

        } else {
            telemetry.addData("pos", "Nothing");
            autoLib.calcMove(75,9f);
            autoLib.calcMove(-40,9f);
            autoLib.calcTurn(-100,5f);
            autoLib.calcMove(110,9f);
            autoLib.calcTurn(-47,5f);
            autoLib.calcMove(127,9f);
            autoLib.setServoAngle();
            autoLib.depositMarker();
            autoLib.calcTurn(-15,5f);
            autoLib.calcMove(-165,9f);


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
