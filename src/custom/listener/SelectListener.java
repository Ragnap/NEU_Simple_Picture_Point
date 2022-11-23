package custom.listener;

import custom.panel.PageEditPane;
import custom.picture.Picture;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class SelectListener implements MouseListener, MouseMotionListener {

    private int x1 = -1, x2 = -1, y1 = -1, y2 = -1;
    private int originX, originY;
    /**
     * 当前绘制页
     */
    private PageEditPane pageEditPane;

    public void setEditPane(PageEditPane pageEditPane) {
        this.pageEditPane = pageEditPane;
    }

    /**
     * 是否是选中模式
     */
    boolean selectMode = true;
    /**
     * 当前选中图形列表
     */
    ArrayList<Picture> coverPictures;
    /**
     * 当前选中图形下标
     */
    int nowIndex = 0;
    /**
     * 拖动移动标记
     */
    boolean moveFlag = false;

    /**
     * 设置工具是否激活
     *
     * @param selectMode 选择工具状态
     */
    public void setSelectMode(boolean selectMode) {
        this.selectMode = selectMode;
    }

    /**
     * 根据当前下标选定图片
     */
    private void setSelectPicture() {
        if (coverPictures == null || coverPictures.isEmpty()) {
            pageEditPane.selectPicture(null);
        } else {
            System.out.println("选中" + coverPictures.get(nowIndex).getName());

            originX = coverPictures.get(nowIndex).getBaseX();
            originY = coverPictures.get(nowIndex).getBaseY();

            pageEditPane.selectPicture(coverPictures.get(nowIndex));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!selectMode) {
            pageEditPane.selectPicture(null);
            return;
        }
        if (x1 == e.getX() && y1 == e.getY()) {
            //重复点击的时候选取包含该点的下一个图形
            nowIndex++;
            //访问完最后一个的时候回到第一个
            if (nowIndex >= coverPictures.size())
                nowIndex = 0;
        } else {
            x1 = e.getX();
            y1 = e.getY();
            coverPictures = pageEditPane.getPicturesAtPosition(x1, y1);
            nowIndex = 0;
            System.out.println("选取点(" + x1 + "," + y2 + "),共有" + coverPictures.size() + "个图形");
        }
        setSelectPicture();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!selectMode) {
            pageEditPane.selectPicture(null);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (moveFlag) {
            moveFlag = false;
            System.out.println("移动到(" + e.getX() + "," + e.getY() + ")");
            //为了多次移动同一图形需要更新图形初始值
            originX = coverPictures.get(nowIndex).getBaseX();
            originY = coverPictures.get(nowIndex).getBaseY();

            x1 = -1;
            y1 = -1;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!selectMode)
            return;

        x2 = e.getX();
        y2 = e.getY();
        if (!moveFlag) {
            moveFlag = true;
            x1 = x2;
            y1 = y2;
        }
        pageEditPane.moveSelectPicture(originX + x2 - x1, originY + y2 - y1);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
