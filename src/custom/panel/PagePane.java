package custom.panel;

import custom.listener.PaintListener;
import custom.picture.Picture;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * 单页面操作
 */
public class PagePane extends JPanel {
    // 画笔
    static public PaintListener paintListener = new PaintListener();

    /**
     * 该页面上的图形列表
     */
    private ArrayList<Picture> pictures = new ArrayList<>();

    public ArrayList<Picture> getPictures() {
        return pictures;
    }

    /**
     * 正在绘制的图形预览
     */
    private Picture previewPicture = null;

    /**
     * 双缓冲消除闪烁
     */
    private Image buffer = null;

    public PagePane() {
        paintListener.setPage(this);
        this.addMouseListener(paintListener);
        this.addMouseMotionListener(paintListener);

    }

    public void addImage(Picture newImage) {
        pictures.add(newImage);
        deletePreview();
    }

    public void addPreview(Picture previewImage) {
        this.previewPicture = previewImage;
    }

    public void deletePreview() {
        this.previewPicture = null;
    }

    public void update() {
        Graphics graphics = this.getGraphics();
        //缓冲区
        if (buffer != null) {
            graphics.drawImage(buffer, 0, 0, this);
        }

        buffer = createImage(this.getWidth(), this.getWidth());
        Graphics2D bufferGraphics = (Graphics2D) buffer.getGraphics();
        //当前界面
        for (Picture image : pictures) {
            image.draw(bufferGraphics);
        }
        if (previewPicture != null) {
            previewPicture.draw(bufferGraphics);
        }
        bufferGraphics.dispose();
    }
}
