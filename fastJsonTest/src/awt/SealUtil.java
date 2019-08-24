package awt;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @Description: ӡ�¹�����
 * @Author Ran.chunlin
 * @Date: Created in 18:24 2018-10-03
 */
public abstract class SealUtil {

    /**
     * Ĭ�ϴ�10x10��λ�ÿ�ʼ������ֹ���ϲ��ֻ���װ����
     */
    private final static int INIT_BEGIN = 10;

    /**
     * ����˽��ӡ��ͼƬ�������浽ָ��·��
     *
     * @param lineSize ���߿��
     * @param font �������
     * @param addString ׷���ַ�
     * @param fullPath ����ȫ·��
     *
     * @throws Exception �쳣
     */
    public static void buildAndStorePersonSeal(int imageSize, int lineSize, SealFont font, String addString,
            String fullPath) throws Exception {
        storeBytes(buildBytes(buildPersonSeal(imageSize, lineSize, font, addString)), fullPath);
    }

    /**
     * ����ӡ��ͼƬ�������浽ָ��·��
     *
     * @param conf �����ļ�F
     * @param fullPath ����ȫ·��
     *
     * @throws Exception �쳣
     */
    public static void buildAndStoreSeal(SealConfiguration conf, String fullPath) throws Exception {
        storeBytes(buildBytes(buildSeal(conf)), fullPath);
    }

    /**
     * ����ӡ��ͼƬ��byte����
     *
     * @param image BufferedImage����
     *
     * @return byte����
     *
     * @throws IOException �쳣
     */
    public static byte[] buildBytes(BufferedImage image) throws Exception {

        try (ByteArrayOutputStream outStream = new ByteArrayOutputStream()) {
            //bufferedImageתΪbyte����
            ImageIO.write(image, "png", outStream);
            return outStream.toByteArray();
        }
    }

