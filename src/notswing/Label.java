package notswing;

import notswing.shapes.Text;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class Label extends Widget {
    private static Font font = new Font("Trebuchet MS", Font.PLAIN, 14);

    private final String content;

    public Label(String initialText) {
        this.content = initialText;
    }

    private Rectangle2D bounds(String s) {
        return font.getStringBounds(s, new FontRenderContext(null, false, false));
    }
    
    @Override
    public double getMinWidth() {
        return bounds(content).getWidth();
    }

    @Override
    public double getMinHeight() {
        return 20;//bounds("Ag").getHeight();
    }

    @Override
    public boolean likesToStretchHorizontally() {
        return false;
    }

    @Override
    public boolean likesToStretchVertically() {
        return false;
    }

    @Override
    public void paint(PaintTarget target) {
        Text text = new Text(font, content, target.x0(), target.y0(), Color.BLACK);
        text.paint(target.gl);
    }
}
