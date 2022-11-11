package Picture;
import java.awt.*;

import static java.lang.Math.abs;


public class Ellipse extends Picture{
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
     * @param OX O点X坐标
     * @param OY O点Y坐标
     * @param BX B点X坐标
     * @param BY B点Y坐标
     */
    public Ellipse(int OX, int OY, int BX, int BY) {
        imageKind = 4;
        baseX = OX;
        baseY = OY;
        a=abs(BX-OX);
        b=abs(BY-OY);
    }

    public void draw(Graphics graphics) {
        graphics.drawOval(baseX - a, baseY - b, a * 2, b * 2);
    }
}
