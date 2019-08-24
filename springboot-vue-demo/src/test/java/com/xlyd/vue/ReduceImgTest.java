package com.xlyd.vue;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ReduceImgTest {
	/**
	 * 图片压缩
	 * @param imgsrc：源图片文件
	 * @param imgdist：目标图片文件
	 * @param rate：压缩比例
	 */

	public static void reduceImg(File imgsrc, File imgdist, Float rate) {
		// image size
		int widthdist = 300;
		int heightdist = 200;

		if (!imgsrc.exists()) {
			return;
		}

		try {
			// read image file as image
			BufferedImage src = ImageIO.read(imgsrc);

			// 如果比例不为空则说明是按比例压缩
			if (rate != null && rate > 0) {
				// 按比例缩放或扩大图片大小，将浮点型转为整型
				widthdist = (int) (src.getWidth() * rate);
				heightdist = (int) (src.getHeight() * rate);
			}

			// 绘制图像 getScaledInstance表示创建此图像的缩放版本，返回一个新的缩放版本Image,按指定的width,height呈现图像
			// Image.SCALE_SMOOTH,选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
			Image img = src.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH);
			BufferedImage outputImg = new BufferedImage(widthdist, heightdist, BufferedImage.TYPE_INT_RGB);
			outputImg.getGraphics().drawImage(img, 0, 0, null);

			// save image
			ImageIO.write(outputImg, "png", imgdist);

		} catch (Exception ef) {
			ef.printStackTrace();
		}
	}

	

	public static void main(String[] args) {

		File srcfile = new File("E:/temp/DSC_1455.JPG");
		File distfile = new File("E:/temp/DSC_1455_Com.png");

		System.out.println("压缩前图片大小：" + srcfile.length());
		Float rate = new Float(0.1);
		reduceImg(srcfile, distfile, rate);
		System.out.println("压缩后图片大小：" + distfile.length());

	}
}