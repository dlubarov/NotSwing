package notswing;

import notswing.shapes.VLine;

import java.awt.*;

public class VerticalSep extends Widget {
    @Override
    public double getMinWidth() {
        return 1;
    }

    @Override
    public double getMinHeight() {
        return 0;
    }

    @Override
    public boolean likesToStretchHorizontally() {
        return false;
    }

    @Override
    public boolean likesToStretchVertically() {
        return true;
    }

    @Override
    public void paint(PaintTarget target) {
        new VLine(target.x0(), target.y0(), target.y1(), Color.GRAY).paint(target.gl);
    }
}
