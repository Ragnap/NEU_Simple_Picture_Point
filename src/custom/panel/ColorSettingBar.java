package custom.panel;

import custom.listener.ColorSettingBarListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * 设置颜色所使用的上方工具栏
 */
public class ColorSettingBar extends JPanel {
    Integer r;
    Integer g;
    Integer b;
    /**
     * R颜色设置条
     */
    JScrollBar scrollBarR= new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256);
    /**
     * G颜色设置条
     */
    JScrollBar scrollBarG= new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256);
    /**
     * B颜色设置条
     */
    JScrollBar scrollBarB= new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256);

    /**
     * R值显示
     */
    JLabel textR= new JLabel();
    /**
     * G值显示
     */
    JLabel textG= new JLabel();
    /**
     * B值显示
     */
    JLabel textB= new JLabel();

    /**
     * 颜色预览
     */
    JLabel preview = new JLabel();

    /**
     * 监听
     */
    ColorSettingBarListener colorSettingBarListener =new ColorSettingBarListener();


    public int getR() {
        return r = scrollBarR.getValue();
    }

    public int getG() {
        return g = scrollBarG.getValue();
    }

    public int getB() {
        return b = scrollBarB.getValue();
    }

    public ColorSettingBar() {
        // 独占一整行
        this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 4, 30));
        this.setBounds(0,0,Toolkit.getDefaultToolkit().getScreenSize().width / 4,30);
        this.setBackground(Color.white);
        // 加个边框
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

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

        preview.setPreferredSize(new Dimension(15,15));
        preview.setOpaque(true);

        this.add(preview);
        this.add(scrollBarR);
        this.add(textR);
        this.add(scrollBarG);
        this.add(textG);
        this.add(scrollBarB);
        this.add(textB);

        updateView();
    }

    public void updateView() {
        r = scrollBarR.getValue();
        g = scrollBarG.getValue();
        b = scrollBarB.getValue();
        textR.setText("R:" + r);
        textG.setText("G:" + g);
        textB.setText("B:" + b);
        preview.setBackground(new Color(r,g,b));

    }
}
