import Image.Line;

import java.awt.*;
import java.awt.event.*;

public class DrawListener implements MouseListener, ActionListener, MouseMotionListener {
    int panelX, panelY;
    int x1, x2, y1, y2;
    private Page page;

    public void setPage(Page page) {
        this.page = page;
        panelX = 8;
        panelY = 52;

    }

    //记录图形类型信息
    String imageType = "";

    public void actionPerformed(ActionEvent e) {
        imageType = e.getActionCommand();
    }


    // 鼠标进入
    public void mouseEntered(MouseEvent e) {
    }


    // 鼠标退出
    public void mouseExited(MouseEvent e) {
    }

    // 鼠标按下
    public void mousePressed(MouseEvent e) {
        System.out.println(e.getX() + " " + e.getY());
        x1 = e.getX() - panelX;
        y1 = e.getY() - panelY;

    }

    // 鼠标释放
    public void mouseReleased(MouseEvent e) {


        x2 = e.getX() - panelX;
        y2 = e.getY() - panelY;

        Line newLine = new Line(x1, y1, x2, y2);
        page.addImage(newLine);
        page.paint();
    }

    // 鼠标单击
    public void mouseClicked(MouseEvent e) {

    }

    // 鼠标移动
    public void mouseMoved(MouseEvent e) {
    }

    // 鼠标拖动拖动
    public void mouseDragged(MouseEvent e) {

    }
}
