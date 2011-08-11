package game;

import notswing.*;
import notswing.win.BaseWindow;
import notswing.win.ChildWindow;

import javax.media.opengl.GL2;
import java.io.File;

public class PlayerInfoWindow {
    public ChildWindow win;
    
    public PlayerInfoWindow() {
        Widget mainBox = new VBox(
                new HBox(
                        new Image(new File("/Users/dlubarov/Desktop/avatar2.gif")),
                        new FixedHSpace(8),
                        new VBox(new Label("Olivier404"), new Label("Level 22 Ninja"), new Label("Mountain View, CA"))
                ),
                new FixedVSpace(8),
                new HBox(new FlexibleSpace(), new Button(new Label("Detailed Stats")), new FlexibleSpace())
        );
        mainBox = mainBox.pad(8);
        
        win = new ChildWindow(mainBox, "Player Info", 0, 0) {
            boolean hasPacked = false;
            @Override public void paint(GL2 gl) {
                if (!hasPacked) {
                    pack();
                    hasPacked = true;
                }
                super.paint(gl);
            }
        };
        win.x0 = win.y0 = 10;
        win.y0 += BaseWindow.BAR_HEIGHT;
    }
}
