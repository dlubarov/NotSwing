package notswing.shapes;

import javax.media.opengl.GL2;
import java.awt.*;

public class HLine extends Shape {
    private final double x0, x1, y;
    private float[] color;

    public HLine(double x0, double x1, double y, Color color) {
        this.x0 = (int) x0 + 0.5;
        this.x1 = (int) x1 + 0.5;
        this.y = (int) y + 0.5;
        this.color = color.getRGBColorComponents(null);
    }
    
    @Override
    public void paint(GL2 gl) {
        gl.glColor3fv(color, 0);
        gl.glBegin(GL2.GL_LINES);
            gl.glVertex2d(x0, y);
            gl.glVertex2d(x1, y);
        gl.glEnd();
    }
}
