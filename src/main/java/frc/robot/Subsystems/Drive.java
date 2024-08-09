package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class Drive extends SubsystemBase{
    private TalonFX leftMotor1;
    private TalonFX leftMotor2;
    private TalonFX rightMotor1;
    private TalonFX rightMotor2;

    public Drive(){
        leftMotor1=new TalonFX(DriveConstants.leftMotorId1);
        leftMotor2=new TalonFX(DriveConstants.leftMotorId2);
        rightMotor1=new TalonFX(DriveConstants.rightMotorId1);
        rightMotor2=new TalonFX(DriveConstants.rightMotorId2);

        TalonFXConfiguration confLeft=new TalonFXConfiguration();
        confLeft.CurrentLimits.SupplyCurrentLimit=DriveConstants.supplyCurrentLimit;
        confLeft.MotorOutput.Inverted=InvertedValue.Clockwise_Positive;
        confLeft.MotorOutput.NeutralMode=NeutralModeValue.Brake;

        TalonFXConfiguration confRight=new TalonFXConfiguration();
        confRight.CurrentLimits.SupplyCurrentLimit=DriveConstants.supplyCurrentLimit;
        confRight.MotorOutput.Inverted=InvertedValue.CounterClockwise_Positive;
        confRight.MotorOutput.NeutralMode=NeutralModeValue.Brake;

        leftMotor1.getConfigurator().apply(confLeft);
        leftMotor2.getConfigurator().apply(confLeft);
        rightMotor1.getConfigurator().apply(confRight);
        rightMotor2.getConfigurator().apply(confRight);
    }

    public void setSpeeds(double left,double right){
        leftMotor1.set(left*DriveConstants.drivePower);
        leftMotor2.set(left*DriveConstants.drivePower);
        rightMotor1.set(right*DriveConstants.drivePower);
        rightMotor2.set(right*DriveConstants.drivePower);
    }
}
