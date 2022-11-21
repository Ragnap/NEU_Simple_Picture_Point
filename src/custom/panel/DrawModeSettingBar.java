package custom.panel;

import custom.listener.DrawListener;
import custom.listener.SelectListener;

import javax.swing.*;
import java.awt.*;


public class DrawModeSettingBar extends BaseBar {
    /**
     * 控制的画笔
     */
    DrawListener drawListener;

    /**
     * 控制的选择工具
     */
    SelectListener selectListener;

    public void setPaintListener(DrawListener drawListener) {
        this.drawListener = drawListener;
    }

    public void setSelectListener(SelectListener selectListener) {
        this.selectListener = selectListener;
    }

    public DrawModeSettingBar(Dimension size) {
        baseSetting(size);
        GridBagLayout layout = (GridBagLayout) this.getLayout();

        // 按钮组
        ButtonGroup modes = new ButtonGroup();

        // 按钮大小
        Dimension buttonSize = new Dimension(70, 15);

        // 预览提示文字 8*1
        JLabel modeText = new JLabel("当前鼠标功能");
        this.add(modeText);
        modeText.setFont(new Font("宋体", Font.BOLD, 12));
        modeText.setPreferredSize(new Dimension(80, 12));
        layout.setConstraints(modeText, buildConstraints(1, 0, 1, 1, new Insets(2, 0, 2, 0)));

        // 选择按钮 默认选中 4*1
        JRadioButton chooseMode = new JRadioButton("选择");
        chooseMode.addActionListener(e -> drawListener.setDrawMode(0));
        chooseMode.addActionListener(e->selectListener.setSelectMode(true));
        modes.add(chooseMode);
        modes.setSelected(chooseMode.getModel(), true);
        this.add(chooseMode);
        chooseMode.setPreferredSize(buttonSize);
        layout.setConstraints(chooseMode, buildConstraints(0, 1, 1, 1, new Insets(2, 0, 2, 0)));

        // 橡皮擦按钮 4*1
        JRadioButton eraserMode = new JRadioButton("橡皮擦");
        eraserMode.addActionListener(e -> drawListener.setDrawMode(-1));
        eraserMode.addActionListener(e->selectListener.setSelectMode(false));
        modes.add(eraserMode);
        this.add(eraserMode);
        eraserMode.setPreferredSize(buttonSize);
        layout.setConstraints(eraserMode, buildConstraints(2, 1, 1, 1, new Insets(2, 0, 2, 0)));

        // 直线按钮 4*1
        JRadioButton lineMode = new JRadioButton("直线");
        lineMode.addActionListener(e -> drawListener.setDrawMode(1));
        lineMode.addActionListener(e->selectListener.setSelectMode(false));
        modes.add(lineMode);
        this.add(lineMode);
        lineMode.setPreferredSize(buttonSize);
        layout.setConstraints(lineMode, buildConstraints(0, 2, 1, 1, new Insets(2, 0, 2, 0)));

        // 矩形按钮 4*1
        JRadioButton rectangleMode = new JRadioButton("矩形");
        rectangleMode.addActionListener(e -> drawListener.setDrawMode(2));
        rectangleMode.addActionListener(e->selectListener.setSelectMode(false));
        modes.add(rectangleMode);
        this.add(rectangleMode);
        rectangleMode.setPreferredSize(buttonSize);
        layout.setConstraints(rectangleMode, buildConstraints(1, 2, 1, 1, new Insets(2, 0, 2, 0)));

        // 圆按钮 4*1
        JRadioButton circleMode = new JRadioButton("圆");
        circleMode.addActionListener(e -> drawListener.setDrawMode(3));
        circleMode.addActionListener(e->selectListener.setSelectMode(false));
        modes.add(circleMode);
        this.add(circleMode);
        circleMode.setPreferredSize(buttonSize);
        layout.setConstraints(circleMode, buildConstraints(2, 2, 1, 1, new Insets(2, 0, 2, 0)));

        // 椭圆按钮 4*1
        JRadioButton ellipseMode = new JRadioButton("椭圆");
        ellipseMode.addActionListener(e -> drawListener.setDrawMode(4));
        ellipseMode.addActionListener(e->selectListener.setSelectMode(false));
        modes.add(ellipseMode);
        this.add(ellipseMode);
        ellipseMode.setPreferredSize(buttonSize);
        layout.setConstraints(ellipseMode, buildConstraints(0, 3, 1, 1, new Insets(2, 0, 2, 0)));

        // 自由线按钮 4*1
        JRadioButton freeLineMode = new JRadioButton("任意线");
        freeLineMode.addActionListener(e -> drawListener.setDrawMode(5));
        freeLineMode.addActionListener(e->selectListener.setSelectMode(false));
        modes.add(freeLineMode);
        this.add(freeLineMode);
        freeLineMode.setPreferredSize(buttonSize);
        layout.setConstraints(freeLineMode, buildConstraints(1, 3, 1, 1, new Insets(2, 0, 2, 0)));

        // 文字按钮 4*1
        JRadioButton textMode = new JRadioButton("文本");
        textMode.addActionListener(e -> drawListener.setDrawMode(6));
        textMode.addActionListener(e->selectListener.setSelectMode(false));
        modes.add(textMode);
        this.add(textMode);
        textMode.setPreferredSize(buttonSize);
        layout.setConstraints(textMode, buildConstraints(2, 3, 1, 1, new Insets(2, 0, 2, 0)));
    }
}
