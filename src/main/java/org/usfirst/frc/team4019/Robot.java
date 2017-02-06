package org.usfirst.frc.team4019;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.ArrayList;

class TalonGroup {
	ArrayList<Talon> talons;

	TalonGroup(int... args) {
		this.talons = new ArrayList<>();
		for (int arg : args) {
			talons.add(new Talon(arg));
		}
	}

	public void set(double value) {
		for (Talon talon : this.talons) {
			talon.set(value);
		}
	}

	public void setInverted(boolean value) {
		for (Talon talon : this.talons) {
			talon.setInverted(value);
		}
	}
}

class ControlStick {
	Joystick joystick;
	asdf
	ControlStick(int port) {
		this.joystick = new Joystick(port);
	}
	public double getX() {
		return this.joystick.getX();
	}
	public double getY() {
		return -this.joystick.getY();
	}
	public double getTwist() {
		double twist = this.joystick.getTwist();
		double center = -0.16;
		double deadzone = 0.2;
		return twist >= center ? Range.spread(twist, center + deadzone, 1) : Range.spread(twist, -1, center - deadzone) - 1;
	}
	public double getThrottle() {
		return this.joystick.getThrottle() * -0.5 + 0.5;
	}
	public boolean getTrigger() {
		return this.joystick.getTrigger();
	}
	public boolean getRawButton(int button) {
		return this.joystick.getRawButton(button);
	}
}

enum DriveMode {ARCADE, TANK, TWIST, TRIPLE, HYBRID}

public class Robot extends IterativeRobot {
	static ControlStick leftStick = new ControlStick(Constants.inputs.leftStick);
	static ControlStick rightStick = new ControlStick(Constants.inputs.rightStick);
	static TalonGroup leftDrive = new TalonGroup(Constants.ports.leftDrive);
	static TalonGroup rightDrive = new TalonGroup(Constants.ports.rightDrive);
	static DriveMode driveMode = DriveMode.HYBRID;
	static Servo servo = new Servo(4);

	@Override
	public void robotInit() {
		rightDrive.setInverted(true);
	}

	@Override
	public void autonomousInit() {
		Autonomous.init();
	}

	@Override
	public void teleopInit() {
		Teleoperated.init();
	}

	@Override
	public void testInit() {
		Test.init();
	}

	@Override
	public void disabledInit() {
		Disabled.init();
	}

	@Override
	public void autonomousPeriodic() {
		Autonomous.periodic();
		Timer.delay(0.001);
	}

	@Override
	public void teleopPeriodic() {
		Teleoperated.periodic();
		Timer.delay(0.001);
	}

	@Override
	public void testPeriodic() {
		Test.periodic();
		Timer.delay(0.001);
	}

	@Override
	public void disabledPeriodic() {
		Disabled.periodic();
		Timer.delay(0.001);
	}
}
