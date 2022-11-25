package custom.file;

import java.util.ArrayList;

/**
 * 包含所有界面的一个文件
 */
public class PicturePointFile {
    ArrayList<Page> pages = new ArrayList<>();

    /**
     * 返回当前总页数
     * @return 总页数
     */
    public int getSize(){
        return pages.size();
    }

    /**
     * 获取下标为index的单页
     *
     * @param index 下标
     * @return 对应的单页
     */
    public Page getPageAt(int index) {
        return pages.get(index);
    }

    /**
     * 获取第一页
     *
     * @return 第一页
     */
    public Page getPageFront() {
        return pages.get(0);
    }


    /**
     * 获取所有单页
     *
     * @return 单页单页
     */
    public ArrayList<Page> getPages() {
        return pages;
    }

    /**
     * 构建一个有一页空白页的文件
     */
    public PicturePointFile() {
        pages.add(new Page());
    }

    /**
     * 在指定位置后插入一页
     *
     * @param index   在下标为index的页后插入一页
     * @param newPage 待插入页
     */
    public void addPage(int index, Page newPage) {
        pages.add(index, newPage);
    }
    /**
     * 删除指定位置的页
     */
    public void deletePage(int index){
        pages.remove(index);
    }
    /**
     * 修改指定位置为指定的页
     *
     * @param index   指定位置
     * @param newPage 指定页
     */
    public void setPage(int index, Page newPage) {
        pages.set(index, newPage);
    }

    /**
     * 清空指定位置的页
     *
     * @param index 指定位置
     */
    public void clearPage(int index) {
        pages.set(index, new Page());
    }
}
