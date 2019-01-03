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
import static org.firstinspires.ftc.teamcode.libraries.Constants.ENCODER_MARGIN;
import static org.firstinspires.ftc.teamcode.libraries.Constants.GOBILDA_MOTOR_ENCODER_COUNTS_PER_REVOLUTION;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_LATCHER;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_LEFT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_LINEAR_SLIDE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.MOTOR_RIGHT_WHEEL;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_DEPOSIT;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_DEPOSIT_ANGLE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_INTAKE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_INTAKE_ANGLE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_INTAKE_ANGLE_POS_INTAKE;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_INTAKE_SPEED;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_LATCHER;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_LATCHER_POS_GRAB;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_LATCHER_POS_REST;
import static org.firstinspires.ftc.teamcode.libraries.Constants.SERVO_OUTTAKE_SPEED;
//import static org.firstinspires.ftc.teamcode.libraries.Constants.TENSOR_READING_TIME;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TENSOR_READING_TIME;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TOUCH_LATCHER_BOTTOM;
import static org.firstinspires.ftc.teamcode.libraries.Constants.TRACK_DISTANCE;
//import static org.firstinspires.ftc.teamcode.libraries.Constants.VUFORIA_KEY;
import static org.firstinspires.ftc.teamcode.libraries.Constants.VUFORIA_KEY;
import static org.firstinspires.ftc.teamcode.libraries.Constants.WHEEL_DIAMETER;

/*
 * Title: AutoLib
 * Date Created: 10/28/2018
 * Date Modified: 11/27/2018
 * Author: Rahul, Poorvi, Varnika
 * Type: Library
 * Description: This will contain the methods for Autonomous, and other autonomous-related programs.
 */

public class AutoLib {
    private Robot robot;
    private LinearOpMode opMode;

    private TFObjectDetector tfod;

    public AutoLib(LinearOpMode opMode) {
        robot = new Robot(opMode);
        this.opMode = opMode;

        initTfod();
    }

    public void calcMove(float centimeters, float power) {
        // Calculate how many encoder counts to move
        final int targetPosition = (int) ((centimeters / (Math.PI * WHEEL_DIAMETER)) *
                GOBILDA_MOTOR_ENCODER_COUNTS_PER_REVOLUTION);

        prepMotorsForCalcMove(targetPosition, targetPosition);

        robot.setDcMotorPower(MOTOR_LEFT_WHEEL, power);
        robot.setDcMotorPower(MOTOR_RIGHT_WHEEL, power);

        // Stays in this while loop until the motors are done moving to their positionp
        while (areBaseMotorsBusy()) {
            opMode.idle();
        }

        robot.setDcMotorPower(MOTOR_LEFT_WHEEL, 0);
        robot.setDcMotorPower(MOTOR_RIGHT_WHEEL, 0);
    }

    public void calcTurn(int degrees, float power) {
        int leftTargetPosition = (int) (2 * ((TRACK_DISTANCE) * degrees
                * GOBILDA_MOTOR_ENCODER_COUNTS_PER_REVOLUTION) /
                (WHEEL_DIAMETER * 360));
        int rightTargetPosition = -leftTargetPosition;

        float leftPower;
        float rightPower;

        // Will switch powers if turning the other way
        if (degrees < 0) {
            leftPower = power;
            rightPower = -power;
        } else {
            leftPower = -power;
            rightPower = power;
        }

        prepMotorsForCalcMove(leftTargetPosition, rightTargetPosition);

        robot.setDcMotorPower(MOTOR_LEFT_WHEEL, leftPower);
        robot.setDcMotorPower(MOTOR_RIGHT_WHEEL, rightPower);

        // Stays in this while loop until the motors are done moving to their position
        while (areBaseMotorsBusy()) {
            opMode.idle();
        }

        robot.setDcMotorPower(MOTOR_LEFT_WHEEL, 0);
        robot.setDcMotorPower(MOTOR_RIGHT_WHEEL, 0);
    }

