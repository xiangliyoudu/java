package fileList;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ShowFileTree {
	public static void main(String[] args) {
		String fileName = "E:/temp/testjar";
		String targetFile = "E:/temp/fileTest";
		//tree(target, 0);
		listFile(fileName, targetFile);
		
		//writeFile("E:/temp/ssss/�½��ı��ĵ�.txt", "E:/temp/fileTest");
	}

	static void listFile(String fileName, String targetPath) {
		File file = new File(fileName);
		if(file.isFile()){
			System.out.println("plz enter the dir name, not the file name!!");
			return;
		}
		String dirName = file.getName();
		System.out.println(dirName);

		File[] files = file.listFiles();
		for (File f : files) {
			File targetFile = new File(targetPath, f.getName());
			if (f.isDirectory()) {
				targetFile.mkdir();
				listFile(f.getPath(), targetFile.getPath());
			} else {
				writeFile(f, targetFile);
				System.out.println(f.getName());
			}
		}
	}

	// level����Ϊ�����ո������
	private static void tree(File f, int level) { 
		if(f.isFile()){
			System.out.println("plz enter the dir name, not the file name!!");
			return;
		}
		String preString = "";
		for (int i = 0; i < level; i++) {
			preString += "    "; // ÿ��һ��������������ĸ��ո�
		}

		File[] childs = f.listFiles(); // �г��˳���·������ʾ��Ŀ¼�е��ļ�
		for (int i = 0; i < childs.length; i++) {
			System.out.println(preString + childs[i].getName());// �����Ϣ
			if (childs[i].isDirectory()) {
				// �ݹ���ñ���Ѱ�Һ��ӵĺ��ӣ�Ŀ¼��Ŀ¼��Ϣ����ÿ��һ�㣬level��1
				tree(childs[i], level + 1);
			}
		}
	}

	private static void writeFile(File fileName, File target){
		DataInputStream dis = null;
		DataOutputStream dos = null;
		
		try {
			dis = new DataInputStream(new FileInputStream(fileName));
			dos = new DataOutputStream(new FileOutputStream(target));
			
			byte[] bytes = new byte[1024];
			int b = 0;
			while((b = dis.read(bytes, 0, bytes.length))!= -1){
				dos.write(bytes, 0, b);
				dos.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(dos != null){
					dos.close();
				}
				if (dis != null) {
					dis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
