package game;

import notswing.Label;
import notswing.PaintTarget;
import notswing.VBox;
import notswing.Widget;

import java.util.LinkedList;
import java.util.Queue;

public class ChatArea extends Widget {
    public static final ChatArea chat = new ChatArea(5);
    
    private final int capacity;
    private final Queue<String> messages;
    
    private ChatArea(int capacity) {
        this.capacity = capacity;
        messages = new LinkedList<String>();
        while (messages.size() < capacity)
            messages.add("");
    }

    public void add(String msg) {
        if (messages.size() >= capacity)
            messages.remove();
        messages.add(msg);
    }

    private VBox worker() {
        VBox box = new VBox();
        for (String msg : messages)
            box.add(new Label(msg));
        return box;
    }

    @Override
    public double getMinWidth() {
        return 0;
    }

    @Override
    public double getMinHeight() {
        return worker().getMinHeight();
    }

    @Override
    public boolean likesToStretchHorizontally() {
        return true;
    }

    @Override
    public boolean likesToStretchVertically() {
        return false;
    }

    @Override
    public void paint(PaintTarget target) {
        worker().paint(target);
    }
}
