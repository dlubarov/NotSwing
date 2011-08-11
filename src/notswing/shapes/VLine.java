package notswing.shapes;

import javax.media.opengl.GL2;
import java.awt.*;

public class VLine extends Shape {
    private final double x, y0, y1;
    private float[] color;

    public VLine(double x, double y0, double y1, Color color) {
        this.x = (int) x + 0.5;
        this.y0 = (int) y0 + 0.5;
        this.y1 = (int) y1 + 0.5;
        this.color = color.getRGBColorComponents(null);
    }
    
    @Override
    public void paint(GL2 gl) {
        gl.glColor3fv(color, 0);
        gl.glBegin(GL2.GL_LINES);
            gl.glVertex2d(x, y0);
            gl.glVertex2d(x, y1);
        gl.glEnd();
    }
}
