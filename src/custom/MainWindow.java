package custom;

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
    ToolBarPane toolBarPane;
    /**
     * 下部页面栏
     */
    JSplitPane pageEditPane;
    /**
     * 下左所有页面预览列表
     */
    PagePreviewPane pagePreviewPane = new PagePreviewPane();
    /**
     * 下右当前编辑页面
     */
    PagePane pagePane = new PagePane();


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
        toolBarPane = new ToolBarPane(windowSize.width, 110);
        // 提供绘制界面给工具栏，方便数据修改
        toolBarPane.setPagePane(pagePane);
        // 上半部分为工具栏
        mainPane.setLeftComponent(toolBarPane);

        // 设置工具栏高度为100
        mainPane.setDividerLocation(toolBarPane.getHeight());
        // 设置工具栏分界线宽度为1
        mainPane.setDividerSize(1);
        // 设置不可移动工具栏的分界线
        mainPane.setEnabled(false);

        // 下半部分为幻灯片部分，由左右两个部分构成
        pageEditPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pagePreviewPane, pageEditPane);
        mainPane.setRightComponent(pageEditPane);
        // 大小为减去工具栏高度100后的剩余部分
        pageEditPane.setSize(windowSize.width, windowSize.height - toolBarPane.getHeight());
        // 幻灯片部分分界线可调
        pageEditPane.setEnabled(true);
        // 调整分界线时重绘
        pageEditPane.setContinuousLayout(true);
        // 默认分界线位置
        pageEditPane.setDividerLocation(0.1);

        // 下左部分为所有页面的预览
        pageEditPane.setLeftComponent(pagePreviewPane);
        // 下右部分为单个界面的编辑
        pageEditPane.setRightComponent(pagePane);

    }


    private void setMenu() {
        String menuTitle = null;
        String[] subMenuTitle = null;

        // 文件菜单
        menuTitle = "文件";
        subMenuTitle = new String[]{
                "新建",
                "打开",
                "保存",
                "另存为"
        };

        menuTitle = "图像";
        subMenuTitle = new String[]{
                "直线画笔",
                "矩形画笔",
                "圆形画笔",
                "椭圆画笔",
                "自由线画笔",
                "-",
                "颜色设置:", "黑色", "红色", "黄色", "蓝色", "绿色", "<-",
                "精细颜色设置",
                "大小设置"};

        menuTitle = "操作";
        subMenuTitle = new String[]{
                "撤销",
                "重做",
                "-",
                "新建图形",
                "新建文字",
                "-",
                "设置画笔"};

        menuTitle = "查看";
        subMenuTitle = new String[]{};

        menuTitle = "设置";
        subMenuTitle = new String[]{};

    }


    public void showMainWindow() {
        mainFrame.setVisible(true);

    }

}