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

        autoLib.landOnGround();
        autoLib.calcMove(5, .3f);

        // Tensorflow
        Constants.GoldObjectPosition gold = autoLib.readGoldObjectPosition();

        if (gold == Constants.GoldObjectPosition.LEFT) {
            telemetry.addData("pos", "Left");
            autoLib.calcTurn(-45, 5f);
            autoLib.calcMove(77, 5f );
            autoLib.calcMove(-20,5f);
            autoLib.calcTurn(-79,5f);
            autoLib.calcMove(100,5f);
            autoLib.calcTurn(-45,5f);
            autoLib.calcMove(115,5f);
            autoLib.calcTurn(-5,5f);
            autoLib.calcMove(-185,5f);
        } else if (gold == Constants.GoldObjectPosition.RIGHT) {
            telemetry.addData("pos", "Right");
            autoLib.calcTurn(30,5f);
            autoLib.calcMove(60,5f);
            autoLib.calcMove(-36,5f);
            autoLib.calcTurn(-135,5f);
            autoLib.calcMove(140,5f);
            autoLib.calcTurn(30,5f);
            autoLib.calcMove(100,5f);
            autoLib.setServoAngle();
            autoLib.depositMarker();
            autoLib.calcTurn(-55,5f);
            autoLib.calcMove(112,5f);
            autoLib.calcMove(-180,5f);
        } else if (gold == Constants.GoldObjectPosition.CENTER) {
            telemetry.addData("pos", "Center");
            autoLib.calcTurn(-15,5f);
            autoLib.calcMove(75,5f);
//            Thread.sleep(100);
            autoLib.calcMove(-40,5f);
            autoLib.calcTurn(-100,5f);
            autoLib.calcMove(110,5f);
            autoLib.calcTurn(-47,5f);
            autoLib.calcMove(127,6f);
            autoLib.calcTurn(-15,5f);
            autoLib.calcMove(-170,7f);
        } else {
            telemetry.addData("pos", "Nothing");
            telemetry.addData("pos", "Center");
            autoLib.calcTurn(-15,.5f);
            autoLib.calcMove(75,.5f);
//            Thread.sleep(100);
            autoLib.calcMove(-40,5f);
            autoLib.calcTurn(-100,5f);
            autoLib.calcMove(110,5f);
            autoLib.calcTurn(-47,5f);
            autoLib.calcMove(127,6f);
            autoLib.calcTurn(-15,5f);
            autoLib.calcMove(-170,7f);


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
