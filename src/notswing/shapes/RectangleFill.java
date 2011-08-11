package notswing.shapes;

import javax.media.opengl.GL2;
import java.awt.*;

public class RectangleFill extends RectangleBase {
    public RectangleFill(double x, double y, double width, double height,
                         Color c1, Color c2, Color c3, Color c4) {
        super(x, y, width, height, c1, c2, c3, c4);
    }
    
    public RectangleFill(double x, double y, double width, double height, Color color) {
        super(x, y, width, height, color);
    }

    protected int primitive() {
        return GL2.GL_QUADS;
    }
}
