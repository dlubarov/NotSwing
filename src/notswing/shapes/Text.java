package notswing.shapes;

import com.jogamp.opengl.util.awt.TextRenderer;
import notswing.win.GlobalWindow;

import javax.media.opengl.GL2;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Text extends Shape {
    private static Map<Font, TextRenderer> rendererCache = new HashMap<Font, TextRenderer>();

    private static TextRenderer getRendererFor(Font font) {
        TextRenderer renderer = rendererCache.get(font);
        if (renderer == null)
            rendererCache.put(font, renderer = new TextRenderer(font, true, true, null, true));
        return renderer;
    }

    private final String text;
    private final Font font;
    private final double x, y;
    private final float[] color;

    public Text(Font font, String text, double x, double y, Color color) {
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;
        this.color = color.getRGBColorComponents(null);
    }
    
    @Override
    public void paint(GL2 gl) {
        gl.glColor3fv(color, 0);
        TextRenderer renderer = getRendererFor(font);
        renderer.beginRendering(GlobalWindow.mainWindow.getWidth(), GlobalWindow.mainWindow.getHeight());
        renderer.setColor(color[0], color[1], color[2], 1);
        renderer.draw(text, (int) x, GlobalWindow.mainWindow.getHeight() - (int) (y + renderer.getBounds("Tg").getHeight()));
        renderer.endRendering();
    }
}
