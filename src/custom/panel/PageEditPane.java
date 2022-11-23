package custom.panel;

import custom.file.Page;
import custom.listener.DrawListener;
import custom.listener.SelectListener;
import custom.picture.*;
import custom.picture.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;


/**
 * 单页面操作
 */
public class PageEditPane extends JPanel {
    /**
     * 画笔工具
     */
    static public DrawListener drawListener = new DrawListener();
    /**
     * 选择工具
     */
    static public SelectListener selectListener = new SelectListener();
    /**
     * 属性设置工具
     */
    static public AttributeSettingBar attributeSettingBar = null;

    public static void setAttributeSettingBar(AttributeSettingBar attributeSettingBar) {
        PageEditPane.attributeSettingBar = attributeSettingBar;
    }

    /**
     * 正在绘制的页面
     */
    private Page nowPage = null;

    public void setNowPage(Page nowPage) {
        this.nowPage = nowPage;
    }

    /**
     * 正在绘制的图形预览
     */
    private Picture previewPicture = null;
    /**
     * 当前选中的图形
     */
    private Picture selectedPicture = null;
    /**
     * 双缓冲消除闪烁
     */
    private Image buffer = null;

    public PageEditPane() {
        // 默认是选择模式
        drawListener.setDrawMode(0);
        drawListener.setEditPane(this);
        this.addMouseListener(drawListener);
        this.addMouseMotionListener(drawListener);

        selectListener.setSelectMode(true);
        selectListener.setEditPane(this);
        this.addMouseListener(selectListener);
        this.addMouseMotionListener(selectListener);
    }

    /**
     * 加入一个新图形并删除的预览
     *
     * @param newPicture 新图形
     */
    public void addPicture(Picture newPicture) {
        nowPage.addPicture(newPicture);
        deletePreview();
        refresh();
    }

    /**
     * 根据文本内容创建一个图形，用于从文件载入
     *
     * @param text 一行表示图形的字符串
     */
    public void addPicture(String text) {
        Picture newPicture = null;

        String[] info = text.split("_");
        // 第一个参数为图形类型
        int drawMode = Integer.parseInt(info[0]);
        // 第二个参数为图形颜色
        int RGB = Integer.parseInt(info[1]);
        // 第三个参数为图形粗细
        float lineWidth = Float.parseFloat(info[2]);
        // 第四个参数为左上角x坐标
        int baseX = Integer.parseInt(info[3]);
        // 第五个参数为左上角x坐标
        int baseY = Integer.parseInt(info[4]);
        // 第六个参数为图形名称
        String name = toOriginString(info[5]);
        System.out.println(name);
        // 其他参数
        switch (drawMode) {
            // 直线
            case 1 -> {
                int endX = Integer.parseInt(info[6]);
                int endY = Integer.parseInt(info[7]);
                newPicture = new Line(drawMode, RGB, lineWidth, baseX, baseY, name, endX, endY);

            }
            // 矩形
            case 2 -> {
                int width = Integer.parseInt(info[6]);
                int height = Integer.parseInt(info[7]);
                newPicture = new Rectangle(drawMode, RGB, lineWidth, baseX, baseY, name, width, height);
            }
            // 圆
            case 3 -> {
                int r = Integer.parseInt(info[6]);
                newPicture = new Circle(drawMode, RGB, lineWidth, baseX, baseY, name, r);
            }
            // 椭圆
            case 4 -> {
                int a = Integer.parseInt(info[6]);
                int b = Integer.parseInt(info[7]);
                newPicture = new Ellipse(drawMode, RGB, lineWidth, baseX, baseY, name, a, b);
            }
            // 自由线
            case 5 -> {
                ArrayList<Integer> pointsX = toOriginArrayList(info[6]);
                ArrayList<Integer> pointsY = toOriginArrayList(info[7]);

                newPicture = new FreeLine(drawMode, RGB, lineWidth, baseX, baseY, name, pointsX, pointsY);
            }
            // 文字
            case 6 -> {
                Font font = new Font(toOriginString(info[7]), Integer.parseInt(info[8]), Integer.parseInt(info[9]));
                newPicture = new Text(drawMode, RGB, 0, baseX, baseY, name, toOriginString(info[6]), font);
            }
        }

        addPicture(newPicture);
    }

    /**
     * 移动选定的的图形
     *
     * @param x 新的X坐标
     * @param y 新的Y坐标
     */
    public void moveSelectPicture(int x, int y) {
        if (selectedPicture == null)
            return;
        selectedPicture.move(x, y);
        attributeSettingBar.updateSetting();
        refresh();
    }

