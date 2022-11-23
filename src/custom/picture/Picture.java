package custom.picture;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 图形基类
 */
public class Picture {
    /**
     * 图形种类
     * 1:直线
     * 2:矩形
     * 3:圆
     * 4:椭圆
     * 5:自由线
     * 6:文字
     */
    int pictureKind;
    /**
     * 图形左上角X坐标
     */
    int baseX;
    /**
     * 图形左上角Y坐标
     */
    int baseY;
    /**
     * 图形名称
     */
    String name;
    /**
     * 图形编号
     */
    int id;
    /**
     * 图形颜色
     */
    Color color = Color.BLACK;

    /**
     * 图形宽带
     */
    BasicStroke stroke = new BasicStroke(1.0f);

    public void setBaseX(int baseX) {
        this.baseX = baseX;
    }

    public void setBaseY(int baseY) {
        this.baseY = baseY;
    }

    public int getPictureKind() {
        return pictureKind;
    }

    public int getBaseX() {
        return baseX;
    }

    public int getBaseY() {
        return baseY;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public BasicStroke getStroke() {
        return stroke;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setStroke(BasicStroke stroke) {
        this.stroke = stroke;
    }

    public Picture() {
    }

    /**
     * 提供参数构建
     *
     * @param pictureKind 图形种类
     * @param RGB         RGB值
     * @param lineWidth   图形粗细，单位像素
     * @param baseX       基点x坐标
     * @param baseY       基点y坐标
     */
    public Picture(int pictureKind, int RGB, float lineWidth, int baseX, int baseY) {
        this.pictureKind = pictureKind;
        this.color = new Color(RGB);
        this.stroke = new BasicStroke(lineWidth);
        this.baseX = baseX;
        this.baseY = baseY;
    }


    /**
     * 绘制图形，多态重载
     *
     * @param graphics 当前画笔
     */
    public void draw(Graphics2D graphics) {

    }

    /**
     * 绘制一个可以覆盖图形的矩形虚线框，多态重载
     *
     * @param graphics 当前画笔
     */
    public void drawBorder(Graphics2D graphics) {

    }

    /**
     * 移动整体图形的到x,y
     *
     * @param x 新的锚点x坐标
     * @param y 新的锚点y坐标
     */
    public void move(int x, int y) {
        //锚点移动距离
        int deltaX = x - baseX;
        int deltaY = y - baseY;
        //所有点移动
        baseX += deltaX;
        baseY += deltaY;
    }

    /**
     * 转换成保持到文件的格式，多态重载
     * pictureKind RGB size baseX baseY name
     *
     * @return 表示该图形的一行字符串
     */
    public String toFileString() {
        return pictureKind + "_" + color.getRGB() + "_" + stroke.getLineWidth() + "_" + baseX + "_" + baseY + "_" + Arrays.toString(name.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 判断图形是否包含某个点，多态重载
     *
     * @param x 点x坐标
     * @param y 点y坐标
     * @return 该图形是否包含该点
     */
    public boolean isCoverPoint(int x, int y) {
        return false;
    }


}
