package custom.panel;

import custom.MainWindow;
import custom.picture.Picture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileSaveBar extends BaseBar {
    JButton saveButton;
    /**
     * 保存的文件名
     */
    String fileName = null;

    /**
     * 当前窗口界面
     */
    MainWindow mainWindow = null;

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public FileSaveBar(Dimension size) {
        baseSetting(size);

        saveButton = new JButton("保存");
        saveButton.setSize(size);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePage();
            }
        });
        this.add(saveButton);

        savePage();
    }

    /**
     * 获取未使用的默认格式的文件名
     *
     * @return 未使用的默认格式文件名
     */
    public String getDefaultFileName() {
        //文件前缀
        String filePrefix = "PicturePointFile";
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
     * 保存当前页到文件
     */
    public void savePage() {
        // 未指定主窗口时返回
        if(mainWindow == null){
            System.out.println("未设置主窗口!");
            return;
        }
        // 主窗口未指定绘图页面时返回
        if(mainWindow.getPagePane() == null){
            System.out.println("未设置主页面!");
            return;
        }
        // 未指定文件名时新建默认文件
        if (fileName == null) {
            fileName = getDefaultFileName();
        }

        //打开文件
        try {
            File file = new File(fileName);
            //不存在时创建文件
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    System.out.println("创建文件失败!");
                    return;
                }
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            ArrayList<Picture> pictures = mainWindow.getPagePane().getPictures();
            out.write(pictures.size()+"\r\n");
            for(Picture picture:pictures){
                out.write(picture.toFileString()+"\r\n");
            }
            out.flush();
            out.close();
            System.out.println("成功保存到"+fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
