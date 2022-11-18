package custom.panel;

import custom.listener.DrawListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class DrawTextSettingBar extends BaseBar {
    /**
     * 当前字体
     */
    Font font = new Font("宋体", Font.PLAIN, 12);

    DrawListener drawListener;

    public void setDrawListener(DrawListener drawListener) {
        this.drawListener = drawListener;
    }

    /**
     * 字型加粗选项框
     */
    JCheckBox boldBox = new JCheckBox("加粗");
    /**
     * 字型斜体选项框
     */
    JCheckBox italicBox = new JCheckBox("斜体");
    /**
     * 字体选择下拉栏
     */
    JComboBox<String> fontChooseBox = new JComboBox<>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());

    /**
     * 字号滚动条
     */
    JScrollBar fontSizeBar= new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 100);

    /**
     * 字号值显示
     */
    JLabel fontSizeValue = new JLabel();

    public DrawTextSettingBar(Dimension size) {
        baseSetting(size);
        GridBagLayout layout = (GridBagLayout) this.getLayout();
        // 字型设置提示文字 2*1
        JLabel fontStyleText = new JLabel("当前字型");
        this.add(fontStyleText);
        fontStyleText.setFont(new Font("宋体", Font.BOLD, 12));
        fontStyleText.setPreferredSize(new Dimension(60, 15));
        layout.setConstraints(fontStyleText, buildConstraints(0, 0, 3, 1, new Insets(0, 10, 0, 10)));

        // 字型加粗选项 2*1
        this.add(boldBox);
        boldBox.setPreferredSize(new Dimension(60, 15));
        boldBox.setFont(new Font("宋体", Font.PLAIN, 12));
        boldBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fontName = font.getFontName();
                int fontStyle = Font.PLAIN;
                if (boldBox.isSelected())
                    fontStyle |= Font.BOLD;
                if (italicBox.isSelected())
                    fontStyle |= Font.ITALIC;
                int fontSize = font.getSize();

                font = new Font(fontName, fontStyle, fontSize);
                updateSetting();

                System.out.println("选择字形:" + ((fontStyle == 0) ? "普通" : ((fontStyle & Font.BOLD) == 0 ? "" : "粗体") + ((fontStyle & Font.ITALIC) == 0 ? "" : "斜体")));
            }
        });
        layout.setConstraints(boldBox, buildConstraints(0, 2, 3, 1, new Insets(0, 10, 5, 10)));

        // 字型斜体选项 2*1
        this.add(italicBox);
        italicBox.setPreferredSize(new Dimension(60, 15));
        italicBox.setFont(new Font("宋体", Font.PLAIN, 12));
        italicBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fontName = font.getFontName();
                int fontStyle = Font.PLAIN;
                if (boldBox.isSelected())
                    fontStyle |= Font.BOLD;
                if (italicBox.isSelected())
                    fontStyle |= Font.ITALIC;
                int fontSize = font.getSize();

                font = new Font(fontName, fontStyle, fontSize);
                updateSetting();

                System.out.println("选择字形:" + ((fontStyle == 0) ? "普通" : ((fontStyle & Font.BOLD) == 0 ? "" : "粗体") + ((fontStyle & Font.ITALIC) == 0 ? "" : "斜体")));
            }
        });
        layout.setConstraints(italicBox, buildConstraints(0, 3, 3, 1, new Insets(0, 10, 0, 10)));

        // 字体选择提示文字 3*1
        JLabel fontChooseText = new JLabel("当前字体");
        this.add(fontChooseText);
        fontChooseText.setFont(new Font("宋体", Font.BOLD, 12));
        fontChooseText.setPreferredSize(new Dimension(150, 15));
        layout.setConstraints(fontChooseText, buildConstraints(3, 0, 3, 1, new Insets(0, 10, 0, 10)));

        // 字体下拉选择菜单,获取系统字体,创建字体选择菜单 3*1
        this.add(fontChooseBox);
        // 字体选择不可输入
        fontChooseBox.setEditable(false);
        fontChooseBox.setPreferredSize(new Dimension(150, 18));
        fontChooseBox.setFont(new Font("宋体", Font.PLAIN, 12));
        fontChooseBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fontName = (String) fontChooseBox.getSelectedItem();
                int fontStyle = font.getStyle();
                int fontSize = font.getSize();
                font = new Font(fontName, fontStyle, fontSize);
                updateSetting();

                System.out.println("选择字体:" + fontName);
            }
        });
        layout.setConstraints(fontChooseBox, buildConstraints(3, 1, 3, 1, new Insets(0, 10, 5, 10)));

        // 字体大小提示文字 3*1
        JLabel fontSizeText = new JLabel("当前字号");
        this.add(fontSizeText);
        fontSizeText.setFont(new Font("宋体", Font.BOLD, 12));
        fontSizeText.setPreferredSize(new Dimension(150, 12));
        layout.setConstraints(fontSizeText, buildConstraints(3, 2, 3, 1, new Insets(0, 10, 0, 10)));

        // 字号滑动条 2*1
        this.add(fontSizeBar);
        fontSizeBar.setUnitIncrement(1);
        fontSizeBar.setPreferredSize(new Dimension(120, 15));
        fontSizeBar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                String fontName = (String) fontChooseBox.getSelectedItem();
                int fontStyle = font.getStyle();
                int fontSize = fontSizeBar.getValue();
                font = new Font(fontName, fontStyle, fontSize);
                updateSetting();
                // 更新文字
                fontSizeValue.setText(String.valueOf(fontSize));

                System.out.println("设置字号:"+fontSize);
            }
        });
        layout.setConstraints(fontSizeBar, buildConstraints(3, 3, 2, 1, new Insets(0, 10, 0, 0)));

        // 字号显示 1*1
        this.add(fontSizeValue);
        fontSizeValue.setPreferredSize(new Dimension(30, 15));
        fontSizeValue.setText(String.valueOf(font.getSize()));
        layout.setConstraints(fontSizeValue, buildConstraints(5, 3, 1, 1, new Insets(0, 10, 0, 10)));


    }

    /**
     * 将修改的结果返回画笔
     */
    public void updateSetting() {
        if (drawListener != null)
            drawListener.setFont(font);
    }
}
