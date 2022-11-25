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
        this.setEnabled(false);
        // 调整分界线时重绘
        this.setContinuousLayout(false);
        // 默认分界线位置
        this.setDividerLocation(0.2);
        // 设置画笔
        PageEditPane.drawListener.setEditPane(pageEditPane);
        // 当前编辑的是默认的第一页
        pageEditPane.setNowPage(nowFile.getPageFront());

        pagePreviewPane.setPagePane(this);
        pagePreviewPane.updatePage(nowPageIndex, pageEditPane.getPageShot());
    }


    /**
     * 在当前页后插入一个新的页
     */
    public void insertNewPageAfter() {
        Page newPage = new Page();
        nowFile.addPage(nowPageIndex, newPage);
        nowPageIndex++;
        pageEditPane.setNowPage(nowFile.getPageAt(nowPageIndex));
        pageEditPane.refresh();

        pagePreviewPane.updatePage(nowPageIndex, pageEditPane.getPageShot());
    }

    /**
     * 重新绘制index的页
     */
    public void resetPage(int index) {
        nowFile.clearPage(index);
        pageEditPane.setNowPage(nowFile.getPageAt(index));
        pageEditPane.refresh();

        pagePreviewPane.updatePage(nowPageIndex, pageEditPane.getPageShot());

    }

    /**
     * 清空文件并打开一个只有一页的新文件
     */
    public void clear() {
        nowFile = new PicturePointFile();
        nowPageIndex = 0;
        pageEditPane.setNowPage(nowFile.getPageAt(nowPageIndex));
        pageEditPane.refresh();

        pagePreviewPane.clear();
    }

    /**
     * 切换到文件的某一页
     */
    public void changePage(int index) {
        System.out.println("切换到第" + index + "页");
        nowPageIndex = index;
        pageEditPane.setNowPage(nowFile.getPageAt(nowPageIndex));
        pageEditPane.refresh();
    }

    /**
     * 删除当前页,并移动到下一页;删除的是最后一页，则需要移动到上一页
     */
    public void deletePage() {
        //如果只有一页了，则将这页清空
        if(nowFile.getPages().size()==1){
            resetPage(0);
            return;
        }
        nowFile.deletePage(nowPageIndex);
        pagePreviewPane.deletePage(nowPageIndex);
        //删除的是最后一页，则需要移动到上一页
        if (nowPageIndex == nowFile.getPages().size())
            nowPageIndex--;
        pageEditPane.setNowPage(nowFile.getPageAt(nowPageIndex));
        pageEditPane.refresh();
    }

    /**
     * 移动当前页到上一页，与上一页交换
     */
    public void moveUpPage() {
        if (nowPageIndex == 0)
            return;
        Page oldPage = nowFile.getPageAt(nowPageIndex - 1);
        Page nowPage = nowFile.getPageAt(nowPageIndex);
        nowFile.setPage(nowPageIndex - 1, nowPage);
        nowFile.setPage(nowPageIndex, oldPage);
        nowPageIndex--;
    }

    /**
     * 移动当前页到下一页，与下一页交换
     */
    public void moveDownPage() {
        if (nowPageIndex + 1 == nowFile.getPages().size())
            return;
        Page oldPage = nowFile.getPageAt(nowPageIndex + 1);
        Page nowPage = nowFile.getPageAt(nowPageIndex);
        nowFile.setPage(nowPageIndex + 1, nowPage);
        nowFile.setPage(nowPageIndex, oldPage);
        nowPageIndex++;
    }

    /**
     * 切换到上一页
     */
    public void previousPage() {
        if (nowPageIndex == 0)
            return;
        nowPageIndex--;
        pageEditPane.setNowPage(nowFile.getPageAt(nowPageIndex));
        pageEditPane.refresh();
    }

    /**
     * 切换到下一页
     */
    public void nextPage() {
        if (nowPageIndex + 1 == nowFile.getPages().size())
            return;
        nowPageIndex++;
        pageEditPane.setNowPage(nowFile.getPageAt(nowPageIndex));
        pageEditPane.refresh();
    }

}
