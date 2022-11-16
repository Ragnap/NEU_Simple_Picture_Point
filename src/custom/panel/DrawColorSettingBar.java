package custom.panel;

import custom.listener.PaintListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/**
 * 设置颜色所使用的上方工具栏
 */
public class DrawColorSettingBar extends JPanel {

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
     * 直接使用已有颜色时的标记，避免修改RGB条时触发事件
     */
    boolean useCommonColor = false;

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

    public DrawColorSettingBar(Dimension size) {
        // 设置大小
        this.setBounds(0, 0, size.width / 4, size.height);
        // 边框
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
//        this.setBackground(Color.LIGHT_GRAY);
        // 布局
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        // 常用颜色按键监听,匿名内部类
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("选择常用颜色：" + e.getActionCommand());
                switch (e.getActionCommand()) {
                    case "black" -> color = Color.BLACK;
                    case "white" -> color = Color.WHITE;
                    case "red" -> color = Color.RED;
                    case "yellow" -> color = Color.YELLOW;
                    case "blue" -> color = Color.BLUE;
                    case "green" -> color = Color.GREEN;
                }
                useCommonColor = true;
                updateColor();
            }
        };

        // 预览提示文字 5*1
        JLabel previewText = new JLabel("当前颜色");
        this.add(previewText);
        previewText.setFont(new Font("宋体", Font.BOLD, 12));
        previewText.setPreferredSize(new Dimension(50, 12));
        layout.setConstraints(previewText, buildConstraints(0, 0, 4, 1, new Insets(2, 10, 2, 10)));

        // 颜色预览 5*4
        this.add(preview);
        preview.setOpaque(true);
        preview.setBorder(BorderFactory.createLineBorder(Color.black));
        preview.setPreferredSize(new Dimension(50, 50));
        layout.setConstraints(preview, buildConstraints(0, 1, 4, 3, new Insets(5, 10, 2, 10)));

        // 常用颜色文字提示 5*1
        JLabel commonColorText = new JLabel("常用颜色");
        this.add(commonColorText);
        commonColorText.setFont(new Font("宋体", Font.BOLD, 12));
        commonColorText.setPreferredSize(new Dimension(50, 12));
        layout.setConstraints(commonColorText, buildConstraints(4, 0, 4, 1, new Insets(2, 0, 2, 10)));

        // 黑色按钮 1*1
        JButton blackButton = new JButton("black");
        this.add(blackButton);
        blackButton.setPreferredSize(new Dimension(15, 15));
        blackButton.setFont(new Font("宋体", Font.PLAIN, 0));
        blackButton.setOpaque(true);
        blackButton.setBackground(Color.BLACK);
        blackButton.addActionListener(actionListener);
        blackButton.setPressedIcon(null);
        layout.setConstraints(blackButton, buildConstraints(5, 1, 1, 1, new Insets(0, 0, 0, 0)));
        // 白色按钮 1*1
        JButton whiteButton = new JButton("white");
        this.add(whiteButton);
        whiteButton.setPreferredSize(new Dimension(15, 15));
        whiteButton.setFont(new Font("宋体", Font.PLAIN, 0));
        whiteButton.setOpaque(true);
        whiteButton.setBackground(Color.WHITE);
        whiteButton.addActionListener(actionListener);
        whiteButton.setPressedIcon(null);
        layout.setConstraints(whiteButton, buildConstraints(6, 1, 1, 1, new Insets(0, 0, 0, 10)));
        // 红色按钮 1*1
        JButton redButton = new JButton("red");
        this.add(redButton);
        redButton.setPreferredSize(new Dimension(15, 15));
        redButton.setFont(new Font("宋体", Font.PLAIN, 0));
        redButton.setOpaque(true);
        redButton.setBackground(Color.RED);
        redButton.addActionListener(actionListener);
        redButton.setPressedIcon(null);
        layout.setConstraints(redButton, buildConstraints(5, 2, 1, 1, new Insets(0, 0, 0, 0)));
        // 黄色按钮 1*1
        JButton yellowButton = new JButton("yellow");
        this.add(yellowButton);
        yellowButton.setPreferredSize(new Dimension(15, 15));
        yellowButton.setFont(new Font("宋体", Font.PLAIN, 0));
        yellowButton.setOpaque(true);
        yellowButton.setBackground(Color.YELLOW);
        yellowButton.addActionListener(actionListener);
        yellowButton.setPressedIcon(null);
        layout.setConstraints(yellowButton, buildConstraints(6, 2, 1, 1, new Insets(0, 0, 0, 10)));
        // 蓝色按钮 1*1
        JButton blueButton = new JButton("blue");
        this.add(blueButton);
        blueButton.setPreferredSize(new Dimension(15, 15));
        blueButton.setFont(new Font("宋体", Font.PLAIN, 0));
        blueButton.setOpaque(true);
        blueButton.setBackground(Color.BLUE);
        blueButton.addActionListener(actionListener);
        blueButton.setPressedIcon(null);
        layout.setConstraints(blueButton, buildConstraints(5, 3, 1, 1, new Insets(0, 0, 0, 0)));
        // 绿色按钮 1*1
        JButton greenButton = new JButton("green");
        this.add(greenButton);
        greenButton.setPreferredSize(new Dimension(15, 15));
        greenButton.setFont(new Font("宋体", Font.PLAIN, 0));
        greenButton.setOpaque(true);
        greenButton.setBackground(Color.GREEN);
        greenButton.addActionListener(actionListener);
        greenButton.setPressedIcon(null);
        layout.setConstraints(greenButton, buildConstraints(6, 3, 1, 1, new Insets(0, 0, 0, 10)));

        // 滑动条监听,匿名内部类
        AdjustmentListener colorSettingBarListener = new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                // 当通过已有颜色设置滚动条时，不响应事件
                if (useCommonColor)
                    return;
                System.out.println("选择自定义颜色：(" + scrollBarR.getValue() + "," + scrollBarG.getValue() + "," + scrollBarB.getValue() + ")");
                color = new Color(scrollBarR.getValue(), scrollBarG.getValue(), scrollBarB.getValue());
                updateColor();
            }
        };

        // RGB设置提示文字 3*1
        JLabel RGBText = new JLabel("RGB设置");
        this.add(RGBText);
        RGBText.setFont(new Font("宋体", Font.BOLD, 12));
        RGBText.setPreferredSize(new Dimension(50, 12));
        layout.setConstraints(RGBText, buildConstraints(8, 0, 3, 1, new Insets(2, 0, 2, 2)));

        // R条 5*1
        this.add(scrollBarR);
        scrollBarR.setUnitIncrement(1);
        scrollBarR.setPreferredSize(new Dimension(100, 15));
        scrollBarR.addAdjustmentListener(colorSettingBarListener);
        layout.setConstraints(scrollBarR, buildConstraints(8, 1, 5, 1, new Insets(0, 0, 2, 2)));

        // R条文字 2*1
        this.add(textR);
        textR.setPreferredSize(new Dimension(50, 15));
        layout.setConstraints(textR, buildConstraints(13, 1, 2, 1, new Insets(0, 0, 2, 2)));

        // G条 5*1
        this.add(scrollBarG);
        scrollBarG.setUnitIncrement(1);
        scrollBarG.setPreferredSize(new Dimension(100, 15));
        scrollBarG.addAdjustmentListener(colorSettingBarListener);
        layout.setConstraints(scrollBarG, buildConstraints(8, 2, 5, 1, new Insets(0, 0, 2, 2)));

        // G条文字 2*1
        this.add(textG);
        textG.setPreferredSize(new Dimension(50, 15));
        layout.setConstraints(textG, buildConstraints(13, 2, 2, 1, new Insets(0, 0, 2, 2)));

        // B条 5*1
        this.add(scrollBarB);
        scrollBarB.setUnitIncrement(1);
        scrollBarB.setPreferredSize(new Dimension(100, 15));
        scrollBarB.addAdjustmentListener(colorSettingBarListener);
        layout.setConstraints(scrollBarB, buildConstraints(8, 3, 5, 1, new Insets(0, 0, 2, 2)));

        // B条文字 2*1
        this.add(textB);
        textB.setPreferredSize(new Dimension(50, 15));
        layout.setConstraints(textB, buildConstraints(13, 3, 2, 1, new Insets(0, 0, 2, 2)));


        updateColor();
    }

    /**
     * 更新设置界面的预览
     */
    private void updateColor() {
        textR.setText("R:" + color.getRed());
        textG.setText("G:" + color.getGreen());
        textB.setText("B:" + color.getBlue());

        scrollBarR.setValue(color.getRed());
        scrollBarG.setValue(color.getGreen());
        scrollBarB.setValue(color.getBlue());

        preview.setBackground(color);

        updateColorSetting();
        // 状态清空,准备下次更新
        if (useCommonColor)
            useCommonColor = false;
    }

    /**
     * 将修改的结果返回画笔
     */
    private void updateColorSetting() {
        if (paintListener != null)
            paintListener.setColor(color);
    }
}
