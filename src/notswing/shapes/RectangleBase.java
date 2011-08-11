package notswing.shapes;

import javax.media.opengl.GL2;
import java.awt.*;

public abstract class RectangleBase extends Shape {
    private final double x, y, width, height;
    private float[][] colors;

    public RectangleBase(double x, double y, double width, double height, Color c1, Color c2, Color c3, Color c4) {
        this.x = (int) x + 0.5;
        this.y = (int) y + 0.5;
        this.width = width;
        this.height = height;
        this.colors = new float[4][];
        this.colors[0] = c1.getRGBColorComponents(null);
        this.colors[1] = c2.getRGBColorComponents(null);
        this.colors[2] = c3.getRGBColorComponents(null);
        this.colors[3] = c4.getRGBColorComponents(null);
    }

    public RectangleBase(double x, double y, double width, double height, Color color) {
        this(x, y, width, height, color, color, color, color);
    }

    protected abstract int primitive();

    @Override
    public void paint(GL2 gl) {
        gl.glBegin(primitive());
            gl.glColor3fv(colors[0], 0);
            gl.glVertex2d(x, y);
            gl.glColor3fv(colors[1], 0);
            gl.glVertex2d(x + width, y);
            gl.glColor3fv(colors[2], 0);
            gl.glVertex2d(x + width, y + height);
            gl.glColor3fv(colors[3], 0);
            gl.glVertex2d(x, y + height);
        gl.glEnd();
    }

    public String toString() {
        return String.format("Rectangle[%.2f, %.2f, w=%.2f, h=%.2f]",
                x, y, width, height);
    }
}
