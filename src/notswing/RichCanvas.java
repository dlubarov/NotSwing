package notswing;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.opengl.util.Animator;
import notswing.win.GlobalWindow;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;

import static javax.media.opengl.GL.*;
import static javax.media.opengl.GL2.*;

public class RichCanvas extends GLCanvas implements GLEventListener {
    public static final RichCanvas canvas = new RichCanvas();
    
    private final Animator animator;
    
    public RichCanvas() {
        super();
        animator = new Animator(this);
        animator.start();
        addGLEventListener(this);
    }

    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = getGL().getGL2();

        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, glAutoDrawable.getWidth(), glAutoDrawable.getHeight(), 0, -1, 10);

        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glDisable(GL.GL_CULL_FACE);
        gl.glDisable(GL_DEPTH_TEST);
        gl.glDepthMask(false);
    }

    public void dispose(GLAutoDrawable glAutoDrawable) {}

    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl = getGL().getGL2();
        GlobalWindow.mainWindow.paint(gl);
    }

    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int w, int h) {}
}
