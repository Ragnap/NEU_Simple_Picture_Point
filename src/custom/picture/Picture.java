package custom.picture;

import java.awt.*;

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
     * 图形颜色
     */
    Color color = Color.BLACK;

    /**
     * 图形宽带
     */
    BasicStroke stroke = new BasicStroke(1.0f);


    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
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

    public Picture(){}
    /**
     * 提供参数构建
     * @param pictureKind 图形种类
     * @param RGB RGB值
     * @param lineWidth 图形粗细，单位像素
     * @param baseX 基点x坐标
     * @param baseY 基点y坐标
     */
    public Picture(int pictureKind,int RGB,float lineWidth,int baseX,int baseY){
        this.pictureKind= pictureKind;
        this.color = new Color(RGB);
        this.stroke = new BasicStroke(lineWidth);
        this.baseX = baseX;
        this.baseY = baseY;
    }
    /**
     * 移动图形
     *
     * @param deltaX X方向上位移
     * @param deltaY Y方向上位移
     */
    public void move(int deltaX, int deltaY) {
        this.baseX += deltaX;
        this.baseY += deltaY;
    }

    /**
     * 绘制图形，多态重载
     *
     * @param graphics 当前画笔
     */
    public void draw(Graphics2D graphics) {

    }

    /**
     * 转换成保持到文件的格式，多态重载
     * pictureKind RGB size baseX baseY 图形的特殊属性值
     *
     * @return 表示该图形的一行字符串
     */
    public String toFileString() {
        return pictureKind + " " + color.getRGB() + " " + stroke.getLineWidth() + " " + baseX + " " + baseY;
    }


}