    /**
     * ����ӡ��ͼƬ
     *
     * @param conf �����ļ�
     *
     * @return BufferedImage����
     *
     * @throws Exception �쳣
     */
    public static BufferedImage buildSeal(SealConfiguration conf) throws Exception {

        //1.����
        BufferedImage bi = new BufferedImage(conf.getImageSize(), conf.getImageSize(), BufferedImage.TYPE_4BYTE_ABGR);

        //2.����
        Graphics2D g2d = bi.createGraphics();

        //2.1���������
        //�ı�������ݣ�����Բ���ĵ����ֻᱻ����
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        //����ͼ�ο����
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(hints);

        //2.2���ñ���͸����
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 0));

        //2.3������
        g2d.fillRect(0, 0, conf.getImageSize(), conf.getImageSize());

        //2.4����͸���ȣ���ʼ��ͼ
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1));

        //2.5���û�����ɫ
        g2d.setPaint(conf.getBackgroudColor());

        //3.������Բ
        if (conf.getBorderCircle() != null) {
            drawCicle(g2d, conf.getBorderCircle(), INIT_BEGIN, INIT_BEGIN);
        } else {
            throw new Exception("BorderCircle can not null��");
        }

        int borderCircleWidth = conf.getBorderCircle().getWidth();
        int borderCircleHeight = conf.getBorderCircle().getHeight();

        //4.���ڱ���Բ
        if (conf.getBorderInnerCircle() != null) {
            int x = INIT_BEGIN + borderCircleWidth - conf.getBorderInnerCircle().getWidth();
            int y = INIT_BEGIN + borderCircleHeight - conf.getBorderInnerCircle().getHeight();
            drawCicle(g2d, conf.getBorderInnerCircle(), x, y);
        }

        //5.���ڻ���Բ
        if (conf.getInnerCircle() != null) {
            int x = INIT_BEGIN + borderCircleWidth - conf.getInnerCircle().getWidth();
            int y = INIT_BEGIN + borderCircleHeight - conf.getInnerCircle().getHeight();
            drawCicle(g2d, conf.getInnerCircle(), x, y);
        }

        //6.������������
        if (borderCircleHeight != borderCircleWidth) {
            drawArcFont4Oval(g2d, conf.getBorderCircle(), conf.getMainFont(), true);
        } else {
            drawArcFont4Circle(g2d, borderCircleHeight, conf.getMainFont(), true);
        }

        //7.�����θ�����
        if (borderCircleHeight != borderCircleWidth) {
            drawArcFont4Oval(g2d, conf.getBorderCircle(), conf.getViceFont(), false);
        } else {
            drawArcFont4Circle(g2d, borderCircleHeight, conf.getViceFont(), false);
        }

        //8.��������
        drawFont(g2d, (borderCircleWidth + INIT_BEGIN) * 2, (borderCircleHeight + INIT_BEGIN) * 2,
                conf.getCenterFont());

        //9.��̧ͷ����
        drawFont(g2d, (borderCircleWidth + INIT_BEGIN) * 2, (borderCircleHeight + INIT_BEGIN) * 2, conf.getTitleFont());

        g2d.dispose();
        return bi;
    }

    /**
     * ����˽��ӡ��ͼƬ
     *
     * @param lineSize ������ϸ
     * @param font �������
     * @param addString �Ƿ�������֣��硰ӡ��
     *
     * @return BufferedImage����
     *
     * @throws Exception �쳣
     */
    public static BufferedImage buildPersonSeal(int imageSize, int lineSize, SealFont font, String addString)
            throws Exception {
        if (font == null || font.getFontText().length() < 2 || font.getFontText().length() > 4) {
            throw new Exception("FontText.length illegal!");
        }

        int fixH = 18;
        int fixW = 2;

        //1.����
        BufferedImage bi = new BufferedImage(imageSize, imageSize / 2, BufferedImage.TYPE_4BYTE_ABGR);

        //2.����
        Graphics2D g2d = bi.createGraphics();

        //2.1���û�����ɫ
        g2d.setPaint(Color.RED);

        //2.2���������
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //3.дǩ��
        int marginW = fixW + lineSize;
        float marginH;
        FontRenderContext context = g2d.getFontRenderContext();
        Rectangle2D rectangle;
        Font f;

        if (font.getFontText().length() == 2) {
            if (addString != null && addString.trim().length() > 0) {
                bi = drawThreeFont(bi, g2d, font.setFontText(font.getFontText() + addString), lineSize, imageSize, fixH,
                        fixW, true);
            } else {
                f = new Font(font.getFontFamily(), Font.BOLD, font.getFontSize());
                g2d.setFont(f);
                rectangle = f.getStringBounds(font.getFontText().substring(0, 1), context);
                marginH = (float) (Math.abs(rectangle.getCenterY()) * 2 + marginW) + fixH - 4;
                g2d.drawString(font.getFontText().substring(0, 1), marginW, marginH);
                marginW += Math.abs(rectangle.getCenterX()) * 2 + (font.getFontSpace() == null ?
                        INIT_BEGIN :
                        font.getFontSpace());
                g2d.drawString(font.getFontText().substring(1), marginW, marginH);

                //����
                BufferedImage nbi = new BufferedImage(imageSize, imageSize, bi.getType());
                Graphics2D ng2d = nbi.createGraphics();
                ng2d.setPaint(Color.RED);
                ng2d.drawImage(bi, 0, 0, imageSize, imageSize, null);

                //��������
                ng2d.setStroke(new BasicStroke(lineSize));
                ng2d.drawRect(0, 0, imageSize, imageSize);
                ng2d.dispose();
                bi = nbi;
            }
        } else if (font.getFontText().length() == 3) {
            if (addString != null && addString.trim().length() > 0) {
                bi = drawFourFont(bi, font.setFontText(font.getFontText() + addString), lineSize, imageSize, fixH,
                        fixW);
            } else {
                bi = drawThreeFont(bi, g2d, font.setFontText(font.getFontText()), lineSize, imageSize, fixH, fixW,
                        false);
            }
        } else {
            bi = drawFourFont(bi, font, lineSize, imageSize, fixH, fixW);
        }

        return bi;
    }

    /**
     * ��byte���鱣��Ϊ�����ļ�
     *
     * @param buf byte����
     * @param fullPath �ļ�ȫ·��
     *
     * @throws IOException �쳣
     */
    private static void storeBytes(byte[] buf, String fullPath) throws IOException {

        File file = new File(fullPath);
        try (FileOutputStream fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            //1.�����Ŀ¼�����ڣ��򴴽�
            File dir = file.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }

            //2.дbyte���鵽�ļ�
            bos.write(buf);
        }
    }

    /**
     * ������
     *
     * @param bi ͼƬ
     * @param g2d ԭ����
     * @param font �������
     * @param lineSize �߿�
     * @param imageSize ͼƬ�ߴ�
     * @param fixH �޸���
     * @param fixW �޸���
     * @param isWithYin �Ƿ��С�ӡ��
     */
    private static BufferedImage drawThreeFont(BufferedImage bi, Graphics2D g2d, SealFont font, int lineSize,
            int imageSize, int fixH, int fixW, boolean isWithYin) {
        fixH -= 9;
        int marginW = fixW + lineSize;
        //��������
        Font f = new Font(font.getFontFamily(), Font.BOLD, font.getFontSize());
        g2d.setFont(f);
        FontRenderContext context = g2d.getFontRenderContext();
        Rectangle2D rectangle = f.getStringBounds(font.getFontText().substring(0, 1), context);
        float marginH = (float) (Math.abs(rectangle.getCenterY()) * 2 + marginW) + fixH;
        int oldW = marginW;

        if (isWithYin) {
            g2d.drawString(font.getFontText().substring(2, 3), marginW, marginH);
            marginW += rectangle.getCenterX() * 2 + (font.getFontSpace() == null ? INIT_BEGIN : font.getFontSpace());
        } else {
            marginW += rectangle.getCenterX() * 2 + (font.getFontSpace() == null ? INIT_BEGIN : font.getFontSpace());
            g2d.drawString(font.getFontText().substring(0, 1), marginW, marginH);
        }

        //����
        BufferedImage nbi = new BufferedImage(imageSize, imageSize, bi.getType());
        Graphics2D ng2d = nbi.createGraphics();
        ng2d.setPaint(Color.RED);
        ng2d.drawImage(bi, 0, 0, imageSize, imageSize, null);

        //��������
        ng2d.setStroke(new BasicStroke(lineSize));
        ng2d.drawRect(0, 0, imageSize, imageSize);
        ng2d.dispose();
        bi = nbi;

        g2d = bi.createGraphics();
        g2d.setPaint(Color.RED);
        g2d.setFont(f);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (isWithYin) {
            g2d.drawString(font.getFontText().substring(0, 1), marginW, marginH += fixH);
            rectangle = f.getStringBounds(font.getFontText(), context);
            marginH += Math.abs(rectangle.getHeight());
            g2d.drawString(font.getFontText().substring(1), marginW, marginH);
        } else {
            g2d.drawString(font.getFontText().substring(1, 2), oldW, marginH += fixH);
            rectangle = f.getStringBounds(font.getFontText(), context);
            marginH += Math.abs(rectangle.getHeight());
            g2d.drawString(font.getFontText().substring(2, 3), oldW, marginH);
        }
        return bi;
    }

    /**
     * ������
     *
     * @param bi ͼƬ
     * @param font �������
     * @param lineSize �߿�
     * @param imageSize ͼƬ�ߴ�
     * @param fixH �޸���
     * @param fixW �޸���
     */
    private static BufferedImage drawFourFont(BufferedImage bi, SealFont font, int lineSize, int imageSize, int fixH,
            int fixW) {
        int marginW = fixW + lineSize;
        //����
        BufferedImage nbi = new BufferedImage(imageSize, imageSize, bi.getType());
        Graphics2D ng2d = nbi.createGraphics();
        ng2d.setPaint(Color.RED);
        ng2d.drawImage(bi, 0, 0, imageSize, imageSize, null);

        //��������
        ng2d.setStroke(new BasicStroke(lineSize));
        ng2d.drawRect(0, 0, imageSize, imageSize);
        ng2d.dispose();
        bi = nbi;

        Graphics2D g2d = bi.createGraphics();
        g2d.setPaint(Color.RED);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        FontRenderContext context = g2d.getFontRenderContext();

        Font f = new Font(font.getFontFamily(), Font.BOLD, font.getFontSize());
        g2d.setFont(f);
        Rectangle2D rectangle = f.getStringBounds(font.getFontText().substring(0, 1), context);
        float marginH = (float) (Math.abs(rectangle.getCenterY()) * 2 + marginW) + fixH;

        g2d.drawString(font.getFontText().substring(2, 3), marginW, marginH);
        int oldW = marginW;
        marginW +=
                Math.abs(rectangle.getCenterX()) * 2 + (font.getFontSpace() == null ? INIT_BEGIN : font.getFontSpace());

        g2d.drawString(font.getFontText().substring(0, 1), marginW, marginH);
        marginH += Math.abs(rectangle.getHeight());

        g2d.drawString(font.getFontText().substring(3, 4), oldW, marginH);

        g2d.drawString(font.getFontText().substring(1, 2), marginW, marginH);

        return bi;
    }

    /**
     * ����Բ��������
     *
     * @param g2d ����
     * @param circleRadius ���ΰ뾶
     * @param font �������
     * @param isTop �Ƿ��������ϲ����������²�
     */
    private static void drawArcFont4Circle(Graphics2D g2d, int circleRadius, SealFont font, boolean isTop) {
        if (font == null) {
            return;
        }

        //1.���峤��
        int fontTextLen = font.getFontText().length();

        //2.�����С��Ĭ�ϸ������峤�ȶ�̬�趨 TODO
        int fontSize = font.getFontSize() == null ? (55 - fontTextLen * 2) : font.getFontSize();

        //3.������ʽ
        int fontStyle = font.isBold() ? Font.BOLD : Font.PLAIN;

        //4.��������
        Font f = new Font(font.getFontFamily(), fontStyle, fontSize);

        FontRenderContext context = g2d.getFontRenderContext();
        Rectangle2D rectangle = f.getStringBounds(font.getFontText(), context);

        //5.����֮���࣬Ĭ�϶�̬����
        double fontSpace;
        if (font.getFontSpace() != null) {
            fontSpace = font.getFontSpace();
        } else {
            if (fontTextLen == 1) {
                fontSpace = 0;
            } else {
                fontSpace = rectangle.getWidth() / (fontTextLen - 1) * 0.9;
            }
        }

        //6.������Ȧ����
        int marginSize = font.getMarginSize() == null ? INIT_BEGIN : font.getMarginSize();

        //7.д��
        double newRadius = circleRadius + rectangle.getY() - marginSize;
        double radianPerInterval = 2 * Math.asin(fontSpace / (2 * newRadius));

        double fix = 0.04;
        if (isTop) {
            fix = 0.18;
        }
        double firstAngle;
        if (!isTop) {
            if (fontTextLen % 2 == 1) {
                firstAngle = Math.PI + Math.PI / 2 - (fontTextLen - 1) * radianPerInterval / 2.0 - fix;
            } else {
                firstAngle = Math.PI + Math.PI / 2 - ((fontTextLen / 2.0 - 0.5) * radianPerInterval) - fix;
            }
        } else {
            if (fontTextLen % 2 == 1) {
                firstAngle = (fontTextLen - 1) * radianPerInterval / 2.0 + Math.PI / 2 + fix;
            } else {
                firstAngle = (fontTextLen / 2.0 - 0.5) * radianPerInterval + Math.PI / 2 + fix;
            }
        }

        for (int i = 0; i < fontTextLen; i++) {
            double theta;
            double thetaX;
            double thetaY;

            if (!isTop) {
                theta = firstAngle + i * radianPerInterval;
                thetaX = newRadius * Math.sin(Math.PI / 2 - theta);
                thetaY = newRadius * Math.cos(theta - Math.PI / 2);
            } else {
                theta = firstAngle - i * radianPerInterval;
                thetaX = newRadius * Math.sin(Math.PI / 2 - theta);
                thetaY = newRadius * Math.cos(theta - Math.PI / 2);
            }

            AffineTransform transform;
            if (!isTop) {
                transform = AffineTransform.getRotateInstance(Math.PI + Math.PI / 2 - theta);
            } else {
                transform = AffineTransform.getRotateInstance(Math.PI / 2 - theta + Math.toRadians(8));
            }
            Font f2 = f.deriveFont(transform);
            g2d.setFont(f2);
            g2d.drawString(font.getFontText().substring(i, i + 1), (float) (circleRadius + thetaX + INIT_BEGIN),
                    (float) (circleRadius - thetaY + INIT_BEGIN));
        }
    }

    /**
     * ������Բ��������
     *
     * @param g2d ����
     * @param circle ��ΧԲ
     * @param font �������
     * @param isTop �Ƿ��������ϲ����������²�
     */
    private static void drawArcFont4Oval(Graphics2D g2d, SealCircle circle, SealFont font, boolean isTop) {
        if (font == null) {
            return;
        }
        float radiusX = circle.getWidth();
        float radiusY = circle.getHeight();
        float radiusWidth = radiusX + circle.getLineSize();
        float radiusHeight = radiusY + circle.getLineSize();

        //1.���峤��
        int fontTextLen = font.getFontText().length();

        //2.�����С��Ĭ�ϸ������峤�ȶ�̬�趨
        int fontSize = font.getFontSize() == null ? 25 + (10 - fontTextLen) / 2 : font.getFontSize();

        //3.������ʽ
        int fontStyle = font.isBold() ? Font.BOLD : Font.PLAIN;

        //4.��������
        Font f = new Font(font.getFontFamily(), fontStyle, fontSize);

        //5.�ܵĽǿ��
        float totalArcAng = (float) (font.getFontSpace() * fontTextLen);

        //6.�ӱ��������ĵ��ƶ�����
        float minRat = 0.90f;

        double startAngle = isTop ? -90f - totalArcAng / 2f : 90f - totalArcAng / 2f;
        double step = 0.5;
        int alCount = (int) Math.ceil(totalArcAng / step) + 1;
        double[] angleArr = new double[alCount];
        double[] arcLenArr = new double[alCount];
        int num = 0;
        double accArcLen = 0.0;
        angleArr[num] = startAngle;
        arcLenArr[num] = accArcLen;
        num++;
        double angR = startAngle * Math.PI / 180.0;
        double lastX = radiusX * Math.cos(angR) + radiusWidth;
        double lastY = radiusY * Math.sin(angR) + radiusHeight;
        for (double i = startAngle + step; num < alCount; i += step) {
            angR = i * Math.PI / 180.0;
            double x = radiusX * Math.cos(angR) + radiusWidth, y = radiusY * Math.sin(angR) + radiusHeight;
            accArcLen += Math.sqrt((lastX - x) * (lastX - x) + (lastY - y) * (lastY - y));
            angleArr[num] = i;
            arcLenArr[num] = accArcLen;
            lastX = x;
            lastY = y;
            num++;
        }
        double arcPer = accArcLen / fontTextLen;
        for (int i = 0; i < fontTextLen; i++) {
            double arcL = i * arcPer + arcPer / 2.0;
            double ang = 0.0;
            for (int p = 0; p < arcLenArr.length - 1; p++) {
                if (arcLenArr[p] <= arcL && arcL <= arcLenArr[p + 1]) {
                    ang = (arcL >= ((arcLenArr[p] + arcLenArr[p + 1]) / 2.0)) ? angleArr[p + 1] : angleArr[p];
                    break;
                }
            }
            angR = (ang * Math.PI / 180f);
            Float x = radiusX * (float) Math.cos(angR) + radiusWidth;
            Float y = radiusY * (float) Math.sin(angR) + radiusHeight;
            double qxang = Math.atan2(radiusY * Math.cos(angR), -radiusX * Math.sin(angR));
            double fxang = qxang + Math.PI / 2.0;

            int subIndex = isTop ? i : fontTextLen - 1 - i;
            String c = font.getFontText().substring(subIndex, subIndex + 1);

            //��ȡ���ָ߿�
            FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f);
            int w = fm.stringWidth(c), h = fm.getHeight();

            if (isTop) {
                x += h * minRat * (float) Math.cos(fxang);
                y += h * minRat * (float) Math.sin(fxang);
                x += -w / 2f * (float) Math.cos(qxang);
                y += -w / 2f * (float) Math.sin(qxang);
            } else {
                x += (h * minRat ) * (float) Math.cos(fxang);
                y += (h * minRat) * (float) Math.sin(fxang);
                x += w / 2f * (float) Math.cos(qxang);
                y += w / 2f * (float) Math.sin(qxang);
            }

            // ��ת
            AffineTransform affineTransform = new AffineTransform();
            affineTransform.scale(0.8, 1);
            if (isTop)
                affineTransform.rotate(Math.toRadians((fxang * 180.0 / Math.PI - 90)), 0, 0);
            else
                affineTransform.rotate(Math.toRadians((fxang * 180.0 / Math.PI + 180 - 90)), 0, 0);
            Font f2 = f.deriveFont(affineTransform);
            g2d.setFont(f2);
            g2d.drawString(c, x.intValue() + INIT_BEGIN, y.intValue() + INIT_BEGIN);
        }
    }

    /**
     * ������
     *
     * @param g2d ����
     * @param circleWidth ����Բ���
     * @param circleHeight ����Բ�߶�
     * @param font �������
     */
    private static void drawFont(Graphics2D g2d, int circleWidth, int circleHeight, SealFont font) {
        if (font == null) {
            return;
        }

        //1.���峤��
        int fontTextLen = font.getFontText().length();

        //2.�����С��Ĭ�ϸ������峤�ȶ�̬�趨
        int fontSize = font.getFontSize() == null ? (55 - fontTextLen * 2) : font.getFontSize();

        //3.������ʽ
        int fontStyle = font.isBold() ? Font.BOLD : Font.PLAIN;

        //4.��������
        Font f = new Font(font.getFontFamily(), fontStyle, fontSize);
        g2d.setFont(f);

        FontRenderContext context = g2d.getFontRenderContext();
        String[] fontTexts = font.getFontText().split("\n");
        if (fontTexts.length > 1) {
            int y = 0;
            for (String fontText : fontTexts) {
                y += Math.abs(f.getStringBounds(fontText, context).getHeight());
            }
            //5.�����ϱ߾�
            float marginSize = INIT_BEGIN + (float) (circleHeight / 2 - y / 2);
            for (String fontText : fontTexts) {
                Rectangle2D rectangle2D = f.getStringBounds(fontText, context);
                g2d.drawString(fontText, (float) (circleWidth / 2 - rectangle2D.getCenterX() + 1), marginSize);
                marginSize += Math.abs(rectangle2D.getHeight());
            }
        } else {
            Rectangle2D rectangle2D = f.getStringBounds(font.getFontText(), context);
            //5.�����ϱ߾࣬Ĭ��������
            float marginSize = font.getMarginSize() == null ?
                    (float) (circleHeight / 2 - rectangle2D.getCenterY()) :
                    (float) (circleHeight / 2 - rectangle2D.getCenterY()) + (float) font.getMarginSize();
            g2d.drawString(font.getFontText(), (float) (circleWidth / 2 - rectangle2D.getCenterX() + 1), marginSize);
        }
    }

    /**
     * ��Բ
     *
     * @param g2d ����
     * @param circle Բ���ö���
     */
    private static void drawCicle(Graphics2D g2d, SealCircle circle, int x, int y) {
        if (circle == null) {
            return;
        }

        //1.Բ������ϸĬ����Բֱ����1/35
        int lineSize = circle.getLineSize() == null ? circle.getHeight() * 2 / (35) : circle.getLineSize();

        //2.��Բ
        g2d.setStroke(new BasicStroke(lineSize));
        g2d.drawOval(x, y, circle.getWidth() * 2, circle.getHeight() * 2);
    }

}