package frc.robot.Commands;

import frc.robot.Subsystems.Turret;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.TurretConstants;

public class TurretCommands {
  private final Turret turret;

  public TurretCommands(Turret turret) {
    this.turret=turret;
  }

    public InstantCommand setTarget(DoubleSupplier target){
        return new InstantCommand(()->turret.setGoal(Math.toRadians(target.getAsDouble())),turret);
    }

    public InstantCommand manualMove(DoubleSupplier translationX) {
        return new InstantCommand(() -> turret.runManual(translationX.getAsDouble()), turret);
    }
}
