package custom.panel;

import custom.MainWindow;
import custom.picture.Picture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class FileFindBar extends BaseBar {
    /**
     * 筛选结果框
     */
    JScrollPane resultPane = new JScrollPane();

    /**
     * 当前窗口界面
     */
    MainWindow mainWindow = null;

    /**
     * 当前种类
     */
    int kind;

    /**
     * 当前关键字
     */
    String keyword;

    /**
     * 选取结果
     */
    ArrayList<Picture> result;
    /**
     * 筛选结果选择下拉列表
     */
    JComboBox<String> resultChooser;

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public FileFindBar(Dimension size) {
        baseSetting(size);
        GridBagLayout layout = (GridBagLayout) this.getLayout();

        // 类别提示文字 5*1
        JLabel previewText = new JLabel("选择类别");
        this.add(previewText);
        previewText.setFont(new Font("宋体", Font.BOLD, 12));
        previewText.setPreferredSize(new Dimension(50, 12));
        layout.setConstraints(previewText, buildConstraints(0, 0, 4, 1, new Insets(2, 10, 2, 10)));

        // 类别下拉框
        String[] kinds = {"不指定", "直线", "矩形", "圆形", "椭圆", "自由线", "文本"};
        JComboBox<String> kindChooser = new JComboBox<>(kinds);
        this.add(kindChooser);
        kindChooser.setPreferredSize(new Dimension(100, 20));
        kindChooser.setFont(new Font("宋体", Font.PLAIN, 12));
        kindChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("查找种类:" + kindChooser.getSelectedItem());
                kind = switch ((String)kindChooser.getSelectedItem()) {
                    case "直线" -> 1;
                    case "矩形" -> 2;
                    case "圆形" -> 3;
                    case "椭圆" -> 4;
                    case "自由线" -> 5;
                    case "文本" -> 6;
                    default -> 0;
                };
                refreshResultChooser();
            }
        });
        layout.setConstraints(kindChooser, buildConstraints(0, 1, 4, 1, new Insets(0, 10, 0, 10)));

        // 类别提示文字 5*1
        JLabel findText = new JLabel("名称搜索");
        this.add(findText);
        findText.setFont(new Font("宋体", Font.BOLD, 12));
        findText.setPreferredSize(new Dimension(50, 12));
        layout.setConstraints(findText, buildConstraints(0, 2, 4, 1, new Insets(2, 10, 2, 10)));

        // 文字输入框
        JTextField inputKeyword = new JTextField();
        this.add(inputKeyword);
        inputKeyword.setPreferredSize(new Dimension(100, 20));
        inputKeyword.setFont(new Font("宋体", Font.PLAIN, 12));
        inputKeyword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("查找关键字:" + inputKeyword.getText());
                keyword = inputKeyword.getText();
                refreshResultChooser();
            }
        });
        layout.setConstraints(inputKeyword, buildConstraints(0, 3, 4, 1, new Insets(0, 10, 0, 10)));

        // 搜索结果提示文字 5*1
        JLabel resultChooseText = new JLabel("搜索结果");
        this.add(resultChooseText);
        resultChooseText.setFont(new Font("宋体", Font.BOLD, 12));
        resultChooseText.setPreferredSize(new Dimension(50, 12));
        layout.setConstraints(resultChooseText, buildConstraints(4, 1, 4, 1, new Insets(2, 10, 2, 10)));

        // 筛选结果列表
        resultChooser = new JComboBox<>();
        this.add(resultChooser);
        resultChooser.setPreferredSize(new Dimension(150, 20));
        resultChooser.setFont(new Font("宋体", Font.PLAIN, 12));
        resultChooser.setEnabled(false);
        resultChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.pagePane.pageEditPane.selectPicture(result.get(resultChooser.getSelectedIndex()));
            }
        });
        layout.setConstraints(resultChooser, buildConstraints(4, 2, 4, 1, new Insets(0, 10, 5, 10)));
    }

    private void refreshResultChooser() {
        result = mainWindow.pagePane.pageEditPane.findPicture(kind, keyword);
        System.out.println("当前筛选的是" + kind + " 查找名称为" + keyword);

        Vector<String> resultList = new Vector<>();
        if (result.isEmpty()) {
            resultChooser.setEnabled(false);
            resultList.add("无对应搜索结果");
        } else {
            resultChooser.setEnabled(true);
            for (Picture nowPicture : result)
                resultList.add(nowPicture.getName());
        }
        resultChooser.setModel(new DefaultComboBoxModel<String>(resultList));
    }
}
