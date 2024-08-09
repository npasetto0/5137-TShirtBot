package frc.robot;

import frc.robot.Subsystems.*;
import frc.robot.Constants.*;
import frc.robot.Commands.*;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private CommandPS4Controller operator;
  private CommandXboxController driver;
  
  private Turret turret;
  private Drive drive;
  private Shooter shooter;
  private Arm arm;

  private TurretCommands turretCommands;
  private DriveCommands driveCommands;
  private ShooterCommands shooterCommands;
  private ArmCommands armCommands;

  public RobotContainer() {
    driver = new CommandXboxController(0);
    operator = new CommandPS4Controller(1);

    arm=new Arm();
    turret=new Turret();
    shooter=new Shooter();
    drive=new Drive();

    armCommands=new ArmCommands(arm);
    turretCommands=new TurretCommands(turret);
    driveCommands=new DriveCommands(drive);
    shooterCommands=new ShooterCommands(shooter);

    configureBindings();
  }

  private void configureBindings() {
    // Arm control
    arm.setDefaultCommand(armCommands.manualMove(() -> -MathUtil.applyDeadband(operator.getLeftY(), ArmConstants.stickDeadband)));

    // Auto arm control
    operator.povDown().onTrue(armCommands.setTarget(()->ArmConstants.lowAngle));
    operator.povRight().onTrue(armCommands.setTarget(()->ArmConstants.midAngle));
    operator.povUp().onTrue(armCommands.setTarget(()->ArmConstants.highAngle));

    // Turret control
    turret.setDefaultCommand(turretCommands.manualMove(() -> -MathUtil.applyDeadband(operator.getRightX(), TurretConstants.stickDeadband)));

    // Shooter control
    operator.R2().onTrue(shooterCommands.shoot());

    // Drive control
    drive.setDefaultCommand(driveCommands.move(()->driver.getLeftX(),()->driver.getLeftY()));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
