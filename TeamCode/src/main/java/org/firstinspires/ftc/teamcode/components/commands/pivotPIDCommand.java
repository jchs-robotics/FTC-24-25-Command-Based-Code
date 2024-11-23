package org.firstinspires.ftc.teamcode.components.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDController;

import org.firstinspires.ftc.teamcode.components.constants;
import org.firstinspires.ftc.teamcode.components.subsystems.PivotSubsystem;

public class pivotPIDCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingluarField"})
    private final PivotSubsystem m_subsystem;
    private final PIDController pidController;
    //private double point;


    public pivotPIDCommand(PivotSubsystem subsystem, double setpoint) {
        this.m_subsystem = subsystem;
        this.pidController = new PIDController(constants.pivotP, constants.pivotI, constants.pivotD);
        pidController.setSetPoint(setpoint);
        addRequirements(subsystem);
    }



    @Override
    public void initialize() {
        //pidController.reset()
    }




    @Override
    public void execute() {
        double speed = pidController.calculate(m_subsystem.pivotEncoder);

        m_subsystem.setMotors(speed);
    }



}

