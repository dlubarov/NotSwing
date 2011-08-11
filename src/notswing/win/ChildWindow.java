package notswing.win;

import notswing.PaintTarget;
import notswing.Widget;

import javax.media.opengl.GL2;
import java.awt.*;

public class ChildWindow {
    private final Widget content;
    private final String caption;
    public double width, height;
    public double x0, y0;

    public ChildWindow(Widget content, String caption, double width, double height) {
        this.content = content;
        this.caption = caption;
        this.width = width;
        this.height = height;

        // Random placement
        x0 = Math.random() * (GlobalWindow.mainWindow.getWidth() - width);
        y0 = BaseWindow.BAR_HEIGHT + Math.random() * (GlobalWindow.mainWindow.getHeight() - height - BaseWindow.BAR_HEIGHT*2);

        GlobalWindow.mainWindow.addWindow(this);
    }

    public ChildWindow(Widget content, String caption) {
        this(content, caption, content.getMinWidth(), content.getMinHeight());
    }

    public boolean hasFocus() {
        return GlobalWindow.mainWindow.focusedChildWindow == this;
    }

    public void giveFocus() {
        GlobalWindow.mainWindow.giveFocusTo(this);
    }

    public boolean contains(int x, int y) {
        return x >= x0 && x < x0 + width && y >= y0 && y < y0 + BaseWindow.BAR_HEIGHT + height;
    }

    public boolean containsInBar(int x, int y) {
        return x >= x0 && x < x0 + width && y >= y0 && y < y0 + BaseWindow.BAR_HEIGHT;
    }

    private void enforceBounds() {
        x0 = Math.max(x0, 0);
        y0 = Math.max(y0, BaseWindow.BAR_HEIGHT);
        x0 = Math.min(x0, GlobalWindow.mainWindow.getWidth() - width);
        y0 = Math.min(y0, GlobalWindow.mainWindow.getHeight() - BaseWindow.BAR_HEIGHT - height);
    }

    private void update() {
        if (GlobalWindow.mainWindow.draggedChildWindow == this) {
            Point pos = MouseInfo.getPointerInfo().getLocation();
            x0 = pos.x - GlobalWindow.mainWindow.dragPosX;
            y0 = pos.y - GlobalWindow.mainWindow.dragPosY;
            enforceBounds();
        }
    }

    public void pack() {
        width = content.getMinWidth();
        height = content.getMinHeight();
    }

    public void paint(GL2 gl) {
        update();
        // TODO: gradual focus
        BaseWindow.paintWindowBG(gl, x0, y0, width, height, caption, hasFocus()?1:0);
        BaseWindow.paintWindowFG(gl, x0, y0, width, height, caption, hasFocus()?1:0);
        content.paint(new PaintTarget(gl, x0, y0 + BaseWindow.BAR_HEIGHT, width, height));
    }
}
