package frc.robot.Commands;

import frc.robot.Subsystems.Arm;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.ArmConstants;

public class ArmCommands {
    private Arm arm;

    public ArmCommands(Arm arm){
        this.arm=arm;
    }

    public InstantCommand setTarget(DoubleSupplier target){
        return new InstantCommand(()->arm.setGoal(Math.toRadians(target.getAsDouble())),arm);
    }

    public InstantCommand manualMove(DoubleSupplier translationX) {
        return new InstantCommand(() -> arm.runManual(translationX.getAsDouble()), arm);
    }
}
