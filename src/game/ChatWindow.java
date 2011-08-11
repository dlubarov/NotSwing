package game;

import notswing.*;
import notswing.win.BaseWindow;
import notswing.win.ChildWindow;
import notswing.win.GlobalWindow;

public class ChatWindow {
    public ChatWindow() {
        Widget vbox = new VBox(
                ChatArea.chat,
                new FixedVSpace(10),
                new HBox(new TextBox("enter message"), new FixedHSpace(3), new Button(new Label("Send")))
        );
        vbox = vbox.pad(8);
        ChildWindow win = new ChildWindow(vbox, "Chat", 250, vbox.getMinHeight());
        win.x0 = GlobalWindow.mainWindow.getWidth() - win.width - 10;
        win.y0 = 10 + BaseWindow.BAR_HEIGHT;
    }
}
