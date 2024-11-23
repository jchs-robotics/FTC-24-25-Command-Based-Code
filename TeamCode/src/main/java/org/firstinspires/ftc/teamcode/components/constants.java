package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.HardwareMap;

public class constants {
// SUBSYSTEM CONSTANTS
    // DRIVE
    public DcMotor FLDrive;
    public DcMotor BLDrive; // no Ei, not boy love
    public DcMotor FRDrive;
    public DcMotor BRDrive;

    // TELESCOPE
    public DcMotor LArm;
    public DcMotor RArm;


    //PIVOT
    public DcMotor LPivot;
    public DcMotor RPivot;

    // INTAKE
    public Servo intake;

    // WRIST
    public Servo LWrist;
    public Servo RWrist;

    // ENCODERS
//    public double pivotEncoder = pivotLeft.getCurrentPosition();


// COMMAND CONSTANTS
    // DRIVE

    // ARM

    // PIVOT

    // INTAKE




// PID CONSTANTS
// P > D > I
    public static double armP = 0.1;
    public static double armI = 0;
    public static double armD = 0;

    public static double pivotP = 0.1;
    public static double pivotI = 0;
    public static double pivotD = 0;


}
