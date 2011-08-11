package notswing;

public class FixedHSpace extends Widget {
    private final double size;

    public FixedHSpace(double size) {
        this.size = size;
    }
    
    @Override
    public double getMinWidth() {
        return size;
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
    public void paint(PaintTarget target) {}
}
