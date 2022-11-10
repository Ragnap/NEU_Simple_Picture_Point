import Image.BaseImage;
import Image.Line;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * 单页面
 */
public class Page extends JPanel {
    /**
     * 该页面上的图形列表
     */
    ArrayList<BaseImage> Images= new ArrayList<>();

    void addImage(BaseImage newImage) {
        Images.add(newImage);
        System.out.println(Images.size());
    }

    public void paint() {
        System.out.println(this.getX() + " " + this.getY());
        Graphics graphics = this.getGraphics();
        for (int i = 0; i < Images.size(); i++) {
            Line now = (Line) Images.get(i);
            graphics.drawLine(now.getBaseX(),now.getBaseY(),now.getEndX(),now.getEndY());
        }
    }
}
