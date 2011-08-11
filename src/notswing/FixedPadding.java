package notswing;

public class FixedPadding extends Widget {
    private final Widget content;
    private final double left, right, top, bottom;

    public FixedPadding(Widget content, double left, double right, double top, double bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        this.content = content;
    }

    @Override
    public double getMinWidth() {
        return content.getMinWidth() + left + right;
    }

    @Override
    public double getMinHeight() {
        return content.getMinHeight() + top + bottom;
    }

    @Override
    public boolean likesToStretchHorizontally() {
        return content.likesToStretchHorizontally();
    }

    @Override
    public boolean likesToStretchVertically() {
        return content.likesToStretchVertically();
    }

    @Override
    public void paint(PaintTarget target) {
        PaintTarget newTarget = new PaintTarget(target.gl,
                target.x0() + left, target.y0() + top,
                target.width() - left - right, target.height() - top - bottom);
        content.paint(newTarget);
    }
}
