import custom.panel.ColorSettingBar;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class MainWindow {
    /**
     * 主界面框架
     */
    JFrame mainFrame= new JFrame("Picture Point 2077");
    /**
     * 主页面,JLayeredPane提供覆盖的高低控制
     */
    JLayeredPane mainWindow = new JLayeredPane();
    /**
     * 当前页面
     */
    Page nowPage = new Page();
    /**
     * 设置面板显示监听器
     */
    SettingListener settingListener = new SettingListener();
    /**
     * 绘画监听器
     */
    Painter painter = new Painter();

    /**
     * 颜色设置工具栏
     */
    ColorSettingBar colorSettingBar= new ColorSettingBar();


    MainWindow() {

        // 窗口图标
        ImageIcon imageIcon = new ImageIcon("./src/Resource/PicturePoint2077.png");
        mainFrame.setIconImage(imageIcon.getImage());
        mainFrame.add(mainWindow);

        // 获取当前屏幕大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // 设置窗口大小为1/4屏幕大小
        mainFrame.setSize(screenSize.width / 2, screenSize.height / 2);
        // 设置居中
        mainFrame.setLocation(screenSize.width / 4, screenSize.height / 4);
        // 设置默认关闭
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 隐藏标题栏
        //mainFrame.setUndecorated(true);

        // 菜单显示监听
        settingListener.setMainWindow(this);

        // 窗口菜单栏
        setMenu();

        // 颜色状态设置栏


        // 默认不显示工具栏
        hideAllSettingPanel();

        // 当前绘制页
        nowPage.setSize(mainFrame.getSize());


        // 画笔
        painter.setPage(nowPage);
        painter.setMainWindow(this);
        mainWindow.addMouseListener(painter);
        mainWindow.addMouseMotionListener(painter);



        mainWindow.setSize(screenSize.width / 2,screenSize.height / 2);
        mainWindow.setLayout(null);
        mainWindow.add(nowPage,JLayeredPane.DEFAULT_LAYER);
        mainWindow.add(colorSettingBar,JLayeredPane.MODAL_LAYER);

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
        subMenuTitle = new String[]{
                "新建",
                "打开",
                "保存",
                "另存为"
        };
        menuBar.add(creatMenu(menuTitle, subMenuTitle));

        // 绘制菜单
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
        menuBar.add(creatMenu(menuTitle, subMenuTitle));

        // 操作菜单
        menuTitle = "操作";
        subMenuTitle = new String[]{
                "撤销",
                "重做",
                "-",
                "新建图形",
                "新建文字",
                "-",
                "设置画笔"};
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
     * @param title         目录名
     * @param subMenuTitles 子目录名,‘-’表示分隔符,目录名后接':'表示二级子目录开始,单独的'<-'表示二级子目录结束
     * @return 生成的目录
     */
    JMenu creatMenu(String title, String[] subMenuTitles) {
        JMenu menu = new JMenu(title);
        for (int i = 0; i < subMenuTitles.length; i++) {
            String subMenuTitle = subMenuTitles[i];
            if (subMenuTitle.equals("-")) {
                menu.addSeparator();
            }
            //检测目录名后两位
            else if ((subMenuTitle.length() > 2) && (subMenuTitle.substring(subMenuTitle.length() - 1)).equals(":")) {
                //构建二级目录
                int fin = i + 1;
                while (fin < subMenuTitles.length && !subMenuTitles[fin].equals("<-")) {
                    fin++;
                }
                String[] secondSubMenuTitles = Arrays.copyOfRange(subMenuTitles, i + 1, fin);
                //二级目录加入到当前目录
                menu.add(creatMenu(subMenuTitle.substring(0, subMenuTitle.length() - 1), secondSubMenuTitles));
                //跳过对应的二级目录项
                i = fin;
            }
            //正常目录
            else {
                JMenuItem subMenu = new JMenuItem(subMenuTitle);
                subMenu.addActionListener(painter);
                subMenu.addActionListener(settingListener);
                menu.add(subMenu);
            }
        }
        return menu;
    }


    /**
     * 隐藏所有工具栏
     */
    public void hideAllSettingPanel(){
        colorSettingBar.setVisible(false);
    }
    /**
     * 颜色设置工具栏可见性
     */
    public void setColorSettingBarVisible(Boolean state){
        colorSettingBar.setVisible(state);

    }
    public void showMainWindow() {
        mainFrame.setVisible(true);

    }

}
