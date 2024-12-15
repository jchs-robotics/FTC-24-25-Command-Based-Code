package org.firstinspires.ftc.teamcode.components.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class DriveSubsystem extends SubsystemBase {

    private DcMotor FLDrive;// = hardwareMap.get(DcMotor.class, "leftFront");
    private DcMotor BLDrive;// = hardwareMap.get(DcMotor.class, "leftBack");
    private DcMotor FRDrive;// = hardwareMap.get(DcMotor.class, "rightFront");
    private DcMotor BRDrive;// = hardwareMap.get(DcMotor.class, "rightBack");

    private IMU imu;

    public double FLEncoder;

    //FIXME Adjust the orientation parameters to match your robot
    IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
            RevHubOrientationOnRobot.LogoFacingDirection.UP,
            RevHubOrientationOnRobot.UsbFacingDirection.RIGHT));




    public DriveSubsystem(final HardwareMap hMap, final String FLName, final String BLName, final String FRName, final String BRName, final String imuName) {
        FLDrive = hMap.get(DcMotor.class, FLName);
        BLDrive = hMap.get(DcMotor.class, BLName);
        FRDrive = hMap.get(DcMotor.class, FRName);
        BRDrive = hMap.get(DcMotor.class, BRName);

        imu = hMap.get(IMU.class, imuName);
    }

//    public DriveSubsystem(){
//        FLDrive = hardwareMap.get(DcMotor.class, "leftFront");
//        BLDrive = hardwareMap.get(DcMotor.class, "leftBack");
//        FRDrive = hardwareMap.get(DcMotor.class, "rightFront");
//        BRDrive = hardwareMap.get(DcMotor.class, "rightBack");
//
//        imu = hardwareMap.get(IMU.class, "imu");
//    }

    public void initializeDrive() {
        imu.initialize(parameters);

        FLDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        BLDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        FLDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BLDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FRDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BRDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    public void Drive(double FLPower, double BLPower, double FRPower, double BRPower) {
// move the motors
        FLDrive.setPower(FLPower);
        BLDrive.setPower(BLPower);
        FRDrive.setPower(FRPower);
        BRDrive.setPower(BRPower);
    }


    public void fwd(double power) {
        FLDrive.setPower(power);
        BLDrive.setPower(power);
        FRDrive.setPower(power);
        BRDrive.setPower(power);
    }
    public void rev(double power) {
        FLDrive.setPower(-power);
        BLDrive.setPower(-power);
        FRDrive.setPower(-power);
        BRDrive.setPower(-power);
    }
    public void right(double power) {
        FLDrive.setPower(power);
        BLDrive.setPower(-power);
        FRDrive.setPower(-power);
        BRDrive.setPower(power);
    }
    public void left(double power) {
        FLDrive.setPower(-power);
        BLDrive.setPower(power);
        FRDrive.setPower(power);
        BRDrive.setPower(-power);
    }


    public void stop() {
        FLDrive.setPower(0);
        BLDrive.setPower(0);
        FRDrive.setPower(0);
        BRDrive.setPower(0);
    }





    @Override
    public void periodic() {
        FLEncoder = FLDrive.getCurrentPosition();
    }

    public boolean tolerance(double point) {
        return FLEncoder < (point + 2.5) && FLEncoder > (point - 2.5);
    }




    public void resetYaw() {
        imu.resetYaw();
    }








    public void setDefaultCommand(double inputX, double inputY, double inputRX) {

        // gets gyroscope stuff
        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        // Rotate the movement direction counter to the bot's rotation
        double rotX = inputX * Math.cos(-botHeading) - inputY * Math.sin(-botHeading);
        double rotY = inputX * Math.sin(-botHeading) + inputY * Math.cos(-botHeading);

        rotX = rotX * 1.1;  // Counteract imperfect strafing

        // makes all motors spin at the like same ratio(?)
        // makes it so it doesnt drift weirdly yk?
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(inputRX), 1);

        double FLPower = (rotY + rotX + inputRX) / denominator;
        double BLPower = (rotY - rotX + inputRX) / denominator;
        double FRPower = (rotY - rotX - inputRX) / denominator;
        double BRPower = (rotY + rotX - inputRX) / denominator;

        FLDrive.setPower(FLPower);
        BLDrive.setPower(BLPower);
        FRDrive.setPower(FRPower);
        BRDrive.setPower(BRPower);
    }









}
