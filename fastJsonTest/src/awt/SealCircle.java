package awt;

/**
 * @Description: ӡ��ԲȦ��
 * @Author Ran.chunlin
 * @Date: Created in 15:41 2018-10-04
 */
public class SealCircle {

    public SealCircle(Integer lineSize, Integer width,Integer height) {
        this.lineSize = lineSize;
        this.width = width;
        this.height = height;
    }

    /**
     * �߿��
     */
    private Integer lineSize;
    /**
     * �뾶
     */
    private Integer width;
    /**
     * �뾶
     */
    private Integer height;

    public Integer getLineSize() {
        return lineSize;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }
}
