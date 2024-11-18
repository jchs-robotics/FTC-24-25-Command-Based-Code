package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.components.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.components.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.components.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.components.subsystems.PivotSubsystem;

//@Config
@Autonomous(name = "preload and leave")
public class preload_and_leave extends CommandOpMode {

    // initiate classes
    private ArmSubsystem armSubsystem; // = new ArmSubsystem();
    private PivotSubsystem pivotSubsystem; // = new PivotSubsystem();
    private IntakeSubsystem intakeSubsystem; // = new IntakeSubsystem();
    private DriveSubsystem driveSubsystem; // = new DriveSubsystem();

    private final ElapsedTime timer = new ElapsedTime();


    @Override
    public void initialize() {


        //CommandScheduler.getInstance().reset();

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
        // drive left into observation zone
        if (timer.seconds() >= 0) {
            driveSubsystem.Drive(-0.5, 0.5, 0.5, -0.5);
        }
        if (timer.seconds() >= 5) {
            driveSubsystem.Drive(0, 0, 0, 0);
        }
        if (timer.seconds() >= 8) {
            driveSubsystem.Drive(0.5, -0.5, -0.5, 0.5);
        }
        if (timer.seconds() >= 11) {
            driveSubsystem.Drive(0, 0, 0, 0);
        }
    }













}


