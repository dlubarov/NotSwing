package game;

import notswing.FixedPadding;
import notswing.Image;
import notswing.VBox;

import java.io.File;

public class SkillPanel extends FixedPadding {
    private static final int p = 2;
    
    public SkillPanel(String skill) {
        super(new Image(new File("/Users/dlubarov/Desktop/skills/" + skill + ".png")),
                p, p, p, p);
    }
}
