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
    JTextField strokeInput = new JTextField();
    JLabel strokeXText = new JLabel("粗细");

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
     * R颜色设置条
     */
    JScrollBar colorRInput = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256);
    JScrollBar colorGInput = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256);
    JScrollBar colorBInput = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256);
    JLabel colorRText = new JLabel("R:255");
    JLabel colorGText = new JLabel("G:255");
    JLabel colorBText = new JLabel("B:255");
    // 在重新读取的时候需要避免调整活动条位置时触发事件
    boolean ignoreColorChange = false;

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
//        kindInput.setEnabled(false);
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
                } else
                    JOptionPane.showMessageDialog(null, "输入不合法", "错误", JOptionPane.ERROR_MESSAGE);
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
                } else
                    JOptionPane.showMessageDialog(null, "输入不合法", "错误", JOptionPane.ERROR_MESSAGE);
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
                } else
                    JOptionPane.showMessageDialog(null, "输入不合法", "错误", JOptionPane.ERROR_MESSAGE);
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
                } else
                    JOptionPane.showMessageDialog(null, "输入不合法", "错误", JOptionPane.ERROR_MESSAGE);
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
                } else
                    JOptionPane.showMessageDialog(null, "输入不合法", "错误", JOptionPane.ERROR_MESSAGE);
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
                } else
                    JOptionPane.showMessageDialog(null, "输入不合法", "错误", JOptionPane.ERROR_MESSAGE);
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
                } else
                    JOptionPane.showMessageDialog(null, "输入不合法", "错误", JOptionPane.ERROR_MESSAGE);
                updateSetting();
            }
        });
        layout.setConstraints(bInput, buildConstraints(6, 3, 2, 1, new Insets(1, 3, 0, 10)));


        // RGB设置提示文字 7*1
        JLabel RGBText = new JLabel("颜色设置");
        this.add(RGBText);
        RGBText.setFont(new Font("宋体", Font.BOLD, 12));
        RGBText.setPreferredSize(new Dimension(50, 12));
        layout.setConstraints(RGBText, buildConstraints(8, 0, 7, 1, new Insets(2, 0, 2, 2)));


        // 滑动条监听,匿名内部类
        AdjustmentListener colorSettingBarListener = new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if (ignoreColorChange)
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
        layout.setConstraints(colorRInput, buildConstraints(8, 1, 5, 1, new Insets(0, 10, 2, 2)));

        // R条文字 2*1
        this.add(colorRText);
        colorRText.setPreferredSize(new Dimension(50, 15));
        layout.setConstraints(colorRText, buildConstraints(13, 1, 2, 1, new Insets(0, 0, 2, 2)));

        // G条 5*1
        this.add(colorGInput);
        colorGInput.setUnitIncrement(1);
        colorGInput.setPreferredSize(new Dimension(100, 15));
        colorGInput.addAdjustmentListener(colorSettingBarListener);
        layout.setConstraints(colorGInput, buildConstraints(8, 2, 5, 1, new Insets(0, 10, 2, 2)));

        // G条文字 2*1
        this.add(colorGText);
        colorGText.setPreferredSize(new Dimension(50, 15));
        layout.setConstraints(colorGText, buildConstraints(13, 2, 2, 1, new Insets(0, 0, 2, 2)));

        // B条 5*1
        this.add(colorBInput);
        colorBInput.setUnitIncrement(1);
        colorBInput.setPreferredSize(new Dimension(100, 15));
        colorBInput.addAdjustmentListener(colorSettingBarListener);
        layout.setConstraints(colorBInput, buildConstraints(8, 3, 5, 1, new Insets(0, 10, 2, 2)));

        // B条文字 2*1
        this.add(colorBText);
        colorBText.setPreferredSize(new Dimension(50, 15));
        layout.setConstraints(colorBText, buildConstraints(13, 3, 2, 1, new Insets(0, 0, 2, 2)));


        updateView();
    }

    public void updateView() {

        endXInput.setVisible(false);
        endXText.setVisible(false);
        endYInput.setVisible(false);
        endYText.setVisible(false);
        heightInput.setVisible(false);
        heightText.setVisible(false);
        widthInput.setVisible(false);
        widthText.setVisible(false);
        rInput.setVisible(false);
        rText.setVisible(false);
        aInput.setVisible(false);
        aText.setVisible(false);
        bInput.setVisible(false);
        bText.setVisible(false);
        if (nowPicture == null) {
            nameInput.setText("");
            kindInput.setText("");
            baseXInput.setText("");
            baseYInput.setText("");
            strokeInput.setText("");
            return;
        }

        switch (nowPicture.getPictureKind()) {
            case 1 -> {
                endXInput.setText(String.valueOf(((Line) nowPicture).getEndX()));
                endXInput.setVisible(true);
                endXText.setVisible(true);
                endYInput.setText(String.valueOf(((Line) nowPicture).getEndY()));
                endYInput.setVisible(true);
                endYText.setVisible(true);
            }
            case 2 -> {
                widthInput.setText(String.valueOf(((Rectangle) nowPicture).getWidth()));
                widthInput.setVisible(true);
                widthText.setVisible(true);

                heightInput.setText(String.valueOf(((Rectangle) nowPicture).getHeight()));
                heightInput.setVisible(true);
                heightText.setVisible(true);
            }
            case 3 -> {
                rInput.setText(String.valueOf(((Circle) nowPicture).getR()));
                rInput.setVisible(true);
                rText.setVisible(true);
            }
            case 4 -> {
                aInput.setText(String.valueOf(((Ellipse) nowPicture).getA()));
                aInput.setVisible(true);
                aText.setVisible(true);
                bInput.setText(String.valueOf(((Ellipse) nowPicture).getB()));
                bInput.setVisible(true);
                bText.setVisible(true);
            }
            case 6 -> {
            }
        }
        updateSetting();
    }


    public void updateSetting() {
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
        strokeInput.setText(String.valueOf(nowPicture.getStroke().getLineWidth()));

        colorRText.setText("R:" + nowPicture.getColor().getRed());
        colorGText.setText("G:" + nowPicture.getColor().getGreen());
        colorBText.setText("B:" + nowPicture.getColor().getBlue());
        ignoreColorChange = true;
        colorRInput.setValue(nowPicture.getColor().getRed());
        colorGInput.setValue(nowPicture.getColor().getGreen());
        colorBInput.setValue(nowPicture.getColor().getBlue());
        ignoreColorChange = false;
        mainWindow.pagePane.pageEditPane.refresh();
    }
}
