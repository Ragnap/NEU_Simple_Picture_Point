package custom.picture;

import java.awt.*;

import static java.lang.Math.abs;


public class Ellipse extends Picture {
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
     * @param OX O点X坐标
     * @param OY O点Y坐标
     * @param BX B点X坐标
     * @param BY B点Y坐标
     */
    public Ellipse(int OX, int OY, int BX, int BY) {
        pictureKind = 4;
        baseX = OX;
        baseY = OY;
        a = abs(BX - OX);
        b = abs(BY - OY);
    }

    /**
     * 提供参数构建
     *
     * @param pictureKind 图形种类
     * @param RGB         RGB值
     * @param lineWidth   图形粗细，单位像素
     * @param baseX       基点x坐标
     * @param baseY       基点y坐标
     * @param a           长半轴
     * @param b           短半轴
     */
    public Ellipse(int pictureKind, int RGB, float lineWidth, int baseX, int baseY, int a, int b) {
        this.pictureKind = pictureKind;
        this.color = new Color(RGB);
        this.stroke = new BasicStroke(lineWidth);
        this.baseX = baseX;
        this.baseY = baseY;
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
        return pictureKind + " " + color.getRGB() + " " + stroke.getLineWidth() + " " + baseX + " " + baseY + " " + a + " " + b;
    }
}
