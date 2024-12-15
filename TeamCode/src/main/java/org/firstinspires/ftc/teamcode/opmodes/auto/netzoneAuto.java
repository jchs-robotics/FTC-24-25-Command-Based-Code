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
@Autonomous(name = "Net Zone Auto")
public class netzoneAuto extends CommandOpMode {

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

        // strafe and score preload
        if (timer.seconds() >= 0) {
            driveSubsystem.left(0.25);
        }
        if (timer.seconds() >= 2) {
            driveSubsystem.stop();
        }

        if (timer.seconds() >= 3) {
            driveSubsystem.right(0.25);
        }
        if (timer.seconds() >= 5) {
            driveSubsystem.stop();
        }
        // drive forward towards preload
        if (timer.seconds() >= 6) {
            driveSubsystem.fwd(0.25);
        }
        // wait
        if (timer.seconds() >= 7) {
            driveSubsystem.stop();
        }
        // drive left 1 seconds
        if (timer.seconds() >= 8) {
            driveSubsystem.left(0.25);
        }
        // wait
        if (timer.seconds() >= 9) {
            driveSubsystem.stop();
        }
        // reverse into net zone
        if (timer.seconds() >= 10) {
            driveSubsystem.rev(0.25);
        }
        // wait
        if (timer.seconds() >= 12) {
            driveSubsystem.stop();
        }

        // drive fwd to second sample
        if (timer.seconds() >= 13) {
            driveSubsystem.right(0.25);
        }
        // stop
        if (timer.seconds() >= 15) {
            driveSubsystem.stop();
        }



    }













}


