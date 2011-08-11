package notswing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VBox extends Widget {
    private final List<Widget> children;

    public VBox(Widget... children) {
        this.children = new ArrayList<Widget>(Arrays.asList(children));
    }

    public void add(Widget child) {
        children.add(child);
    }
    
    @Override
    public double getMinWidth() {
        double result = 0;
        for (Widget child : children)
            result = Math.max(result, child.getMinWidth());
        return result;
    }

    @Override
    public double getMinHeight() {
        double result = 0;
        for (Widget child : children)
            result += child.getMinHeight();
        return result;
    }

    @Override
    public boolean likesToStretchHorizontally() {
        boolean result = true;
        for (Widget child : children)
            result &= child.likesToStretchHorizontally();
        return result;
    }

    @Override
    public boolean likesToStretchVertically() {
        boolean result = false;
        for (Widget child : children)
            result |= child.likesToStretchVertically();
        return result;
    }

    private int numStretchyChildren() {
        int count = 0;
        for (Widget child : children)
            if (child.likesToStretchVertically())
                ++count;
        return count;
    }

    @Override
    public void paint(PaintTarget target) {
        double spaceToFill = target.height() - getMinHeight();
        double stretchAmount = numStretchyChildren() == 0 ? 0 : spaceToFill / numStretchyChildren();

        double currentY = target.y0();
        for (Widget child : children) {
            double childHeight = child.getMinHeight();
            if (child.likesToStretchVertically())
                childHeight += stretchAmount;
            PaintTarget childTarget = new PaintTarget(target.gl,
                    target.x0(), currentY, target.width(), childHeight);
            child.paint(childTarget);
            currentY += childHeight;
        }
    }
}
