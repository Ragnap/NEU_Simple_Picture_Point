package custom.panel;

import custom.listener.DrawListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class DrawStrokeSettingBar extends BaseBar {

    BasicStroke stroke = new BasicStroke();
    /**
     * 控制的画笔
     */
    DrawListener drawListener;

    public void setPaintListener(DrawListener drawListener) {
        this.drawListener = drawListener;
    }

    /**
     * 粗细设置条
     */
    JScrollBar scrollBarStroke = new JScrollBar(JScrollBar.HORIZONTAL, 1, 1, 1, 100);
    /**
     * 当前粗细值
     */
    JLabel nowStrokeValue = new JLabel();

    public DrawStrokeSettingBar(Dimension size){
        baseSetting(size);
        GridBagLayout layout = (GridBagLayout)this.getLayout();
        // 预览提示文字 4*1
        JLabel nowStrokeText = new JLabel("当前画笔大小");
        this.add(nowStrokeText);
        nowStrokeText.setFont(new Font("宋体", Font.BOLD, 12));
        nowStrokeText.setPreferredSize(new Dimension(100, 12));
        layout.setConstraints(nowStrokeText, buildConstraints(0, 0, 4,1, new Insets(2, 10, 2, 10)));

        // 滑动条监听,匿名内部类
        AdjustmentListener colorSettingBarListener = new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                System.out.println("设置粗细：" + scrollBarStroke.getValue() + "px" );
                stroke= new BasicStroke(scrollBarStroke.getValue());
                updateView();
            }
        };

        // 粗细值条 4*1
        this.add(scrollBarStroke);
        scrollBarStroke.setUnitIncrement(1);
        scrollBarStroke.setPreferredSize(new Dimension(100, 15));
        scrollBarStroke.addAdjustmentListener(colorSettingBarListener);
        layout.setConstraints(scrollBarStroke, buildConstraints(0, 1, 4,1, new Insets(10, 0, 2, 2)));

        // 当前粗细值文字 4*1
        this.add(nowStrokeValue);
        nowStrokeValue.setText(scrollBarStroke.getValue()+"像素");
        nowStrokeValue.setFont(new Font("宋体", Font.BOLD, 12));
        nowStrokeValue.setPreferredSize(new Dimension(100, 12));
        layout.setConstraints(nowStrokeValue, buildConstraints(1, 2, 2,1, new Insets(15, 0, 2, 2)));

    }
    /**
     * 更新文字
     */
    public void updateView(){
        nowStrokeValue.setText(scrollBarStroke.getValue()+"像素");
        updateSetting();
    }
    /**
     * 将修改的结果返回画笔
     */
    public void updateSetting(){
        if (drawListener != null)
            drawListener.setStroke(stroke);
    }
}
