package org.firstinspires.ftc.teamcode.libraries;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;
import static org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus.LABEL_GOLD_MINERAL;
import static org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus.LABEL_SILVER_MINERAL;
import static org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus.TFOD_MODEL_ASSET;
import static org.firstinspires.ftc.teamcode.libraries.Constants.Direction.BACKWARD;
import static org.firstinspires.ftc.teamcode.libraries.Constants.Direction.FORWARD;
import static org.firstinspires.ftc.teamcode.libraries.Constants.Direction.LEFT;
import static org.firstinspires.ftc.teamcode.libraries.Constants.Direction.RIGHT;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_BACK_LEFT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_BACK_RIGHT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_FRONT_LEFT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_FRONT_RIGHT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.NEVEREST_40_REVOLUTION_ENCODER_COUNT;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TENSOR_READING_TIME;
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

    public void calcMove(float centimeters, float power, Constants.Direction direction) {
        // Calculates target encoder position
        final int targetPosition = (int) ((((centimeters / (Math.PI * WHEEL_DIAMETER)) *
                NEVEREST_40_REVOLUTION_ENCODER_COUNT)) * WHEEL_GEAR_RATIO);

        // Specific directions require different wheels to move for mecanum
        if (direction == FORWARD) {
            prepMotorsForCalcMove(targetPosition, targetPosition, targetPosition, targetPosition);
        } else if (direction == BACKWARD) {
            prepMotorsForCalcMove(-targetPosition, -targetPosition, -targetPosition, -targetPosition);
        } else if (direction == LEFT) {
            prepMotorsForCalcMove(-targetPosition, targetPosition, targetPosition, -targetPosition);
        } else if (direction == RIGHT) {
            prepMotorsForCalcMove(targetPosition, -targetPosition, -targetPosition, targetPosition);
        }

        setBaseMotorPowers(power);

        while (areBaseMotorsBusy()) {
            opMode.idle();
        }

        setBaseMotorPowers(0);
    }

    public void calcTurn(int degrees, float power) {
        // Calculates target encoder position
        int leftTargetPosition = (int) (2 * ((TRACK_DISTANCE) * degrees
                * NEVEREST_40_REVOLUTION_ENCODER_COUNT) /
                (WHEEL_DIAMETER * 360));

        prepMotorsForCalcMove(leftTargetPosition, -leftTargetPosition, leftTargetPosition, -leftTargetPosition);

        setBaseMotorPowers(power);

        while (areBaseMotorsBusy()) {
            opMode.idle();
        }

        setBaseMotorPowers(0);
    }

    private void setBaseMotorPowers(float power) {
        robot.setDcMotorPower(MOTOR_FRONT_LEFT_WHEEL, power);
        robot.setDcMotorPower(MOTOR_FRONT_RIGHT_WHEEL, power);
        robot.setDcMotorPower(MOTOR_BACK_LEFT_WHEEL, power);
        robot.setDcMotorPower(MOTOR_BACK_RIGHT_WHEEL, power);
    }

    private void prepMotorsForCalcMove(int frontLeftTargetPosition, int frontRightTargetPosition,
                                       int backLeftTargetPosition, int backRightTargetPosition) {
        robot.setDcMotorMode(MOTOR_FRONT_LEFT_WHEEL, STOP_AND_RESET_ENCODER);
        robot.setDcMotorMode(MOTOR_FRONT_RIGHT_WHEEL, STOP_AND_RESET_ENCODER);
        robot.setDcMotorMode(MOTOR_BACK_LEFT_WHEEL, STOP_AND_RESET_ENCODER);
        robot.setDcMotorMode(MOTOR_BACK_RIGHT_WHEEL, STOP_AND_RESET_ENCODER);

        robot.setDcMotorMode(MOTOR_FRONT_LEFT_WHEEL, RUN_TO_POSITION);
        robot.setDcMotorMode(MOTOR_FRONT_RIGHT_WHEEL, RUN_TO_POSITION);
        robot.setDcMotorMode(MOTOR_BACK_LEFT_WHEEL, RUN_TO_POSITION);
        robot.setDcMotorMode(MOTOR_BACK_RIGHT_WHEEL, RUN_TO_POSITION);

        robot.setDcMotorTargetPosition(MOTOR_FRONT_LEFT_WHEEL, frontLeftTargetPosition);
        robot.setDcMotorTargetPosition(MOTOR_FRONT_RIGHT_WHEEL, frontRightTargetPosition);
        robot.setDcMotorTargetPosition(MOTOR_BACK_LEFT_WHEEL, backLeftTargetPosition);
        robot.setDcMotorTargetPosition(MOTOR_BACK_RIGHT_WHEEL, backRightTargetPosition);
    }

    private boolean areBaseMotorsBusy() {
        return robot.isMotorBusy(MOTOR_FRONT_LEFT_WHEEL) || robot.isMotorBusy(MOTOR_FRONT_RIGHT_WHEEL) ||
                robot.isMotorBusy(MOTOR_BACK_LEFT_WHEEL) || robot.isMotorBusy(MOTOR_BACK_RIGHT_WHEEL);
    }


    //********** Latcher Methods **********//

    public void landOnGround() throws InterruptedException {
//        robot.setDcMotorPower(MOTOR_LATCHER, 0.5f);
//        // The motor will stop when it detects that it's on the ground
//        while (robot.getGroundDistanceCenti() >= 5.3) {
//            opMode.idle();
//        }
//
//        // Waiting for the latcher to raise enough so it can unlatch
//        Thread.sleep(1000);
//        robot.setDcMotorPower(MOTOR_LATCHER, 0f);
//
//        robot.setServoPosition(SERVO_LATCHER, SERVO_LATCHER_POS_REST);
    }

    public void moveLatcherToBottom() {
//        robot.setDcMotorPower(MOTOR_LATCHER, -.2f);
//        while (!robot.isTouchSensorPressed(TOUCH_LATCHER_BOTTOM)) {
//            opMode.idle();
//        }
//        robot.setDcMotorPower(MOTOR_LATCHER, 0);
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
