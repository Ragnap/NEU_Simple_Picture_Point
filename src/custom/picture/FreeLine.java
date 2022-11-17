package custom.picture;

import java.awt.*;
import java.util.ArrayList;

public class FreeLine extends Picture {
    /**
     * 路径上每个点的X坐标
     */
    ArrayList<Integer> pointsX;
    /**
     * 路径上每个点的Y坐标
     */
    ArrayList<Integer> pointsY;

    /**
     * 不给出初始点的初始化
     */
    public FreeLine() {
        pointsX = new ArrayList<>();
        pointsY = new ArrayList<>();
    }

    /**
     * 给出路径点集坐标
     *
     * @param x 路径上的点x
     * @param y 路径上的点y
     */
    public FreeLine(ArrayList<Integer> x, ArrayList<Integer> y) {
        pictureKind =5;
        pointsX = x;
        pointsY = y;
    }

    /**
     * 提供参数构建
     *
     * @param pictureKind 图形种类
     * @param RGB         RGB值
     * @param lineWidth   图形粗细，单位像素
     * @param baseX       基点x坐标
     * @param baseY       基点y坐标
     * @param pointsX     点的x坐标
     * @param pointsY     点的y坐标
     */
    public FreeLine(int pictureKind, int RGB, float lineWidth, int baseX, int baseY, ArrayList<Integer> pointsX, ArrayList<Integer> pointsY) {
        this.pictureKind = pictureKind;
        this.color = new Color(RGB);
        this.stroke = new BasicStroke(lineWidth);
        this.baseX = baseX;
        this.baseY = baseY;
        this.pointsX = pointsX;
        this.pointsY = pointsY;
    }

    public void draw(Graphics2D graphics) {
        //设置特色属性
        Color originColor = graphics.getColor();
        BasicStroke originStroke = (BasicStroke) graphics.getStroke();
        graphics.setColor(color);
        graphics.setStroke(stroke);

        for (int i = 1; i < pointsX.size(); i++) {
            graphics.drawLine(pointsX.get(i - 1), pointsY.get(i - 1), pointsX.get(i), pointsY.get(i));
        }
        //还原画笔
        graphics.setColor(originColor);
        graphics.setStroke(originStroke);
    }

    /**
     * 转换成保持到文件的格式
     * 5 RGB size baseX baseY pointSize [pointsX] [pointsY]
     *
     * @return 表示该图形的一行字符串
     */
    public String toFileString() {
        String pointSting = String.valueOf(pointsX.size());
        for (Integer x : pointsX) {
            pointSting += " " + x;
        }
        for (Integer y : pointsY) {
            pointSting += " " + y;
        }
        return pictureKind + " " + color.getRGB() + " " + stroke.getLineWidth() + " " + baseX + " " + baseY + " " + pointSting;
    }
}
