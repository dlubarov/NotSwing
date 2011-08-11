package notswing;

import javax.media.opengl.GL2;

public class PaintTarget {
    public final GL2 gl;
    private final double x0, y0;
    private final double width, height;

    public PaintTarget(GL2 gl, double x0, double y0, double width, double height) {
        this.gl = gl;
        this.x0 = x0;
        this.y0 = y0;
        this.width = width;
        this.height = height;
    }

    public final double x0() {
        return x0;
    }

    public final double y0() {
        return y0;
    }

    public final double x1() {
        return x0 + width;
    }

    public final double y1() {
        return y0 + height;
    }

    public final double width() {
        return width;
    }

    public final double height() {
        return height;
    }

    public String toString() {
        return String.format("PaintTarget[%.2f, %.2f, %.2f, %.2f]", x0(), y0(), x1(), y1());
    }
}
