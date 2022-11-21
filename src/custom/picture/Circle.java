package custom.picture;

import java.awt.*;
import java.util.ArrayList;

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

    /**
     * 创建以O为圆心,过B点的圆
     *
     * @param OX O点X坐标
     * @param OY O点Y坐标
     * @param BX B点X坐标
     * @param BY B点Y坐标
     */
    public Circle(int OX, int OY, int BX, int BY) {
        this.pictureKind = 3;

        this.id = ++circleCount;
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
     * @param baseX       基点x坐标
     * @param baseY       基点y坐标
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
     * 转换成保持到文件的格式
     * 3 RGB size baseX baseY r
     *
     * @return 表示该图形的一行字符串
     */
    public String toFileString() {
        return pictureKind + " " + color.getRGB() + " " + stroke.getLineWidth() + " " + baseX + " " + baseY + " " + name + " " + r;
    }
}
