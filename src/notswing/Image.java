package notswing;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import notswing.shapes.Bitmap;

import java.io.File;
import java.io.IOException;

public class Image extends Widget {
    private Texture tex = null;
    private File file;
    
    public Image(Texture tex) {
        this.tex = tex;
    }
    
    public Image(File f) {
        file = f;
    }

    private void ensureInit() {
        if (tex == null) {
            try {
                tex = TextureIO.newTexture(file, false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public double getMinWidth() {
        ensureInit();
        return tex.getImageWidth();
    }

    @Override
    public double getMinHeight() {
        ensureInit();
        return tex.getImageHeight();
    }

    @Override
    public boolean likesToStretchHorizontally() {
        return false;
    }

    @Override
    public boolean likesToStretchVertically() {
        return false;
    }

    @Override
    public void paint(PaintTarget target) {
        ensureInit();
        new Bitmap(target.x0(), target.y0(), target.x1(), target.y1(), tex).paint(target.gl);
    }
}
