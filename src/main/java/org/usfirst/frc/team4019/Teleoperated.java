package org.usfirst.frc.team4019;

public abstract class Teleoperated {
	public static int init() {
		return 0;
	}

	public static int periodic() {
		DriveMode previous = Robot.driveMode;
		if (Robot.rightStick.getRawButton(7)) {
			Robot.driveMode = DriveMode.ARCADE;
		} else if (Robot.rightStick.getRawButton(8)) {
			Robot.driveMode = DriveMode.HYBRID;
		} else if (Robot.rightStick.getRawButton(9)) {
			Robot.driveMode = DriveMode.TWIST;
		} else if (Robot.rightStick.getRawButton(10)) {
			Robot.driveMode = DriveMode.TRIPLE;
		} else if (Robot.rightStick.getRawButton(11)) {
			Robot.driveMode = DriveMode.TANK;
		}
		if (Robot.driveMode != previous) {
			System.out.println("Drive mode changed to: " + Robot.driveMode);
		}

		double[] speeds;
		boolean modifier = Robot.rightStick.getTrigger();

		if (Robot.driveMode == DriveMode.TANK) {
			speeds = new double[] {Robot.leftStick.getY(), Robot.rightStick.getY()};
		} else {
			double forward = 0;
			double rotation = 0;
			switch (Robot.driveMode) {
				case ARCADE:
					forward = Robot.rightStick.getY();
					rotation = Robot.rightStick.getX();
					break;
				case HYBRID:
					forward = Robot.rightStick.getY();
					rotation = Robot.rightStick.getX() + (modifier ? Robot.rightStick.getTwist() : 0);
					break;
				case TWIST:
					forward = Robot.rightStick.getY();
					rotation = Robot.rightStick.getTwist();
					break;
				case TRIPLE:
					forward = Robot.rightStick.getY();
					rotation = Robot.rightStick.getTwist() + Robot.rightStick.getX() * Range.clamp(forward * 4, -1, 1);
					break;
			}
			speeds = new double[] {forward + rotation, forward - rotation};
		}

		speeds = Scale.scale(Scale.compress(speeds), Robot.rightStick.getThrottle() * (modifier ? 0.5 : 1));

		Robot.leftDrive.set(speeds[0]);
		Robot.rightDrive.set(speeds[1]);

		return 0;
	}
}