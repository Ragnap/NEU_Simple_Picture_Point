package custom.picture;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.*;

public class FreeLine extends Picture {
    /**
     * 所有自由线的记数
     */
    static int freeLineCount = 0;
    /**
     * 路径上每个点的X坐标
     */
    ArrayList<Integer> pointsX;
    /**
     * 路径上每个点的Y坐标
     */
    ArrayList<Integer> pointsY;

    /**
     * 给出路径点集坐标
     *
     * @param x         路径上的点x
     * @param y         路径上的点y
     * @param isPreview 是否是临时预览
     */
    public FreeLine(ArrayList<Integer> x, ArrayList<Integer> y, boolean isPreview) {
        if (!isPreview)
            freeLineCount++;

        this.pictureKind = 5;
        this.id = freeLineCount;
        this.name = "自由线" + id;

        this.pointsX = x;
        this.pointsY = y;
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
     * @param pointsX     点的x坐标
     * @param pointsY     点的y坐标
     */
    public FreeLine(int pictureKind, int RGB, float lineWidth, int baseX, int baseY, String name, ArrayList<Integer> pointsX, ArrayList<Integer> pointsY) {
        this.pictureKind = pictureKind;
        this.color = new Color(RGB);
        this.stroke = new BasicStroke(lineWidth);
        this.baseX = baseX;
        this.baseY = baseY;
        this.id = ++freeLineCount;
        this.name = name;
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
     * 绘制一个可以覆盖图形的矩形虚线框，多态重载
     *
     * @param graphics 当前画笔
     */
    public void drawBorder(Graphics2D graphics) {
        //设置特色属性
        Color originColor = graphics.getColor();
        BasicStroke originStroke = (BasicStroke) graphics.getStroke();
        graphics.setColor(new Color(91, 209, 215, 60));
        //找横坐标和纵坐标的最大最小值
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        for (int nowX : pointsX) {
            minX = min(minX, nowX);
            maxX = max(maxX, nowX);
        }
        for (int nowY : pointsY) {
            minY = min(minY, nowY);
            maxY = max(maxY, nowY);
        }
        //拓展线宽
        minX -= stroke.getLineWidth() / 2;
        maxX += stroke.getLineWidth() / 2;
        minY -= stroke.getLineWidth() / 2;
        maxY += stroke.getLineWidth() / 2;

        graphics.fillRect(minX, minY, maxX - minX, maxY - minY);
        graphics.setColor(new Color(32, 73, 105, 90));
        graphics.setStroke(new BasicStroke(5));
        graphics.drawRect(minX, minY, maxX - minX, maxY - minY);
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
        String pointX = pointsX.toString();
        String pointY = pointsY.toString();
        return super.toFileString() + "_" + "_" + pointX + "_" + pointY;
    }

    /**
     * 判断图形是否包含某个点，多态重载
     *
     * @param x 点x坐标
     * @param y 点y坐标
     * @return 该图形是否包含该点
     */
    public boolean isCoverPoint(int x, int y) {
        //转换问题为判断点是否在组成自由线的某一段直线上
        for (int i = 1; i < pointsX.size(); i++) {
            int x1 = pointsX.get(i - 1);
            int y1 = pointsY.get(i - 1);
            int x2 = pointsX.get(i);
            int y2 = pointsY.get(i);

            //利用点到线段距离是否小于线段宽度判断，使用向量法求解距离
            double distance = getPointSegmentDistance(x1, y1, x2, y2, x, y);
            if (distance <= stroke.getLineWidth() / 2)
                return true;
        }
        return false;
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




