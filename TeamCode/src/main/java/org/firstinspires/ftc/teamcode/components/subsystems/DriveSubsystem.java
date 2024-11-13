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

    private DcMotor FLDrive = hardwareMap.get(DcMotor.class, "left_front_drive");
    private DcMotor BLDrive = hardwareMap.get(DcMotor.class, "left_back_drive");
    private DcMotor FRDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
    private DcMotor BRDrive = hardwareMap.get(DcMotor.class, "right_back_drive");

    private IMU imu = hardwareMap.get(IMU.class, "imu");

    //FIXME Adjust the orientation parameters to match your robot
    IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
            RevHubOrientationOnRobot.LogoFacingDirection.UP,
            RevHubOrientationOnRobot.UsbFacingDirection.RIGHT));


    public DriveSubsystem(){
    }

    public void initializeDrive() {
        imu.initialize(parameters);
    }


    public void Drive() {
// move the motors
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







    @Override
    public void periodic() {
    }

}
