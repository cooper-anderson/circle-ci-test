package org.usfirst.frc.team4019;

public abstract class Test {
	public static int init() {
		return 0;
	}

	public static int periodic() {
		Robot.servo.set((Robot.rightStick.getTwist() + 1) / 2);
		return 0;
	}
}