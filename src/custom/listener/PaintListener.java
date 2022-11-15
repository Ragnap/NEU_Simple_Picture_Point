package custom.listener;

import custom.panel.PagePane;
import custom.picture.*;

import custom.picture.Rectangle;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PaintListener implements MouseListener, MouseMotionListener {

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
    private PagePane nowPage;
    /**
     * 当前绘制模式
     * 0:不进行绘制
     * 1:直线
     * 2:矩形
     * 3:圆
     * 4:椭圆
     * 5:自由线
     */
    private int drawMode = 1;
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
     * @param nowPage 当前页面
     */
    public void setPage(PagePane nowPage) {
        this.nowPage = nowPage;
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
        //避免非任意线时单次点击产生一个像素点
        if (x2 == x1 && y1 == y2 && drawMode != 5)
            return;
        Picture newPicture = getNewPicture();
        if (newPicture != null) {
            nowPage.addImage(newPicture);

            String pictureType = switch (drawMode) {
                case 1 -> "直线";
                case 2 -> "矩形";
                case 3 -> "圆形";
                case 4 -> "椭圆";
                case 5 -> "任意线";
                default -> null;
            };
            System.out.println("新增图形：" + pictureType);
        }
        nowPage.update();
    }

    // 鼠标单击
    public void mouseClicked(MouseEvent e) {
        nowPage.update();
    }

    // 鼠标移动
    public void mouseMoved(MouseEvent e) {

    }

    // 鼠标拖动拖动
    public void mouseDragged(MouseEvent e) {
        if (drawMode == 0)
            return;
        x2 = e.getX();
        y2 = e.getY();
        if (drawMode == 5) {
            trailX.add(x2);
            trailY.add(y2);
        }
        Picture newPicture = getNewPicture();
        if (newPicture != null)
            nowPage.addPreview(newPicture);
        nowPage.update();
    }
}
