package custom.panel;

import custom.listener.ColorSettingBarListener;
import custom.listener.PaintListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * 设置颜色所使用的上方工具栏
 */
public class ColorSettingBar extends JPanel {

    Color color = new Color(0, 0, 0);

    /**
     * 控制的画笔
     */
    PaintListener paintListener;

    public void setPaintListener(PaintListener paintListener) {
        this.paintListener = paintListener;
    }

    /**
     * R颜色设置条
     */
    JScrollBar scrollBarR = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256);
    /**
     * G颜色设置条
     */
    JScrollBar scrollBarG = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256);
    /**
     * B颜色设置条
     */
    JScrollBar scrollBarB = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256);

    /**
     * R值显示
     */
    JLabel textR = new JLabel();
    /**
     * G值显示
     */
    JLabel textG = new JLabel();
    /**
     * B值显示
     */
    JLabel textB = new JLabel();

    /**
     * 颜色预览
     */
    JLabel preview = new JLabel();

    /**
     * 监听
     */
    ColorSettingBarListener colorSettingBarListener = new ColorSettingBarListener();

    public ColorSettingBar() {
        // 独占一整行
//        this.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/2 , 30));
//        this.setBackground(Color.white);
//        this.setOpaque(true);
        // 加个边框
//        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        colorSettingBarListener.setColorSettingBar(this);

        scrollBarR.setUnitIncrement(1);
        scrollBarR.setPreferredSize(new Dimension(100, 15));
        scrollBarR.addAdjustmentListener(colorSettingBarListener);
        textR.setPreferredSize(new Dimension(50, 15));

        scrollBarG.setUnitIncrement(1);
        scrollBarG.setPreferredSize(new Dimension(100, 15));
        scrollBarG.addAdjustmentListener(colorSettingBarListener);
        textG.setPreferredSize(new Dimension(50, 15));

        scrollBarB.setUnitIncrement(1);
        scrollBarB.setPreferredSize(new Dimension(100, 15));
        scrollBarB.addAdjustmentListener(colorSettingBarListener);
        textB.setPreferredSize(new Dimension(50, 15));

        preview.setPreferredSize(new Dimension(15, 15));
        preview.setOpaque(true);

        this.add(preview);
        this.add(scrollBarR);
        this.add(textR);
        this.add(scrollBarG);
        this.add(textG);
        this.add(scrollBarB);
        this.add(textB);
        updatePreview();
    }

    public void updatePreview() {
        int r = scrollBarR.getValue();
        int g = scrollBarG.getValue();
        int b = scrollBarB.getValue();
        textR.setText("R:" + r);
        textG.setText("G:" + g);
        textB.setText("B:" + b);
        color = new Color(r, g, b);
        preview.setBackground(color);
        if (paintListener != null)
            paintListener.setColor(color);

    }
}
