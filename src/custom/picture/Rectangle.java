package custom.picture;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Rectangle extends Picture {
    /**
     * 所有矩形的记数
     */
    static int rectangleCount = 0;
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
     * @param AX        A点x坐标
     * @param AY        A点Y坐标
     * @param BX        B点X坐标
     * @param BY        B点Y坐标
     * @param isPreview 是否是临时预览
     */
    public Rectangle(int AX, int AY, int BX, int BY, boolean isPreview) {
        if (!isPreview)
            rectangleCount++;
        this.pictureKind = 2;

        this.id = rectangleCount;
        this.name = "矩形" + id;

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
     * @param name        名称
     * @param width       长
     * @param height      宽
     */
    public Rectangle(int pictureKind, int RGB, float lineWidth, int baseX, int baseY, String name, int width, int height) {
        this.pictureKind = pictureKind;
        this.color = new Color(RGB);
        this.stroke = new BasicStroke(lineWidth);
        this.baseX = baseX;
        this.baseY = baseY;
        this.id = ++rectangleCount;
        this.name = name;
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
     * 绘制一个可以覆盖图形的矩形虚线框，多态重载
     *
     * @param graphics 当前画笔
     */
    public void drawBorder(Graphics2D graphics) {
        //设置特色属性
        Color originColor = graphics.getColor();
        BasicStroke originStroke = (BasicStroke) graphics.getStroke();
        graphics.setColor(new Color(91, 209, 215, 60));
        int minX = (int) (baseX - stroke.getLineWidth() / 2);
        int width = (int) (this.width + stroke.getLineWidth());
        int minY = (int) (baseY - stroke.getLineWidth() / 2);
        int height = (int) (this.height + stroke.getLineWidth());
        graphics.fillRect(minX, minY, width, height);
        graphics.setColor(new Color(32, 73, 105, 90));
        graphics.setStroke(new BasicStroke(5));
        graphics.drawRect(minX, minY, width, height);
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
        return super.toFileString() + "_" + width + "_" + height;
    }

    /**
     * 判断图形是否包含某个点，多态重载
     *
     * @param x 点x坐标
     * @param y 点y坐标
     * @return 该图形是否包含该点
     */
    public boolean isCoverPoint(int x, int y) {
        // 直接看是否在矩形范围内就行
        int nowX = x - baseX;
        int nowY = y - baseY;
        // x坐标在[-线宽/2,宽度+线宽/2]范围内，y坐标在[-线宽/2,宽度+线宽/2]范围内
        return ((nowX >= -stroke.getLineWidth() / 2 && nowX <= width + stroke.getLineWidth() / 2) && (nowY >= -stroke.getLineWidth() / 2 && nowY <= height + stroke.getLineWidth() / 2));
    }
}
