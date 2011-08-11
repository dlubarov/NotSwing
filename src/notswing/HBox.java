package notswing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HBox extends Widget {
    private final List<Widget> children;
    
    public HBox(Widget... children) {
        this.children = new ArrayList<Widget>(Arrays.asList(children));
    }

    public void add(Widget child) {
        children.add(child);
    }
    
    @Override
    public double getMinWidth() {
        double result = 0;
        for (Widget child : children)
            result += child.getMinWidth();
        return result;
    }

    @Override
    public double getMinHeight() {
        double result = 0;
        for (Widget child : children)
            result = Math.max(result, child.getMinHeight());
        return result;
    }

    @Override
    public boolean likesToStretchHorizontally() {
        boolean result = false;
        for (Widget child : children)
            result |= child.likesToStretchHorizontally();
        return result;
    }

    @Override
    public boolean likesToStretchVertically() {
        boolean result = true;
        for (Widget child : children)
            result &= child.likesToStretchVertically();
        return result;
    }

    private int numStretchyChildren() {
        int count = 0;
        for (Widget child : children)
            if (child.likesToStretchHorizontally())
                ++count;
        return count;
    }

    @Override
    public void paint(PaintTarget target) {
        double spaceToFill = target.width() - getMinWidth();
        double stretchAmount = numStretchyChildren() == 0 ? 0 : spaceToFill / numStretchyChildren();

        double currentX = target.x0();
        for (Widget child : children) {
            double childWidth = child.getMinWidth();
            if (child.likesToStretchHorizontally())
                childWidth += stretchAmount;
            PaintTarget childTarget = new PaintTarget(target.gl,
                    currentX, target.y0(), childWidth, target.height());
            child.paint(childTarget);
            currentX += childWidth;
        }
    }
}
