package notswing.win;

import notswing.ColorUtil;
import notswing.shapes.RectangleFill;
import notswing.shapes.RectangleFillAndOutline;
import notswing.shapes.RectangleOutline;
import notswing.shapes.Text;

import javax.media.opengl.GL2;
import java.awt.*;

public class BaseWindow {
    public static double BAR_HEIGHT = 20;
    private static Font font = new Font("Trebuchet MS", Font.PLAIN, 14);
    public static Color outlineColor = Color.GRAY;
    
    public static void paintWindowBG(GL2 gl, double x0, double y0, double width, double height, String caption, float focus) {
        width -= 1; height -= 1;
        float contentSat = .03f * focus,
              contentVal = .97f + .03f * focus;
        RectangleFill contentRect = new RectangleFill(
                x0, y0 + BAR_HEIGHT, width, height,
                ColorUtil.phasalColor(contentSat, contentVal, 0, 4),
                ColorUtil.phasalColor(contentSat, contentVal, 1, 4),
                ColorUtil.phasalColor(contentSat, contentVal, 2, 4),
                ColorUtil.phasalColor(contentSat, contentVal, 3, 4));
        contentRect.paint(gl);
    }

    public static void paintWindowFG(GL2 gl, double x0, double y0, double width, double height, String caption, float focus) {
        width -= 1; height -= 1;
        float barSat = .2f * focus,
              barVal = .9f + .1f * focus;
        RectangleFillAndOutline barRect = new RectangleFillAndOutline(
                x0, y0, width, BAR_HEIGHT,
                ColorUtil.phasalColor(barSat, barVal, 0, 4),
                ColorUtil.phasalColor(barSat, barVal, 1, 4),
                ColorUtil.phasalColor(barSat, barVal, 2, 4),
                ColorUtil.phasalColor(barSat, barVal, 3, 4),
                outlineColor);
        RectangleOutline contentRect = new RectangleOutline(
                x0, y0 + BAR_HEIGHT, width, height,
                outlineColor);
        barRect.paint(gl);
        contentRect.paint(gl);
        new Text(font, caption, x0 + 5, y0, ColorUtil.grayShade(0.2f*(1-focus))).paint(gl);
    }
}
