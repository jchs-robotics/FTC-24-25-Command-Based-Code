package org.firstinspires.ftc.teamcode.components.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDController;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.components.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.components.subsystems.DriveSubsystem;

public class driveCommands extends CommandBase {

    private final DriveSubsystem m_subsystem;
    private final PIDController pidController;

    private double point;



    public driveCommands(DriveSubsystem subsystem, double setpoint) {
        this.m_subsystem = subsystem;
        this.pidController = new PIDController(0.0, 0.0, 0.000);
        pidController.setSetPoint(setpoint);
        point = setpoint;
        addRequirements(subsystem);
    }



    @Override
    public void initialize() {
        pidController.reset();
    }

    @Override
    public void execute() {
        double speed = pidController.calculate(m_subsystem.FLEncoder);

        m_subsystem.Drive(speed, speed, speed, speed);
    }

    @Override
    public void end(boolean interrupted) {
        m_subsystem.Drive(0, 0, 0, 0);
    }

    @Override
    public boolean isFinished() {
        return m_subsystem.tolerance(point);
    }


    // turn to command - button input to set a heading and use pid to turn to that





}
