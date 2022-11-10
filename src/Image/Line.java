package Image;
/**
 * 直线类
 */
public class Line extends BaseImage {
    /**
     * 直线右侧截止点X坐标
     */
    int endX;
    /**
     * 直线右侧截止点Y坐标
     */
    int endY;
    public int getEndX(){
        return endX;
    }
    public int getEndY(){
        return endY;
    }
    /**
     * 通过给定端点A，B创建直线
     *
     * @param AX A点x坐标
     * @param AY A点Y坐标
     * @param BX B点X坐标
     * @param BY B点Y坐标
     */
    public Line(int AX, int AY, int BX, int BY) {
        // 确保起点在左
        if (AX < BX) {
            this.baseX = AX;
            this.baseY = AY;
            this.endX = BX;
            this.endY = BY;
        }
        else{
            this.baseX = BX;
            this.baseY = BY;
            this.endX = AX;
            this.endY = AY;
        }
    }

}
