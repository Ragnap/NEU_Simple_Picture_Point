package custom.picture;

import java.awt.*;

public class Rectangle extends Picture {
    /**
     * 矩形宽
     */
    int weight;
    /**
     * 矩形长
     */
    int height;

    /**
     * 通过给左上角点A，右下角点B创建矩形
     *
     * @param AX A点x坐标
     * @param AY A点Y坐标
     * @param BX B点X坐标
     * @param BY B点Y坐标
     */
    public Rectangle(int AX, int AY, int BX, int BY) {
        imageKind = 2;
        this.baseX = Math.min(AX, BX);
        this.baseY = Math.min(AY, BY);
        this.weight = Math.abs(AX - BX);
        this.height = Math.abs(AY - BY);
    }

    public void draw(Graphics2D graphics){
        //设置特色属性
        Color originColor = graphics.getColor();
        BasicStroke originStroke = (BasicStroke) graphics.getStroke();
        graphics.setColor(color);
        graphics.setStroke(stroke);

        graphics.drawRect(baseX, baseY, weight, height);
        //还原画笔
        graphics.setColor(originColor);
        graphics.setStroke(originStroke);
    }
    /**
     * 转换成保持到文件的格式
     * 2 R G B size baseX baseY weight height
     *
     * @return 表示该图形的一行字符串
     */
    public String toFileString() {
        return imageKind + " " + color.getRed() + " " + color.getGreen() + " " + color.getBlue() + " " + stroke.getLineWidth() + " "
                + baseX + " " + baseY + " " + weight + " " + height;
    }
}
