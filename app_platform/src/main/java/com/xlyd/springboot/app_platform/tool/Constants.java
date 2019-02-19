package com.xlyd.springboot.app_platform.tool;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	// session
	public final static String BACKEND_USER_SESSION = "backendUserSession";
	public final static String DEV_USER_SESSION = "devUserSession";
	// message
	public final static String SYS_MESSAGE = "message";
	// page
	public final static int PAGE_SIZE = 5;
	// file upload
	public final static int FILE_SIZE = 5000000;
	public final static String FILEUPLOAD_PATH_PIC = "E:/temp/";
	public final static String FILEUPLOAD_ERROR_1 = " * APK信息不完整！";
	public final static String FILEUPLOAD_ERROR_2 = " * 上传失败！";
	public final static String FILEUPLOAD_ERROR_3 = " * 上传文件格式不正确！";
	public final static String FILEUPLOAD_ERROR_4 = " * 上传文件过大！";
	public final static String FILEUPLOAD_ERROR_5 = " * 上传文件不能为空！";

	// pic type
	public static boolean checkPicType(String type) {
		List<String> picList = new ArrayList<>();
		picList.add("jpg");
		picList.add("png");
		picList.add("jpeg");

		return picList.contains(type);
	}

	public static void main(String[] args) {

		System.out.println(checkPicType("jpg"));
		System.out.println(checkPicType("jpdg"));
	}

}
