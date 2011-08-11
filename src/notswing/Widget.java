package notswing;

public abstract class Widget {
    public abstract double getMinWidth();
    public abstract double getMinHeight();

    public abstract boolean likesToStretchHorizontally();
    public abstract boolean likesToStretchVertically();

    public abstract void paint(PaintTarget target);

    public Widget pad(double amount) {
        return new FixedPadding(this, amount, amount, amount, amount);
    }
}
