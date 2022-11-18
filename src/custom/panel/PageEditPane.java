package custom.panel;

import custom.file.Page;
import custom.listener.DrawListener;
import custom.picture.*;
import custom.picture.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * 单页面操作
 */
public class PageEditPane extends JPanel {
    // 画笔
    static public DrawListener drawListener = new DrawListener();

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
     * 双缓冲消除闪烁
     */
    private Image buffer = null;

    public PageEditPane() {
        drawListener.setEditPane(this);
        this.addMouseListener(drawListener);
        this.addMouseMotionListener(drawListener);
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

        String[] info = text.split(" ");
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
        // 其他参数
        switch (drawMode) {
            // 直线
            case 1 -> {
                int endX = Integer.parseInt(info[5]);
                int endY = Integer.parseInt(info[6]);
                newPicture = new Line(drawMode, RGB, lineWidth, baseX, baseY, endX, endY);

            }
            // 矩形
            case 2 -> {
                int width = Integer.parseInt(info[5]);
                int height = Integer.parseInt(info[6]);
                newPicture = new Rectangle(drawMode, RGB, lineWidth, baseX, baseY, width, height);
            }
            // 圆
            case 3 -> {
                int r = Integer.parseInt(info[5]);
                newPicture = new Circle(drawMode, RGB, lineWidth, baseX, baseY, r);
            }
            // 椭圆
            case 4 -> {
                int a = Integer.parseInt(info[5]);
                int b = Integer.parseInt(info[6]);
                newPicture = new Ellipse(drawMode, RGB, lineWidth, baseX, baseY, a, b);
            }
            // 自由线
            case 5 -> {
                int pointSize = Integer.parseInt(info[5]);
                ArrayList<Integer> pointsX = new ArrayList<Integer>();
                ArrayList<Integer> pointsY = new ArrayList<Integer>();

                for (int i = 0; i < pointSize; i++) {
                    pointsX.add(Integer.parseInt(info[6 + i]));
                    pointsY.add(Integer.parseInt(info[6 + pointSize + i]));
                }
                System.out.println(pointsX.size());
                newPicture = new FreeLine(drawMode, RGB, lineWidth, baseX, baseY, pointsX, pointsY);
            }
            // 文字
            case 6 -> {
                Font font = new Font(info[6],Integer.parseInt(info[7]),Integer.parseInt(info[8]));
                newPicture = new Text(drawMode,RGB,0,baseX,baseY,info[5],font);
            }
        }

        addPicture(newPicture);
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
        if (previewPicture != null) {
            previewPicture.draw(bufferGraphics);
        }
        bufferGraphics.dispose();
    }
    /**
     * 获取当前界面截图
     */
    public Image getPageShot(){
        return buffer;
    }
    /**
     * 刷新当前页面到最新，避免双缓冲带来的延迟问题
     */
    public void refresh(){
        update();
        update();
    }
}
