package fastJsonTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;

public class JsonFileIdChange {

	public static void main(String[] args) throws IOException {

		//String fileName = "E:/temp/Json_uuid/admin/2D83UCJEX/note.json";
		//String str = readFile(fileName);
		//String uuid = getUUId();
		//JSONObject notejson = JSONObject.parseObject(str);
		//System.out.println(notejson);
		//notejson.put("id", uuid);
		//System.out.println(notejson.toJSONString(notejson, true));
		//String targerFileName = "E:/temp/Json_uuid/admin/2D83UCJEX/temp.json";
		//writeJsonString(fileName, notejson.toJSONString(notejson, true));

		tree("E:/temp/admin");
	}

	private static void tree(String sourceFileName) throws IOException {
		File f = new File(sourceFileName);
		File[] childs = f.listFiles();
		
		// empty file, return
		if(childs.length == 0 ){
			return;
		}

		for (int i = 0; i < childs.length; i++) {
			if (childs[i].isDirectory()) {
				System.out.println(childs[i].getName());
				tree(childs[i].getPath());
			} else {
				String fileName = childs[i].getPath().replace("\\", "/");
				
				//只对json文件进行操作
				if(fileName.endsWith("note.json")){
					String str = readFile(fileName);
					String uuid = getUUId();
					JSONObject notejson = JSONObject.parseObject(str);
					notejson.put("id", uuid);
					System.out.println(JSONObject.toJSONString(notejson, true));
					writeJsonString(fileName, JSONObject.toJSONString(notejson, true));
					File oldFile = new File(childs[i].getParent());
					String newFileName = oldFile.getParent() + "/" + uuid;
					oldFile.renameTo(new File(newFileName));
				}
			}
		}
	}

	public static String readFile(String fileName) throws IOException {
		String str = "";
		FileInputStream fis = new FileInputStream(fileName);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr); 
		
		String line = null;
		while ((line = br.readLine()) != null) {
			str += line;
		}
		br.close();
		return str;
	}

	public static String getUUId() {
		return UUID.randomUUID().toString();
	}

	public static void writeJsonString(String targerFileName, String jsonString) throws IOException {
		FileOutputStream fos = new FileOutputStream(targerFileName);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write(jsonString);
		bw.flush();
		bw.close();
	}

}
