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
        imageKind = 4;
        baseX = OX;
        baseY = OY;
        a = abs(BX - OX);
        b = abs(BY - OY);
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
     * 4 R G B size baseX baseY a b
     *
     * @return 表示该图形的一行字符串
     */
    public String toFileString() {
        return imageKind + " " + color.getRed() + " " + color.getGreen() + " " + color.getBlue() + " " + stroke.getLineWidth() + " "
                + baseX + " " + baseY + " " + a + " " + b;
    }
}
