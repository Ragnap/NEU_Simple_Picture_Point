package custom.picture;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.sqrt;

public class Circle extends Picture {
    /**
     * 所有圆形的编号记数
     */
    static int circleCount = 0;

    /**
     * 半径
     */
    int r;

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    /**
     * 创建以O为圆心,过B点的圆
     *
     * @param OX        O点X坐标
     * @param OY        O点Y坐标
     * @param BX        B点X坐标
     * @param BY        B点Y坐标
     * @param isPreview 是否是临时预览
     */
    public Circle(int OX, int OY, int BX, int BY, boolean isPreview) {
        if (!isPreview)
            circleCount++;
        this.pictureKind = 3;

        this.id = circleCount;
        this.name = "圆形" + id;

        this.baseX = OX;
        this.baseY = OY;
        this.r = (int) sqrt((BX - OX) * (BX - OX) + (BY - OY) * (BY - OY));
    }

    /**
     * 提供参数构建
     *
     * @param pictureKind 图形种类
     * @param RGB         RGB值
     * @param lineWidth   图形粗细，单位像素
     * @param baseX       圆点x坐标
     * @param baseY       圆点y坐标
     * @param name        名称
     * @param r           半径
     */
    public Circle(int pictureKind, int RGB, float lineWidth, int baseX, int baseY, String name, int r) {
        this.pictureKind = pictureKind;
        this.color = new Color(RGB);
        this.stroke = new BasicStroke(lineWidth);
        this.baseX = baseX;
        this.baseY = baseY;
        this.id = ++circleCount;
        this.name = name;
        this.r = r;
    }

    public void draw(Graphics2D graphics) {
        //设置特色属性
        Color originColor = graphics.getColor();
        BasicStroke originStroke = (BasicStroke) graphics.getStroke();
        graphics.setColor(color);
        graphics.setStroke(stroke);

        graphics.drawOval(baseX - r, baseY - r, r * 2, r * 2);
        //还原画笔
        graphics.setColor(originColor);
        graphics.setStroke(originStroke);
    }

    /**
     * 绘制一个可以覆盖图形的矩形虚线框，多态重载
     *
     * @param graphics 当前画笔
     */
    public void drawBorder(Graphics2D graphics) {
        //设置特色属性
        Color originColor = graphics.getColor();
        BasicStroke originStroke = (BasicStroke) graphics.getStroke();
        graphics.setColor(new Color(91, 209, 215, 60));
        int minX = (int) (baseX - r - stroke.getLineWidth() / 2);
        int width = (int) (r * 2 + stroke.getLineWidth());
        int minY = (int) (baseY - r - stroke.getLineWidth() / 2);
        int height = (int) (r * 2 + stroke.getLineWidth());
        graphics.fillRect(minX, minY, width, height);
        graphics.setColor(new Color(32, 73, 105, 90));
        graphics.setStroke(new BasicStroke(5));
        graphics.drawRect(minX, minY, width, height);
        //还原画笔
        graphics.setColor(originColor);
        graphics.setStroke(originStroke);
    }

    /**
     * 转换成保持到文件的格式
     * 3 RGB size baseX baseY r
     *
     * @return 表示该图形的一行字符串
     */
    public String toFileString() {
        return super.toFileString() + "_" + r;
    }

    /**
     * 判断图形是否包含某个点，多态重载
     *
     * @param x 点x坐标
     * @param y 点y坐标
     * @return 该图形是否包含该点
     */
    public boolean isCoverPoint(int x, int y) {
        //通过计算点到圆点距离
        int distanceSquare = (x - baseX) * (x - baseX) + (y - baseY) * (y - baseY);
        return distanceSquare <= (r + stroke.getLineWidth() / 2) * (r + stroke.getLineWidth() / 2);
    }
}
