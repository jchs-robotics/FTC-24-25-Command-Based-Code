package org.firstinspires.ftc.teamcode.opmodes.teleop;


import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Base64;

@TeleOp(name = "Telemetry :3")
public class encoder_positition_WOOOO_I_LOVE_ENCODERS extends OpMode {


    //private final Gamepad manipulatorController = new Gamepad();

    private DcMotor LPivot;// = hardwareMap.get(DcMotor.class, "leftPivot");
    private DcMotor RPivot;

    private DcMotor FArm;// = hardwareMap.get(DcMotor.class, "frontTelescope");
    private DcMotor BArm;


    @Override
    public void init() {

        LPivot = hardwareMap.get(DcMotor.class, "leftPivot");
        RPivot = hardwareMap.get(DcMotor.class, "rightPivot");

        RPivot.setDirection(DcMotorSimple.Direction.REVERSE);

        LPivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RPivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        FArm = hardwareMap.get(DcMotor.class, "frontTelescope");
        BArm = hardwareMap.get(DcMotor.class, "backTelescope");

        FArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        // reset encoders

    }

    @Override
    public void loop() {


        // TODO make sure to adjust so it doesnt break :(
        // manual arm
        if (gamepad2.a) { // retract
            BArm.setPower(0.5);
            FArm.setPower(0.5);
        } else if (gamepad2.x) { // extend
            BArm.setPower(-0.5);
            FArm.setPower(-0.5);
        } else {
            BArm.setPower(0);
            FArm.setPower(0);
        }

        // manual pivotd
        if (gamepad2.left_trigger > 0.15) { // lowers pivot
            LPivot.setPower(0.5);
            RPivot.setPower(0.5);
        } else if (gamepad2.right_trigger > 0.15) { // raises pivot
            LPivot.setPower(-0.5);
            RPivot.setPower(-0.5);
        } else {
            LPivot.setPower(0);
            RPivot.setPower(0);
        }


        double LPivotEnc = LPivot.getCurrentPosition();
        double FArmEnc = FArm.getCurrentPosition();


        telemetry.addData("Left Pivot Encoder Position: ", LPivotEnc);
        telemetry.addData("Front Arm Encoder Position: ", FArmEnc);



    }


}
