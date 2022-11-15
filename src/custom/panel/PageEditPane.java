package custom.panel;

import custom.picture.Picture;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * 单页面操作
 */
public class PageEditPane extends JPanel {
    /**
     * 该页面上的图形列表
     */
    private ArrayList<Picture> Images = new ArrayList<>();

    /**
     * 正在绘制的图形预览
     */
    private Picture previewImage = null;

    /**
     * 双缓冲消除闪烁
     */
    private Image buffer = null;


    public void addImage(Picture newImage) {
        Images.add(newImage);
        deletePreview();
    }

    public void addPreview(Picture previewImage) {
        this.previewImage = previewImage;
    }

    public void deletePreview() {
        this.previewImage = null;
    }

    public void update() {
        Graphics graphics = this.getGraphics();
        //缓冲区
        if (buffer != null) {
            graphics.drawImage(buffer, 0, 0, this);
        }

        buffer = createImage(this.getWidth(), this.getWidth());
        Graphics2D bufferGraphics = (Graphics2D)buffer.getGraphics();
        //当前界面
        for (Picture image : Images) {
            image.draw(bufferGraphics);
        }
        if (previewImage != null) {
            previewImage.draw(bufferGraphics);
        }

        bufferGraphics.dispose();
    }
}
