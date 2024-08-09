package frc.robot.Subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.trajectory.TrapezoidProfile.State;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.TurretConstants;

public class Turret extends ProfiledPIDSubsystem {
    private CANSparkMax motor;
    private DutyCycleEncoder encoder;


    public Turret(){
        super(new ProfiledPIDController(
                TurretConstants.kP,
                TurretConstants.kI,
                TurretConstants.kD,
                new TrapezoidProfile.Constraints(
                TurretConstants.kMaxVelocity,
                TurretConstants.kMaxAcceleration)),
            0.0);
        
            super.m_controller.setTolerance(Math.toRadians(TurretConstants.turretTolerance));

        motor=new CANSparkMax(TurretConstants.motorId,MotorType.kBrushless);
        motor.setSmartCurrentLimit(TurretConstants.supplyCurrentLimit);
        motor.setIdleMode(IdleMode.kBrake);
        
        encoder = new DutyCycleEncoder(TurretConstants.encoderId);
        encoder.setDistancePerRotation(-2*Math.PI);
        encoder.setPositionOffset(TurretConstants.encoderOffset);

        setGoal(getMeasurement());
    }

    public void setVoltage(double volts) {
        motor.setVoltage(volts);
    }

    @Override
    public double getMeasurement() {
        return encoder.getDistance();
    }

    @Override
    protected void useOutput(double output, State setpoint) {
        setVoltage(output);
    }

    public void runManual(double output) {
        this.setGoal(MathUtil.clamp(this.getGoal()+TurretConstants.stickPIDWeight*output,TurretConstants.minimumUsedAngle,TurretConstants.maximumUsedAngle));
    }

    public double getGoal() {
        return super.m_controller.getGoal().position;
    }
}
