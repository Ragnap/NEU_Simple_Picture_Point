package custom.picture;

import java.awt.*;

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
     * @param x       左上角点x坐标
     * @param y       左上角点Y坐标
     * @param content 文本
     * @param font    字体
     */
    public Text(int x, int y, String content, Font font) {
        this.pictureKind = 6;
        this.id = ++textCount;
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
     * 转换成保持到文件的格式
     * 6 RGB size baseX baseY content fontName fontStyle fontSize
     *
     * @return 表示该图形的一行字符串
     */
    public String toFileString() {
        return pictureKind + " " + color.getRGB() + " 0 " + baseX + " " + baseY + " " + name + " " + content + " " + font.getFontName() + " " + font.getStyle() + " " + font.getSize();
    }
}
