package awt;
import java.awt.*;

/**
 * @Description: ӡ��������
 * @Author Ran.chunlin
 * @Date: Created in 12:17 2018-10-04
 */
public class SealConfiguration {
    /**
     * ������
     */
    private SealFont mainFont;
    /**
     * ������
     */
    private SealFont viceFont;
    /**
     * ̧ͷ����
     */
    private SealFont titleFont;
    /**
     * ��������
     */
    private SealFont centerFont;
    /**
     * ����Բ
     */
    private SealCircle borderCircle;
    /**
     * �ڱ���Բ
     */
    private SealCircle borderInnerCircle;
    /**
     * ����Բ
     */
    private SealCircle innerCircle;
    /**
     * ����ɫ��Ĭ�Ϻ�ɫ
     */
    private Color backgroudColor = Color.RED;
    /**
     * ͼƬ����ߴ磬Ĭ��300
     */
    private Integer imageSize = 300;

    public SealConfiguration setMainFont(SealFont mainFont) {
        this.mainFont = mainFont;
        return this;
    }

    public SealConfiguration setViceFont(SealFont viceFont) {
        this.viceFont = viceFont;
        return this;
    }

    public SealConfiguration setTitleFont(SealFont titleFont) {
        this.titleFont = titleFont;
        return this;
    }

    public SealConfiguration setCenterFont(SealFont centerFont) {
        this.centerFont = centerFont;
        return this;
    }

    public SealConfiguration setBorderCircle(SealCircle borderCircle) {
        this.borderCircle = borderCircle;
        return this;
    }

    public SealConfiguration setBorderInnerCircle(SealCircle borderInnerCircle) {
        this.borderInnerCircle = borderInnerCircle;
        return this;
    }

    public SealConfiguration setInnerCircle(SealCircle innerCircle) {
        this.innerCircle = innerCircle;
        return this;
    }

    public SealConfiguration setBackgroudColor(Color backgroudColor) {
        this.backgroudColor = backgroudColor;
        return this;
    }

    public SealConfiguration setImageSize(Integer imageSize) {
        this.imageSize = imageSize;
        return this;
    }

    public SealFont getMainFont() {
        return mainFont;
    }

    public SealFont getViceFont() {
        return viceFont;
    }

    public SealFont getTitleFont() {
        return titleFont;
    }

    public SealFont getCenterFont() {
        return centerFont;
    }

    public SealCircle getBorderCircle() {
        return borderCircle;
    }

    public SealCircle getBorderInnerCircle() {
        return borderInnerCircle;
    }

    public SealCircle getInnerCircle() {
        return innerCircle;
    }

    public Color getBackgroudColor() {
        return backgroudColor;
    }

    public Integer getImageSize() {
        return imageSize;
    }
}