package notswing;

public class EvenGrid extends Widget {
    private Widget[][] children;
    
    public EvenGrid(Widget[][] children) {
        this.children = children;
    }

    @Override
    public double getMinWidth() {
        return children[0][0].getMinWidth() * children[0].length;
    }

    @Override
    public double getMinHeight() {
        return children[0][0].getMinHeight() * children.length;
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
        for (int r = 0; r < children.length; ++r)
            for (int c = 0; c < children[r].length; ++c) {
                double y = target.y0() + r * children[0][0].getMinHeight();
                double x = target.x0() + c * children[0][0].getMinWidth();
                PaintTarget newTarget = new PaintTarget(target.gl, x, y,
                        children[0][0].getMinWidth(), children[0][0].getMinHeight());
                children[r][c].paint(newTarget);
            }
    }
}