    private void prepMotorsForCalcMove(int leftTargetPosition, int rightTargetPosition) {
        robot.setDcMotorMode(MOTOR_LEFT_WHEEL, STOP_AND_RESET_ENCODER);
        robot.setDcMotorMode(MOTOR_RIGHT_WHEEL, STOP_AND_RESET_ENCODER);

        robot.setDcMotorMode(MOTOR_LEFT_WHEEL, RUN_TO_POSITION);
        robot.setDcMotorMode(MOTOR_RIGHT_WHEEL, RUN_TO_POSITION);

        robot.setDcMotorTargetPosition(MOTOR_LEFT_WHEEL, leftTargetPosition);
        robot.setDcMotorTargetPosition(MOTOR_RIGHT_WHEEL, rightTargetPosition);
    }

    private boolean areBaseMotorsBusy() {
        return robot.isMotorBusy(MOTOR_LEFT_WHEEL) || robot.isMotorBusy(MOTOR_RIGHT_WHEEL);
    }

    public void landOnGround() throws InterruptedException {
        robot.setDcMotorPower(MOTOR_LATCHER, 0.5f);
        // The motor will stop when it detects that it's on the ground
        while (robot.getGroundDistanceCenti() >= 5.3) {
            opMode.idle();
        }

        // Waiting for the latcher to raise enough so it can unlatch
        Thread.sleep(1000);
        robot.setDcMotorPower(MOTOR_LATCHER, 0f);

        robot.setServoPosition(SERVO_LATCHER, SERVO_LATCHER_POS_REST);
    }


    // SupportOp Methods
    public void moveLatcherToBottom() {
        robot.setDcMotorPower(MOTOR_LATCHER, -.2f);
        while (!robot.isTouchSensorPressed(TOUCH_LATCHER_BOTTOM)) {
            opMode.idle();
        }
        robot.setDcMotorPower(MOTOR_LATCHER, 0);
    }


    // Tensor Flow methods
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

    public Constants.GoldObjectPosition readGoldObjectPosition() throws InterruptedException {
        if (tfod != null) {
            tfod.activate();
        }

        if (tfod == null) {
            opMode.telemetry.addData("TFOD", "Null");
            opMode.telemetry.update();
            Thread.sleep(10000);
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

    public void moveLinearSlide(int positionValue, float power) {
        robot.setDcMotorMode(MOTOR_LINEAR_SLIDE, STOP_AND_RESET_ENCODER);
        robot.setDcMotorMode(MOTOR_LINEAR_SLIDE, RUN_TO_POSITION);
        robot.setDcMotorTargetPosition(MOTOR_LINEAR_SLIDE, positionValue);

        robot.setDcMotorPower(MOTOR_LINEAR_SLIDE, power);

        while (robot.isMotorBusy(MOTOR_LINEAR_SLIDE)) {
            opMode.idle();
        }

        robot.setDcMotorPower(MOTOR_LINEAR_SLIDE, 0);
    }

    public void depositMarker() {
        ElapsedTime time = new ElapsedTime();
        time.reset();
        robot.setServoPosition(SERVO_INTAKE, SERVO_INTAKE_SPEED);
        while (time.seconds() < 2) {
            opMode.idle();
        }
        robot.setServoPosition(SERVO_INTAKE, .5f);
    }

    public void setServoAngle() throws InterruptedException {
        robot.setServoPosition(SERVO_INTAKE_ANGLE, 0);
        Thread.sleep(2000);
        robot.setServoPosition(SERVO_INTAKE_ANGLE, 1);
        Thread.sleep(2000);
    }

    //Marker Dropping Servo
    public void setServoPosition() throws InterruptedException {
        robot.setServoPosition(SERVO_DEPOSIT_ANGLE, 0); // TODO:
        Thread.sleep(2000);
        robot.setServoPosition(SERVO_DEPOSIT_ANGLE, 1); //TODO
    }
}
