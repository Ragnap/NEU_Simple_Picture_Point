package custom.picture;

import java.awt.*;

import static java.lang.Math.sqrt;

public class Circle extends Picture {
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
        imageKind = 3;
        baseX = OX;
        baseY = OY;
        r = (int) sqrt((BX - OX) * (BX - OX) + (BY - OY) * (BY - OY));
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
}
