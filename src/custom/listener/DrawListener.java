package custom.listener;

import custom.panel.PageEditPane;
import custom.picture.*;

import custom.picture.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawListener implements MouseListener, MouseMotionListener {

    private int x1, x2, y1, y2;

    /**
     * 鼠标拖动轨迹上的点x坐标
     */
    private ArrayList<Integer> trailX;
    /**
     * 鼠标拖动轨迹上的点y坐标
     */
    private ArrayList<Integer> trailY;
    /**
     * 当前绘制页
     */
    private PageEditPane pageEditPane;
    /**
     * 当前绘制模式
     * 0:不进行绘制
     * 1:直线
     * 2:矩形
     * 3:圆
     * 4:椭圆
     * 5:自由线
     * 6:文字
     */
    private int drawMode = 0;
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
    private Font font = new Font("宋体", Font.PLAIN, 12);

    /**
     * 当前待插入的文字
     */

    public void setDrawMode(int drawMode) {
        this.drawMode = drawMode;
    }

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
     * 通过弹出输入框的方式获得待插入字符
     *
     * @return 待插入的字符
     */
    private String getTextContent() {
        return JOptionPane.showInputDialog(pageEditPane, "待插入的文字", "插入文字", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 根据drawMode的值与对应的变量创建新的对应的图形
     *
     * @return 指向对应的图形的父类
     */
    Picture getNewPicture(boolean isPreview) {
        // 插入文字的模式需要获取待插入文字
        String textContent = null;
        if (drawMode == 6) {
            textContent = getTextContent();
            // 取消了文字的输入
            if (textContent == null)
                return null;
        }
        // 获取对应的图像
        Picture newPicture = switch (drawMode) {
            case 1 -> new Line(x1, y1, x2, y2, isPreview);
            case 2 -> new Rectangle(x1, y1, x2, y2, isPreview);
            case 3 -> new Circle(x1, y1, x2, y2, isPreview);
            case 4 -> new Ellipse(x1, y1, x2, y2, isPreview);
            case 5 -> new FreeLine(trailX, trailY, isPreview);
            case 6 -> new Text(x1, y1, textContent, font, isPreview);
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
     * @param pageEditPane 当前页面
     */
    public void setEditPane(PageEditPane pageEditPane) {
        this.pageEditPane = pageEditPane;
    }

    /**
     * 添加一个新的图像
     */
    void addPicture() {
        Picture newPicture = getNewPicture(false);
        if (newPicture != null) {
            pageEditPane.addPicture(newPicture);
            String pictureType = switch (drawMode) {
                case 1 -> "直线";
                case 2 -> "矩形";
                case 3 -> "圆形";
                case 4 -> "椭圆";
                case 5 -> "任意线";
                case 6 -> "文字";
                default -> null;
            };
            System.out.println("新增图形：" + pictureType);
        }
        pageEditPane.refresh();
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
        x1 = e.getX();
        y1 = e.getY();
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
        x2 = e.getX();
        y2 = e.getY();
        //避免绘制普通图像时单次点击产生一个像素点
        if (x2 == x1 && y1 == y2 && (drawMode == 1 || drawMode == 2 || drawMode == 3 || drawMode == 4))
            return;
        addPicture();
    }

    // 鼠标单击
    public void mouseClicked(MouseEvent e) {
        pageEditPane.refresh();
    }

    // 鼠标移动
    public void mouseMoved(MouseEvent e) {

    }

    // 鼠标拖动拖动
    public void mouseDragged(MouseEvent e) {
        if (drawMode == 0)
            return;
        // 记录预览中的结束点
        x2 = e.getX();
        y2 = e.getY();

        // 自由线模式记录路径点
        if (drawMode == 5) {
            trailX.add(x2);
            trailY.add(y2);
        }
        // 文字模式不添加预览
        if (drawMode != 6) {
            Picture newPicture = getNewPicture(true);
            if (newPicture != null)
                pageEditPane.addPreview(newPicture);
            pageEditPane.update();
        }

    }
}
