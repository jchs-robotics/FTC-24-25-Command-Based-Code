package org.firstinspires.ftc.teamcode.components.commands;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static java.lang.Math.abs;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDController;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.constants;
import org.firstinspires.ftc.teamcode.components.subsystems.PivotSubsystem;

public class pivotPIDCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingluarField"})
    private final PivotSubsystem m_subsystem;
    private final PIDController pidController;
    private double point;

    //private Telemetry telemetry;

    public pivotPIDCommand(PivotSubsystem subsystem, double setpoint) {
        this.m_subsystem = subsystem;
        this.pidController = new PIDController(constants.pivotP, constants.pivotI, constants.pivotD);
        pidController.setSetPoint(setpoint);
        point = setpoint;
        addRequirements(subsystem);
    }



    @Override
    public void initialize() {
        pidController.reset();

        //telemetry.addLine("Pivot PID Command Running");
        //telemetry.addData("Pivot PID Command", "Running");
        //System.out.println("Pivot PID Command Running");
    }




    @Override
    public void execute() {
        double speed = pidController.calculate(m_subsystem.pivotEncoder);

        // if lower than set point, go up
        // if greater than set point, go down


        // logically, this works
//        if (m_subsystem.pivotEncoder < (point + 10) || m_subsystem.pivotEncoder > (point - 10)) {
            m_subsystem.setMotors(speed);
//        }

    }

    @Override
    public void end(boolean interrupted) {
        m_subsystem.setMotors(0);

       // telemetry.addData("Pivot PID Command", "Finished");
        //System.out.println("Pivot PID Command Finished");
    }

    @Override
    public boolean isFinished() {
        //if (m_subsystem == point)

        // logically, this works
        return m_subsystem.tolerance(point);

    }


}
