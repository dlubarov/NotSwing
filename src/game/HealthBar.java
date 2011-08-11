package game;

import notswing.ColorUtil;
import notswing.PaintTarget;
import notswing.Widget;
import notswing.shapes.RectangleFill;
import notswing.shapes.RectangleFillAndOutline;
import notswing.shapes.RectangleOutline;

import java.awt.*;

public class HealthBar extends Widget {
    private final Color color, darker;
    private final double amount;

    public HealthBar(Color color, double amount) {
        this.color = color;
        float[] rgb = color.getRGBComponents(null);
        float scale = 0.5f;
        darker = new Color(rgb[0]*scale, rgb[1]*scale, rgb[2]*scale);
        this.amount = amount;
    }

    @Override
    public double getMinWidth() {
        return 30;
    }

    @Override
    public double getMinHeight() {
        return 100;
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
        RectangleFillAndOutline bg = new RectangleFillAndOutline(
                target.x0(), target.y0(), target.width(), target.height(),
                ColorUtil.grayShade(.9f), Color.GRAY);
        
        double h = target.height() * amount;
        RectangleFill fg1 = new RectangleFill(
                target.x0(), target.y1() - h, target.width()/2, h,
                darker, color, color, darker);
        RectangleFill fg2 = new RectangleFill(
                target.x0() + target.width()/2, target.y1() - h, target.width()/2, h,
                color, darker, darker, color);
        
        RectangleOutline fgOut = new RectangleOutline(
                target.x0(), target.y1() - h, target.width(), h,
                Color.GRAY);
        
        bg.paint(target.gl);
        fg1.paint(target.gl);
        fg2.paint(target.gl);
        fgOut.paint(target.gl);
    }
}
