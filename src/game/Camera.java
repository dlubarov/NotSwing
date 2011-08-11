package game;

import notswing.RichCanvas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class Camera implements KeyListener {
    public double x, y, z;
    private double dir;

    public Camera() {
        x = y = z = 0; z = 6;
        dir = Math.PI / 4;
        RichCanvas.canvas.addKeyListener(this);
    }

    public double cenX() {
        return x + Math.cos(dir);
    }

    public double cenY() {
        return y + Math.sin(dir);
    }

    public double cenZ() {
        return z - .25;
    }

    private void forward(double amt) {
        x += amt * Math.cos(dir);
        y += amt * Math.sin(dir);
    }

    public void logic(double dt) {
        double forwardSpe = 20;
        double rotSpe = 1;
        if (keyDown(KeyEvent.VK_UP))
            forward(dt*forwardSpe);
        if (keyDown(KeyEvent.VK_DOWN))
            forward(-dt*forwardSpe);
        if (keyDown(KeyEvent.VK_LEFT))
            dir += dt*rotSpe;
        if (keyDown(KeyEvent.VK_RIGHT))
            dir -= dt*rotSpe;
    }

    Set<Integer> depressedKeys = new HashSet<Integer>();

    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        depressedKeys.add(key);
    }

    public void keyReleased(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        depressedKeys.remove(key);
    }

    public void keyTyped(KeyEvent keyEvent) {}

    private boolean keyDown(int key) {
        return depressedKeys.contains(key);
    }
}
