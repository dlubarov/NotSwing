package game;

import notswing.*;
import notswing.win.BaseWindow;
import notswing.win.ChildWindow;
import notswing.win.GlobalWindow;

import javax.media.opengl.GL2;

public class CharacterWindow {
    public CharacterWindow() {
        Widget stats = new EvenGrid(new Widget[][] {
                {new SkillPanel("aa"), new SkillPanel("bb")},
                {new SkillPanel("cc"), new SkillPanel("dd")},
                {new SkillPanel("ii"), new SkillPanel("jj")},
        });
        Widget skills = new EvenGrid(new Widget[][] {
                {new SkillPanel("ee"), new SkillPanel("ff")},
                {new SkillPanel("gg"), new SkillPanel("hh")},
                {new SkillPanel("kk")}//, new SkillPanel("ll")},
        });
        skills = new VBox(new FlexibleSpace(), skills, new FlexibleSpace());
        
        Widget content = new HBox(stats, new VerticalSep().pad(4), skills);
        content = content.pad(6);
        
        ChildWindow win = new ChildWindow(content, "Skills", 0, 0) {
            boolean hasPacked = false;
            @Override public void paint(GL2 gl) {
                if (!hasPacked) {
                    pack();
                    x0 = 10;
                    y0 = GlobalWindow.mainWindow.getHeight() - this.height - BaseWindow.BAR_HEIGHT - 10;
                    hasPacked = true;
                }
                super.paint(gl);
            }
        };
    }
}
