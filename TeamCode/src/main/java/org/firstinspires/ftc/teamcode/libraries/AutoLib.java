package org.firstinspires.ftc.teamcode.libraries;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import java.util.List;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;
import static org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus.LABEL_GOLD_MINERAL;
import static org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus.LABEL_SILVER_MINERAL;
import static org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus.TFOD_MODEL_ASSET;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_INTAKE_SLIDE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_LATCHER;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_LEFT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_RIGHT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.NEVEREST_40_REVOLUTION_ENCODER_COUNT;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_LATCHER;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_LATCHER_POS_REST;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TENSOR_READING_TIME;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TOUCH_LATCHER_BOTTOM;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TOUCH_LATCHER_TOP;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TRACK_DISTANCE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.VUFORIA_KEY;
import static org.firstinspires.ftc.teamcode.libraries.Constants.WHEEL_DIAMETER;
import static org.firstinspires.ftc.teamcode.libraries.Constants.WHEEL_GEAR_RATIO;

/*
 * Title: AutoLib
 * Date Created: 10/28/2018
 * Date Modified: 1/20/2019
 * Author: Rahul, Poorvi, Varnika
 * Type: Library
 * Description: This will contain the methods for Autonomous, and other autonomous-related programs.
 */

public class AutoLib {
    private Robot robot;
    private LinearOpMode opMode;

    // Declaring TensorFlow detection
    private TFObjectDetector tfod;

    public AutoLib(LinearOpMode opMode) {
        robot = new Robot(opMode);
        this.opMode = opMode;

        initTfod();
    }


    //********** Base Motor Methods **********//

    public void calcMove(float centimeters, float power) {
        // Calculates target encoder position
        final int targetPosition = (int) ((((centimeters / (Math.PI * WHEEL_DIAMETER)) *
                NEVEREST_40_REVOLUTION_ENCODER_COUNT)) * WHEEL_GEAR_RATIO);

        // Specific directions require different wheels to move for mecanum
    /*    if (direction == FORWARD) {*/
            prepMotorsForCalcMove(targetPosition, targetPosition);
       /* } else if (direction == BACKWARD) {
            prepMotorsForCalcMove(-targetPosition, -targetPosition);
        }*/
// else if (direction == LEFT) {
//            prepMotorsForCalcMove(-targetPosition, targetPosition, targetPosition, -targetPosition);
//        } else if (direction == RIGHT) {
//            prepMotorsForCalcMove(targetPosition, -targetPosition, -targetPosition, targetPosition);
//        }

        setBaseMotorPowers(power);

        while (areBaseMotorsBusy()) {
            opMode.idle();
        }

        setBaseMotorPowers(0);
    }

    public void calcTurn(int degrees, float power) {
        // Calculates target encoder position
        int targetPosition = (int) (2 * ((TRACK_DISTANCE) * degrees
                * NEVEREST_40_REVOLUTION_ENCODER_COUNT) /
                (WHEEL_DIAMETER * 360));


        prepMotorsForCalcMove(targetPosition,targetPosition);

        setBaseMotorPowers(power);

        while (areBaseMotorsBusy()) {
            opMode.idle();
        }

        setBaseMotorPowers(0);
    }

    private void setBaseMotorPowers(float power) {
        robot.setDcMotorPower(MOTOR_LEFT_WHEEL, power);
//        robot.setDcMotorPower(MOTOR_FRONT_RIGHT_WHEEL, power);
        robot.setDcMotorPower(MOTOR_RIGHT_WHEEL, power);
//        robot.setDcMotorPower(MOTOR_BACK_RIGHT_WHEEL, power);
    }

