package game;

import notswing.*;
import notswing.win.BaseWindow;
import notswing.win.ChildWindow;
import notswing.win.GlobalWindow;

import java.awt.*;

public class HealthBarsWindow {
    public HealthBarsWindow() {
        Widget hp = new HealthBar(Color.RED, 0.90);
        Widget mp = new HealthBar(Color.BLUE, 0.65);
        hp = new VBox(hp, new HBox(new FlexibleSpace(), new notswing.Label("HP"), new FlexibleSpace()));
        mp = new VBox(mp, new HBox(new FlexibleSpace(), new notswing.Label("MP"), new FlexibleSpace()));
        Widget content = new HBox(hp, new FixedHSpace(8), mp);
        content = new FixedPadding(content, 8, 8, 8, 5);
        
        ChildWindow win = new ChildWindow(content, "Status");
        win.x0 = GlobalWindow.mainWindow.getWidth() - win.width - 10;
        win.y0 = GlobalWindow.mainWindow.getHeight() - win.height - 10 - BaseWindow.BAR_HEIGHT;
    }
}
