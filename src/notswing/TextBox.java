package notswing;

import notswing.shapes.RectangleFillAndOutline;
import notswing.shapes.Text;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class TextBox extends Widget {
    private static Font font = new Font("Trebuchet MS", Font.PLAIN, 14);

    private final String currentText;
    
    public TextBox(String initialText) {
        this.currentText = initialText;
    }
    
    @Override
    public double getMinWidth() {
        return 50;
    }

    @Override
    public double getMinHeight() {
        return 20;
    }

    @Override
    public boolean likesToStretchHorizontally() {
        return true;
    }

    @Override
    public boolean likesToStretchVertically() {
        return false;
    }

    @Override
    public void paint(PaintTarget target) {
        RectangleFillAndOutline rect = new RectangleFillAndOutline(
                target.x0(), target.y0(),
                target.width(), target.height(),
                Color.WHITE, Color.GRAY);
        rect.paint(target.gl);
        Text text = new Text(font, currentText, target.x0() + 5, target.y0(), Color.BLACK);
        text.paint(target.gl);
    }
}
