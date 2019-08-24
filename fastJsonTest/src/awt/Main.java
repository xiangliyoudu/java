package awt;

import java.awt.Color;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        /**
         * ӡ�������ļ�
         */
        SealConfiguration configuration = new SealConfiguration();

        String fontFamily = "����";
        /**
         * ������
         */
        SealFont mainFont = new SealFont();
//        mainFont.setBold(true);
        mainFont.setFontFamily(fontFamily);
        mainFont.setMarginSize(10);
        /**************************************************/
        mainFont.setFontText("�����ڴ����ݿƼ������������޹�˾");
//        mainFont.setFontText("����Ͱͣ��й����ɷ����޹�˾");
        mainFont.setFontSize(33);
        mainFont.setFontSpace(31.0);
        /**************************************************/
        //mainFont.setFontText("ZHITUWANG CO.LTDECIDDO SH  NANNINGSHI");
        //mainFont.setFontSize(20);
        //mainFont.setFontSpace(15.0);
        /**************************************************/
//        mainFont.setFontText("�����޵���ͼ���Ա���ר����");
//        mainFont.setFontSize(25);
//        mainFont.setFontSpace(12.0);

        /**
         * ������
         */
        SealFont viceFont = new SealFont();
//        viceFont.setBold(true);
        viceFont.setFontFamily(fontFamily);
        viceFont.setMarginSize(5);
        /**************************************************/
        //viceFont.setFontText("123456789012345");
        //viceFont.setFontSize(13);
        //viceFont.setFontSpace(12.0);
        /**************************************************/
        viceFont.setFontText("������֤");
        viceFont.setFontSize(22);
        viceFont.setFontSpace(12.0);

        /**
         * ��������
         */
        SealFont centerFont = new SealFont();
//        centerFont.setBold(true);
        centerFont.setFontFamily(fontFamily);
        /**************************************************/
        centerFont.setFontText("��");
        centerFont.setFontSize(100);
        /**************************************************/
        //centerFont.setFontText("�Ա�����\n��ͼ���Ա�\nר����");
        //centerFont.setFontSize(20);
        /**************************************************/
        //centerFont.setFontText("123456789012345");
        //centerFont.setFontSize(20);
        /**************************************************/
//        centerFont.setFontText("����ר��");
//        centerFont.setFontSize(25);

        /**
         * ̧ͷ����
         */
        SealFont titleFont = new SealFont();
//        titleFont.setBold(true);
        titleFont.setFontFamily(fontFamily);
        titleFont.setFontSize(33);
        /**************************************************/
        titleFont.setFontText("����ר����");
        titleFont.setMarginSize(96);
        titleFont.setFontSpace(0.0);
        /**************************************************/
//        titleFont.setFontText("������֤");
//        titleFont.setMarginSize(68);
//        titleFont.setMarginSize(27);

        /**
         * ���������
         */
        configuration.setMainFont(mainFont);
        /**
         * ��Ӹ�����
         */
//        configuration.setViceFont(viceFont);
        /**
         * �����������
         */
        configuration.setCenterFont(centerFont);
        /**
         * ���̧ͷ����
         */
        configuration.setTitleFont(titleFont);

        /**
         * ͼƬ��С
         */
        configuration.setImageSize(350);
        /**
         * ������ɫ
         */
        configuration.setBackgroudColor(Color.RED);
        /**
         * ���ߴ�ϸ���뾶
         */
        //configuration.setBorderCircle(new SealCircle(3, 140, 140));
        configuration.setBorderCircle(new SealCircle(8, 150, 150));
        /**
         * �ڱ��ߴ�ϸ���뾶
         */
        //configuration.setBorderInnerCircle(new SealCircle(1, 135, 135));
//        configuration.setBorderInnerCircle(new SealCircle(1, 135, 95));
        /**
         * �ڻ��ߴ�ϸ���뾶
         */
        //configuration.setInnerCircle(new SealCircle(2, 105, 105));
//        configuration.setInnerCircle(new SealCircle(2, 85, 45));

        //1.���ɹ���
        try {
            SealUtil.buildAndStoreSeal(configuration, "E:/temp/awt/����.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //2.����˽��
        SealFont font = new SealFont();
        font.setFontSize(120).setBold(true).setFontText("�����ж�");
        SealUtil.buildAndStorePersonSeal(300, 16, font, "ӡ", "E:/temp/awt/˽��.png");
    }

}
