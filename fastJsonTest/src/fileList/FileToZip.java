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
	 * �������sourceFilePathĿ¼�µ�Դ�ļ��������fileName���Ƶ�zip�ļ�������ŵ�zipFilePath·����
	 * 
	 * @param sourceFilePath:��ѹ�����ļ�·��
	 * @param zipFilePath:ѹ������·��
	 * @param fileName:ѹ�����ļ�������
	 * @return
	 */
	public static boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName) {
		boolean flag = false;
		File sourceFile = new File(sourceFilePath);
		BufferedInputStream bis = null;
		ZipOutputStream zos = null;

		if (!sourceFile.exists()) {
			System.out.println("��ѹ�����ļ�Ŀ¼��" + sourceFilePath + "������.");
		} else {
			try {
				File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
				if (zipFile.exists()) {
					System.out.println(zipFilePath + "Ŀ¼�´�������Ϊ:" + fileName
							+ ".zip" + "����ļ�.");
				} else {
					File[] sourceFiles = sourceFile.listFiles();
					if (null == sourceFiles || sourceFiles.length < 1) {
						System.out.println("��ѹ�����ļ�Ŀ¼��" + sourceFilePath
								+ "���治�����ļ�������ѹ��.");
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
							// ����ZIPʵ�壬����ӽ�ѹ����
							ZipEntry zipEntry = new ZipEntry(file.getName());
							zos.putNextEntry(zipEntry);
							// ��ȡ��ѹ�����ļ���д��ѹ������
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
				// �ر���
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
			System.out.println("�ļ�����ɹ�!");
		} else {
			System.out.println("�ļ����ʧ��!");
		}
	}

}