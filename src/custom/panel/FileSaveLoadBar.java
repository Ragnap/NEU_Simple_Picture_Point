package custom.panel;

import custom.MainWindow;
import custom.file.Page;
import custom.picture.Picture;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class FileSaveLoadBar extends BaseBar {
    /**
     * 新建按钮
     */
    JButton createButton;
    /**
     * 保存按钮
     */
    JButton saveButton;
    /**
     * 打开按钮
     */
    JButton loadButton;
    /**
     * 另存为按钮
     */
    JButton saveAsButton;
    /**
     * 文件选择器
     */
    JFileChooser fileChooser;
    /**
     * 保存的文件名
     */
    String filePath;
    /**
     * 当前窗口界面
     */
    MainWindow mainWindow = null;

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public FileSaveLoadBar(Dimension size) {
        baseSetting(size);
        // 创建文件选择器
        fileChooser = new JFileChooser();
        // 设置默认选择的文件为当前文件夹下的默认文件名
        fileChooser.setCurrentDirectory(new File("./"));
        // 设置文件选择的模式为只选文件
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        // 设置不可多选
        fileChooser.setMultiSelectionEnabled(false);
        // 设置默认使用的文件过滤器
        fileChooser.setFileFilter(new FileNameExtensionFilter("Picture Point File(*.ptt)", "ptt"));


        // 新建按钮
        createButton = new JButton("新建");
        createButton.setSize(size);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPage();
            }
        });
        this.add(createButton);
        // 保存按钮
        saveButton = new JButton("保存");
        saveButton.setSize(size);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePage();
            }
        });
        this.add(saveButton);
        // 打开按钮
        loadButton = new JButton("打开");
        loadButton.setSize(size);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPage();
            }
        });
        this.add(loadButton);
        // 另存为按钮
        saveAsButton = new JButton("另存为");
        saveAsButton.setSize(size);
        saveAsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAsPage();
            }
        });
        this.add(saveAsButton);
    }

    /**
     * 获取未使用的默认格式的文件名
     *
     * @return 未使用的默认格式文件名
     */
    public String getDefaultFileName() {
        //文件前缀
        String filePrefix = "./Temp_PicturePoint_File/PicturePointFile";
        //文件后缀
        String fileSuffix = ".ptt";
        //默认文件编号从1开始
        int defaultID = 1;
        //完整文件名
        String defaultFileName = filePrefix + defaultID + fileSuffix;
        //当前文件
        File file = new File(defaultFileName);
        //寻找未使用的编号
        while (file.exists()) {
            defaultID++;
            defaultFileName = filePrefix + defaultID + fileSuffix;
            file = new File(defaultFileName);
        }
        return defaultFileName;
    }

    /**
     * 通过文件选取器获取文件保存路径与文件名到filepath中
     */
    public void chooseFilePath(String title) {
        // 打开文件选择框（线程将被阻塞, 直到选择框被关闭）
        int result = fileChooser.showDialog(mainWindow.getPagePane(), title);
        // 选择了新文件
        if (result == JFileChooser.APPROVE_OPTION) {
            // 如果点击了"确定", 则获取选择的文件路径
            filePath = fileChooser.getSelectedFile().getAbsolutePath();

            System.out.println("打开文件" + filePath);
        }
        // 不选择时使用默认名
        else {
            filePath = getDefaultFileName();
        }
    }

    /**
     * 打开新的文件
     */
    public void createPage() {

        // 未指定主窗口时返回
        if (mainWindow == null) {
            System.out.println("未设置主窗口!");
            return;
        }
        // 主窗口未指定绘图页面时返回
        if (mainWindow.getPagePane() == null) {
            System.out.println("未设置主页面!");
            return;
        }
        // 已经打开了已有文件,进行一次保存
        if (filePath != null) {
            savePage();
        }
        // 打开新文件的路径
        chooseFilePath("创建");

        try {
            File file = new File(filePath);
            // 已存在对应文件时弹出提醒
            if (file.exists()) {
                System.out.println("存在已有文件！");

                int choose = JOptionPane.showConfirmDialog(mainWindow.getPagePane(), "已存在对应的文件，是否覆盖？", "已存在对应文件", JOptionPane.OK_CANCEL_OPTION);
                if (choose == 0) {
                    if (file.delete()) {
                        System.out.println("成功覆盖原有文件！");
                    } else {
                        System.out.println("删除原有文件失败！");
                        return;
                    }
                } else {
                    return;
                }
            }
            // 创建文件
            if (!file.createNewFile()) {
                System.out.println("创建文件失败!");
                return;
            }
            System.out.println("成功新建" + filePath);
            mainWindow.getPagePane().clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存当前页到文件
     */
    public void savePage() {
        // 未指定主窗口时返回
        if (mainWindow == null) {
            System.out.println("未设置主窗口!");
            return;
        }
        // 主窗口未指定绘图页面时返回
        if (mainWindow.getPagePane() == null) {
            System.out.println("未设置主页面!");
            return;
        }

        //打开文件
        try {
            File file = null;
            // 未指定文件名时等同于另存为
            if (filePath == null) {
                chooseFilePath("保存");
                file = new File(filePath);
                // 已存在对应文件时弹出提醒
                if (file.exists()) {
                    System.out.println("存在已有文件！");
                    int choose = JOptionPane.showConfirmDialog(mainWindow.getPagePane(), "已存在对应的文件，是否覆盖？", "已存在对应文件", JOptionPane.OK_CANCEL_OPTION);
                    if (choose == 0) {
                        if (file.delete()) {
                            System.out.println("成功覆盖原有文件！");
                        } else {
                            System.out.println("删除原有文件失败！");
                            return;
                        }
                    } else {
                        return;
                    }
                }
                // 否则创建文件
                if (!file.createNewFile()) {
                    System.out.println("创建文件失败!");
                    return;
                }
            } else
                file = new File(filePath);


            BufferedWriter out = new BufferedWriter(new FileWriter(file));

            ArrayList<Page> pages = mainWindow.getPagePane().getNowFile().getPages();
            // 保存整个文件页数
            out.write(pages.size() + "\r\n");
            for (Page page : pages) {
                // 每个页面单独保存
                ArrayList<Picture> pictures = page.getPictures();
                // 保存图形总数
                out.write(pictures.size() + "\r\n");
                for (Picture picture : pictures) {
                    // 保存单个图形
                    out.write(picture.toFileString() + "\r\n");
                }
            }

            out.flush();
            out.close();
            System.out.println("成功保存到" + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件到当前页
     */
    public void loadPage() {
        // 未指定主窗口时返回
        if (mainWindow == null) {
            System.out.println("未设置主窗口!");
            return;
        }
        // 主窗口未指定绘图页面时返回
        if (mainWindow.getPagePane() == null) {
            System.out.println("未设置主页面!");
            return;
        }
        // 存在已经打开的文件时先保存一次原文件
        if (filePath != null)
            savePage();
        // 选取一个文件
        chooseFilePath("打开");
        // 打开文件
        try {
            File file = new File(filePath);
            // 不存在时创建文件
            if (!file.exists()) {
                System.out.println("打开文件失败!");
                return;
            }
            System.out.println("成功读取" + filePath);
            BufferedReader in = new BufferedReader(new FileReader(file));
            // 清空当前文件
            mainWindow.getPagePane().clear();
            // 总页数
            int pageSize = Integer.parseInt(in.readLine());

            // 插入页数
            for (int i = 1; i < pageSize; i++)
                mainWindow.getPagePane().insertNewPageAfter();

            // 读取一页中的图形
            for (int i = 0; i < pageSize; i++) {
                // 重设当前绘制页
                mainWindow.getPagePane().resetPage(i);
                // 当前页所含图形数
                int pictureSize = Integer.parseInt(in.readLine());
                // 读取每个图形
                for (int j = 0; j < pictureSize; j++) {
                    String picture = in.readLine();
                    mainWindow.getPagePane().getPageEditPane().addPicture(picture);
                }

            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 另存为
     */
    public void saveAsPage() {
        // 未指定主窗口时返回
        if (mainWindow == null) {
            System.out.println("未设置主窗口!");
            return;
        }
        // 主窗口未指定绘图页面时返回
        if (mainWindow.getPagePane() == null) {
            System.out.println("未设置主页面!");
            return;
        }
        // 打开新文件的路径
        chooseFilePath("另存为");
        try {
            File file = new File(filePath);
            // 已存在对应文件时弹出提醒
            if (file.exists()) {
                System.out.println("存在已有文件！");

                int choose = JOptionPane.showConfirmDialog(mainWindow.getPagePane(), "已存在对应的文件，是否覆盖？", "已存在对应文件", JOptionPane.OK_CANCEL_OPTION);
                if (choose == 0) {
                    if (file.delete()) {
                        System.out.println("成功覆盖原有文件！");
                    } else {
                        System.out.println("删除原有文件失败！");
                        return;
                    }
                } else {
                    return;
                }
            }
            // 创建文件
            if (!file.createNewFile()) {
                System.out.println("创建文件失败!");
                return;
            }
            savePage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
