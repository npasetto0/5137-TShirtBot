package frc.robot.Constants;

public class ArmConstants {
    public static final double kP = 10; 
    public static final double kI = 0.0;
    public static final double kD = 0.2;
    public static final double kMaxVelocity = 2.0;
    public static final double kMaxAcceleration = 2.0;

    public static final int leftMotorId = 5;
    public static final int rightMotorId = 6;
    public static final int encoderId = 7;

    public static final int supplyCurrentLimit = 80;

    public static final double encoderOffset = 0;

    // Feedforward Constants
    public static final double kS = 0.2642;
    public static final double kG = 0.23172;
    public static final double kV = 0.68469; 
    public static final double kA = 0.069366;

    public static final double stickDeadband = 0.1;
    public static final double stickWeight = 0.3;
    public static final double stickPIDWeight = 0.02;

    public static final double armTolerance = Math.toRadians(0.5); // Degrees

    public static final double minimumUsedAngle = Math.toRadians(0); // Degrees
    public static final double maximumUsedAngle = Math.toRadians(90); // Degrees
    public static final double normalRangeCenter = (minimumUsedAngle+maximumUsedAngle)/2.0; //Radians

    public static final double lowAngle = 30; // Degrees
    public static final double midAngle = 45; // Degrees
    public static final double highAngle = 60; // Degrees
}
