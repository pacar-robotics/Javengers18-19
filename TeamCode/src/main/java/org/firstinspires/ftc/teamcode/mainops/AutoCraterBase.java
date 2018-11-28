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
        autoLib.calcTurn(15, .3f);

        // Tensorflow
        Constants.GoldObjectPosition gold = autoLib.readGoldObjectPosition();

        if (gold == Constants.GoldObjectPosition.LEFT) {
            telemetry.addData("pos", "Left");
            autoLib.calcTurn(-45, .3f);
            autoLib.calcMove(77, .3f );
            autoLib.calcMove(-20,.3f);
            autoLib.calcTurn(-79,.3f);
            autoLib.calcMove(100,.3f);
            autoLib.calcTurn(-45,.3f);
            autoLib.calcMove(115,.3f);
            autoLib.calcTurn(-5,.6f);
            autoLib.calcMove(-185,.7f);
        } else if (gold == Constants.GoldObjectPosition.RIGHT) {
            telemetry.addData("pos", "Right");
            autoLib.calcTurn(20,.3f);
            autoLib.calcMove(82,.3f);
            autoLib.calcMove(-36,-.3f);
            autoLib.calcTurn(-140,.3f);
            autoLib.calcMove(155,.5f);
            autoLib.calcTurn(-55,.5f);
            autoLib.calcMove(112,.5f);
            autoLib.calcMove(-180,.6f);
        } else if (gold == Constants.GoldObjectPosition.CENTER) {
            telemetry.addData("pos", "Center");
            autoLib.calcTurn(-15,.5f);
            autoLib.calcMove(75,.5f);
//            Thread.sleep(100);
            autoLib.calcMove(-40,.5f);
            autoLib.calcTurn(-100,.5f);
            autoLib.calcMove(110,.5f);
            autoLib.calcTurn(-47,.5f);
            autoLib.calcMove(127,.6f);
            autoLib.calcTurn(-15,.5f);
            autoLib.calcMove(-170,.7f);
        } else {
            telemetry.addData("pos", "Nothing");

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
