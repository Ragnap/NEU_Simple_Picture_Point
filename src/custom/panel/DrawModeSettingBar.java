package custom.panel;

import custom.listener.PaintListener;

import javax.swing.*;
import java.awt.*;


public class DrawModeSettingBar extends JPanel {
    /**
     * 控制的画笔
     */
    PaintListener paintListener;

    public void setPaintListener(PaintListener paintListener) {
        this.paintListener = paintListener;
    }

    /**
     * 构建一个Constrains
     *
     * @param x      组件最左上占用的格子的x坐标
     * @param y      组件最左上占用的格子的y坐标
     * @param width  组件占用的水平格子数
     * @param height 组件占用的竖直格子数
     * @return 对应的约束
     */
    private GridBagConstraints buildConstraints(int x, int y, int width, int height, Insets insets) {
        GridBagConstraints constraints = new GridBagConstraints();
        // 组件所在的区域比组件本身要大时进行缩放
        constraints.fill = GridBagConstraints.NONE;
        // 组件所在的区域比组件本身要小时居中
        constraints.anchor = GridBagConstraints.CENTER;
        // 默认组件间隔
        constraints.insets = insets;
        // 默认缩放改动
        constraints.weightx = 1;
        constraints.weighty = 1;

        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = height;

        return constraints;
    }

    public DrawModeSettingBar(Dimension size) {
        // 设置大小
        this.setBounds(0, 0, size.width / 4, size.height);
        // 边框
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
//        this.setBackground(Color.LIGHT_GRAY);
        // 布局
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        // 预览提示文字 8*1
        JLabel modeText = new JLabel("当前鼠标功能");
        this.add(modeText);
        modeText.setFont(new Font("宋体", Font.BOLD, 12));
        modeText.setPreferredSize(new Dimension(80, 12));
        layout.setConstraints(modeText, buildConstraints(1, 0, 1, 1, new Insets(2, 0, 2, 0)));
        // 按钮组
        ButtonGroup modes = new ButtonGroup();
        // 按钮大小
        Dimension buttonSize = new Dimension(70, 15);

        // 选择按钮 4*1
        JRadioButton chooseMode = new JRadioButton("选择");
        chooseMode.addActionListener(e -> paintListener.setDrawMode(0));
        modes.add(chooseMode);
        this.add(chooseMode);
        chooseMode.setPreferredSize(buttonSize);
        layout.setConstraints(chooseMode, buildConstraints(0, 1, 1, 1, new Insets(2, 0, 2, 0)));

        // 选择按钮 4*1
        JRadioButton eraserMode = new JRadioButton("橡皮擦");
        eraserMode.addActionListener(e -> paintListener.setDrawMode(-1));
        modes.add(eraserMode);
        this.add(eraserMode);
        eraserMode.setPreferredSize(buttonSize);
        layout.setConstraints(eraserMode, buildConstraints(2, 1, 1, 1, new Insets(2, 0, 2, 0)));

        // 直线按钮 4*1
        JRadioButton lineMode = new JRadioButton("直线");
        lineMode.addActionListener(e -> paintListener.setDrawMode(1));
        modes.add(lineMode);
        this.add(lineMode);
        lineMode.setPreferredSize(buttonSize);
        layout.setConstraints(lineMode, buildConstraints(0, 2, 1, 1, new Insets(2, 0, 2, 0)));

        // 矩形按钮 4*1
        JRadioButton rectangleMode = new JRadioButton("矩形");
        rectangleMode.addActionListener(e -> paintListener.setDrawMode(2));
        modes.add(rectangleMode);
        this.add(rectangleMode);
        rectangleMode.setPreferredSize(buttonSize);
        layout.setConstraints(rectangleMode, buildConstraints(1, 2, 1, 1, new Insets(2, 0, 2, 0)));

        // 圆按钮 4*1
        JRadioButton circleMode = new JRadioButton("圆");
        circleMode.addActionListener(e -> paintListener.setDrawMode(3));
        modes.add(circleMode);
        this.add(circleMode);
        circleMode.setPreferredSize(buttonSize);
        layout.setConstraints(circleMode, buildConstraints(2, 2, 1, 1, new Insets(2, 0, 2, 0)));

        // 椭圆按钮 4*1
        JRadioButton ellipseMode = new JRadioButton("椭圆");
        ellipseMode.addActionListener(e -> paintListener.setDrawMode(4));
        modes.add(ellipseMode);
        this.add(ellipseMode);
        ellipseMode.setPreferredSize(buttonSize);
        layout.setConstraints(ellipseMode, buildConstraints(0, 3, 1, 1, new Insets(2, 0, 2, 0)));

        // 自由线按钮 4*1
        JRadioButton freeLineMode = new JRadioButton("任意线");
        freeLineMode.addActionListener(e -> paintListener.setDrawMode(5));
        modes.add(freeLineMode);
        this.add(freeLineMode);
        freeLineMode.setPreferredSize(buttonSize);
        layout.setConstraints(freeLineMode, buildConstraints(1, 3, 1, 1, new Insets(2, 0, 2, 0)));

        // 文字按钮 4*1
        JRadioButton textMode = new JRadioButton("文本");
        textMode.addActionListener(e -> paintListener.setDrawMode(6));
        modes.add(textMode);
        this.add(textMode);
        textMode.setPreferredSize(buttonSize);
        layout.setConstraints(textMode, buildConstraints(2, 3, 1, 1, new Insets(2, 0, 2, 0)));

    }
}
