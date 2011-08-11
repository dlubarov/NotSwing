package game;

import com.jogamp.opengl.util.gl2.GLUT;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Enemy {
    private static final GLU glu = new GLU();
    private static final GLUT glut = new GLUT();
    
    private static final String[] possibleNames = {
            "Empror1",
            "Sir Heiki",
            "Matt256",
            "Aarrgg67",
            "Expert1212",
            "Paperbag",
            "13th Dude",
            "danizduhman",
            "soad_lp",
            "Cl0n3_L3ad3r",
            "Sir Jem 05",
            "tatanka 94",
            "Toony",
            "Dark2246",
            "Drumgun",
            "Muumi75",
    };

    private static final String[] possibleChats = {
            "lol",
            "sup",
            "wtf",
            "hey",
            "yo",
            "0wnt",
            "pwned",
            "dude",
            "whoa",
            "rofl",
            "lmao",
            "haha",
            "hey what up",
            "free stuff plz",
    };
    
    private static Random rnd = new Random();

    private static String randomName() {
        return possibleNames[rnd.nextInt(possibleNames.length)];
    }

    private static String randomChat() {
        return possibleChats[rnd.nextInt(possibleChats.length)];
    }

    private double x, y;
    private double dir;
    private final String username;
    private double dirDir;
    private double timeLeft;
    private Color col;

    public Enemy() {
        this.username = randomName();
        x = (rnd.nextDouble() - .5) * 40;
        y = (rnd.nextDouble() - .5) * 40;
        dir = rnd.nextDouble() * Math.PI * 2;
        newDir();
        col = new Color((float) Math.random(), .2f, 1f);
    }

    private double distance() {
        return Math.sqrt(x*x + y*y);
    }

    private void newDir() {
        dirDir = (rnd.nextDouble() - 0.5) * 0.1;
        timeLeft = rnd.nextDouble() * 2;
    }

    private void chat() {
        String msg = username + ": " + randomChat();
        ChatArea.chat.add(msg);
    }

    public void logic(double dt) {
        if (rnd.nextDouble() < dt * 0.02)
            chat();
        
        double spe = 0.3;
        if (distance() > 100)
            dir = Math.atan2(y, x) + Math.PI;
        else {
            dir += dirDir;
            timeLeft -= dt;
            if (timeLeft <= 0)
                newDir();
        }
        x += spe * Math.cos(dir);
        y += spe * Math.sin(dir);
    }

    public void paint(GL2 gl) {
        gl.glPushMatrix();
        gl.glTranslated(x, y, 0);
        gl.glRotated(dir * 180 / Math.PI, 0, 0, 1);
        Random r = new Random(username.hashCode() * 100001);
        gl.glColor4fv(col.getRGBComponents(null), 0);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
        switch (System.identityHashCode(this) % 5) {
            case 0:
                glut.glutSolidCylinder(1, 4, 12, 12);
                break;
            case 1:
                glut.glutSolidSphere(2, 12, 12);
                break;
            case 2:
                glut.glutSolidCone(2, 2, 12, 12);
                break;
            case 3:
                gl.glRotated(90, 1, 0, 0);
                glut.glutSolidCone(2, 2, 12, 12);
                gl.glRotated(180, 0, 1, 0);
                glut.glutSolidCone(2, 2, 12, 12);
                break;
            case 4:
                glut.glutSolidSphere(2, 8, 8);
                gl.glTranslated(0, 0, 2);
                glut.glutSolidSphere(1.5, 8, 8);
                gl.glTranslated(0, 0, 1.5);
                glut.glutSolidSphere(1, 8, 8);
                break;
        }
        gl.glPopMatrix();
    }
}
