package custom.picture;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Text extends Picture {
    /**
     * 所有文字记数
     */
    static int textCount = 0;
    /**
     * 字体
     */
    Font font = new Font("宋体", Font.PLAIN, 12);
    /**
     * 内容
     */
    String content = "";

    /**
     * 通过给左上角点与文本与字体创建文字
     *
     * @param x         左上角点x坐标
     * @param y         左上角点Y坐标
     * @param content   文本
     * @param font      字体
     * @param isPreview 是否是临时预览
     */
    public Text(int x, int y, String content, Font font, boolean isPreview) {
        if (!isPreview)
            textCount++;
        this.pictureKind = 6;
        this.id = textCount;
        this.name = "文字" + id;
        this.baseX = x;
        this.baseY = y;
        this.content = content;
        this.font = font;
    }

    /**
     * 提供参数构建
     *
     * @param pictureKind 图形种类
     * @param RGB         RGB值
     * @param lineWidth   画笔粗细，文本无关
     * @param baseX       基点x坐标
     * @param baseY       基点y坐标
     * @param content     文本内容
     * @param name        名称
     * @param font        字体
     */
    public Text(int pictureKind, int RGB, float lineWidth, int baseX, int baseY, String name, String content, Font font) {
        this.pictureKind = pictureKind;
        this.color = new Color(RGB);
        this.stroke = new BasicStroke(lineWidth);
        this.baseX = baseX;
        this.baseY = baseY;
        this.id = ++textCount;
        this.name = name;
        this.content = content;
        this.font = font;
    }

    /**
     * 绘制图形
     *
     * @param graphics 当前画笔
     */
    public void draw(Graphics2D graphics) {
        //设置特色属性
        Color originColor = graphics.getColor();
        BasicStroke originStroke = (BasicStroke) graphics.getStroke();
        Font originFont = graphics.getFont();
        graphics.setColor(color);
        graphics.setStroke(stroke);
        graphics.setFont(font);

        graphics.drawString(content, baseX, baseY);

        //还原画笔
        graphics.setColor(originColor);
        graphics.setStroke(originStroke);
        graphics.setFont(originFont);
    }
    /**
     * 绘制一个可以覆盖图形的矩形虚线框，多态重载
     *
     * @param graphics 当前画笔
     */
    public void drawBorder(Graphics2D graphics){
        //设置特色属性
        Color originColor = graphics.getColor();
        BasicStroke originStroke = (BasicStroke) graphics.getStroke();
        graphics.setColor(new Color(91,209,215, 60));

        int width = content.length() * font.getSize();
        int height = font.getSize();

        graphics.fillRect(baseX, baseY-height, width, height);
        graphics.setColor(new Color(32,73,105,90));
        graphics.setStroke(new BasicStroke(5));
        graphics.drawRect(baseX, baseY-height, width, height);
        //还原画笔
        graphics.setColor(originColor);
        graphics.setStroke(originStroke);
    }
    /**
     * 转换成保持到文件的格式
     * 6 RGB size baseX baseY content fontName fontStyle fontSize
     *
     * @return 表示该图形的一行字符串
     */
    public String toFileString() {
        return super.toFileString() + "_" + Arrays.toString(content.getBytes(StandardCharsets.UTF_8)) + "_" + Arrays.toString(font.getFontName().getBytes(StandardCharsets.UTF_8)) + "_" + font.getStyle() + "_" + font.getSize();
    }

    /**
     * 判断图形是否包含某个点，多态重载
     *
     * @param x 点x坐标
     * @param y 点y坐标
     * @return 该图形是否包含该点
     */
    public boolean isCoverPoint(int x, int y) {
        // 直接看是否在文本矩形范围内就行
        int width = content.length() * font.getSize();
        int height = font.getSize();
        int nowX = x - baseX;
        int nowY = y - baseY + height;

        // x坐标在[0,宽度]范围内，y坐标在[0,宽度]范围内
        return ((nowX >= 0 && nowX <= width) && (nowY >= 0 && nowY <= height));
    }
}
