package game;

import notswing.*;
import notswing.win.GlobalWindow;

import java.io.IOException;

public class Demo {
    public static void main(String[] _) throws IOException {
        Widget content = new GameWidget();
        GlobalWindow.mainWindow = new GlobalWindow(content, 1024, 768, "NotSwing");

        new ChatWindow();
        new PlayerInfoWindow();
        new HealthBarsWindow();
        new CharacterWindow();
    }
}
