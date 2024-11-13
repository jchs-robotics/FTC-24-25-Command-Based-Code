package org.firstinspires.ftc.teamcode.components.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorImpl;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class PivotSubsystem extends SubsystemBase {



    private DcMotor LPivot;// = hardwareMap.get(DcMotor.class, "left pivot");
    private DcMotor RPivot;// = HardwareMap.get(DcMotor.class, "right pivot");

    public double pivotEncoder = LPivot.getCurrentPosition();

    // Creates subsystem devices
//    public PivotSubsystem(final HardwareMap hMap, final String LName, final String RName){
//        LPivot = hMap.get(DcMotor.class, LName);
//        RPivot = hMap.get(DcMotor.class, RName);
//
//        RPivot.setDirection(DcMotorSimple.Direction.REVERSE);
//    }
    public PivotSubsystem(){

        LPivot = hardwareMap.get(DcMotor.class, "left pivot");
        RPivot = hardwareMap.get(DcMotor.class, "right pivot");



        RPivot.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    // rotates pivot
    public void setMotors(double speed) {
        LPivot.setPower(speed);
        RPivot.setPower(speed);
    }



    public void setDefaultCommand(double LPower, double RPower) {
        LPivot.setPower(LPower - RPower);
        RPivot.setPower(LPower - RPower);
    }









    @Override
    public void periodic() {

    }
}
