package custom.panel;

import custom.MainWindow;
import custom.picture.*;
import custom.picture.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class AttributeSettingBar extends BaseBar {
    MainWindow mainWindow = null;

    /**
     * 当前修改的图形对象
     */
    Picture nowPicture = null;

    /**
     * 图形的名称
     */
    JTextField nameInput = new JTextField();
    JLabel nameText = new JLabel("图形名称");
    /**
     * 图形的类别
     */
    JLabel kindInput = new JLabel();
    JLabel kindText = new JLabel("图形种类");
    /**
     * 图形的锚点X坐标
     */
    JTextField baseXInput = new JTextField();
    JLabel baseXText = new JLabel("锚点X坐标");
    /**
     * 图形的锚点Y坐标
     */
    JTextField baseYInput = new JTextField();
    JLabel baseYText = new JLabel("锚点Y坐标");
    /**
     * 图形的粗细
     */
    JScrollBar strokeInput = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256);
    JLabel strokeText = new JLabel("粗细设置");
    JLabel strokeValue = new JLabel();
    /**
     * 直线用到的另一个端点的X值
     */
    JTextField endXInput = new JTextField();
    JLabel endXText = new JLabel("端点X坐标");
    /**
     * 直线用到的另一个端点的X值
     */
    JTextField endYInput = new JTextField();
    JLabel endYText = new JLabel("端点Y坐标");


    /**
     * 矩形用到的长
     */
    JTextField heightInput = new JTextField();
    JLabel heightText = new JLabel("长");
    /**
     * 矩形用到的宽
     */
    JTextField widthInput = new JTextField();
    JLabel widthText = new JLabel("宽");

    /**
     * 圆用到的半径
     */
    JTextField rInput = new JTextField();
    JLabel rText = new JLabel("半径");

    /**
     * 椭圆用到的长半径
     */
    JTextField aInput = new JTextField();
    JLabel aText = new JLabel("半长轴");
    /**
     * 椭圆用到的短半径
     */
    JTextField bInput = new JTextField();
    JLabel bText = new JLabel("半短轴");


    /**
     * 颜色设置条
     */
    // 在重新读取的时候需要避免调整活动条位置时触发事件
    boolean ignoreScrollChange = false;
    JLabel colorRGBText = new JLabel("颜色设置");
    JScrollBar colorRInput = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256);
    JScrollBar colorGInput = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256);
    JScrollBar colorBInput = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256);
    JLabel colorRValue = new JLabel("R:255");
    JLabel colorGValue = new JLabel("G:255");
    JLabel colorBValue = new JLabel("B:255");


    /**
     * 字体的粗细
     */
    JScrollBar fontSizeInput = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 101);
    JLabel fontSizeText = new JLabel("字体大小设置");
    JLabel fontSizeValue = new JLabel();


    /**
     * 字型设置
     */
    JCheckBox fontBoldBox = new JCheckBox("加粗");
    JCheckBox fontItalicBox = new JCheckBox("斜体");
    JLabel fontStyleText = new JLabel("字型设置");

    /**
     * 字体设置
     */
    JComboBox<String> fontChooseBox = new JComboBox<>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
    JLabel fontChooseText = new JLabel("字体设置");
    /**
     * 文字内容设置
     */
    JTextField textContentInput = new JTextField();
    JLabel textContentText = new JLabel("文本设置");


    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void setNowPicture(Picture nowPicture) {
        this.nowPicture = nowPicture;
        updateView();

    }

    public AttributeSettingBar(Dimension size) {
        baseSetting(size);
        GridBagLayout layout = (GridBagLayout) this.getLayout();
        // 图形名称设置提示文字 3*1
        this.add(nameText);
        nameText.setFont(new Font("宋体", Font.BOLD, 12));
        nameText.setPreferredSize(new Dimension(100, 15));
        layout.setConstraints(nameText, buildConstraints(0, 0, 3, 1, new Insets(2, 10, 2, 10)));
        // 图形名称设置 3*1
        this.add(nameInput);
        nameInput.setPreferredSize(new Dimension(100, 20));
        nameInput.setFont(new Font("宋体", Font.PLAIN, 12));
        nameInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("重设选定图形名称为:" + nameInput.getText());
                nowPicture.setName(nameInput.getText());
            }
        });
        layout.setConstraints(nameInput, buildConstraints(0, 1, 3, 1, new Insets(0, 10, 2, 10)));

        // 图形种类设置提示文字 3*1
        this.add(kindText);
        kindText.setFont(new Font("宋体", Font.BOLD, 12));
        kindText.setPreferredSize(new Dimension(100, 15));
        layout.setConstraints(kindText, buildConstraints(0, 2, 3, 1, new Insets(0, 10, 2, 10)));
        // 图形种类值文字 3*1
        this.add(kindInput);
        kindInput.setText("");
        kindInput.setFont(new Font("宋体", Font.PLAIN, 12));
        kindInput.setPreferredSize(new Dimension(100, 20));
        layout.setConstraints(kindInput, buildConstraints(0, 3, 3, 1, new Insets(0, 10, 2, 10)));

        // 图形锚点X设置提示文字 2*1
        this.add(baseXText);
        baseXText.setFont(new Font("宋体", Font.BOLD, 12));
        baseXText.setPreferredSize(new Dimension(60, 15));
        layout.setConstraints(baseXText, buildConstraints(3, 0, 2, 1, new Insets(1, 10, 0, 0)));
        // 图形锚点X设置 2*1
        this.add(baseXInput);
        baseXInput.setPreferredSize(new Dimension(30, 20));
        baseXInput.setFont(new Font("宋体", Font.PLAIN, 12));
        baseXInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //在最多五为纯数字时允许设置
                if (baseXInput.getText().matches("[0-9]{0,5}")) {
                    System.out.println("重设选定图形锚点X为:" + baseXInput.getText());
                    nowPicture.setBaseX(Integer.parseInt(baseXInput.getText()));
                } else
                    JOptionPane.showMessageDialog(null, "输入不合法", "错误", JOptionPane.ERROR_MESSAGE);
                updateSetting();
            }
        });
        layout.setConstraints(baseXInput, buildConstraints(6, 0, 2, 1, new Insets(1, 3, 0, 10)));

        // 图形锚点Y设置提示文字 2*1
        this.add(baseYText);
        baseYText.setFont(new Font("宋体", Font.BOLD, 12));
        baseYText.setPreferredSize(new Dimension(60, 15));
        layout.setConstraints(baseYText, buildConstraints(3, 1, 2, 1, new Insets(1, 10, 0, 0)));
        // 图形锚点Y设置 2*1
        this.add(baseYInput);
        baseYInput.setPreferredSize(new Dimension(30, 20));
        baseYInput.setFont(new Font("宋体", Font.PLAIN, 12));
        baseYInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //在最多五为纯数字时允许设置
                if (baseYInput.getText().matches("[0-9]{0,5}")) {
                    System.out.println("重设选定图形锚点X为:" + baseYInput.getText());
                    nowPicture.setBaseY(Integer.parseInt(baseYInput.getText()));
                } else
                    JOptionPane.showMessageDialog(null, "输入不合法", "错误", JOptionPane.ERROR_MESSAGE);
                updateSetting();
            }
        });
        layout.setConstraints(baseYInput, buildConstraints(6, 1, 2, 1, new Insets(1, 3, 0, 10)));

        // 图形端点X设置提示文字 2*1
        this.add(endXText);
        endXText.setFont(new Font("宋体", Font.BOLD, 12));
        endXText.setPreferredSize(new Dimension(60, 15));
        layout.setConstraints(endXText, buildConstraints(3, 2, 2, 1, new Insets(1, 10, 0, 0)));
        // 图形锚点X设置 2*1
        this.add(endXInput);
        endXInput.setPreferredSize(new Dimension(30, 20));
        endXInput.setFont(new Font("宋体", Font.PLAIN, 12));
        endXInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //在最多五为纯数字时允许设置
                if (endXInput.getText().matches("[0-9]{0,5}")) {
                    System.out.println("重设选定图形锚点X为:" + endXInput.getText());
                    ((Line) nowPicture).setEndX(Integer.parseInt(endXInput.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "输入不合法", "错误", JOptionPane.ERROR_MESSAGE);
                    endXInput.setText(String.valueOf(((Line) nowPicture).getEndX()));
                }
                updateSetting();
            }
        });
        layout.setConstraints(endXInput, buildConstraints(6, 2, 2, 1, new Insets(1, 3, 0, 10)));

        // 图形端点Y设置提示文字 2*1
        this.add(endYText);
        endYText.setFont(new Font("宋体", Font.BOLD, 12));
        endYText.setPreferredSize(new Dimension(60, 15));
        layout.setConstraints(endYText, buildConstraints(3, 3, 2, 1, new Insets(1, 10, 0, 0)));
        // 图形锚点Y设置 2*1
        this.add(endYInput);
        endYInput.setPreferredSize(new Dimension(30, 20));
        endYInput.setFont(new Font("宋体", Font.PLAIN, 12));
        endYInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //在最多五为纯数字时允许设置
                if (endYInput.getText().matches("[0-9]{0,5}")) {
                    System.out.println("重设选定图形锚点X为:" + endYInput.getText());
                    ((Line) nowPicture).setEndY(Integer.parseInt(endYInput.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "输入不合法", "错误", JOptionPane.ERROR_MESSAGE);
                    endYInput.setText(String.valueOf(((Line) nowPicture).getEndY()));
                }
                updateSetting();
            }
        });
        layout.setConstraints(endYInput, buildConstraints(6, 3, 2, 1, new Insets(1, 3, 0, 10)));

        // 图形宽设置提示文字 2*1
        this.add(widthText);
        widthText.setFont(new Font("宋体", Font.BOLD, 12));
        widthText.setPreferredSize(new Dimension(60, 15));
        layout.setConstraints(widthText, buildConstraints(3, 2, 2, 1, new Insets(1, 10, 0, 0)));
        // 图形宽设置 2*1
        this.add(widthInput);
        widthInput.setPreferredSize(new Dimension(30, 20));
        widthInput.setFont(new Font("宋体", Font.PLAIN, 12));
        widthInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //在最多五为纯数字时允许设置
                if (widthInput.getText().matches("[0-9]{0,5}")) {
                    System.out.println("重设选定图形锚点X为:" + widthInput.getText());
                    ((Rectangle) nowPicture).setWidth(Integer.parseInt(widthInput.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "输入不合法", "错误", JOptionPane.ERROR_MESSAGE);
                    widthInput.setText(String.valueOf(((Rectangle) nowPicture).getWidth()));
                }
                updateSetting();
            }
        });
        layout.setConstraints(widthInput, buildConstraints(6, 2, 2, 1, new Insets(1, 3, 0, 10)));

        // 图形高设置提示文字 2*1
        this.add(heightText);
        heightText.setFont(new Font("宋体", Font.BOLD, 12));
        heightText.setPreferredSize(new Dimension(60, 15));
        layout.setConstraints(heightText, buildConstraints(3, 3, 2, 1, new Insets(1, 10, 0, 0)));
        // 图形高设置 2*1
        this.add(heightInput);
        heightInput.setPreferredSize(new Dimension(30, 20));
        heightInput.setFont(new Font("宋体", Font.PLAIN, 12));
        heightInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //在最多五为纯数字时允许设置
                if (heightInput.getText().matches("[0-9]{0,5}")) {
                    System.out.println("重设选定图形锚点X为:" + heightInput.getText());
                    ((Rectangle) nowPicture).setHeight(Integer.parseInt(heightInput.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "输入不合法", "错误", JOptionPane.ERROR_MESSAGE);
                    heightInput.setText(String.valueOf(((Rectangle) nowPicture).getHeight()));
                }
                updateSetting();
            }
        });
        layout.setConstraints(heightInput, buildConstraints(6, 3, 2, 1, new Insets(1, 3, 0, 10)));

        // 圆形半径设置提示文字 2*1
        this.add(rText);
        rText.setFont(new Font("宋体", Font.BOLD, 12));
        rText.setPreferredSize(new Dimension(60, 15));
        layout.setConstraints(rText, buildConstraints(3, 2, 2, 1, new Insets(1, 10, 0, 0)));
        // 圆形半径设置 2*1
        this.add(rInput);
        rInput.setPreferredSize(new Dimension(30, 20));
        rInput.setFont(new Font("宋体", Font.PLAIN, 12));
        rInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //在最多五为纯数字时允许设置
                if (rInput.getText().matches("[0-9]{0,5}")) {
                    System.out.println("重设选定图形锚点X为:" + rInput.getText());
                    ((Circle) nowPicture).setR(Integer.parseInt(rInput.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "输入不合法", "错误", JOptionPane.ERROR_MESSAGE);
                    rInput.setText(String.valueOf(((Circle) nowPicture).getR()));
                }
                updateSetting();
            }
        });
        layout.setConstraints(rInput, buildConstraints(6, 2, 2, 1, new Insets(1, 3, 0, 10)));

        // 椭圆长半径设置提示文字 2*1
        this.add(aText);
        aText.setFont(new Font("宋体", Font.BOLD, 12));
        aText.setPreferredSize(new Dimension(60, 15));
        layout.setConstraints(aText, buildConstraints(3, 2, 2, 1, new Insets(1, 10, 0, 0)));
        // 椭圆长半径设置 2*1
        this.add(aInput);
        aInput.setPreferredSize(new Dimension(30, 20));
        aInput.setFont(new Font("宋体", Font.PLAIN, 12));
        aInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //在最多五为纯数字时允许设置
                if (aInput.getText().matches("[0-9]{0,5}")) {
                    System.out.println("重设选定图形锚点X为:" + aInput.getText());
                    ((Ellipse) nowPicture).setA(Integer.parseInt(aInput.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "输入不合法", "错误", JOptionPane.ERROR_MESSAGE);
                    //非法输入时恢复
                    aInput.setText(String.valueOf(((Ellipse) nowPicture).getA()));
                }
                updateSetting();
            }
        });
        layout.setConstraints(aInput, buildConstraints(6, 2, 2, 1, new Insets(1, 3, 0, 10)));

        // 图形高设置提示文字 2*1
        this.add(bText);
        bText.setFont(new Font("宋体", Font.BOLD, 12));
        bText.setPreferredSize(new Dimension(60, 15));
        layout.setConstraints(bText, buildConstraints(3, 3, 2, 1, new Insets(1, 10, 0, 0)));
        // 图形高设置 2*1
        this.add(bInput);
        bInput.setPreferredSize(new Dimension(30, 20));
        bInput.setFont(new Font("宋体", Font.PLAIN, 12));
        bInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //在最多五为纯数字时允许设置
                if (bInput.getText().matches("[0-9]{0,5}")) {
                    System.out.println("重设选定图形锚点X为:" + bInput.getText());
                    ((Ellipse) nowPicture).setB(Integer.parseInt(bInput.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "输入不合法", "错误", JOptionPane.ERROR_MESSAGE);
                    bInput.setText(String.valueOf(((Ellipse) nowPicture).getB()));
                }
                updateSetting();
            }
        });
        layout.setConstraints(bInput, buildConstraints(6, 3, 2, 1, new Insets(1, 3, 0, 10)));


        // RGB设置提示文字 7*1

        this.add(colorRGBText);
        colorRGBText.setFont(new Font("宋体", Font.BOLD, 12));
        colorRGBText.setPreferredSize(new Dimension(50, 12));
        layout.setConstraints(colorRGBText, buildConstraints(8, 0, 7, 1, new Insets(2, 0, 2, 2)));


        // 滑动条监听,匿名内部类
        AdjustmentListener colorSettingBarListener = new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if (ignoreScrollChange)
                    return;
                System.out.println("重设选定图形颜色为：(" + colorRInput.getValue() + "," + colorGInput.getValue() + "," + colorBInput.getValue() + ")");
                nowPicture.setColor(new Color(colorRInput.getValue(), colorGInput.getValue(), colorBInput.getValue()));
                updateSetting();
            }
        };

        // R条 5*1
        this.add(colorRInput);
        colorRInput.setUnitIncrement(1);
        colorRInput.setPreferredSize(new Dimension(100, 15));
        colorRInput.addAdjustmentListener(colorSettingBarListener);
        layout.setConstraints(colorRInput, buildConstraints(8, 1, 5, 1, new Insets(0, 10, 0, 2)));

        // R条文字 2*1
        this.add(colorRValue);
        colorRValue.setPreferredSize(new Dimension(50, 15));
        layout.setConstraints(colorRValue, buildConstraints(13, 1, 2, 1, new Insets(0, 0, 0, 2)));

        // G条 5*1
        this.add(colorGInput);
        colorGInput.setUnitIncrement(1);
        colorGInput.setPreferredSize(new Dimension(100, 15));
        colorGInput.addAdjustmentListener(colorSettingBarListener);
        layout.setConstraints(colorGInput, buildConstraints(8, 2, 5, 1, new Insets(0, 10, 0, 2)));

        // G条文字 2*1
        this.add(colorGValue);
        colorGValue.setPreferredSize(new Dimension(50, 15));
        layout.setConstraints(colorGValue, buildConstraints(13, 2, 2, 1, new Insets(0, 0, 0, 2)));

        // B条 5*1
        this.add(colorBInput);
        colorBInput.setUnitIncrement(1);
        colorBInput.setPreferredSize(new Dimension(100, 15));
        colorBInput.addAdjustmentListener(colorSettingBarListener);
        layout.setConstraints(colorBInput, buildConstraints(8, 3, 5, 1, new Insets(0, 10, 0, 2)));

        // B条文字 2*1
        this.add(colorBValue);
        colorBValue.setPreferredSize(new Dimension(50, 15));
        layout.setConstraints(colorBValue, buildConstraints(13, 3, 2, 1, new Insets(0, 0, 0, 2)));

        // 线条粗细文字 7:1
        this.add(strokeText);
        strokeText.setFont(new Font("宋体", Font.BOLD, 12));
        strokeText.setPreferredSize(new Dimension(140, 15));
        layout.setConstraints(strokeText, buildConstraints(15, 0, 6, 1, new Insets(2, 0, 0, 0)));

        // 粗细值条 5*1
        this.add(strokeInput);
        strokeInput.setUnitIncrement(1);
        strokeInput.setPreferredSize(new Dimension(100, 15));
        strokeInput.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if (ignoreScrollChange)
                    return;
                System.out.println("重设选定图形粗细为：" + strokeInput.getValue());
                nowPicture.setStroke(new BasicStroke(strokeInput.getValue()));
                updateSetting();
            }
        });
        layout.setConstraints(strokeInput, buildConstraints(15, 1, 5, 1, new Insets(0, 0, 0, 2)));

        // 当前粗细值文字 2*1
        this.add(strokeValue);
        strokeValue.setPreferredSize(new Dimension(40, 15));
        layout.setConstraints(strokeValue, buildConstraints(20, 1, 1, 1, new Insets(0, 0, 0, 2)));

        // 文字大小文字提示 7:1
        this.add(fontSizeText);
        fontSizeText.setFont(new Font("宋体", Font.BOLD, 12));
        fontSizeText.setPreferredSize(new Dimension(140, 15));
        layout.setConstraints(fontSizeText, buildConstraints(15, 0, 6, 1, new Insets(2, 0, 0, 0)));

        // 文字大小条 5*1
        this.add(fontSizeInput);
        fontSizeInput.setUnitIncrement(1);
        fontSizeInput.setPreferredSize(new Dimension(100, 15));
        fontSizeInput.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if (ignoreScrollChange)
                    return;
                System.out.println("重设选定文字大小为：" + fontSizeInput.getValue());
                Font originFont = ((Text) nowPicture).getFont();
                ((Text) nowPicture).setFont(new Font(originFont.getFontName(), originFont.getStyle(), fontSizeInput.getValue()));
                updateSetting();
            }
        });
        layout.setConstraints(fontSizeInput, buildConstraints(15, 1, 5, 1, new Insets(0, 0, 0, 2)));

        // 当前文字大小值文字 2*1
        this.add(fontSizeValue);
        fontSizeValue.setPreferredSize(new Dimension(40, 15));
        layout.setConstraints(fontSizeValue, buildConstraints(20, 1, 1, 1, new Insets(0, 0, 0, 2)));


        // 字型设置提示文字 6*1
        this.add(fontStyleText);
        fontStyleText.setFont(new Font("宋体", Font.BOLD, 12));
        fontStyleText.setPreferredSize(new Dimension(120, 15));
        layout.setConstraints(fontStyleText, buildConstraints(15, 2, 6, 1, new Insets(0, 0, 0, 2)));

        // 字型加粗选项 2*1
        this.add(fontBoldBox);
        fontBoldBox.setPreferredSize(new Dimension(50, 15));
        fontBoldBox.setFont(new Font("宋体", Font.PLAIN, 12));
        fontBoldBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fontStyle = Font.PLAIN;
                if (fontBoldBox.isSelected())
                    fontStyle |= Font.BOLD;
                if (fontItalicBox.isSelected())
                    fontStyle |= Font.ITALIC;
                Font originFont = ((Text) nowPicture).getFont();
                ((Text) nowPicture).setFont(new Font(originFont.getFontName(), fontStyle, originFont.getSize()));
                updateSetting();

                System.out.println("重设字形:" + ((fontStyle == 0) ? "普通" : ((fontStyle & Font.BOLD) == 0 ? "" : "粗体") + ((fontStyle & Font.ITALIC) == 0 ? "" : "斜体")));
            }
        });
        layout.setConstraints(fontBoldBox, buildConstraints(16, 3, 2, 1, new Insets(2, 0, 0, 0)));

        // 字型斜体选项 2*1
        this.add(fontItalicBox);
        fontItalicBox.setPreferredSize(new Dimension(50, 15));
        fontItalicBox.setFont(new Font("宋体", Font.PLAIN, 12));
        fontItalicBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fontStyle = Font.PLAIN;
                if (fontBoldBox.isSelected())
                    fontStyle |= Font.BOLD;
                if (fontItalicBox.isSelected())
                    fontStyle |= Font.ITALIC;
                Font originFont = ((Text) nowPicture).getFont();
                ((Text) nowPicture).setFont(new Font(originFont.getFontName(), fontStyle, originFont.getSize()));
                updateSetting();

                System.out.println("重设字形:" + ((fontStyle == 0) ? "普通" : ((fontStyle & Font.BOLD) == 0 ? "" : "粗体") + ((fontStyle & Font.ITALIC) == 0 ? "" : "斜体")));
            }
        });
        layout.setConstraints(fontItalicBox, buildConstraints(18, 3, 2, 1, new Insets(0, 0, 0, 2)));

        // 字体选择提示文字 8*1
        this.add(fontChooseText);
        fontChooseText.setFont(new Font("宋体", Font.BOLD, 12));
        fontChooseText.setPreferredSize(new Dimension(160, 15));
        layout.setConstraints(fontChooseText, buildConstraints(21, 0, 8, 1, new Insets(0, 10, 0, 10)));

        // 字体下拉选择菜单,获取系统字体,创建字体选择菜单 8*1
        this.add(fontChooseBox);
        fontChooseBox.setEditable(false);
        fontChooseBox.setPreferredSize(new Dimension(160, 18));
        fontChooseBox.setFont(new Font("宋体", Font.PLAIN, 12));
        fontChooseBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fontName = (String) fontChooseBox.getSelectedItem();
                Font originFont = ((Text) nowPicture).getFont();
                ((Text) nowPicture).setFont(new Font(fontName, originFont.getStyle(), originFont.getSize()));
                updateSetting();

                System.out.println("重设字体:" + fontName);

            }
        });
        layout.setConstraints(fontChooseBox, buildConstraints(21, 1, 8, 1, new Insets(0, 10, 0, 10)));

        // 文字内容设置提示文字 8*1
        this.add(textContentText);
        textContentText.setFont(new Font("宋体", Font.BOLD, 12));
        textContentText.setPreferredSize(new Dimension(160, 15));
        layout.setConstraints(textContentText, buildConstraints(21, 2, 8, 1, new Insets(0, 10, 0, 10)));
        // 文字内容设置 8*1
        this.add(textContentInput);
        textContentInput.setPreferredSize(new Dimension(160, 20));
        textContentInput.setFont(new Font("宋体", Font.PLAIN, 12));
        textContentInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("重设选定图形名称为:" + textContentInput.getText());
                ((Text) nowPicture).setContent(textContentInput.getText());

                updateSetting();
                System.out.println("重设文字内容:" + textContentInput.getText());
            }
        });
        layout.setConstraints(textContentInput, buildConstraints(21, 3, 8, 1, new Insets(0, 10, 0, 10)));

        updateView();
    }

    public void updateView() {
        for (Component component : getComponents()) {
            component.setVisible(false);
        }

        if (nowPicture == null) {
            nameInput.setText("");
            kindInput.setText("");
            baseXInput.setText("");
            baseYInput.setText("");
            strokeValue.setText("");
            return;
        }
        nameInput.setVisible(true);
        nameText.setVisible(true);
        kindInput.setVisible(true);
        kindText.setVisible(true);
        baseXInput.setVisible(true);
        baseXText.setVisible(true);
        baseYInput.setVisible(true);
        baseYText.setVisible(true);
        colorRGBText.setVisible(true);
        colorRInput.setVisible(true);
        colorRValue.setVisible(true);
        colorGInput.setVisible(true);
        colorGValue.setVisible(true);
        colorBInput.setVisible(true);
        colorBValue.setVisible(true);

        switch (nowPicture.getPictureKind()) {
            case 1 -> {
                endXInput.setText(String.valueOf(((Line) nowPicture).getEndX()));
                endXInput.setVisible(true);
                endXText.setVisible(true);
                endYInput.setText(String.valueOf(((Line) nowPicture).getEndY()));
                endYInput.setVisible(true);
                endYText.setVisible(true);
                strokeInput.setVisible(true);
                strokeText.setVisible(true);
                strokeValue.setVisible(true);
            }
            case 2 -> {
                widthInput.setText(String.valueOf(((Rectangle) nowPicture).getWidth()));
                widthInput.setVisible(true);
                widthText.setVisible(true);
                heightInput.setText(String.valueOf(((Rectangle) nowPicture).getHeight()));
                heightInput.setVisible(true);
                heightText.setVisible(true);
                strokeInput.setVisible(true);
                strokeText.setVisible(true);
                strokeValue.setVisible(true);
            }
            case 3 -> {
                rInput.setText(String.valueOf(((Circle) nowPicture).getR()));
                rInput.setVisible(true);
                rText.setVisible(true);
                strokeInput.setVisible(true);
                strokeText.setVisible(true);
                strokeValue.setVisible(true);
            }
            case 4 -> {
                aInput.setText(String.valueOf(((Ellipse) nowPicture).getA()));
                aInput.setVisible(true);
                aText.setVisible(true);
                bInput.setText(String.valueOf(((Ellipse) nowPicture).getB()));
                bInput.setVisible(true);
                bText.setVisible(true);
                strokeInput.setVisible(true);
                strokeText.setVisible(true);
                strokeValue.setVisible(true);
            }
            case 5 -> {
                strokeInput.setVisible(true);
                strokeText.setVisible(true);
                strokeValue.setVisible(true);
            }
            case 6 -> {
                fontSizeInput.setVisible(true);
                fontSizeText.setVisible(true);
                fontSizeValue.setVisible(true);

                fontStyleText.setVisible(true);
                fontBoldBox.setVisible(true);
                fontItalicBox.setVisible(true);

                fontChooseText.setVisible(true);
                fontChooseBox.setVisible(true);

                textContentText.setVisible(true);
                textContentInput.setVisible(true);
            }
        }
        updateSetting();
    }


    public void updateSetting() {
        ignoreScrollChange = true;
        nameInput.setText(nowPicture.getName());
        kindInput.setText(switch (nowPicture.getPictureKind()) {
            case 1 -> "直线";
            case 2 -> "矩形";
            case 3 -> "圆";
            case 4 -> "椭圆";
            case 5 -> "自由线";
            case 6 -> "文本";
            default -> "";
        });
        baseXInput.setText(String.valueOf(nowPicture.getBaseX()));
        baseYInput.setText(String.valueOf(nowPicture.getBaseY()));
        strokeInput.setValue((int) nowPicture.getStroke().getLineWidth());
        strokeValue.setText(String.valueOf(((int) nowPicture.getStroke().getLineWidth())));

        colorRValue.setText("R:" + nowPicture.getColor().getRed());
        colorGValue.setText("G:" + nowPicture.getColor().getGreen());
        colorBValue.setText("B:" + nowPicture.getColor().getBlue());
        colorRInput.setValue(nowPicture.getColor().getRed());
        colorGInput.setValue(nowPicture.getColor().getGreen());
        colorBInput.setValue(nowPicture.getColor().getBlue());

        if (nowPicture.getPictureKind() == 6) {
            fontSizeValue.setText(String.valueOf(((Text) nowPicture).getFont().getSize()));
            fontSizeInput.setValue(((Text) nowPicture).getFont().getSize());

            fontBoldBox.setSelected((((Text) nowPicture).getFont().getStyle() & Font.BOLD) != 0);
            fontItalicBox.setSelected((((Text) nowPicture).getFont().getStyle() & Font.ITALIC) != 0);

            fontChooseBox.setSelectedItem(((Text) nowPicture).getFont().getName());
            textContentInput.setText(((Text) nowPicture).getContent());
        }


        ignoreScrollChange = false;
        mainWindow.pagePane.pageEditPane.refresh();
    }
}
