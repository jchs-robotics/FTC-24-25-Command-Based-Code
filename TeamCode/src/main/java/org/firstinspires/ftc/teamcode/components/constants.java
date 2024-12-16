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
// tune a letter until it starts oscillation
// P > D > I
    // FIXME will have to change PID if mechanical changes stuff (make chain better? please)

    // TODO tune arm PID
    //  slower than teleop :(
    public static double armP = 0.07;
    public static double armI = 0.0001;
    public static double armD = 0.001;

    // TODO tune pivot PID
    //  slower than teleop :(
    public static double pivotP = 0.05; // 0.15
    public static double pivotI = 0.00; // 0.001
    public static double pivotD = 0.00; // 0.01, 0.009




// setpoints

    // pivot setpoints
    public static double pivotUp = 1000;
    public static double pivotZero = 0;
    public static double pivotDown = -350;

    // arm setpoints
    public static double armHigh = 1500;
    public static double armMid = 1000;
    public static double armZero = 200;


}