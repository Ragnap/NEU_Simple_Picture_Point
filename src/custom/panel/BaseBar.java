package custom.panel;

import javax.swing.*;
import java.awt.*;

public class BaseBar extends JPanel {
    /**
     * 构建一个Constrains
     *
     * @param x      组件最左上占用的格子的x坐标
     * @param y      组件最左上占用的格子的y坐标
     * @param width  组件占用的水平格子数
     * @param height 组件占用的竖直格子数
     * @return 对应的约束
     */
    public GridBagConstraints buildConstraints(int x, int y, int width, int height, Insets insets) {
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

    /**
     * 构建一个Constrains
     *
     * @param x      组件最左上占用的格子的x坐标
     * @param y      组件最左上占用的格子的y坐标
     * @param width  组件占用的水平格子数
     * @param height 组件占用的竖直格子数
     * @param fill   组件所在的区域比组件本身要大时进行缩放的类型
     * @return 对应的约束
     */
    public GridBagConstraints buildConstraints(int x, int y, int width, int height, Insets insets, int fill) {
        GridBagConstraints constraints = buildConstraints(x, y, width, height, insets);
        // 组件所在的区域比组件本身要大时进行缩放
        constraints.fill = fill;
        return constraints;
    }

    public void baseSetting(Dimension size) {
        // 设置大小
        this.setBounds(0, 0, size.width / 5, size.height);
        // 边框
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
//        this.setBackground(Color.LIGHT_GRAY);
        // 布局
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
    }

    /**
     * 更新文字
     */
    public void updateView() {

    }

    /**
     * 将修改的结果返回
     */
    public void updateSetting() {

    }
}
