package game;

import com.jogamp.opengl.util.gl2.GLUT;
import jogamp.opengl.glu.GLUquadricImpl;
import notswing.PaintTarget;
import notswing.Widget;
import notswing.win.GlobalWindow;

import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import static javax.media.opengl.GL.GL_COLOR_BUFFER_BIT;
import static javax.media.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

public class GameWidget extends Widget {
    private static final int NUM_ENEMIES = 40;
    private final GLU glu = new GLU();
    private final GLUT glut = new GLUT();
    private final Enemy[] enemies;
    private final Camera cam = new Camera();

    public GameWidget() {
        enemies = new Enemy[NUM_ENEMIES];
        for (int i = 0; i < NUM_ENEMIES; ++i)
            enemies[i] = new Enemy();
    }
    
    @Override
    public double getMinWidth() {
        return GlobalWindow.mainWindow.getWidth();
    }

    @Override
    public double getMinHeight() {
        return GlobalWindow.mainWindow.getWidth();
    }

    @Override
    public boolean likesToStretchHorizontally() {
        return false;
    }

    @Override
    public boolean likesToStretchVertically() {
        return false;
    }

    long lastLogic = System.nanoTime();
    private void logic() {
        long time = System.nanoTime();
        double dt = (time - lastLogic)*1e-9;
        if (Math.random() < 0.1)
            System.out.println("fps: " + 1/dt);
        for (Enemy e : enemies)
            e.logic(dt);
        cam.logic(dt);
        lastLogic = time;
    }

    @Override
    public void paint(PaintTarget target) {
        logic();
        GL2 gl = target.gl;
        gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
        gl.glClearColor(0.3f, 0.5f, 1.0f, 0.0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glDepthMask(true);
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, new float[] {0.5f, 0.5f, 0.5f, 1f}, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, new float[] {.5f, .5f, .5f, 1f}, 0);
        gl.glEnable(GL2.GL_LIGHT0);

        // Perspective projection
        gl.glMatrixMode(GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        float fovy = 60f,
              aspect = GlobalWindow.mainWindow.getWidth() / (float) GlobalWindow.mainWindow.getHeight(),
              zNear = 0.2f, zFar = 1000f;
        glu.gluPerspective(fovy, aspect, zNear, zFar);

        // Model view
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        glu.gluLookAt(cam.x, cam.y, cam.z,
                cam.cenX(), cam.cenY(), cam.cenZ(),
                0, 0, 1);

        // Clear the screen
        gl.glClear(GL_COLOR_BUFFER_BIT);
        gl.glClear(GL_DEPTH_BUFFER_BIT);

        // Ground
        gl.glPushMatrix();
        gl.glTranslated(0, 0, -1.6);
        gl.glColor3d(0.5, 0.5, 0.2);
        glut.glutSolidCylinder(100, 0.1, 24, 24);
        gl.glPopMatrix();

        // Enemies
        for (Enemy e : enemies)
            e.paint(gl);

        // Restore matrices
        gl.glMatrixMode(GL_PROJECTION);
        gl.glPopMatrix();
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glPopMatrix();

        gl.glDisable(GL2.GL_DEPTH_TEST);
        gl.glDisable(GL2.GL_LIGHTING);
    }
}
