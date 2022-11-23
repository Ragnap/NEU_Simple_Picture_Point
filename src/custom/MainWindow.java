package custom;

import custom.file.PicturePointFile;
import custom.panel.PageEditPane;
import custom.panel.PagePane;
import custom.panel.PagePreviewPane;
import custom.panel.ToolBarPane;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    /**
     * 主界面框架
     */
    JFrame mainFrame = new JFrame("Picture Point 2077");
    /**
     * 主页面
     */
    JSplitPane mainPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    /**
     * 上部设置工具栏
     */
    public ToolBarPane toolBarPane;
    /**
     * 下部页面栏
     */
    public PagePane pagePane;
    /**
     * 下左所有页面预览列表
     */
    PagePreviewPane pagePreviewPane = new PagePreviewPane();
    /**
     * 下右当前编辑页面
     */
    PageEditPane pageEditPane = new PageEditPane();

    public MainWindow() {
        // 设置窗口图标
        mainFrame.setIconImage(new ImageIcon("./src/Resource/PicturePoint2077.png").getImage());
        // 获取当前屏幕大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = new Dimension(screenSize.width / 2, screenSize.height * 2 / 3);
        // 设置窗口
        mainFrame.setSize(windowSize);
        // 设置居中
        mainFrame.setLocation((screenSize.width - windowSize.width) / 2, (screenSize.height - windowSize.height) / 2);
        // 设置默认关闭
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置主界面
        mainFrame.setContentPane(mainPane);
        // 主界面大小等同于窗口
        mainPane.setSize(mainFrame.getSize());

        // 工具栏大小设置为窗口宽度*100
        toolBarPane = new ToolBarPane(windowSize.width, 110,this);
        // 上半部分为工具栏
        mainPane.setLeftComponent(toolBarPane);
        // 设置工具栏高度为100
        mainPane.setDividerLocation(toolBarPane.getHeight());
        // 设置工具栏分界线宽度为1
        mainPane.setDividerSize(1);
        // 设置不可移动工具栏的分界线
        mainPane.setEnabled(false);

        // 下半部分为幻灯片部分，由左右两个部分构成
        pagePane = new PagePane(windowSize.width, windowSize.height - toolBarPane.getHeight());
        mainPane.setRightComponent(pagePane);
    }

    public void showMainWindow() {
        mainFrame.setVisible(true);
    }
}