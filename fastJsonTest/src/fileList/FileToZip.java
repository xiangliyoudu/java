package fileList;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public final class FileToZip {

	private FileToZip() {
	}

	/**
	 * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下
	 * 
	 * @param sourceFilePath:待压缩的文件路径
	 * @param zipFilePath:压缩后存放路径
	 * @param fileName:压缩后文件的名称
	 * @return
	 */
	public static boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName) {
		boolean flag = false;
		File sourceFile = new File(sourceFilePath);
		BufferedInputStream bis = null;
		ZipOutputStream zos = null;

		if (!sourceFile.exists()) {
			System.out.println("待压缩的文件目录：" + sourceFilePath + "不存在.");
		} else {
			try {
				File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
				if (zipFile.exists()) {
					System.out.println(zipFilePath + "目录下存在名字为:" + fileName
							+ ".zip" + "打包文件.");
				} else {
					File[] sourceFiles = sourceFile.listFiles();
					if (null == sourceFiles || sourceFiles.length < 1) {
						System.out.println("待压缩的文件目录：" + sourceFilePath
								+ "里面不存在文件，无需压缩.");
					} else {
						zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
						byte[] bufs = new byte[1024 * 10];
						for (int i = 0; i < sourceFiles.length; i++) {
							File file = sourceFiles[i];
							if(file.isDirectory()){
								ZipEntry zipEntry = new ZipEntry(file.getName() + File.separator);
								zos.putNextEntry(zipEntry);
								continue;
							}
							// 创建ZIP实体，并添加进压缩包
							ZipEntry zipEntry = new ZipEntry(file.getName());
							zos.putNextEntry(zipEntry);
							// 读取待压缩的文件并写进压缩包里
							bis = new BufferedInputStream(new FileInputStream(file), 1024 * 10);
							int read = 0;
							while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
								zos.write(bufs, 0, read);
							}
						}
						flag = true;
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				// 关闭流
				try {
					if (null != bis) {
						bis.close();
					}
					if (null != zos) {
						zos.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		String sourceFilePath = "E:/temp/ssss";
		String zipFilePath = "E:/temp/fileTest";
		String fileName = "12700153file";
		boolean flag = FileToZip.fileToZip(sourceFilePath, zipFilePath, fileName);
		if (flag) {
			System.out.println("文件打包成功!");
		} else {
			System.out.println("文件打包失败!");
		}
	}

}