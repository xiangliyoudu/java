package com.execl;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

class Member {
	private Integer code;

	private String name;

	private Integer age;

	private Date birth;

	public Member(Integer code, String name, Integer age, Date birth) {
		super();
		this.code = code;
		this.name = name;
		this.age = age;
		this.birth = birth;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

}

public class ExportExecl {
	/**
	 * @功能：手工构建一个简单格式的Excel
	 */
	private static List<Member> getMember() throws Exception {
		List<Member> list = new ArrayList<Member>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");

		Member user1 = new Member(1, "熊大", 24, df.parse("1993-08-28"));
		Member user2 = new Member(2, "熊二", 23, df.parse("1994-08-19"));
		Member user3 = new Member(3, "熊熊", 24, df.parse("1983-11-22"));
		Member user4 = new Member(4, "熊儿", 24, df.parse("1993-01-12"));
		list.add(user1);
		list.add(user2);
		list.add(user3);
		list.add(user4);

		return list;
	}

	public static void main(String[] args) throws Exception {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("学生表一");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		
		// 表头字符串数组
		HSSFCell cell = null;
		String[] headStrings = {"学号","姓名","年龄","生日"};
		for (int i = 0;i < headStrings.length;i++) {
			cell = row.createCell(i);
			cell.setCellValue(headStrings[i]);
			cell.setCellStyle(style);
		}
/*		cell.setCellValue("学号");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("年龄");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("生日");
		cell.setCellStyle(style);*/

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		List<Member> list = ExportExecl.getMember();

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 1);
			Member stu = list.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell(0).setCellValue(stu.getCode());
			row.createCell(1).setCellValue(stu.getName());
			row.createCell(2).setCellValue(stu.getAge());
			row.createCell(3).setCellValue(new SimpleDateFormat("yyyy-mm-dd")
									    		  .format(stu.getBirth()));
		}
		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream("E:temp/Members.xls");
			wb.write(fout);
			fout.close();
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 第六步，下载excel  
       
	}
}
