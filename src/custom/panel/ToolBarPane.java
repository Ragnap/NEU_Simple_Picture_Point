package custom.panel;

import custom.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 顶部工具栏
 */
public class ToolBarPane extends JSplitPane {
    /**
     * 主窗口
     */
    MainWindow mainWindow = null;

    /**
     * 上部菜单选项栏
     */
    JLayeredPane menuPane = new JLayeredPane();

    /**
     * 文件设置栏
     */
    JPanel fileSettingPane = new JPanel();
    /**
     * 图像设置栏
     */
    JPanel pictureSettingPane = new JPanel();
    /**
     * 操作设置栏
     */
    JPanel operationSettingPane = new JPanel();
    /**
     * 查看设置栏
     */
    JPanel viewSettingPane = new JPanel();

    public ToolBarPane(int width, int height, MainWindow mainWindow) {
        //给工具栏传主页面方便读取数据
        this.mainWindow = mainWindow;

        // 工具栏大小
        this.setSize(width, height);
        // 背景颜色
        this.setBackground(Color.LIGHT_GRAY);
        // 竖向面板
        this.setOrientation(JSplitPane.VERTICAL_SPLIT);
        // 设置菜单栏高度为20
        this.setDividerLocation(20);
        // 设置菜单栏分界线宽度为0
        this.setDividerSize(0);
        // 设置不可移动菜单栏的分界线
        this.setEnabled(false);
        // 上部分为选项菜单
        this.setTopComponent(menuPane);


        buildMenu();

        // 下部分对应多个设置栏

        buildFileSettingPane();
        buildPictureSettingPane();
        buildOperationSettingPane();
        buildViewSettingPane();

        // 默认时设置栏打开文件菜单
        this.setBottomComponent(fileSettingPane);
    }

    /**
     * 构建设置的菜单栏面板
     */
    void buildMenu() {
        // 菜单宽度为20
        menuPane.setSize(this.getWidth(), 20);
        // 菜单布局为网格布局,间隔为0
        menuPane.setLayout(new GridLayout(1, 8, 0, 0));
        // 按钮切换监听,匿名内部类
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("打开" + e.getActionCommand() + "选项菜单");
                int dividerLocation = getDividerLocation();
                switch (e.getActionCommand()) {
                    case "文件" -> setBottomComponent(fileSettingPane);
                    case "图像" -> setBottomComponent(pictureSettingPane);
                    case "操作" -> setBottomComponent(operationSettingPane);
                    case "查看" -> setBottomComponent(viewSettingPane);
                }
                setDividerLocation(dividerLocation);

            }
        };
        // 不同菜单选项为透明无边框按钮
        JButton fileMenuButton = new JButton("文件");
        fileMenuButton.setContentAreaFilled(false);
        fileMenuButton.setBorder(null);
        fileMenuButton.addActionListener(actionListener);
        menuPane.add(fileMenuButton);

        JButton pictureMenuButton = new JButton("图像");
        pictureMenuButton.setContentAreaFilled(false);
        pictureMenuButton.setBorder(null);
        pictureMenuButton.addActionListener(actionListener);
        menuPane.add(pictureMenuButton);

        JButton operationMenuButton = new JButton("操作");
        operationMenuButton.setContentAreaFilled(false);
        operationMenuButton.setBorder(null);
        operationMenuButton.addActionListener(actionListener);
        menuPane.add(operationMenuButton);

        JButton viewMenuButton = new JButton("查看");
        viewMenuButton.setContentAreaFilled(false);
        viewMenuButton.setBorder(null);
        viewMenuButton.addActionListener(actionListener);
        menuPane.add(viewMenuButton);
    }

    /**
     * 构建文件设置面板
     */
    void buildFileSettingPane() {
        Dimension dimension = new Dimension(this.getWidth(), this.getWidth() - getDividerLocation() - getDividerSize() - 2);
        fileSettingPane.setSize(dimension);
        dimension = new Dimension(this.getWidth() - 5, this.getWidth() - getDividerLocation() - getDividerSize() - 2);
        // 文件操作面板
        FileSaveLoadBar fileSaveLoadBar = new FileSaveLoadBar(dimension);
        fileSaveLoadBar.setMainWindow(mainWindow);
        fileSettingPane.add(fileSaveLoadBar);
    }

    /**
     * 构建图片设置面板
     */
    void buildPictureSettingPane() {
        Dimension dimension = new Dimension(this.getWidth(), this.getWidth() - getDividerLocation() - getDividerSize() - 2);
        pictureSettingPane.setSize(dimension);
        dimension = new Dimension(this.getWidth() - 5, this.getWidth() - getDividerLocation() - getDividerSize() - 2);
        // 绘制模式控制栏
        DrawModeSettingBar drawModeSettingBar = new DrawModeSettingBar(dimension);
        drawModeSettingBar.setPaintListener(PageEditPane.drawListener);
        pictureSettingPane.add(drawModeSettingBar);
        // 颜色控制栏
        DrawColorSettingBar drawColorSettingBar = new DrawColorSettingBar(dimension);
        drawColorSettingBar.setPaintListener(PageEditPane.drawListener);
        pictureSettingPane.add(drawColorSettingBar);
        // 粗细控制栏
        DrawStrokeSettingBar drawStrokeSettingBar = new DrawStrokeSettingBar(dimension);
        drawStrokeSettingBar.setPaintListener(PageEditPane.drawListener);
        pictureSettingPane.add(drawStrokeSettingBar);
    }

    /**
     * 构建操作设置面板
     */
    void buildOperationSettingPane() {
        operationSettingPane.setSize(new Dimension(this.getWidth(), this.getWidth() - getDividerLocation() - getDividerSize() - 2));
    }

    /**
     * 构建查看设置面板
     */
    void buildViewSettingPane() {
        viewSettingPane.setSize(new Dimension(this.getWidth(), this.getWidth() - getDividerLocation() - getDividerSize() - 2));
    }


}
