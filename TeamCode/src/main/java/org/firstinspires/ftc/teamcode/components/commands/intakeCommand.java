package org.firstinspires.ftc.teamcode.components.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.components.subsystems.IntakeSubsystem;

public class intakeCommand extends CommandBase {
    private final IntakeSubsystem m_subsystem;


    public intakeCommand(IntakeSubsystem subsystem) {
        m_subsystem = subsystem;
        addRequirements(subsystem);
    }


    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
    }



}
