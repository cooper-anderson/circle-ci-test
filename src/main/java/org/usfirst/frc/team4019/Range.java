package org.usfirst.frc.team4019;

public abstract class Range {
    public static double clamp(double value, double endpoint1, double endpoint2) {
        double min = Math.min(endpoint1, endpoint2);
        double max = Math.max(endpoint1, endpoint2);
        return Math.min(Math.max(value, min), max);
    }

    public static double clamp(double value) {
        return clamp(value, 0, 1);
    }

    public static double spread(double value, double min, double max) {
        return (clamp(value, min, max) - min) / (max - min);
    }
}