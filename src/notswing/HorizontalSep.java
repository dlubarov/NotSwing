package notswing;

import notswing.shapes.HLine;

import java.awt.*;

public class HorizontalSep extends Widget {
    @Override
    public double getMinWidth() {
        return 0;
    }

    @Override
    public double getMinHeight() {
        return 1;
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
        new HLine(target.x0(), target.x1(), target.y0(), Color.GRAY).paint(target.gl);
    }
}
