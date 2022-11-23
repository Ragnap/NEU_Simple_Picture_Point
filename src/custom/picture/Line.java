package custom.picture;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.lang.Math.*;

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

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    /**
     * 通过给定端点A，B创建直线
     *
     * @param AX        A点x坐标
     * @param AY        A点Y坐标
     * @param BX        B点X坐标
     * @param BY        B点Y坐标
     * @param isPreview 是否是临时预览
     */
    public Line(int AX, int AY, int BX, int BY, boolean isPreview) {
        if (!isPreview)
            lineCount++;
        this.pictureKind = 1;
        this.id = lineCount;
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

    /**
     * 移动整体图形的到x,y
     *
     * @param x 新的锚点x坐标
     * @param y 新的锚点y坐标
     */
    public void move(int x, int y) {
        //锚点移动距离
        int deltaX = x- baseX ;
        int deltaY = y- baseY ;
        //所有点移动
        baseX += deltaX;
        baseY += deltaY;
        endX += deltaX;
        endY += deltaY;
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
     * 绘制一个可以覆盖图形的矩形虚线框，多态重载
     *
     * @param graphics 当前画笔
     */
    public void drawBorder(Graphics2D graphics) {
        //设置特色属性
        Color originColor = graphics.getColor();
        BasicStroke originStroke = (BasicStroke) graphics.getStroke();
        graphics.setColor(new Color(91, 209, 215, 60));
        int minX = (int) (min(baseX, endX) - stroke.getLineWidth() / 2);
        int width = (int) (abs(endX - baseX) + stroke.getLineWidth());
        int minY = (int) (min(baseY, endY) - stroke.getLineWidth() / 2);
        int height = (int) (abs(endY - baseY) + stroke.getLineWidth());

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
     * 1 RGB size baseX baseY endX endY
     *
     * @return 表示该图形的一行字符串
     */
    public String toFileString() {
        return super.toFileString() + "_" + endX + "_" + endY;
    }

    /**
     * 判断图形是否包含某个点，多态重载
     *
     * @param x 点x坐标
     * @param y 点y坐标
     * @return 该图形是否包含该点
     */
    public boolean isCoverPoint(int x, int y) {
        //利用点到线段距离是否小于线段宽度判断
        return getPointSegmentDistance(baseX, baseY, endX, endY, x, y) <= stroke.getLineWidth() / 2;
    }

    /**
     * 求点到线段距离，使用向量法求解距离
     *
     * @param x1 线段端点1的x坐标
     * @param y1 线段端点1的y坐标
     * @param x2 线段端点2的x坐标
     * @param y2 线段端点2的y坐标
     * @param x  待求解点的x坐标
     * @param y  待求解点的x坐标
     * @return 点到线段距离
     */
    double getPointSegmentDistance(int x1, int y1, int x2, int y2, int x, int y) {
        double cross = (x2 - x1) * (x - x1) + (y2 - y1) * (y - y1);
        if (cross <= 0)
            return sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));

        double d2 = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1); //|AB|^2：矢量AB的大小的平方
        if (cross >= d2)
            return sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2)); //是|BP|：矢量的大小

        double r = cross / d2;  //求出垂足
        double px = x1 + (x2 - x1) * r;
        double py = y1 + (y2 - y1) * r;
        return sqrt((x - px) * (x - px) + (py - y) * (py - y));
    }
}
