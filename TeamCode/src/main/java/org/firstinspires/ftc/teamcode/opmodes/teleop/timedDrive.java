package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "Timed + Telemetry")
public class timedDrive extends OpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontMotor;
    private DcMotor leftBackMotor;
    private DcMotor rightFrontMotor;
    private DcMotor rightBackMotor;
    private IMU imu;


    private DcMotor pivotL;
    private DcMotor pivotR;
    private DcMotor telescopeF;
    private DcMotor telescopeB;

    private CRServo intake;
    private CRServo wristL;
    private CRServo wristR;



    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");


        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
        leftFrontMotor  = hardwareMap.get(DcMotor.class, "leftFront");
        leftBackMotor  = hardwareMap.get(DcMotor.class, "leftBack");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFront");
        rightBackMotor = hardwareMap.get(DcMotor.class, "rightBack");

        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightBackMotor.setDirection(DcMotor.Direction.FORWARD);



        pivotL = hardwareMap.get(DcMotor.class, "leftPivot");
        pivotR = hardwareMap.get(DcMotor.class, "rightPivot");
        telescopeF = hardwareMap.get(DcMotor.class, "frontTelescope");
        telescopeB = hardwareMap.get(DcMotor.class, "backTelescope");


        pivotR.setDirection(DcMotorSimple.Direction.REVERSE);


        pivotL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pivotR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telescopeB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telescopeF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //telescopeF.setDirection(DcMotorSimple.Direction.REVERSE);

        wristL = hardwareMap.get(CRServo.class, "leftWrist");
        wristR = hardwareMap.get(CRServo.class, "rightWrist");
        intake = hardwareMap.get(CRServo.class, "intake");

        wristR.setDirection(CRServo.Direction.REVERSE);





        // Retrieve the IMU from the hardware map
        imu = hardwareMap.get(IMU.class, "imu");
        //FIXME Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.RIGHT));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);


        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit START
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits START
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits START but before they hit STOP
     */
    @Override
    public void loop() {

        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        // reset heading
        // FIXME adjust to whatever we fw heavy vro
        if (gamepad1.back) {
            imu.resetYaw();
        }

        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);


        // Rotate the movement direction counter to the bot's rotation
        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX = rotX * 1.1;  // Counteract imperfect strafing


        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);

        double frontLeftPower = (rotY + rotX + rx) / denominator;
        double backLeftPower = (rotY - rotX + rx) / denominator;
        double frontRightPower = (rotY - rotX - rx) / denominator;
        double backRightPower = (rotY + rotX - rx) / denominator;

        leftFrontMotor.setPower(frontLeftPower);
        leftBackMotor.setPower(backLeftPower);
        rightFrontMotor.setPower(frontRightPower);
        rightBackMotor.setPower(backRightPower);





        // MANIPULATORs
        // TODO uncomment and initisalize motors

        // manual pivotd
        if (gamepad2.left_trigger > 0.15) { // lowers pivot
            pivotL.setPower(-0.5);
            pivotR.setPower(-0.5);
        } else if (gamepad2.right_trigger > 0.15) { // raises pivot
            pivotL.setPower(0.5);
            pivotR.setPower(0.5);
        } else {
            pivotL.setPower(0);
            pivotR.setPower(0);
        }


        // TODO make sure to adjust so it doesnt break :(
        // manual arm
        if (gamepad2.a) { // retract
            telescopeB.setPower(0.5);
            telescopeF.setPower(0.5);
        } else if (gamepad2.x) { // extend
            telescopeB.setPower(-0.5);
            telescopeF.setPower(-0.5);
        } else {
            telescopeB.setPower(0);
            telescopeF.setPower(0);
        }


        // manual intake
        if (gamepad2.right_bumper) { // intake
            intake.setPower(1);
        } else if (gamepad2.left_bumper) { // outtake
            intake.setPower(-1);
        } else {
            intake.setPower(0);
        }


        // wrist
        if (gamepad2.y) { // wrist forward
            wristL.setPower(1);
            wristR.setPower(1);
        } else if (gamepad2.b) { // wrist back
            wristL.setPower(-1);
            wristR.setPower(-1);
        } else {
            wristL.setPower(0);
            wristR.setPower(0);
        }


        telemetry.addData("Left pivot encoder position", pivotL.getCurrentPosition());
        telemetry.addData("Right pivot encoder position", pivotR.getCurrentPosition());

        telemetry.addData(" ", " ");

        telemetry.addData("Left telescope encoder position", telescopeF.getCurrentPosition());
        telemetry.addData("Right telescope encoder position", telescopeB.getCurrentPosition());

        telemetry.addData(" ", " ");

        telemetry.addData("IMU", imu.getRobotYawPitchRollAngles());

    }
}
