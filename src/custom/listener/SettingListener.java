package custom.listener;

import custom.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 控制主界面中不同控制面板的显示
 */
public class SettingListener implements ActionListener {

    MainWindow mainWindow;

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.print("选中" + e.getActionCommand());
        // 画笔设置
        switch (e.getActionCommand()) {
            case "精细颜色设置" -> mainWindow.setColorSettingBarVisible(true);

//            default -> drawMode = 0;
        }
    }
}
