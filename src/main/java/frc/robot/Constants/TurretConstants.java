package frc.robot.Constants;

public class TurretConstants {
        // IDs
        public static final int motorId = 8;
        public static final int encoderId = 12;
    
        // PID coefficients
        public static final double kP = 26; 
        public static final double kI = 0.0;
        public static final double kD = 0.2;
    
        public static final int supplyCurrentLimit = 40;
        public static final double kMaxVelocity = 2.0;
        public static final double kMaxAcceleration = 2.0;
    
        public static final double stickPIDWeight = 0.02;
    
        public static final double minimumUsedAngle = Math.toRadians(0);
        public static final double maximumUsedAngle = Math.toRadians(360);
    
        public static final double stickDeadband = 0.1;
    
        public static final double turretTolerance = Math.toRadians(0.5); // Degrees

        public static final double encoderOffset = 0; // Degrees
}
