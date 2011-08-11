package notswing;

import java.awt.*;

public final class ColorUtil {
    private ColorUtil() {}

    private static final double period = 5f; // in seconds

    private static double phase() {
        return System.nanoTime() / period * 1e-9 % 1;
    }

    public static Color phasalColor(float s, float b, int i, int n) {
        float h = (float) phase() + i / (float) n;
        return Color.getHSBColor(h, s, b);
    }

    public static Color phasalColor(float s, float b) {
        return phasalColor(s, b, 0, 1);
    }

    public static Color grayShade(float value) {
        return new Color(value, value, value);
    }
}
