package frc.robot.Commands;

import frc.robot.Subsystems.Drive;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.DriveConstants;

public class DriveCommands {
    private Drive drive;

    public DriveCommands(Drive drive){
        this.drive=drive;
    }

    public InstantCommand move(DoubleSupplier x,DoubleSupplier y) {
        return new InstantCommand(() -> {
            double xVal=x.getAsDouble();
            double yVal=y.getAsDouble();
            drive.setSpeeds(xVal-yVal,-xVal-yVal);
        }, drive);
    }
}
