package custom.picture;

import java.awt.*;

public class Rectangle extends Picture {
    /**
     * 矩形宽
     */
    int width;
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
        pictureKind = 2;
        this.baseX = Math.min(AX, BX);
        this.baseY = Math.min(AY, BY);
        this.width = Math.abs(AX - BX);
        this.height = Math.abs(AY - BY);
    }

    /**
     * 提供参数构建
     *
     * @param pictureKind 图形种类
     * @param RGB         RGB值
     * @param lineWidth   图形粗细，单位像素
     * @param baseX       基点x坐标
     * @param baseY       基点y坐标
     * @param width       长
     * @param height      宽
     */
    public Rectangle(int pictureKind, int RGB, float lineWidth, int baseX, int baseY, int width, int height) {
        this.pictureKind = pictureKind;
        this.color = new Color(RGB);
        this.stroke = new BasicStroke(lineWidth);
        this.baseX = baseX;
        this.baseY = baseY;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics2D graphics) {
        //设置特色属性
        Color originColor = graphics.getColor();
        BasicStroke originStroke = (BasicStroke) graphics.getStroke();
        graphics.setColor(color);
        graphics.setStroke(stroke);

        graphics.drawRect(baseX, baseY, width, height);
        //还原画笔
        graphics.setColor(originColor);
        graphics.setStroke(originStroke);
    }

    /**
     * 转换成保持到文件的格式
     * 2 RGB size baseX baseY weight height
     *
     * @return 表示该图形的一行字符串
     */
    public String toFileString() {
        return pictureKind + " " + color.getRGB() + " " + stroke.getLineWidth() + " " + baseX + " " + baseY + " " + width + " " + height;
    }
}
