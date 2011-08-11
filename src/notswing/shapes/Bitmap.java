package notswing.shapes;

import com.jogamp.opengl.util.texture.Texture;

import javax.media.opengl.GL2;

public class Bitmap extends Shape {
    private final double x0, y0, x1, y1;
    private final Texture tex;
    
    public Bitmap(double x0, double y0, double x1, double y1, Texture tex) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.tex = tex;
    }

    @Override
    public void paint(GL2 gl) {
        gl.glColor3f(1, 1, 1);
        gl.glEnable(GL2.GL_TEXTURE_2D);
        tex.enable();
        tex.bind();

        gl.glBegin(GL2.GL_QUADS);
            gl.glTexCoord2d(0, 0);
            gl.glVertex2d(x0, y0);

            gl.glTexCoord2d(1, 0);
            gl.glVertex2d(x1, y0);

            gl.glTexCoord2d(1, 1);
            gl.glVertex2d(x1, y1);

            gl.glTexCoord2d(0, 1);
            gl.glVertex2d(x0, y1);
        gl.glEnd();
        
        gl.glDisable(GL2.GL_TEXTURE_2D);
    }
}
