package Picture;

import java.awt.*;

/**
 * 图形基类
 */
public class Picture {
    /**
     * 图形种类
     * 1:直线
     * 2:矩形
     * 3:圆
     * 4:椭圆
     * 5:自由线
     */
    int imageKind;
    /**
     * 图形左上角X坐标
     */
    int baseX;
    /**
     * 图形左上角Y坐标
     */
    int baseY;
    /**
     *  图形名称
     */
    String name;

    /**
     *  图形颜色
     */
    Color color = Color.BLACK;

    /**
     *  图形宽带
     */
    BasicStroke stroke = new BasicStroke(1.0f);

    public int getBaseX() {
        return baseX;
    }

    public int getBaseY() {
        return baseY;
    }

    public int getImageKind() {
        return imageKind;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setStroke(BasicStroke stroke) {
        this.stroke = stroke;
    }

    /**
     * 移动图形
     *
     * @param deltaX X方向上位移
     * @param deltaY Y方向上位移
     */
    public void move(int deltaX, int deltaY) {
        this.baseX += deltaX;
        this.baseY += deltaY;
    }

    /**
     * 绘制图形，多态重载
     * @param graphics 当前画笔
     */
    public void draw(Graphics2D graphics){

    }
}
