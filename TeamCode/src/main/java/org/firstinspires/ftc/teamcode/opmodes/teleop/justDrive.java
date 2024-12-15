package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.components.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.components.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.components.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.components.subsystems.PivotSubsystem;

@TeleOp(name = "Drivetrain")
public class justDrive extends CommandOpMode {
    private GamepadEx driveController; // = new GamepadEx(gamepad1);


    private DriveSubsystem driveSubsystem;

    @Override
    public void initialize() {
        // waitForStart();

        CommandScheduler.getInstance().reset();

        // assign the things to the things
        driveController = new GamepadEx(gamepad1);

        driveSubsystem = new DriveSubsystem(hardwareMap, "leftFront", "leftBack", "rightFront", "rightBack", "imu");

        driveSubsystem.initializeDrive();
    }


    @Override
    public void run() {
        CommandScheduler.getInstance().run();

        if (gamepad1.back) {
            driveSubsystem.resetYaw();
        }
// ~~~~~~~~~~ MANUAL CONTROLS ~~~~~~~~~~ //

// joystick movement for drive
        driveSubsystem.setDefaultCommand(driveController.getLeftX(), driveController.getLeftY(), driveController.getRightX());


    }


}
