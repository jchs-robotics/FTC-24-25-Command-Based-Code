package org.firstinspires.ftc.teamcode.components.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeSubsystem extends SubsystemBase {

    // needs 1 intake and 2 wrist
    private Servo LWrist;
    private Servo RWrist;

    private CRServo Intake; // CRServo allows the servo to spin forever

    private double wristPos;
    private double intakePower;


    public IntakeSubsystem() {
        LWrist = hardwareMap.get(Servo.class, "left wrist");
        RWrist = hardwareMap.get(Servo.class, "right wrist");

        Intake = hardwareMap.get(CRServo.class, "intake");
    }




  // spin stuff
    public Command setWrist(double setpoint) {
        LWrist.setPosition(setpoint);
        RWrist.setPosition(setpoint);
        return null;
    }

    public Command setIntake(double intakePower) {
        Intake.setPower(intakePower);
        return null;
    }




    public void setDefaultCommand(Boolean Lump, Boolean Rump, Boolean B, Boolean Y) {


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
            wristPos = 300;
        } else if (Y) {
            wristPos = 0;
        }

        LWrist.setPosition(wristPos);
        RWrist.setPosition(wristPos);
    }









    @Override
    public void periodic() {

    }

}
