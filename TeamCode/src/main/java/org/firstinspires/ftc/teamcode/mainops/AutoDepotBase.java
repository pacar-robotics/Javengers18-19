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
        autoLib.calcMove(5, 3f);


        // Tensorflow
        Constants.GoldObjectPosition gold = autoLib.readGoldObjectPosition();
//        telemetry.addData("Detecting Mineral", "Left Mineral");
//        telemetry.update();
        if (gold == Constants.GoldObjectPosition.LEFT) {
            telemetry.addData("pos", "Left");
            telemetry.update();
            autoLib.calcTurn(-40, 2f);
            autoLib.calcMove(105, 2f);
            autoLib.calcTurn(65, .3f);
            autoLib.calcMove(60, 2f);
            autoLib.setServoAngle();
          //  autoLib.depositMarker();
            autoLib.calcTurn(28, .3f);
            autoLib.calcMove(-175, 4f);
            autoLib.calcMove(-12, .4f);
//           autoLib.calcMove(-60,.5f);
//            autoLib.calcTurn(100,1f);
//            autoLib.calcMove(150,1f);
//            autoLib.calcTurn(35,1f);
//            autoLib.calcMove(40,1f);
        } else if (gold == Constants.GoldObjectPosition.RIGHT) {
            telemetry.update();
            telemetry.addData("pos", "Right");
            autoLib.calcTurn(40, 4f);
            autoLib.calcMove(100, 4f);
            //autoLib.calcMove(-5,5f);
            autoLib.calcTurn(-60, 2f);
            autoLib.calcMove(68, 4f);
            autoLib.calcTurn(5, 4f);
            autoLib.setServoAngle();
           // autoLib.depositMarker();
            autoLib.calcTurn(-35, 5f);
            autoLib.calcMove(-168, 4f);
        } else if (gold == Constants.GoldObjectPosition.CENTER) {
            telemetry.addData("pos", "Center");
            telemetry.update();
            autoLib.calcMove(155, 4f);
            autoLib.setServoAngle();
            //autoLib.depositMarker();
            autoLib.calcTurn(73, 4f);
            autoLib.calcMove(-83, 4f);
            autoLib.calcTurn(-20, 4f);
            autoLib.calcMove(-140, 4f);
            autoLib.calcMove(-5, .4f);

        } else {
            telemetry.addData("pos", "Nothing");
            telemetry.update();
            telemetry.update();
            autoLib.calcTurn(-40, 2f);
            autoLib.calcMove(105, 2f);
            autoLib.calcTurn(45, .3f);
            autoLib.calcMove(80, 2f);
            autoLib.setServoAngle();
            //autoLib.depositMarker();
            autoLib.calcTurn(28, .3f);
            autoLib.calcMove(-195, 4f);
            autoLib.calcMove(-12, .4f);
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
