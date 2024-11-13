package org.firstinspires.ftc.teamcode.components.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDController;

import org.firstinspires.ftc.teamcode.components.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.components.subsystems.PivotSubsystem;

public class armPIDCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingluarField"})
    private final ArmSubsystem m_subsystem;
    private final PIDController pidController;
    //private double point;


    public armPIDCommand(ArmSubsystem subsystem, double setpoint) {
        this.m_subsystem = subsystem;
        this.pidController = new PIDController(0.0, 0.0, 0.000);
        pidController.setSetPoint(setpoint);
        addRequirements(subsystem);
    }



    @Override
    public void initialize() {
        //pidController.reset()
    }

    @Override
    public void execute() {
        double speed = pidController.calculate(m_subsystem.armEncoder);

        m_subsystem.setMotors(speed);
    }



}