    private void prepMotorsForCalcMove(int leftTargetPosition, int rightTargetPosition) {
        robot.setDcMotorMode(MOTOR_LEFT_WHEEL, STOP_AND_RESET_ENCODER);
//        robot.setDcMotorMode(MOTOR_FRONT_RIGHT_WHEEL, STOP_AND_RESET_ENCODER);
        robot.setDcMotorMode(MOTOR_RIGHT_WHEEL, STOP_AND_RESET_ENCODER);
//        robot.setDcMotorMode(MOTOR_BACK_RIGHT_WHEEL, STOP_AND_RESET_ENCODER);
//
        robot.setDcMotorMode(MOTOR_LEFT_WHEEL, RUN_TO_POSITION);
        robot.setDcMotorMode(MOTOR_RIGHT_WHEEL, RUN_TO_POSITION);
//        robot.setDcMotorMode(MOTOR_BACK_LEFT_WHEEL, RUN_TO_POSITION);
//        robot.setDcMotorMode(MOTOR_BACK_RIGHT_WHEEL, RUN_TO_POSITION);
//
        robot.setDcMotorTargetPosition(MOTOR_LEFT_WHEEL, leftTargetPosition);
//        robot.setDcMotorTargetPosition(MOTOR_FRONT_RIGHT_WHEEL, frontRightTargetPosition);
//        robot.setDcMotorTargetPosition(MOTOR_BACK_LEFT_WHEEL, backLeftTargetPosition);
        robot.setDcMotorTargetPosition(MOTOR_RIGHT_WHEEL, rightTargetPosition);
    }

    public void moveLinearSlideToDepot() {
        ElapsedTime time = new ElapsedTime();

        robot.setDcMotorPower(MOTOR_INTAKE_SLIDE, -1f);
        while (time.seconds() <= .0005) {
            opMode.idle();
        }
        setBaseMotorPowers(0);
    }

    private boolean areBaseMotorsBusy() {
//        return robot.isMotorBusy(MOTOR_FRONT_LEFT_WHEEL) || robot.isMotorBusy(MOTOR_FRONT_RIGHT_WHEEL) ||
//                robot.isMotorBusy(MOTOR_BACK_LEFT_WHEEL) || robot.isMotorBusy(MOTOR_BACK_RIGHT_WHEEL);
        return true;
    }


    //********** Latcher Methods **********//

    public void landOnGround() throws InterruptedException {
        robot.setDcMotorPower(MOTOR_LATCHER, -0.5f);
        // The motor will stop when it detects that it's on the ground
        while (!robot.isTouchSensorPressed(TOUCH_LATCHER_BOTTOM)) {
            opMode.idle();
            opMode.telemetry.addData("Status",  robot.isTouchSensorPressed(TOUCH_LATCHER_BOTTOM));
            opMode.telemetry.update();

        }
        opMode.telemetry.addData("Status", "Pressed");
        opMode.telemetry.update();

        robot.setDcMotorPower(MOTOR_LATCHER,0);

        robot.setServoPosition(SERVO_LATCHER, SERVO_LATCHER_POS_REST);
    }

    public void moveLatcherToBottom() {
        robot.setDcMotorPower(MOTOR_LATCHER, .2f);
        while (!robot.isTouchSensorPressed(TOUCH_LATCHER_BOTTOM)) {
            opMode.idle();
            opMode.telemetry.addData("Status", robot.isTouchSensorPressed(TOUCH_LATCHER_BOTTOM));
            opMode.telemetry.update();

        }
        robot.setDcMotorPower(MOTOR_LATCHER, 0);
    }


    //********** Tensor Flow Methods **********//

    private void initTfod() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = opMode.hardwareMap.get(WebcamName.class, "Webcam");

        //  Instantiate the Vuforia engine
        VuforiaLocalizer vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.

        /*
         * Configure Tensor Flow
         */
        int tfodMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

    public Constants.GoldObjectPosition readGoldObjectPosition() {
        if (tfod != null) {
            tfod.activate();
        }

        Constants.GoldObjectPosition goldObjectPosition = null;
        ElapsedTime time = new ElapsedTime();
        time.reset();

        while (time.seconds() < TENSOR_READING_TIME) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                if (updatedRecognitions.size() == 2) {
                    int goldMineralX = -1;
                    int silverMineralX = -1;
                    for (Recognition recognition : updatedRecognitions) {
                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = (int) recognition.getLeft();
                        } else if (silverMineralX == -1) {
                            silverMineralX = (int) recognition.getLeft();
                        }

                        if (goldMineralX != -1 && silverMineralX != -1) {
                            if (goldMineralX < silverMineralX) {
                                goldObjectPosition = Constants.GoldObjectPosition.CENTER;
                            } else if (goldMineralX > silverMineralX) {
                                goldObjectPosition = Constants.GoldObjectPosition.RIGHT;
                            }
                        } else if (goldMineralX == -1 && silverMineralX != 1) {
                            goldObjectPosition = Constants.GoldObjectPosition.LEFT;
                        }
                    }
                }
            }
        }
        if (tfod != null) {
            tfod.shutdown();
        }

        return goldObjectPosition;
    }
}