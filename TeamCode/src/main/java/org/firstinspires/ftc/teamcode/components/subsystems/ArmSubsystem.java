package org.firstinspires.ftc.teamcode.components.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ArmSubsystem extends SubsystemBase {



    private DcMotor FArm;// = hardwareMap.get(DcMotor.class, "left pivot");
    private DcMotor BArm;// = hardwareMap.get(DcMotor.class, "right pivot");



    public double armEncoder;// = LArm.getCurrentPosition();

    private double manualPower;



    // Creates subsystem devices
    public ArmSubsystem(final HardwareMap hMap, final String FName, final String BName){
        FArm = hMap.get(DcMotor.class, FName);
        BArm = hMap.get(DcMotor.class, BName);

        //BArm.setDirection(DcMotorSimple.Direction.REVERSE);

        // armEncoder = FArm.getCurrentPosition();
        FArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }



    // extend/retract telescope
    public void setMotors(double speed) {
        FArm.setPower(speed);
        BArm.setPower(speed);
    }












    @Override
    public void periodic() {
        armEncoder = FArm.getCurrentPosition();
    }

    public boolean tolerance(double point) {
        return armEncoder < (point + 2.5) && armEncoder > (point - 2.5);
    }


    public void setDefaultCommand(boolean A, boolean X) {

//        if (A) { // retract
//            if (armEncoder < -200) {
//                manualPower = 0.9;
//            } else if (armEncoder > -200) {
//                manualPower = 0.5;
//            }
//        } else if (X && armEncoder > -1500) { // extend
//            if (armEncoder > -1500) {
//                manualPower = -0.9;
//            } else if (armEncoder < -1500) {
//                manualPower = -0.5;
//            }
//        } else {
//            manualPower = 0;
//        }

        if (A) { // retract
            if (armEncoder < -200) {
                manualPower = 0.9;
            } else if (armEncoder > -200) {
                manualPower = 0.0;
            }
        } else if (X) { // extend
            manualPower = -0.9;
        } else {
            manualPower = 0;
        }

        FArm.setPower(manualPower);
        BArm.setPower(manualPower);

    }


}
