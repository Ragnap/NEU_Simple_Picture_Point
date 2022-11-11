import Image.BaseImage;
import Image.Line;

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
    ArrayList<BaseImage> Images = new ArrayList<>();

    /**
     * 正在绘制的图形预览
     */
    BaseImage previewImage = null;

    /**
     * 双缓冲消除闪烁
     */
    Image buffer = null;


    void addImage(BaseImage newImage) {
        Images.add(newImage);
        System.out.println(Images.size());
        deletePreview();
    }

    void addPreview(BaseImage previewImage) {
        this.previewImage = previewImage;
    }

    void deletePreview() {
        this.previewImage = null;
    }

    public void paint() {
        Graphics graphics = this.getGraphics();

        //缓冲区
        if (buffer != null) {
            graphics.drawImage(buffer,0,0,this);
        }

        buffer = createImage(this.getWidth(), this.getWidth());
        Graphics bufferGraphics = buffer.getGraphics();

        //当前界面
        for (int i = 0; i < Images.size(); i++) {
            Line now = (Line) Images.get(i);
            bufferGraphics.drawLine(now.getBaseX(), now.getBaseY(), now.getEndX(), now.getEndY());
        }
        if (previewImage != null) {

            if (previewImage.getImageKind() == 1) {
                Line now = (Line) previewImage;

                bufferGraphics.drawLine(now.getBaseX(), now.getBaseY(), now.getEndX(), now.getEndY());
            }
        }

        bufferGraphics.dispose();

    }
}
