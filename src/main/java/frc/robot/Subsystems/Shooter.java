package frc.robot.Subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase{
    private CANSparkMax leftMotor;
    private CANSparkMax rightMotor;
    private CANSparkMax firstMotor;
    private CANSparkMax wheelMotor;

    public Shooter(){
        leftMotor=new CANSparkMax(ShooterConstants.leftMotorId,MotorType.kBrushless);
        rightMotor=new CANSparkMax(ShooterConstants.rightMotorId,MotorType.kBrushless);
        firstMotor=new CANSparkMax(ShooterConstants.firstMotorId,MotorType.kBrushless);
        wheelMotor=new CANSparkMax(ShooterConstants.firstMotorId,MotorType.kBrushless);
        
        leftMotor.restoreFactoryDefaults();
        rightMotor.restoreFactoryDefaults();
        firstMotor.restoreFactoryDefaults();
        wheelMotor.restoreFactoryDefaults();

        leftMotor.setSmartCurrentLimit(ShooterConstants.supplyCurrentLimit);
        rightMotor.setSmartCurrentLimit(ShooterConstants.supplyCurrentLimit);
        firstMotor.setSmartCurrentLimit(ShooterConstants.supplyCurrentLimit);
        wheelMotor.setSmartCurrentLimit(ShooterConstants.supplyCurrentLimit);

        leftMotor.setIdleMode(IdleMode.kCoast);
        rightMotor.setIdleMode(IdleMode.kCoast);
        firstMotor.setIdleMode(IdleMode.kCoast);
        wheelMotor.setIdleMode(IdleMode.kBrake);

        leftMotor.setInverted(false);
        rightMotor.setInverted(true);
        firstMotor.setInverted(false);
        wheelMotor.setInverted(false);
    }

    public void enableWheel(){
        wheelMotor.set(ShooterConstants.wheelMotorPower);
    }

    public void stopWheel(){
        wheelMotor.set(0);
    }

    public void enableFirst(){
        firstMotor.set(ShooterConstants.firstMotorPower);
    }

    public void stopFirst(){
        firstMotor.set(0);
    }

    public void enableSides(){
        leftMotor.set(ShooterConstants.sideMotorPower);
        rightMotor.set(ShooterConstants.sideMotorPower);
    }

    public void stopSides(){
        leftMotor.set(0);
        rightMotor.set(0);
    }
}
