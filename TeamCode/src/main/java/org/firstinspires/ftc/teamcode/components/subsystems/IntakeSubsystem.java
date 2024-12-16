package org.firstinspires.ftc.teamcode.components.subsystems;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
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






  // spin stuff
    public void setWrist(double wristPower) {
        LWrist.setPower(wristPower);
        RWrist.setPower(wristPower);
    }

    public void setIntake(double intakePower) {
        Intake.setPower(intakePower);
    }




    public void setDefaultCommand(boolean Lump, boolean Rump, boolean B, boolean Y, double RTrigger, double LTrigger) {


        if (Rump) {
            intakePower = 1;
        } else if (Lump) {
            intakePower = -1;
        } else {
            intakePower = 0;
        }

        Intake.setPower(intakePower);


// button
        // wrist
        if (Y) {
            wristPower = -1; // wrist down
        } else if (B) {
            wristPower = 1; // wrist up
        }
// triggers
        else if (RTrigger > 0.15) { // wrist up
            wristPower = 0.15;
        } else if (LTrigger > 0.15) { // wrist down
            wristPower = -0.15;
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
