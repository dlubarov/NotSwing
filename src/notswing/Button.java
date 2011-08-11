package notswing;

import notswing.shapes.RectangleFillAndOutline;
import notswing.win.BaseWindow;

public class Button extends Widget {
    private final Widget inner;

    public Button(Widget inner) {
        this.inner = new FixedPadding(inner, 5, 4, 0, 0);
    }
    
    @Override
    public double getMinWidth() {
        return inner.getMinWidth();
    }

    @Override
    public double getMinHeight() {
        return inner.getMinHeight();
    }

    @Override
    public boolean likesToStretchHorizontally() {
        return inner.likesToStretchHorizontally();
    }

    @Override
    public boolean likesToStretchVertically() {
        return inner.likesToStretchVertically();
    }

    @Override
    public void paint(PaintTarget target) {
        RectangleFillAndOutline rect = new RectangleFillAndOutline(
                target.x0(), target.y0(), target.width(), target.height(),
                ColorUtil.phasalColor(.1f, 1f),
                BaseWindow.outlineColor);
        rect.paint(target.gl);
        inner.paint(target);
    }
}
