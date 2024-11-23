package org.firstinspires.ftc.teamcode.components.commands;

import static java.lang.Math.abs;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDController;

import org.firstinspires.ftc.teamcode.components.constants;
import org.firstinspires.ftc.teamcode.components.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.components.subsystems.PivotSubsystem;

public class armPIDCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingluarField"})
    private final ArmSubsystem m_subsystem;
    private final PIDController pidController;
    private double point;




    public armPIDCommand(ArmSubsystem subsystem, double setpoint) {
        this.m_subsystem = subsystem;
        this.pidController = new PIDController(constants.armP, constants.armI, constants.armD);
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
        double speed = pidController.calculate(m_subsystem.armEncoder);

        m_subsystem.setMotors(speed);
    }

    @Override
    public void end(boolean interrupted) {
        m_subsystem.setMotors(0);
    }

    @Override
    public boolean isFinished() {
        return m_subsystem.tolerance(point);
    }


}

