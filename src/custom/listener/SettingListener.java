package custom.listener;

import custom.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    }
}
