package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.components.commands.armPIDCommand;
import org.firstinspires.ftc.teamcode.components.commands.pivotPIDCommand;
import org.firstinspires.ftc.teamcode.components.constants;
import org.firstinspires.ftc.teamcode.components.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.components.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.components.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.components.subsystems.PivotSubsystem;


//@Config
@TeleOp(name = "Tele Op")
public class teleop extends CommandOpMode {

    private GamepadEx manipulatorController; // = new GamepadEx(gamepad2);
    private GamepadEx driveController; // = new GamepadEx(gamepad1);


    // initiate classes
    private ArmSubsystem armSubsystem; // = new ArmSubsystem();
    private PivotSubsystem pivotSubsystem; // = new PivotSubsystem();
    private IntakeSubsystem intakeSubsystem; // = new IntakeSubsystem();
    private DriveSubsystem driveSubsystem; // = new DriveSubsystem();



    @Override
    public void initialize() {
       // waitForStart();

        CommandScheduler.getInstance().reset();

        // assign the things to the things
        manipulatorController = new GamepadEx(gamepad2);
        driveController = new GamepadEx(gamepad1);

        armSubsystem = new ArmSubsystem(hardwareMap, "frontTelescope", "backTelescope");
        pivotSubsystem = new PivotSubsystem(hardwareMap, "leftPivot", "rightPivot");
        intakeSubsystem = new IntakeSubsystem(hardwareMap, "leftWrist", "rightWrist", "intake");
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

// when you hold triggers it manually moves pivot
        pivotSubsystem.setDefaultCommand(manipulatorController.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER), manipulatorController.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER));

// when you press x or a manually move elevator
        armSubsystem.setDefaultCommand(manipulatorController.getButton(GamepadKeys.Button.A), manipulatorController.getButton(GamepadKeys.Button.X));

// when you hold y or b it moves the wrist
// when you hold bumpers it rotates intake
        intakeSubsystem.setDefaultCommand(manipulatorController.getButton(GamepadKeys.Button.LEFT_BUMPER), manipulatorController.getButton(GamepadKeys.Button.RIGHT_BUMPER), manipulatorController.getButton(GamepadKeys.Button.B), manipulatorController.getButton(GamepadKeys.Button.Y));



// ~~~~~~~~~~ PRESS AND BUTTON AND DO SOMETHING ~~~~~~~~~~ //
        // TODO turn to positions

        //CommandScheduler.getInstance().setDefaultCommand(armSubsystem, armSubsystem.setMotors(0));



        // TODO manipulator set positions
        // button(button).onTrue(seq)

        // FIXME when these are uncommented the intake just spins for some reason
        //  - intake command never ends
        //      - change intake to manual only? that would fix issues + give more control

        // zero when you press dpad down
        new GamepadButton(manipulatorController, GamepadKeys.Button.DPAD_DOWN).whenPressed(zeroSeq());
        // go to intake position when dpad left
        new GamepadButton(manipulatorController, GamepadKeys.Button.DPAD_LEFT).whenPressed(intakeSeq());
        // go to mid basket when dpad right
        new GamepadButton(manipulatorController, GamepadKeys.Button.DPAD_RIGHT).whenPressed(medSeq());
        // go to high basket when dpad up
        new GamepadButton(manipulatorController, GamepadKeys.Button.DPAD_UP).whenPressed(highSeq());



        // useful telemetry


    }




    // ~~~~~~~~~~ COMMAND GROUPS FOR MANIPULATOR ~~~~~~~~~~ //


    public Command zeroSeq() { // when you press a button it moves manipulators to starting position
        return new SequentialCommandGroup(
               // new intakeCommand(intakeSubsystem, 0, 0), // FIXME intake command never ends

//                new armPIDCommand(armSubsystem, constants.armZero),
                new pivotPIDCommand(pivotSubsystem, constants.pivotZero)
        );
    }

    public Command intakeSeq() { // when you press a button it moves manipulators to ground
        return new ParallelCommandGroup(
                //new intakeCommand(intakeSubsystem, 0, 1), // FIXME intake command never ends

                new pivotPIDCommand(pivotSubsystem, constants.pivotDown)


//                ,new armPIDCommand(armSubsystem, constants.armMid)
        );
    }



    public Command medSeq() { // when you press a button it moves manipulators to middle basket
        return new ParallelCommandGroup(

                new pivotPIDCommand(pivotSubsystem, constants.pivotUp)


               // ,new armPIDCommand(armSubsystem, constants.armMid)
                // ,new intakeCommand(intakeSubsystem, 0, -1) // FIXME intake command never ends
        );
    }

    public Command highSeq() { // when you press a button it moves manipulators to high basket
        return new ParallelCommandGroup(

                new pivotPIDCommand(pivotSubsystem, constants.pivotUp)


//                ,new armPIDCommand(armSubsystem, constants.armHigh)
                // ,new intakeCommand(intakeSubsystem, 0, -1) // FIXME intake command never ends
        );
    }








} // end of class