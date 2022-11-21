package custom.picture;

import java.awt.*;

/**
 * 直线类
 */
public class Line extends Picture {
    /**
     * 所有矩形的记数
     */
    static int lineCount = 0;
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
        this.pictureKind = 1;

        this.id = ++lineCount;
        this.name = "直线" + id;
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

    /**
     * 提供参数构建
     *
     * @param pictureKind 图形种类
     * @param RGB         RGB值
     * @param lineWidth   图形粗细，单位像素
     * @param baseX       基点x坐标
     * @param baseY       基点y坐标
     * @param name        名称
     * @param endX        终止点x坐标
     * @param endY        终止点y坐标
     */
    public Line(int pictureKind, int RGB, float lineWidth, int baseX, int baseY, String name, int endX, int endY) {
        this.pictureKind = pictureKind;
        this.color = new Color(RGB);
        this.stroke = new BasicStroke(lineWidth);
        this.baseX = baseX;
        this.baseY = baseY;
        this.id = ++lineCount;
        this.name = name;
        this.endX = endX;
        this.endY = endY;
    }

    public void draw(Graphics2D graphics) {
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

    /**
     * 转换成保持到文件的格式
     * 1 RGB size baseX baseY endX endY
     *
     * @return 表示该图形的一行字符串
     */
    public String toFileString() {
        return pictureKind + " " + color.getRGB() + " " + stroke.getLineWidth() + " " + baseX + " " + baseY + " " + name + " " + endX + " " + endY;
    }
}
