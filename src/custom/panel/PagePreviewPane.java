package custom.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.awt.Image.SCALE_DEFAULT;

/**
 * 所有PPT预览的列表
 */
public class PagePreviewPane extends JScrollPane {
    PagePane pagePane;

    public void setPagePane(PagePane pagePane) {
        this.pagePane = pagePane;
    }

    /**
     * 按钮界面
     */
    JPanel pages = new JPanel();
    /**
     * 每一个缩略图对应一个按钮
     */
    ArrayList<JButton> pageButton = new ArrayList<>();

    ArrayList<Image> pageShot = new ArrayList<>();
    private int pageSize = 0;

    public PagePreviewPane() {
        pages.setPreferredSize(new Dimension(100, 1000));
        add(pages);
        setViewportView(pages);
    }

    /**
     *
     */
    private JButton buildButton() {
        JButton button = new JButton(String.valueOf(pageSize + 1));
        button.setPreferredSize(new Dimension(100, 50));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pagePane.changePage(Integer.parseInt(e.getActionCommand())-1);
            }
        });
        pages.add(button);
        return button;
    }

    /**
     * 设置某一页的缩略图
     *
     * @param index 页数的下标
     * @param shot  缩略图
     */
    public void updatePage(int index, Image shot) {
        if (index >= pageSize) {
            pageButton.add(buildButton());
            pageShot.add(shot);
            pageSize++;
        } else {
            pageShot.set(index, shot);
        }

        pages.revalidate();
        pages.repaint();
        repaint();
    }

    /**
     * 删除某一页
     *
     * @param index 页数的下标
     */
    public void deletePage(int index) {
        // 隐藏最后一个
        pageButton.get(pageSize - 1).setVisible(false);
        pageButton.remove(pageSize - 1);
        // 删除对应的缩略图
        pageShot.remove(index);


        pageSize--;
        pages.revalidate();
        pages.repaint();
        repaint();
    }

    void refresh() {
        if (pageSize == 0)
            return;
        int buttonWidth = pageButton.get(0).getWidth();
        int buttonHeight = pageButton.get(0).getHeight();

        for (int i = 0; i < pageSize; i++) {
            //缩放截图到和按钮大小一样大
            Image scaledShot = pageShot.get(i).getScaledInstance(buttonWidth, buttonHeight, SCALE_DEFAULT);
            pageButton.get(i).setIcon(new ImageIcon(scaledShot));
        }
        pages.repaint();
    }

    void clear() {
        // 清空按钮
        for (JButton button : pageButton)
            pages.remove(button);
        pageButton.clear();
        // 清空存储的图片
        pageShot.clear();

        pageSize = 0;

        // 重新绘制
        pages.revalidate();
        pages.repaint();
        repaint();
    }
}
