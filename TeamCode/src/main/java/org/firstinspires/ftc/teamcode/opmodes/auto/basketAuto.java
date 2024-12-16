package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.components.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.components.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.components.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.components.subsystems.PivotSubsystem;

@Autonomous(name="basketAuto")
public class basketAuto extends CommandOpMode {

    private ArmSubsystem armSubsystem; // = new ArmSubsystem();
    private PivotSubsystem pivotSubsystem; // = new PivotSubsystem();
    private IntakeSubsystem intakeSubsystem; // = new IntakeSubsystem();
    private DriveSubsystem driveSubsystem; // = new DriveSubsystem();

    private final ElapsedTime timer = new ElapsedTime();

    @Override
    public void initialize() {
        CommandScheduler.getInstance().reset();

        armSubsystem = new ArmSubsystem(hardwareMap, "frontTelescope", "backTelescope");
        pivotSubsystem = new PivotSubsystem(hardwareMap, "leftPivot", "rightPivot");
        intakeSubsystem = new IntakeSubsystem(hardwareMap, "leftWrist", "rightWrist", "intake");
        driveSubsystem = new DriveSubsystem(hardwareMap, "leftFront", "leftBack", "rightFront", "rightBack", "imu");

        driveSubsystem.initializeDrive();

        waitForStart();
        timer.reset();
    }

    @Override
    public void run() {
        CommandScheduler.getInstance().run();

        if (timer.seconds() < 1.5) {

        }






    } // end of run




} // end of class