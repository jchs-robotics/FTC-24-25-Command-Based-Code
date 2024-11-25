package org.firstinspires.ftc.teamcode.components.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import static java.lang.Math.abs;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorImpl;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class PivotSubsystem extends SubsystemBase {



    private DcMotor LPivot;// = hardwareMap.get(DcMotor.class, "left pivot");
    private DcMotor RPivot;// = HardwareMap.get(DcMotor.class, "right pivot");

    public double pivotEncoder;

     // Creates subsystem devices
    public PivotSubsystem(final HardwareMap hMap, final String LName, final String RName){
        LPivot = hMap.get(DcMotor.class, LName);
        RPivot = hMap.get(DcMotor.class, RName);

        RPivot.setDirection(DcMotorSimple.Direction.REVERSE);

        // UP IS POSITIVE
       // pivotEncoder = LPivot.getCurrentPosition();

        LPivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RPivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }




    // rotates pivot
    public void setMotors(double speed) {
        LPivot.setPower(speed);
        RPivot.setPower(speed);
    }



    public void setDefaultCommand(double LPower, double RPower) {
//        LPivot.setPower(Math.pow((RPower - LPower), 3));
//        RPivot.setPower(Math.pow((RPower - LPower), 3));
        if (LPower > 0.15) { // lower
            LPivot.setPower(-0.75);
            RPivot.setPower(-0.75);
        } else if (RPower > 0.15) { // raise
            LPivot.setPower(0.75);
            RPivot.setPower(0.75);
        } else {
            LPivot.setPower(0);
            RPivot.setPower(0);
        }

    }



//    public double getPivotPos() {
//        return pivotEncoder;
//    }
//
//    public boolean isAtPivotPos() {
//        double error = getPivotPos() -
//    }



    @Override
    public void periodic() {

            //telemetry.addData("Left Pivot Encoder: ", pivotEncoder);

        pivotEncoder = LPivot.getCurrentPosition();

    }


    public boolean tolerance(double point) {
        return pivotEncoder < (point + 2.5) && pivotEncoder > (point - 2.5);
    }



}
