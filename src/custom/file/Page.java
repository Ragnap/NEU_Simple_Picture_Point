package custom.file;

import custom.picture.Picture;

import java.util.ArrayList;

/**
 * 单页
 */
public class Page {
    /**
     * 该页面上的图形列表
     */
    private ArrayList<Picture> pictures = new ArrayList<>();

    public ArrayList<Picture> getPictures() {
        return pictures;
    }

    public void addPicture(Picture newPicture){
        pictures.add(newPicture);
    }


    /**
     * 获取覆盖某个点的所有图形列表
     * @param x 点的x坐标
     * @param y 点的y坐标
     * @return 所有满足的图形
     */
    public ArrayList<Picture> getPicturesAtPosition(int x,int y){
        ArrayList<Picture> selectPictures=new ArrayList<>();
        for(Picture picture :pictures){
            if(picture.isCoverPoint(x,y))
                selectPictures.add(picture);
        }
        return selectPictures;
    }
}
