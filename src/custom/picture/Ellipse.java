package custom.picture;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.lang.Math.abs;


public class Ellipse extends Picture {
    /**
     * 所有椭圆的记数
     */
    static int ellipseCount = 0;
    /**
     * 半长轴
     */
    int a;
    /**
     * 半短轴
     */
    int b;

    /**
     * 创建以O为圆心,过B点的矩形的内切椭圆
     *
     * @param OX        O点X坐标
     * @param OY        O点Y坐标
     * @param BX        B点X坐标
     * @param BY        B点Y坐标
     * @param isPreview 是否是临时预览
     */
    public Ellipse(int OX, int OY, int BX, int BY, boolean isPreview) {
        if (!isPreview)
            ellipseCount++;

        this.pictureKind = 4;

        this.id = ellipseCount;
        this.name = "椭圆" + id;

        this.baseX = OX;
        this.baseY = OY;
        this.a = abs(BX - OX);
        this.b = abs(BY - OY);
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
     * @param a           长半轴
     * @param b           短半轴
     */
    public Ellipse(int pictureKind, int RGB, float lineWidth, int baseX, int baseY, String name, int a, int b) {
        this.pictureKind = pictureKind;
        this.color = new Color(RGB);
        this.stroke = new BasicStroke(lineWidth);
        this.baseX = baseX;
        this.baseY = baseY;
        this.id = ++ellipseCount;
        this.name = name;
        this.a = a;
        this.b = b;
    }

    public void draw(Graphics2D graphics) {
        //设置特色属性
        Color originColor = graphics.getColor();
        BasicStroke originStroke = (BasicStroke) graphics.getStroke();
        graphics.setColor(color);
        graphics.setStroke(stroke);

        graphics.drawOval(baseX - a, baseY - b, a * 2, b * 2);
        //还原画笔
        graphics.setColor(originColor);
        graphics.setStroke(originStroke);
    }

    /**
     * 转换成保持到文件的格式
     * 4 RGB size baseX baseY a b
     *
     * @return 表示该图形的一行字符串
     */
    public String toFileString() {
        return pictureKind + "_" + color.getRGB() + "_" + stroke.getLineWidth() + "_" + baseX + "_" + baseY + "_" + Arrays.toString(name.getBytes(StandardCharsets.UTF_8)) + "_" + a + "_" + b;
    }

    /**
     * 判断图形是否包含某个点，多态重载
     *
     * @param x 点x坐标
     * @param y 点y坐标
     * @return 该图形是否包含该点
     */
    public boolean isCoverPoint(int x, int y) {
        //转换为以椭圆中心的坐标系后通过计算椭圆方程判断
        double nowX = x - baseX;
        double nowY = y - baseY;
        //增加一定的范围来适应边界
        return (nowX * nowX) / (a * a) + (nowY * nowY) / (b * b) <= 1.1;
    }
}
