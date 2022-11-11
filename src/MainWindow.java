import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainWindow {
    /**
     * 主界面框架
     */
    static JFrame mainFrame;

    /**
     * 当前页面
     */
    static Page nowPage = new Page();
    /**
     * 绘画监听器
     */
    static DrawListener drawListener = new DrawListener();

    MainWindow() {


        // 窗口标题
        mainFrame = new JFrame("Picture Point 2077");

        // 窗口图标
        ImageIcon imageIcon = new ImageIcon("./src/Resource/PicturePoint2077.png");
        mainFrame.setIconImage(imageIcon.getImage());

        // 获取当前屏幕大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // 设置窗口大小为1/4屏幕大小
        mainFrame.setSize(screenSize.width / 2, screenSize.height / 2);
        // 设置居中
        mainFrame.setLocation(screenSize.width / 4, screenSize.height / 4);
        // 设置默认关闭
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 空界面
        mainFrame.add(nowPage);


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

        String menuTitle = null;
        String[] subMenuTitle = null;

        // 文件菜单
        menuTitle = "文件";
        subMenuTitle = new String[]{"新建", "打开", "保存", "另存为"};
        menuBar.add(creatMenu(menuTitle, subMenuTitle));

        // 绘制菜单
        menuTitle = "图像";
        subMenuTitle = new String[]{"直线画笔", "矩形画笔", "圆形画笔", "椭圆画笔", "自由线画笔",};
        menuBar.add(creatMenu(menuTitle, subMenuTitle));

        // 操作菜单
        menuTitle = "操作";
        subMenuTitle = new String[]{"撤销", "重做", "-", "新建图形", "新建文字", "-", "设置画笔"};
        menuBar.add(creatMenu(menuTitle, subMenuTitle));

        // 查看菜单
        menuTitle = "查看";

        subMenuTitle = new String[]{};
        menuBar.add(creatMenu(menuTitle, subMenuTitle));

        // 设置菜单
        menuTitle = "设置";
        subMenuTitle = new String[]{};
        menuBar.add(creatMenu(menuTitle, subMenuTitle));

        mainFrame.setJMenuBar(menuBar);
    }

    /**
     * 根据输入的子项创建一个创建目录
     *
     * @param title        目录名
     * @param subMenuTitle 子目录名，‘-’表示分隔符
     * @return 生成的目录
     */
    JMenu creatMenu(String title, String[] subMenuTitle) {
        JMenu menu = new JMenu(title);
        for (String subMenu : subMenuTitle) {
            if (subMenu.equals("-"))
                menu.addSeparator();
            else {
                JMenuItem fileCrate = new JMenuItem(subMenu);
                fileCrate.addActionListener(drawListener);
                menu.add(fileCrate);
            }
        }
        return menu;
    }

    /**
     * 配置监听器
     */
    void setListener() {
        drawListener.setPage(nowPage);
        mainFrame.addMouseListener(drawListener);
        mainFrame.addMouseMotionListener(drawListener);
    }

    public void showMainWindow() {
        mainFrame.setVisible(true);
    }

}
