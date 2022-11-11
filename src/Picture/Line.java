package Picture;

import java.awt.*;

/**
 * 直线类
 */
public class Line extends Picture {
    /**
     * 直线右侧截止点X坐标
     */
    int endX;
    /**
     * 直线右侧截止点Y坐标
     */
    int endY;

    /**
     * 通过给定端点A，B创建直线
     *
     * @param AX A点x坐标
     * @param AY A点Y坐标
     * @param BX B点X坐标
     * @param BY B点Y坐标
     */
    public Line(int AX, int AY, int BX, int BY) {

        imageKind = 1;
        // 确保起点在左
        if (AX < BX) {
            this.baseX = AX;
            this.baseY = AY;
            this.endX = BX;
            this.endY = BY;
        } else {
            this.baseX = BX;
            this.baseY = BY;
            this.endX = AX;
            this.endY = AY;
        }
    }

    public void draw(Graphics2D graphics){
        //设置特色属性
        Color originColor = graphics.getColor();
        BasicStroke originStroke = (BasicStroke) graphics.getStroke();
        graphics.setColor(color);
        graphics.setStroke(stroke);

        graphics.drawLine(baseX, baseY, endX, endY);
        //还原画笔
        graphics.setColor(originColor);
        graphics.setStroke(originStroke);
    }
}
