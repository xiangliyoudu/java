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
	 * @���ܣ��ֹ�����һ���򵥸�ʽ��Excel
	 */
	private static List<Member> getMember() throws Exception {
		List<Member> list = new ArrayList<Member>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");

		Member user1 = new Member(1, "�ܴ�", 24, df.parse("1993-08-28"));
		Member user2 = new Member(2, "�ܶ�", 23, df.parse("1994-08-19"));
		Member user3 = new Member(3, "����", 24, df.parse("1983-11-22"));
		Member user4 = new Member(4, "�ܶ�", 24, df.parse("1993-01-12"));
		list.add(user1);
		list.add(user2);
		list.add(user3);
		list.add(user4);

		return list;
	}

	public static void main(String[] args) throws Exception {
		// ��һ��������һ��webbook����Ӧһ��Excel�ļ�
		HSSFWorkbook wb = new HSSFWorkbook();
		// �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet
		HSSFSheet sheet = wb.createSheet("ѧ����һ");
		// ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short
		HSSFRow row = sheet.createRow(0);
		// ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		
		// ��ͷ�ַ�������
		HSSFCell cell = null;
		String[] headStrings = {"ѧ��","����","����","����"};
		for (int i = 0;i < headStrings.length;i++) {
			cell = row.createCell(i);
			cell.setCellValue(headStrings[i]);
			cell.setCellStyle(style);
		}
/*		cell.setCellValue("ѧ��");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("����");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("����");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("����");
		cell.setCellStyle(style);*/

		// ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���
		List<Member> list = ExportExecl.getMember();

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 1);
			Member stu = list.get(i);
			// ���Ĳ���������Ԫ�񣬲�����ֵ
			row.createCell(0).setCellValue(stu.getCode());
			row.createCell(1).setCellValue(stu.getName());
			row.createCell(2).setCellValue(stu.getAge());
			row.createCell(3).setCellValue(new SimpleDateFormat("yyyy-mm-dd")
									    		  .format(stu.getBirth()));
		}
		// �����������ļ��浽ָ��λ��
		try {
			FileOutputStream fout = new FileOutputStream("E:temp/Members.xls");
			wb.write(fout);
			fout.close();
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// ������������excel  
       
	}
}
