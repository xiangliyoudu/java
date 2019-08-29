package com.execl;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

public class ExportExcleUtils {

	/**
	 * @param headStrings : 表头字符串列表
	 * @param contentStrings ：内容列表
	 * @return：一个webbook，对应一个Excel文件
	 */
	public static HSSFWorkbook createWookBook(List<List<String>> contentStrings) {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("数据表一");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = null;
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);

		// 第五步，写入实体数据
		for (int i = 0; i < contentStrings.size(); i++) {
			row = sheet.createRow(i);
			List<String> sts = contentStrings.get(i);
			for (int j = 0; j < sts.size(); j++) {
				// 第四步，创建单元格，并设置值
				row.createCell(j).setCellValue(sts.get(j));
			}
		}

		return wb;
	}

}
