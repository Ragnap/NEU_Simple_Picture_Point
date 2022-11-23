package custom.panel;

import custom.file.Page;
import custom.file.PicturePointFile;

import javax.swing.*;

public class PagePane extends JSplitPane {
    /**
     * 左所有页面预览列表
     */
    public PagePreviewPane pagePreviewPane = new PagePreviewPane();
    /**
     * 右当前编辑页面
     */
    public PageEditPane pageEditPane = new PageEditPane();
    /**
     * 当前打开的文件
     */
    PicturePointFile nowFile = new PicturePointFile();
    /**
     * 当前页数
     */
    int nowPageIndex = 0;

    public PicturePointFile getNowFile() {
        return nowFile;
    }

    public PagePane(int width, int height) {
        this.setSize(width, height);
        // 设置竖直分隔线
        this.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        // 左部分为所有页面的预览
        this.setLeftComponent(pagePreviewPane);
        // 右部分为单个界面的编辑
        this.setRightComponent(pageEditPane);
        // 幻灯片部分分界线可调
        this.setEnabled(true);
        // 调整分界线时重绘
        this.setContinuousLayout(true);
        // 默认分界线位置
        this.setDividerLocation(0.1);
        // 设置画笔
        PageEditPane.drawListener.setEditPane(pageEditPane);
        // 当前编辑的是默认的第一页
        pageEditPane.setNowPage(nowFile.getPageFront());
    }

    /**
     * 在当前页后插入一个新的页
     */
    public void insertNewPageAfter(){
        Page newPage = new Page();
        nowFile.addPage(nowPageIndex,newPage);
        nowPageIndex++;
        pageEditPane.setNowPage(nowFile.getPageAt(nowPageIndex));
        pageEditPane.refresh();

    }
    /**
     * 重新绘制index的页
     */
    public void resetPage(int index){
        nowFile.clearPage(index);
        pageEditPane.setNowPage(nowFile.getPageAt(index));
        pageEditPane.refresh();
    }
    /**
     * 清空文件并打开一个只有一页的新文件
     */
    public void clear(){
        nowFile = new PicturePointFile();
        nowPageIndex = 0;
        pageEditPane.setNowPage(nowFile.getPageAt(nowPageIndex));
        pageEditPane.refresh();
    }
    /**
     * 返回当前总页数
     *
     * @return 当前总页数
     */
    public int getPageNum(){
        return nowFile.getPages().size();
    }
}