    /**
     * 选中一个图片
     *
     * @param picture 带选中图片
     */
    public void selectPicture(Picture picture) {
        selectedPicture = picture;
        attributeSettingBar.setNowPicture(picture);
        refresh();
    }
    /**
     * 以图形类别查找
     *
     * @param pictureKind 设定的图形类别,为0时不指定种类
     * @return 所有符合的图形
     */
    public ArrayList<Picture> findPicture(int pictureKind) {
        if (pictureKind == 0) {
            return nowPage.getPictures();
        }
        ArrayList<Picture> results = new ArrayList<>();
        for (Picture nowPicture : nowPage.getPictures()) {
            if (nowPicture.getPictureKind() == pictureKind)
                results.add(nowPicture);
        }
        return results;
    }

    /**
     * 以图形名称模糊查找和种类复合查找
     *
     * @param pictureKind 设定的图形类别
     * @param name        设定的图形类别
     * @return 所有符合的图形
     */
    public ArrayList<Picture> findPicture(int pictureKind, String name) {
        if (name == null || name.isEmpty()) {
            return findPicture(pictureKind);
        }
        ArrayList<Picture> results = new ArrayList<>();
        for (Picture nowPicture : findPicture(pictureKind)) {
            if (nowPicture.getName().contains(name))
                results.add(nowPicture);
        }
        return results;
    }


    /**
     * 获取覆盖某个点的所有图形列表
     *
     * @param x 点的x坐标
     * @param y 点的y坐标
     * @return 所有满足的图形
     */
    public ArrayList<Picture> getPicturesAtPosition(int x, int y) {
        return nowPage.getPicturesAtPosition(x, y);
    }

    /**
     * 增加一个不会直接添加到文件中的临时预览图形
     *
     * @param previewPicture 临时预览图形
     */
    public void addPreview(Picture previewPicture) {
        this.previewPicture = previewPicture;
    }

    /**
     * 删除临时预览图形
     */
    public void deletePreview() {
        this.previewPicture = null;
    }

    /**
     * 更新当前界面
     */
    public void update() {
        Graphics graphics = this.getGraphics();
        //缓冲区
        if (buffer != null) {
            graphics.drawImage(buffer, 0, 0, this);
        }

        buffer = createImage(this.getWidth(), this.getWidth());
        Graphics2D bufferGraphics = (Graphics2D) buffer.getGraphics();
        //当前界面
        for (Picture picture : nowPage.getPictures()) {
            picture.draw(bufferGraphics);
        }
        if (selectedPicture != null) {
            selectedPicture.drawBorder(bufferGraphics);
        }
        if (previewPicture != null) {
            previewPicture.draw(bufferGraphics);
        }
        bufferGraphics.dispose();
    }

    /**
     * 获取当前界面截图
     */
    public Image getPageShot() {
        return buffer;
    }

    /**
     * 刷新当前页面到最新，避免双缓冲带来的延迟问题
     */
    public void refresh() {
        update();
        update();
    }


    /**
     * 将[xx, xx]形式的byte字符串返回成原来的字符串
     *
     * @param byteString [xx, xx]形式的byte字符串
     * @return 原来的字符串
     */
    private String toOriginString(String byteString) {
        //去掉头尾[]
        byteString = byteString.substring(1, byteString.length() - 1);
        //拆分为字符串数组
        String[] splitString = byteString.split(", ");
        //字符串数组转化为整型数组
        byte[] bytes = new byte[splitString.length];
        for (int i = 0; i < splitString.length; i++)
            bytes[i] = Byte.parseByte(splitString[i]);
        //由整型数组构建string
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * 将[xx, xx]形式的字符串返回成原来的ArrayList
     *
     * @param ArrayListString [xx, xx]形式的int字符串
     * @return ArrayList<int>的值
     */
    private ArrayList<Integer> toOriginArrayList(String ArrayListString) {
        //去掉头尾[]
        ArrayListString = ArrayListString.substring(1, ArrayListString.length() - 1);
        //拆分为字符串数组
        String[] splitString = ArrayListString.split(", ");

        ArrayList<Integer> origin = new ArrayList<Integer>();
        //字符串数组转化为整型数组
        for (String nowString : splitString)
            origin.add(Integer.valueOf(nowString));

        return origin;
    }
}
