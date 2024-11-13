package org.firstinspires.ftc.teamcode.components.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDController;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.components.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.components.subsystems.DriveSubsystem;

public class driveCommands extends CommandBase {

    private final DriveSubsystem m_subsystem;
    private final PIDController pidController;



    public driveCommands(DriveSubsystem subsystem, double setpoint) {
        this.m_subsystem = subsystem;
        this.pidController = new PIDController(0.0, 0.0, 0.000);
        pidController.setSetPoint(setpoint);
        addRequirements(subsystem);
    }



    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
    }

    // default command - controller input

    // turn to command - button input to set a heading and use pid to turn to that





}
