package awt;

import java.awt.*;

/**
 * @Description: ӡ��������
 * @Author Ran.chunlin
 * @Date: Created in 12:17 2018-10-04
 */
public class SealFont {

    public SealFont(String fontText) {
        this.fontText = fontText;
    }

    public SealFont() {
    }

    /**
     * ��������
     */
    private String fontText;
    /**
     * �Ƿ�Ӵ�
     */
    private Boolean isBold = true;
    /**
     * ��������Ĭ��Ϊ����
     */
    private String fontFamily = "����";
    /**
     * �����С
     */
    private Integer fontSize;
    /**
     * �־�
     */
    private Double fontSpace;
    /**
     * �߾ࣨ���߾���ϱ߾ࣩ
     */
    private Integer marginSize;

    /**
     * ��ȡϵͳ֧�ֵ�����������
     */
    public static String[] getSupportFontNames() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    }

    public SealFont setFontSpace(Double fontSpace) {
        this.fontSpace = fontSpace;
        return this;
    }

    public SealFont setMarginSize(Integer marginSize) {
        this.marginSize = marginSize;
        return this;
    }

    public SealFont setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
        return this;
    }

    public SealFont setFontText(String fontText) {
        this.fontText = fontText;
        return this;
    }

    public SealFont setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public SealFont setBold(Boolean bold) {
        isBold = bold;
        return this;
    }

    public String getFontText() {
        return fontText;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public Double getFontSpace() {
        return fontSpace;
    }

    public Integer getMarginSize() {
        return marginSize;
    }

    public Boolean isBold() {
        return isBold;
    }
}