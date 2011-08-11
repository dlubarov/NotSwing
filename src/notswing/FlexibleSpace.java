package notswing;

public class FlexibleSpace extends Widget {
    @Override
    public double getMinWidth() {
        return 0;
    }

    @Override
    public double getMinHeight() {
        return 0;
    }

    @Override
    public boolean likesToStretchHorizontally() {
        return true;
    }

    @Override
    public boolean likesToStretchVertically() {
        return true;
    }

    @Override
    public void paint(PaintTarget target) {}
}
