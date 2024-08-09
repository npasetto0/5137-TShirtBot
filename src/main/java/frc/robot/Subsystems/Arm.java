package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.State;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class Arm extends ProfiledPIDSubsystem{
    private CANSparkMax leftMotor;
    private CANSparkMax rightMotor;
    private DutyCycleEncoder encoder;
    private ArmFeedforward feedForward;

    public Arm(){
        super(new ProfiledPIDController(
                ArmConstants.kP,
                ArmConstants.kI,
                ArmConstants.kD,
                new TrapezoidProfile.Constraints(
                ArmConstants.kMaxVelocity,
                ArmConstants.kMaxAcceleration)),
            0.0);
        leftMotor=new CANSparkMax(ArmConstants.leftMotorId,MotorType.kBrushless);
        rightMotor=new CANSparkMax(ArmConstants.rightMotorId,MotorType.kBrushless);
        
        leftMotor.restoreFactoryDefaults();
        rightMotor.restoreFactoryDefaults();

        leftMotor.setSmartCurrentLimit(ArmConstants.supplyCurrentLimit);
        rightMotor.setSmartCurrentLimit(ArmConstants.supplyCurrentLimit);

        leftMotor.setIdleMode(IdleMode.kBrake);
        rightMotor.setIdleMode(IdleMode.kBrake);

        leftMotor.setInverted(false);
        rightMotor.setInverted(true);

        encoder = new DutyCycleEncoder(ArmConstants.encoderId);
        encoder.setDistancePerRotation(-2*Math.PI);
        encoder.setPositionOffset(ArmConstants.encoderOffset);

        feedForward = new ArmFeedforward(
            ArmConstants.kS,
            ArmConstants.kG,
            ArmConstants.kV,
            ArmConstants.kA);
        
        setGoal(getMeasurement());
    }

    public void setVoltage(double volts) {
        leftMotor.setVoltage(volts);
        rightMotor.setVoltage(volts);
    }

    @Override
    public double getMeasurement() {
        double measurement=encoder.getDistance();
        return (measurement-ArmConstants.normalRangeCenter+Math.PI)%(2*Math.PI)+ArmConstants.normalRangeCenter-Math.PI;
    }

    @Override
    protected void useOutput(double output, State setpoint) {
        double feed = feedForward.calculate(setpoint.position, setpoint.velocity);
        setVoltage(output + feed);
    }

    @Override
    public void periodic() {
        //updateDashboard();
        if (encoder.isConnected()) {
            useOutput(super.m_controller.calculate(getMeasurement()), super.m_controller.getSetpoint());
        }
    }

    public void runManual(double output) {
        if (encoder.isConnected()) {
            this.setGoal(MathUtil.clamp(this.getGoal()+ArmConstants.stickPIDWeight*output,ArmConstants.minimumUsedAngle,ArmConstants.maximumUsedAngle));
        } else {
            leftMotor.set(ArmConstants.stickWeight*output);
            rightMotor.set(ArmConstants.stickWeight*output);
        }
    }

    public double getGoal() {
        return super.m_controller.getGoal().position;
    }
}
