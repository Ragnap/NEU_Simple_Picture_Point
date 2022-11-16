package custom.panel;

import custom.listener.PaintListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class DrawStrokeSettingBar extends JPanel {

    BasicStroke stroke = new BasicStroke();
    /**
     * 控制的画笔
     */
    PaintListener paintListener;

    public void setPaintListener(PaintListener paintListener) {
        this.paintListener = paintListener;
    }

    /**
     * 粗细设置条
     */
    JScrollBar scrollBarStroke = new JScrollBar(JScrollBar.HORIZONTAL, 1, 1, 1, 100);
    /**
     * 当前粗细值
     */
    JLabel nowStrokeValue = new JLabel();

    /**
     * 构建一个Constrains
     *
     * @param x     组件最左上占用的格子的x坐标
     * @param y     组件最左上占用的格子的y坐标
     * @param width 组件占用的水平格子数
     * @return 对应的约束
     */
    private GridBagConstraints buildConstraints(int x, int y, int width, Insets insets) {
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
        constraints.gridheight = 1;

        return constraints;
    }
    DrawStrokeSettingBar(Dimension size){
        // 设置大小
        this.setBounds(0, 0, size.width / 5, size.height);
        // 边框
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
//        this.setBackground(Color.LIGHT_GRAY);
        // 布局
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        // 预览提示文字 4*1
        JLabel nowStrokeText = new JLabel("当前画笔大小");
        this.add(nowStrokeText);
        nowStrokeText.setFont(new Font("宋体", Font.BOLD, 12));
        nowStrokeText.setPreferredSize(new Dimension(100, 12));
        layout.setConstraints(nowStrokeText, buildConstraints(0, 0, 4, new Insets(2, 10, 2, 10)));

        // 滑动条监听,匿名内部类
        AdjustmentListener colorSettingBarListener = new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                System.out.println("设置粗细：" + scrollBarStroke.getValue() + "px" );
                stroke= new BasicStroke(scrollBarStroke.getValue());
                updateStroke();
            }
        };

        // 粗细值条 4*1
        this.add(scrollBarStroke);
        scrollBarStroke.setUnitIncrement(1);
        scrollBarStroke.setPreferredSize(new Dimension(100, 15));
        scrollBarStroke.addAdjustmentListener(colorSettingBarListener);
        layout.setConstraints(scrollBarStroke, buildConstraints(0, 1, 4, new Insets(10, 0, 2, 2)));

        // 当前粗细值文字 4*1
        this.add(nowStrokeValue);
        nowStrokeValue.setText(scrollBarStroke.getValue()+"像素");
        nowStrokeValue.setFont(new Font("宋体", Font.BOLD, 12));
        nowStrokeValue.setPreferredSize(new Dimension(100, 12));
        layout.setConstraints(nowStrokeValue, buildConstraints(1, 2, 2, new Insets(15, 0, 2, 2)));

    }
    /**
     * 更新文字
     */
    void updateStroke(){
        nowStrokeValue.setText(scrollBarStroke.getValue()+"像素");
        updateStrokeSetting();
    }
    /**
     * 将修改的结果返回画笔
     */
    void updateStrokeSetting(){
        if (paintListener != null)
            paintListener.setStroke(stroke);
    }
}
