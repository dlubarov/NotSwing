package notswing.shapes;

import javax.media.opengl.GL2;
import java.awt.*;

public class RectangleFillAndOutline extends Shape {
    private final RectangleFill fill;
    private final RectangleOutline outline;

    public RectangleFillAndOutline(double x, double y, double width, double height,
                                   Color c1Fill, Color c2Fill, Color c3Fill, Color c4Fill,
                                   Color c1Out, Color c2Out, Color c3Out, Color c4Out) {
        fill = new RectangleFill(x, y, width, height, c1Fill, c2Fill, c3Fill, c4Fill);
        outline = new RectangleOutline(x, y, width, height, c1Out, c2Out, c3Out, c4Out);
    }

    public RectangleFillAndOutline(double x, double y, double width, double height,
                                   Color c1Fill, Color c2Fill, Color c3Fill, Color c4Fill,
                                   Color colorOut) {
        fill = new RectangleFill(x, y, width, height, c1Fill, c2Fill, c3Fill, c4Fill);
        outline = new RectangleOutline(x, y, width, height, colorOut, colorOut, colorOut, colorOut);
    }
    
    public RectangleFillAndOutline(double x, double y, double width, double height,
                                   Color colorFill, Color colorOutline) {
        fill = new RectangleFill(x, y, width, height, colorFill);
        outline = new RectangleOutline(x, y, width, height, colorOutline);
    }

    @Override
    public void paint(GL2 gl) {
        fill.paint(gl);
        outline.paint(gl);
    }
}
