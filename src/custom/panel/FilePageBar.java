package custom.panel;

import custom.MainWindow;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FilePageBar extends BaseBar {
    /**
     * 当前页数
     */
    JLabel nowPageIndexValue = new JLabel("1 / 1");
    /**
     * 新建按钮
     */
    JButton createButton;
    /**
     * 删除当前页按钮
     */
    JButton deleteButton;
    /**
     * 当前页上移按钮
     */
    JButton moveUpButton;
    /**
     * 当前页下移按钮
     */
    JButton moveDownButton;
    /**
     * 下一页按钮
     */
    JButton nextButton;
    /**
     * 上一页按钮
     */
    JButton previousButton;
    /**
     * 当前窗口界面
     */
    MainWindow mainWindow = null;


    public FilePageBar(MainWindow mainWindow, Dimension size) {
        baseSetting(size);
        this.mainWindow = mainWindow;
        GridBagLayout layout = (GridBagLayout) this.getLayout();

        size = new Dimension(size.width / 5, size.height / 4);
        //当前页数提示文字2*1
        JLabel nowPageIndexText = new JLabel("当前页数");
        this.add(nowPageIndexText);
        nowPageIndexText.setFont(new Font("宋体", Font.BOLD, 12));
        nowPageIndexText.setPreferredSize(new Dimension(50, 12));
        layout.setConstraints(nowPageIndexText, buildConstraints(0, 0, 2, 1, new Insets(2, 5, 0, 5), 1));

        //当前页数值
        this.add(nowPageIndexValue);
        nowPageIndexValue.setFont(new Font("黑体", Font.BOLD, 15));
        nowPageIndexValue.setPreferredSize(new Dimension(50, 20));
        layout.setConstraints(nowPageIndexValue, buildConstraints(0, 1, 2, 1, new Insets(2, 10, 0, 5), 1));


        // 新建按钮 1*1
        createButton = new JButton("插入新页");
        createButton.setSize(size);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.pagePane.insertNewPageAfter();
                updateView();
            }
        });
        this.add(createButton);
        layout.setConstraints(createButton, buildConstraints(2, 0, 1, 1, new Insets(10, 10, 5, 5), 1));
        // 删除按钮 1*1
        deleteButton = new JButton("删除当前页");
        deleteButton.setSize(size);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.pagePane.deletePage();
                updateView();
            }
        });
        this.add(deleteButton);
        layout.setConstraints(deleteButton, buildConstraints(2, 1, 1, 1, new Insets(5, 10, 10, 5), 1));
        // 上移按钮
        moveUpButton = new JButton("前移当前页");
        moveUpButton.setSize(size);
        moveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.pagePane.moveUpPage();
                updateView();
            }
        });
        this.add(moveUpButton);
        layout.setConstraints(moveUpButton, buildConstraints(3, 0, 1, 1, new Insets(10, 5, 5, 5), 1));
        // 下移按钮
        moveDownButton = new JButton("后移当前页");
        moveDownButton.setSize(size);
        moveDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.pagePane.moveDownPage();
                updateView();
            }
        });
        this.add(moveDownButton);
        layout.setConstraints(moveDownButton, buildConstraints(3, 1, 1, 1, new Insets(5, 5, 10, 5), 1));
        // 上一页按钮
        previousButton = new JButton("上一页");
        previousButton.setSize(size);
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.pagePane.previousPage();
                updateView();
            }
        });
        this.add(previousButton);
        layout.setConstraints(previousButton, buildConstraints(4, 0, 1, 1, new Insets(10, 5, 5, 10), 1));
        // 下一页按钮
        nextButton = new JButton("下一页");
        nextButton.setSize(size);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.pagePane.nextPage();
                updateView();
            }
        });
        this.add(nextButton);
        layout.setConstraints(nextButton, buildConstraints(4, 1, 1, 1, new Insets(5, 5, 10, 10), 1));

    }

    public void updateView() {
        int allPage = mainWindow.pagePane.getNowFile().getSize();
        int nowPage = mainWindow.pagePane.getNowPageIndex()+1;
        nowPageIndexValue.setText(nowPage + " / " + allPage);
    }
}
