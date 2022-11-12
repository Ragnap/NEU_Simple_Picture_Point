import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

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
    static Painter painter = new Painter();

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
                "直线画笔", "矩形画笔", "圆形画笔", "椭圆画笔", "自由线画笔",
                "-",
                "颜色设置:", "黑色", "红色", "黄色", "蓝色", "绿色", "<-",
                "精细颜色设置", "大小设置"};
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
                menu.add(subMenu);
            }
        }
        return menu;
    }

    /**
     * 配置监听器
     */
    void setListener() {
        painter.setPage(nowPage);
        mainFrame.addMouseListener(painter);
        mainFrame.addMouseMotionListener(painter);
    }

    public void showMainWindow() {
        mainFrame.setVisible(true);
    }

}
