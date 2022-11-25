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

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public FilePageBar(Dimension size) {
        baseSetting(size);
        GridBagLayout layout = (GridBagLayout) this.getLayout();

        size = new Dimension(size.width / 5, size.height / 4);
        // 新建按钮 1*1
        createButton = new JButton("插入新页");
        createButton.setSize(size);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.pagePane.insertNewPageAfter();
            }
        });
        this.add(createButton);
        layout.setConstraints(createButton, buildConstraints(0, 0, 1, 1, new Insets(10, 10, 5, 5), 1));
        // 删除按钮 1*1
        deleteButton = new JButton("删除当前页");
        deleteButton.setSize(size);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.pagePane.deletePage();
            }
        });
        this.add(deleteButton);
        layout.setConstraints(deleteButton, buildConstraints(0, 1, 1, 1, new Insets(5, 10, 10, 5), 1));
        // 上移按钮
        moveUpButton = new JButton("前移当前页");
        moveUpButton.setSize(size);
        moveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.pagePane.moveUpPage();
            }
        });
        this.add(moveUpButton);
        layout.setConstraints(moveUpButton, buildConstraints(1, 0, 1, 1, new Insets(10, 5, 5, 5), 1));
        // 下移按钮
        moveDownButton = new JButton("后移当前页");
        moveDownButton.setSize(size);
        moveDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.pagePane.moveDownPage();
            }
        });
        this.add(moveDownButton);
        layout.setConstraints(moveDownButton, buildConstraints(1, 1, 1, 1, new Insets(5, 5, 10, 5), 1));
        // 上一页按钮
        previousButton = new JButton("上一页");
        previousButton.setSize(size);
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.pagePane.previousPage();
            }
        });
        this.add(previousButton);
        layout.setConstraints(previousButton, buildConstraints(2, 0, 1, 1, new Insets(10, 5, 5, 10), 1));
        // 下一页按钮
        nextButton = new JButton("下一页");
        nextButton.setSize(size);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.pagePane.nextPage();
            }
        });
        this.add(nextButton);
        layout.setConstraints(nextButton, buildConstraints(2, 1, 1, 1, new Insets(5, 5, 10, 10), 1));

    }
}
