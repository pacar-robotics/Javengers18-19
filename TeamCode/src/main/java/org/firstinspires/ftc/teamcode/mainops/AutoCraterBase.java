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
            autoLib.calcMove(10,.2f);
            autoLib.calcTurn(15,.2f);
            autoLib.calcMove(50,.2f);
            autoLib.calcMove(-20,.2f);
            autoLib.calcTurn(70,.2f);
            autoLib.calcMove(80,.2f);
            autoLib.calcTurn(30,.2f);
            autoLib.calcMove(100,.2f);
            autoLib.depositMarker();
            autoLib.stopintake();
            autoLib.calcMove(-104,.2f);

        } else if (gold == Constants.GoldObjectPosition.RIGHT) {
            telemetry.addData("pos", "Right");
            autoLib.calcMove(10,.2f);
            autoLib.calcTurn(-15,.2f);
            autoLib.calcMove(20,.2f);
           autoLib.calcMove(-10,.2f);
           autoLib.calcTurn(70,.2f);
           autoLib.calcMove(120,.2f);
           autoLib.calcTurn(40,.2f);
           autoLib.calcMove(100,.2f);
            autoLib.depositMarker();
            autoLib.stopintake();
            autoLib.calcMove(-104,.2f);

        } else if (gold == Constants.GoldObjectPosition.CENTER) {
            telemetry.addData("pos", "Center");
            telemetry.update();
            autoLib.calcMove(10,.5f);
            autoLib.calcTurn(-10,.5f);
            autoLib.calcMove(50,.5f);
            autoLib.calcMove(-30,.5f);
            autoLib.calcTurn(105,.5f);
            autoLib.calcMove(100,.5f);
            autoLib.calcTurn(61,.5f);
            autoLib.calcMove(57,.3f);
            autoLib.depositMarker();
            autoLib.stopintake();
            autoLib.calcTurn(30,.2f);
            autoLib.calcMove(-106,.3f);

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
