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
     * 5:文本
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

    public int getBaseX() {
        return baseX;
    }

    public int getBaseY() {
        return baseY;
    }

    public int getImageKind() {
        return imageKind;
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
    public void draw(Graphics graphics){

    }
}
