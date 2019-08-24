package awt;

/**
 * @Description: Ó¡ÕÂÔ²È¦Àà
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
     * Ïß¿í¶È
     */
    private Integer lineSize;
    /**
     * °ë¾¶
     */
    private Integer width;
    /**
     * °ë¾¶
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
