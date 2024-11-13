package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.TriggerReader;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.components.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.components.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.components.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.components.subsystems.PivotSubsystem;


//@Config
@TeleOp(name = "Tele Op")
public class teleop extends CommandOpMode {
    private GamepadEx manipulatorController = new GamepadEx(gamepad2);
    private GamepadEx driveController = new GamepadEx(gamepad1);


    // manipulator buttons
    // set positions (command groups)
    Button mDown = new GamepadButton(manipulatorController, GamepadKeys.Button.DPAD_DOWN);
    Button mLeft = new GamepadButton(manipulatorController, GamepadKeys.Button.DPAD_LEFT);
    Button mRight = new GamepadButton(manipulatorController, GamepadKeys.Button.DPAD_RIGHT);


    // manual control
    Button mA = new GamepadButton(manipulatorController, GamepadKeys.Button.A);
    Button mB = new GamepadButton(manipulatorController, GamepadKeys.Button.B);

    Button mLeftBumper = new GamepadButton(manipulatorController, GamepadKeys.Button.LEFT_BUMPER);
    Button mRightBumper = new GamepadButton(manipulatorController, GamepadKeys.Button.RIGHT_BUMPER);

    TriggerReader mRightTrigger = new TriggerReader(manipulatorController, GamepadKeys.Trigger.RIGHT_TRIGGER);
    TriggerReader mLeftTrigger = new TriggerReader(manipulatorController, GamepadKeys.Trigger.LEFT_TRIGGER);








    // initiate classes
    ArmSubsystem armSubsystem = new ArmSubsystem();
    PivotSubsystem pivotSubsystem = new PivotSubsystem();
    IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    DriveSubsystem driveSubsystem = new DriveSubsystem();



    @Override
    public void initialize() {
        CommandScheduler.getInstance().reset();
        driveSubsystem.initializeDrive();
    }

    @Override
    public void run() {
        CommandScheduler.getInstance().run();


// ~~~~~~~~~~ MANUAL CONTROLS ~~~~~~~~~~ //

// joystick movement for drive
        driveSubsystem.setDefaultCommand(driveController.getLeftX(), driveController.getLeftY(), driveController.getRightX());

// when you hold triggers it manually moves pivot
        pivotSubsystem.setDefaultCommand(manipulatorController.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER), manipulatorController.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER));

// when you press x or a manually move elevator
        armSubsystem.setDefaultCommand(manipulatorController.getButton(GamepadKeys.Button.A), manipulatorController.getButton(GamepadKeys.Button.X));

// when you hold y or b it moves the wrist
// when you hold bumpers it rotates intake
        intakeSubsystem.setDefaultCommand(manipulatorController.getButton(GamepadKeys.Button.LEFT_BUMPER), manipulatorController.getButton(GamepadKeys.Button.RIGHT_BUMPER), manipulatorController.getButton(GamepadKeys.Button.B), manipulatorController.getButton(GamepadKeys.Button.Y));



        // TODO turn to positions

        CommandScheduler.getInstance().setDefaultCommand(armSubsystem, armSubsystem.setMotors(0));
























//    // manual manipulator controls ----- THESE TURNED INTO THE DEFAULT COMMANDS
//
//        // arm control
//        mA.whileHeld(armSubsystem.setMotors(1)); // extend arm
//        mB.whileHeld(armSubsystem.setMotors(-1)); // retract arm
//
//        // pivot control
//        if (mLeftTrigger.isDown()) {
//            pivotSubsystem.setMotors(-0.5); // pivot go backwards
//        }
//        if (mRightTrigger.isDown()) {
//            pivotSubsystem.setMotors(0.5); // pivot go forwards
//        }
//
//        // intake control
//        mLeftBumper.whenPressed(intakeSubsystem.setIntake(1)); // intake
//        mRightBumper.whenPressed(intakeSubsystem.setIntake(-1)); // outtake




    }














}
