package custom.listener;

import custom.panel.PageEditPane;
import custom.picture.Picture;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class SelectListener implements MouseListener, MouseMotionListener {

    private int x1 = -1, x2 = -1, y1 = -1, y2 = -1;

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
    ArrayList<Picture> coverPictures = null;
    /**
     * 当前选中图形下标
     */
    int nowIndex = 0;

    /**
     * 设置工具是否激活
     *
     * @param selectMode 选择工具状态
     */
    public void setSelectMode(boolean selectMode) {
        this.selectMode = selectMode;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!selectMode){
            pageEditPane.selectPicture(null);
            return;
        }
        int nowX = e.getX();
        int nowY = e.getY();
        //重复点击的时候选取包含该点的下一个图形
        if (x1 == nowX && y1 == nowY) {
            nowIndex++;
            //访问完最后一个的时候回到第一个
            if (nowIndex == coverPictures.size())
                nowIndex = 0;
        } else {
            x1 = nowX;
            y1 = nowY;
            coverPictures = pageEditPane.getPicturesAtPosition(x1, y1);
            nowIndex = 0;
            System.out.println("选取点(" + nowX + "," + nowY + "),共有" + coverPictures.size() + "个图形");

        }
        if (!coverPictures.isEmpty()) {
            System.out.println("选中" + coverPictures.get(nowIndex).getName());
            pageEditPane.selectPicture(coverPictures.get(nowIndex));
        }
        else{
            pageEditPane.selectPicture(null);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!selectMode){
            pageEditPane.selectPicture(null);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
