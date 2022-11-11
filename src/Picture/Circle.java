package Picture;

import java.awt.*;

import static java.lang.Math.sqrt;

public class Circle extends Picture {
    /**
     * 半径
     */
    int r;

    public int getR() {
        return r;
    }

    /**
     * 创建以O为圆心,过B点的圆
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

    public void draw(Graphics graphics) {
        graphics.drawOval(baseX - r, baseY - r, r * 2, r * 2);
    }
}
