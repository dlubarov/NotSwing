package notswing.win;

import notswing.*;

import javax.media.opengl.GL2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import static javax.media.opengl.GL.GL_COLOR_BUFFER_BIT;
import static javax.media.opengl.GL.GL_DEPTH_BUFFER_BIT;

public class GlobalWindow extends MouseAdapter {
    public static GlobalWindow mainWindow;

    private final JFrame frame;
    private final Widget content;
    private final LinkedHashSet<ChildWindow> childWindows;

    private boolean draggingMainWindow = false;
    public ChildWindow draggedChildWindow = null;
    public ChildWindow focusedChildWindow = null;
    public double dragPosX, dragPosY;

    public GlobalWindow(Widget content, int width, int height, String caption) {
        frame = new JFrame(caption);
        this.content = content;
        RichCanvas canvas = RichCanvas.canvas;
        frame.add(canvas);
        frame.setSize(width, height);
        frame.setUndecorated(true);
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        canvas.addMouseListener(this);
        canvas.requestFocus();
        childWindows = new LinkedHashSet<ChildWindow>();
    }

    public int getWidth() {
        return frame.getWidth();
    }

    public int getHeight() {
        return frame.getHeight();
    }

    private void update() {
        if (draggingMainWindow) {
            Point pos = MouseInfo.getPointerInfo().getLocation();
            frame.setLocation((int) (pos.x - dragPosX), (int) (pos.y - dragPosY));
        }
    }

    public void paint(GL2 gl) {
        update();
        gl.glClear(GL_COLOR_BUFFER_BIT);
        gl.glClear(GL_DEPTH_BUFFER_BIT);

        String caption = "NotSwing v0.000001";
        BaseWindow.paintWindowBG(gl, 0, 0, getWidth(), getHeight() - BaseWindow.BAR_HEIGHT, caption, 1);
        PaintTarget target = new PaintTarget(gl, 0, BaseWindow.BAR_HEIGHT, getWidth(), getHeight() - BaseWindow.BAR_HEIGHT);

        content.paint(target);
        for (ChildWindow child : childWindows)
            child.paint(gl);

        BaseWindow.paintWindowFG(gl, 0, 0, getWidth(), getHeight() - BaseWindow.BAR_HEIGHT, caption, 1);
    }

    public void addWindow(ChildWindow child) {
        childWindows.add(child);
        focusedChildWindow = child;
    }

    public void giveFocusTo(ChildWindow child) {
        if (childWindows.remove(child))
            childWindows.add(child);
        focusedChildWindow = child;
    }

    private ChildWindow windowUnder(int x, int y) {
        LinkedList<ChildWindow> ll = new LinkedList<ChildWindow>(childWindows);
        Iterator<ChildWindow> iter = ll.descendingIterator();
        while (iter.hasNext()) {
            ChildWindow child = iter.next();
            if (child.contains(x, y))
                return child;
        }
        return null;
    }

    @Override
    public void mousePressed(MouseEvent ev) {
        int x = ev.getX(), y = ev.getY();
        ChildWindow selectedWindow = windowUnder(x, y);
        if (selectedWindow == null) {
            focusedChildWindow = null;
            if (y < BaseWindow.BAR_HEIGHT) {
                draggingMainWindow = true;
                draggedChildWindow = null;
                Point pos = MouseInfo.getPointerInfo().getLocation();
                dragPosX = pos.x - frame.getLocation().x;
                dragPosY = pos.y - frame.getLocation().y;
            }
        } else {
            selectedWindow.giveFocus();
            if (selectedWindow.containsInBar(x, y)) {
                draggedChildWindow = selectedWindow;
                draggingMainWindow = false;
                Point pos = MouseInfo.getPointerInfo().getLocation();
                dragPosX = pos.x - draggedChildWindow.x0;
                dragPosY = pos.y - draggedChildWindow.y0;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent ev) {
        draggingMainWindow = false;
        draggedChildWindow = null;
    }
}
