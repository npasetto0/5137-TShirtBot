package frc.robot.Commands;

import frc.robot.Subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.ShooterConstants;

public class ShooterCommands {
    private Shooter shooter;

    public ShooterCommands(Shooter shooter){
        this.shooter=shooter;
    }

    public InstantCommand enableWheel(){
        return new InstantCommand(()->shooter.enableWheel(),shooter);
    }

    public InstantCommand stopWheel(){
        return new InstantCommand(()->shooter.stopWheel(),shooter);
    }

    public InstantCommand enableFirst(){
       return new InstantCommand(()->shooter.enableFirst(),shooter);
    }

    public InstantCommand stopFirst(){
        return new InstantCommand(()->shooter.stopFirst(),shooter);
    }

    public InstantCommand enableSides(){
        return new InstantCommand(()->shooter.enableSides(),shooter);
    }

    public InstantCommand stopSides(){
        return new InstantCommand(()->shooter.stopSides(),shooter);
    }

    public SequentialCommandGroup shoot() {
        return new SequentialCommandGroup(enableSides(),new WaitCommand(ShooterConstants.revTime),enableWheel(),new WaitCommand(ShooterConstants.wheelMotorTime),stopWheel(),enableFirst(),new WaitCommand(ShooterConstants.firstTime),stopSides());
    }
}
