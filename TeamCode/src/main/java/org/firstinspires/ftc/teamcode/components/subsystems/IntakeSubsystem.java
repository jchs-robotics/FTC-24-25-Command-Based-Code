package org.firstinspires.ftc.teamcode.components.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeSubsystem extends SubsystemBase {

    // needs 1 intake and 2 wrist
    // CRServo allows the servo to spin forever
    private CRServo LWrist;// = hardwareMap.get(CRServo.class, "leftWrist");
    private CRServo RWrist;// = hardwareMap.get(CRServo.class, "rightWrist");

    private CRServo Intake;// = hardwareMap.get(CRServo.class, "intake");

    private double wristPower;
    private double intakePower;



    public IntakeSubsystem(final HardwareMap hMap, final String LName, final String RName, final String intakeName){
        LWrist = hMap.get(CRServo.class, LName);
        RWrist = hMap.get(CRServo.class, RName);

        LWrist.setDirection(CRServo.Direction.REVERSE);

        Intake = hMap.get(CRServo.class, intakeName);
    }


//    public IntakeSubsystem() {
//        LWrist = hardwareMap.get(CRServo.class, "leftWrist");
//        RWrist = hardwareMap.get(CRServo.class, "rightWrist");
//
//        Intake = hardwareMap.get(CRServo.class, "intake");
//    }




  // spin stuff
    public Command setWrist(double wristPower) {
        LWrist.setPower(wristPower);
        RWrist.setPower(wristPower);
        return null;
    }

    public Command setIntake(double intakePower) {
        Intake.setPower(intakePower);
        return null;
    }




    public void setDefaultCommand(boolean Lump, boolean Rump, boolean B, boolean Y) {


        // intake
        if (Rump) {
            intakePower = 1;
        } else if (Lump) {
            intakePower = -1;
        } else {
            intakePower = 0;
        }

        Intake.setPower(intakePower);



        // wrist
        if (B) {
            wristPower = -1;
        } else if (Y) {
            wristPower = 1;
        } else {
            wristPower = 0;
        }

        LWrist.setPower(wristPower);
        RWrist.setPower(wristPower);
    }









    @Override
    public void periodic() {

    }

}
