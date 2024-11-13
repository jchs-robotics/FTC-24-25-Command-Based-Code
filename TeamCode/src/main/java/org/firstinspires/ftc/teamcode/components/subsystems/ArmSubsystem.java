package org.firstinspires.ftc.teamcode.components.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class ArmSubsystem extends SubsystemBase {



    private DcMotor LArm;// = hardwareMap.get(DcMotor.class, "left pivot");
    private DcMotor RArm;// = HardwareMap.get(DcMotor.class, "right pivot");

    public double armEncoder = LArm.getCurrentPosition();

    private double manualPower;

    // Creates subsystem devices
//    public PivotSubsystem(final HardwareMap hMap, final String LName, final String RName){
//        LPivot = hMap.get(DcMotor.class, LName);
//        RPivot = hMap.get(DcMotor.class, RName);
//
//        RPivot.setDirection(DcMotorSimple.Direction.REVERSE);
//    }
    public ArmSubsystem(){

        LArm = hardwareMap.get(DcMotor.class, "left arm");
        RArm = hardwareMap.get(DcMotor.class, "right arm");

        RArm.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    // extend/retract telescope
    public Command setMotors(double speed) {
        LArm.setPower(speed);
        RArm.setPower(speed);

        return null;
    }



    public void setDefaultCommand(Boolean A, Boolean X) {
        if (A) {
            manualPower = 0.9;
        } else if (X) {
            manualPower = -0.9;
        } else {
            manualPower = 0;
        }

        LArm.setPower(manualPower);
        RArm.setPower(manualPower);

    }








    @Override
    public void periodic() {

    }
}
