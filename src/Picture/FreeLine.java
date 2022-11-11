package Picture;

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
        pointsX = x;
        pointsY = y;
    }

    public void draw(Graphics graphics) {
        for (int i = 1; i < pointsX.size(); i++) {
            graphics.drawLine(pointsX.get(i - 1), pointsY.get(i - 1), pointsX.get(i), pointsY.get(i));
        }
    }

}
