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
		String filePath6000 = "C:\\Users\\Administrator\\Desktop\\19������CA�����˿���ϸ���Թ��˻���0829.xls";
		String filePath9000 = "C:\\Users\\Administrator\\Desktop\\ȫ����Ԥ���û�����9550-��8.29.xlsx";
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

		// ��һ��������һ��webbook����Ӧһ��Excel�ļ�
		HSSFWorkbook wb = new HSSFWorkbook();
		// �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet
		HSSFSheet sheet = wb.createSheet("��һ");
		// ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short
		HSSFRow row = sheet.createRow(0);
		// ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);

		// ��ͷ�ַ�������
		HSSFCell cell = null;
		String[] headStrings = { "��λ����", "��˰��ʶ���", "��ϵ�绰", "���", "e", "a", "b", "c", "d" };
		for (int i = 0; i < headStrings.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(headStrings[i]);
			cell.setCellStyle(style);
		}


		// ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���
		for (int i = 0; i < ls9000.size(); i++) {
			row = sheet.createRow(i);
			List<String> stu = ls9000.get(i);
			System.out.println(stu.size());
			// ���Ĳ���������Ԫ�񣬲�����ֵ
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
		// �����������ļ��浽ָ��λ��
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
