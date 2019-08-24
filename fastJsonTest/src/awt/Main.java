package awt;

import java.awt.Color;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        /**
         * 印章配置文件
         */
        SealConfiguration configuration = new SealConfiguration();

        String fontFamily = "宋体";
        /**
         * 主文字
         */
        SealFont mainFont = new SealFont();
//        mainFont.setBold(true);
        mainFont.setFontFamily(fontFamily);
        mainFont.setMarginSize(10);
        /**************************************************/
        mainFont.setFontText("天云融创数据科技（北京）有限公司");
//        mainFont.setFontText("阿里巴巴（中国）股份有限公司");
        mainFont.setFontSize(33);
        mainFont.setFontSpace(31.0);
        /**************************************************/
        //mainFont.setFontText("ZHITUWANG CO.LTDECIDDO SH  NANNINGSHI");
        //mainFont.setFontSize(20);
        //mainFont.setFontSpace(15.0);
        /**************************************************/
//        mainFont.setFontText("欢乐无敌制图网淘宝店专用章");
//        mainFont.setFontSize(25);
//        mainFont.setFontSpace(12.0);

        /**
         * 副文字
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
        viceFont.setFontText("正版认证");
        viceFont.setFontSize(22);
        viceFont.setFontSpace(12.0);

        /**
         * 中心文字
         */
        SealFont centerFont = new SealFont();
//        centerFont.setBold(true);
        centerFont.setFontFamily(fontFamily);
        /**************************************************/
        centerFont.setFontText("★");
        centerFont.setFontSize(100);
        /**************************************************/
        //centerFont.setFontText("淘宝欢乐\n制图网淘宝\n专用章");
        //centerFont.setFontSize(20);
        /**************************************************/
        //centerFont.setFontText("123456789012345");
        //centerFont.setFontSize(20);
        /**************************************************/
//        centerFont.setFontText("发货专用");
//        centerFont.setFontSize(25);

        /**
         * 抬头文字
         */
        SealFont titleFont = new SealFont();
//        titleFont.setBold(true);
        titleFont.setFontFamily(fontFamily);
        titleFont.setFontSize(33);
        /**************************************************/
        titleFont.setFontText("人事专用章");
        titleFont.setMarginSize(96);
        titleFont.setFontSpace(0.0);
        /**************************************************/
//        titleFont.setFontText("正版认证");
//        titleFont.setMarginSize(68);
//        titleFont.setMarginSize(27);

        /**
         * 添加主文字
         */
        configuration.setMainFont(mainFont);
        /**
         * 添加副文字
         */
//        configuration.setViceFont(viceFont);
        /**
         * 添加中心文字
         */
        configuration.setCenterFont(centerFont);
        /**
         * 添加抬头文字
         */
        configuration.setTitleFont(titleFont);

        /**
         * 图片大小
         */
        configuration.setImageSize(350);
        /**
         * 背景颜色
         */
        configuration.setBackgroudColor(Color.RED);
        /**
         * 边线粗细、半径
         */
        //configuration.setBorderCircle(new SealCircle(3, 140, 140));
        configuration.setBorderCircle(new SealCircle(8, 150, 150));
        /**
         * 内边线粗细、半径
         */
        //configuration.setBorderInnerCircle(new SealCircle(1, 135, 135));
//        configuration.setBorderInnerCircle(new SealCircle(1, 135, 95));
        /**
         * 内环线粗细、半径
         */
        //configuration.setInnerCircle(new SealCircle(2, 105, 105));
//        configuration.setInnerCircle(new SealCircle(2, 85, 45));

        //1.生成公章
        try {
            SealUtil.buildAndStoreSeal(configuration, "E:/temp/awt/公章.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //2.生成私章
        SealFont font = new SealFont();
        font.setFontSize(120).setBold(true).setFontText("翔里有毒");
        SealUtil.buildAndStorePersonSeal(300, 16, font, "印", "E:/temp/awt/私章.png");
    }

}
