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
}
