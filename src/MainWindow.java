import javax.swing.*;
import java.awt.*;

public class MainWindow {
    /**
     * 主界面框架
     */
    static JFrame mainFrame;

    /**
     * 当前页面
     */
    static Page nowPage = new Page();



    MainWindow() {
        // 窗口标题
        mainFrame = new JFrame("Picture Point 2077");
        // 窗口位置
        mainFrame.setLocation(800, 300);
        // 窗口大小
        mainFrame.setSize(900, 600);
        // 空界面
        mainFrame.add(nowPage);
        //
        // 窗口菜单栏
        setMenu();
        // 监听器
        setListener();
        //隐藏标题栏
        //mainFrame.setUndecorated(true);
    }

    /**
     * 配置菜单栏
     */
    private void setMenu() {
        JMenuBar menuBar = new JMenuBar();

        // 文件菜单
        JMenu fileMenu = new JMenu("文件");

        JMenuItem fileCrate = new JMenuItem("新建");
        fileMenu.add(fileCrate);
        JMenuItem fileOpen = new JMenuItem("打开");
        fileMenu.add(fileOpen);
        JMenuItem fileSave = new JMenuItem("保存");
        fileMenu.add(fileSave);
        JMenuItem fileMenuSaveAs = new JMenuItem("另存为");
        fileMenu.add(fileMenuSaveAs);


        menuBar.add(fileMenu);

        // 绘制菜单
        JMenu drawMenu = new JMenu("图像");

        JMenuItem drawSetPencil = new JMenuItem("设置画笔");
        drawMenu.add(drawSetPencil);
        menuBar.add(drawMenu);

        // 操作菜单
        JMenu operationMenu = new JMenu("图像");
        JMenuItem operationUndo = new JMenuItem("撤销");
        operationMenu.add(operationUndo);
        JMenuItem operationRedo = new JMenuItem("重做");
        operationMenu.add(operationRedo);
        operationMenu.addSeparator();
        JMenuItem operationCratePic = new JMenuItem("新建图形");
        operationMenu.add(operationCratePic);
        JMenuItem operationCrateText = new JMenuItem("新建文字");
        operationMenu.add(operationCrateText);
        operationMenu.addSeparator();
        JMenuItem operationSetPencil = new JMenuItem("设置画笔");
        operationMenu.add(operationSetPencil);
        menuBar.add(operationMenu);

        // 查看菜单
        JMenu viewMenu = new JMenu("查看");

        menuBar.add(viewMenu);


        // 设置菜单
        JMenu settingMenu = new JMenu("设置");

        menuBar.add(settingMenu);

        mainFrame.setJMenuBar(menuBar);

    }

    /**
     * 配置监听器
     */
    void setListener() {
        DrawListener drawListener = new DrawListener();
        drawListener.setPage(nowPage);
        mainFrame.addMouseListener(drawListener);
        mainFrame.addMouseMotionListener(drawListener);
    }

    public void showMainWindow() {

        mainFrame.setVisible(true);
    }

}
