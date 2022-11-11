import Picture.Picture;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * 单页面
 */
public class Page extends JPanel {
    /**
     * 该页面上的图形列表
     */
    ArrayList<Picture> Images = new ArrayList<>();

    /**
     * 正在绘制的图形预览
     */
    Picture previewImage = null;

    /**
     * 双缓冲消除闪烁
     */
    Image buffer = null;


    void addImage(Picture newImage) {
        Images.add(newImage);
        deletePreview();
    }

    void addPreview(Picture previewImage) {
        this.previewImage = previewImage;
    }

    void deletePreview() {
        this.previewImage = null;
    }

    public void paint() {
        Graphics graphics = this.getGraphics();

        //缓冲区
        if (buffer != null) {
            graphics.drawImage(buffer, 0, 0, this);
        }

        buffer = createImage(this.getWidth(), this.getWidth());
        Graphics bufferGraphics = buffer.getGraphics();

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
