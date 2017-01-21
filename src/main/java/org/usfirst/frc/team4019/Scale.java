package org.usfirst.frc.team4019;

public abstract class Scale {
	public static double[] compress(double[] values) {
		// Set the divisor to the maximum absolute value
		double divisor = Math.abs(values[0]);
		for (int i = 1; i < values.length; i++) {
			if (Math.abs(values[i]) > divisor) {
				divisor = Math.abs(values[i]);
			}
		}
		// If the divisor is less than 1 set it to 1
		divisor = Math.max(divisor, 1);
		// Divide all values by the divisor and return
		double[] result = values.clone();
		for (int i = 0; i < result.length; i++) {
			result[i] /= divisor;
		}
		return result;
	}

	public static double[] scale(double[] values, double multiplier) {
		// Multiply all values by the multiplier and return
		double[] result = values.clone();
		for (int i = 0; i < result.length; i++) {
			result[i] *= multiplier;
		}
		return result;
	}
}