import Picture.*;
import Picture.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Painter implements MouseListener, ActionListener, MouseMotionListener {
    int panelX, panelY;
    int x1, x2, y1, y2;
    private Page page;
    /**
     * 绘制模式
     * 0:不进行绘制
     * 1:直线
     * 2:矩形
     * 3:圆
     * 4:椭圆
     * 5:自由线
     */
    private int drawMode = 0;
    /**
     * 鼠标拖动轨迹上的点x坐标
     */
    private ArrayList<Integer> trailX;
    /**
     * 鼠标拖动轨迹上的点y坐标
     */
    private ArrayList<Integer> trailY;
    /**
     * 当前画笔颜色
     */
    private Color color = Color.BLACK;
    /**
     * 当前画笔粗细
     */
    private BasicStroke stroke = new BasicStroke();
    /**
     * 当前画笔字体
     */
    private Font font = new Font("宋体", Font.PLAIN, 10);

    public void setColor(Color color) {
        this.color = color;
    }

    public void setStroke(BasicStroke stroke) {
        this.stroke = stroke;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    /**
     * 根据drawMode的值与对应的变量创建新的对应的图形
     *
     * @return 指向对应的图形的父类
     */
    Picture getNewPicture() {
        Picture newPicture = switch (drawMode) {
            case 1 -> new Line(x1, y1, x2, y2);
            case 2 -> new Rectangle(x1, y1, x2, y2);
            case 3 -> new Circle(x1, y1, x2, y2);
            case 4 -> new Ellipse(x1, y1, x2, y2);
            case 5 -> new FreeLine(trailX, trailY);
            default -> null;
        };
        if (newPicture == null)
            return null;
        newPicture.setColor(color);
        newPicture.setStroke(stroke);
        return newPicture;
    }

    /**
     * 设置当前绘画页面
     *
     * @param page 当前页面
     */
    public void setPage(Page page) {
        this.page = page;
        panelX = 8;
        panelY = 52;
    }


    public void actionPerformed(ActionEvent e) {
        System.out.print("选中" + e.getActionCommand());
        System.out.print(" 颜色为(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "," + color.getAlpha() + ")");
        System.out.println(" 粗细为" + stroke.getLineWidth() + "px");
        // 画笔设置
        switch (e.getActionCommand()) {
            case "直线画笔" -> drawMode = 1;
            case "矩形画笔" -> drawMode = 2;
            case "圆形画笔" -> drawMode = 3;
            case "椭圆画笔" -> drawMode = 4;
            case "自由线画笔" -> drawMode = 5;
//            default -> drawMode = 0;
        }
        // 颜色设置
        switch (e.getActionCommand()) {
            case "黑色" -> color = Color.black;
            case "红色" -> color = Color.red;
            case "黄色" -> color = Color.yellow;
            case "蓝色" -> color = Color.blue;
            case "绿色" -> color = Color.green;
//            default -> color = Color.black;
        }
        if(e.getActionCommand().equals("精细颜色设置")){
            String inputValue = JOptionPane.showInputDialog("Please input a value");
            System.out.println(inputValue);
        }
    }


    // 鼠标进入
    public void mouseEntered(MouseEvent e) {

    }


    // 鼠标退出
    public void mouseExited(MouseEvent e) {

    }

    // 鼠标按下
    public void mousePressed(MouseEvent e) {
        if (drawMode == 0)
            return;
        x1 = e.getX() - panelX;
        y1 = e.getY() - panelY;
        if (drawMode == 5) {
            trailX = new ArrayList<>();
            trailY = new ArrayList<>();
            trailX.add(x1);
            trailY.add(y1);
        }
    }

    // 鼠标释放
    public void mouseReleased(MouseEvent e) {
        if (drawMode == 0)
            return;
        x2 = e.getX() - panelX;
        y2 = e.getY() - panelY;
        //避免单次点击产生一个像素点
        if (x2 == x1 && y1 == y2 && drawMode != 5)
            return;
        Picture newPicture = getNewPicture();
        if (newPicture != null)
            page.addImage(newPicture);
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
        if (drawMode == 0)
            return;
        x2 = e.getX() - panelX;
        y2 = e.getY() - panelY;
        if (drawMode == 5) {
            trailX.add(x2);
            trailY.add(y2);
        }
        Picture newPicture = getNewPicture();
        if (newPicture != null)
            page.addPreview(newPicture);
        page.paint();
    }
}
