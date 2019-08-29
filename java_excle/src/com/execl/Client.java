package com.execl;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

public class Client {

	public static void main(String[] args) {
		List<List<String>> result = new ArrayList<List<String>>();
		
		ImportExecl ie = new ImportExecl();
		String filePath6000 = "C:\\Users\\Administrator\\Desktop\\19年内蒙CA发起退款明细表（对公账户）0829.xls";
		String filePath9000 = "C:\\Users\\Administrator\\Desktop\\全盟市预交用户数据9550-董8.29.xlsx";
		List<List<String>> ls6000 = ie.read(filePath6000);
		List<List<String>> ls9000 = ie.read(filePath9000);
		for (int i = 0; i < ls6000.size(); i++) {
			List<String> ls6000items = ls6000.get(i);
//			System.out.println(ls6000items.get(1));
			String name6 = ls6000items.get(1);
			String city = ls6000items.get(5);
			for (int j = 0; j < ls9000.size(); j++) {
				List<String> items = ls9000.get(j);
				String name = items.get(0);
				if (("ds" + name6).equals(name)) {
//					System.out.println("****************" + city);
					items.add(city);
					ls9000.set(j, items);
				}
			}
		}

		System.out.println("----------");
		ls9000.forEach(System.out::println);

		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("表一");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);

		// 表头字符串数组
		HSSFCell cell = null;
		String[] headStrings = { "单位名称", "纳税人识别号", "联系电话", "金额", "e", "a", "b", "c", "d" };
		for (int i = 0; i < headStrings.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(headStrings[i]);
			cell.setCellStyle(style);
		}


		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		for (int i = 0; i < ls9000.size(); i++) {
			row = sheet.createRow(i);
			List<String> stu = ls9000.get(i);
			System.out.println(stu.size());
			// 第四步，创建单元格，并设置值
			row.createCell(0).setCellValue(stu.get(0));
			row.createCell(1).setCellValue(stu.get(1));
			row.createCell(2).setCellValue(stu.get(2));
			row.createCell(3).setCellValue(stu.get(3));
			row.createCell(4).setCellValue(stu.get(4));
			row.createCell(5).setCellValue(stu.get(5));
			row.createCell(6).setCellValue(stu.get(6));
			row.createCell(7).setCellValue(stu.get(7));
			if(stu.size()==9) {
				row.createCell(8).setCellValue(stu.get(8));
			}
		}
		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream("E:temp/data9000.xls");
			wb.write(fout);
			fout.close();
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("finished");

	}
}
