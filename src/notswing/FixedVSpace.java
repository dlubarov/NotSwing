package notswing;

public class FixedVSpace extends Widget {
    private final double size;
    
    public FixedVSpace(double size) {
        this.size = size;
    }
    
    @Override
    public double getMinWidth() {
        return 0;
    }

    @Override
    public double getMinHeight() {
        return size;
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
    public void paint(PaintTarget target) {}
}
