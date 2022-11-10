package Image;
/**
 * 图形基类
 */
public class BaseImage {
    /**
     * 图形左上角X坐标
     */
    int baseX;
    /**
     * 图形左上角Y坐标
     */
    int baseY;

    public int getBaseX(){
        return baseX;
    }
    public int getBaseY(){
        return baseY;
    }
    /**
     * 移动图形
     *
     * @param deltaX X方向上位移
     * @param deltaY Y方向上位移
     */
    public void move(int deltaX, int deltaY) {
        this.baseX += deltaX;
        this.baseY += deltaY;
    }


}
