package com.execl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @����������excel��ȡ
 * 
 *               �����jar��
 * 
 *               poi-3.8-beta3-20110606.jar
 * 
 *               poi-ooxml-3.8-beta3-20110606.jar
 * 
 *               poi-examples-3.8-beta3-20110606.jar
 * 
 *               poi-excelant-3.8-beta3-20110606.jar
 * 
 *               poi-ooxml-schemas-3.8-beta3-20110606.jar
 * 
 *               poi-scratchpad-3.8-beta3-20110606.jar
 * 
 *               xmlbeans-2.3.0.jar
 * 
 *               dom4j-1.6.1.jar
 * 
 *               jar���������ص�ַ��http://poi.apache.org/download.html
 * 
 *               ����poi-bin-3.8-beta3-20110606.zipp
 * 
 */

public class ImportExecl {
	/** ������ */
	private int totalRows = 0;
	/** ������ */
	private int totalCells = 0;
	/** ������Ϣ */
	private String errorInfo;

	/** ���췽�� */
	public ImportExecl() {
	}

	public int getTotalRows() {
		return totalRows;
	}

	public int getTotalCells() {
		return totalCells;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public boolean validateExcel(String filePath) {
		/** ����ļ����Ƿ�Ϊ�ջ����Ƿ���Excel��ʽ���ļ� */
		if (filePath == null
				|| !(WDWUtil.isExcel2003(filePath) || WDWUtil
						.isExcel2007(filePath))) {
			errorInfo = "�ļ�������excel��ʽ";
			return false;
		}
		/** ����ļ��Ƿ���� */
		File file = new File(filePath);
		if (file == null || !file.exists()) {
			errorInfo = "�ļ�������";
			return false;
		}
		return true;
	}

	public List<List<String>> read(String filePath) {
		List<List<String>> dataLst = new ArrayList<List<String>>();
		InputStream is = null;
		try {
			/** ��֤�ļ��Ƿ�Ϸ� */
			if (!validateExcel(filePath)) {
				System.out.println(errorInfo);
				return null;
			}
			/** �ж��ļ������ͣ���2003����2007 */
			boolean isExcel2003 = true;
			if (WDWUtil.isExcel2007(filePath)) {
				isExcel2003 = false;
			}
			/** ���ñ����ṩ�ĸ�������ȡ�ķ��� */
			File file = new File(filePath);
			is = new FileInputStream(file);
			dataLst = read(is, isExcel2003);
			is.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}
		/** ��������ȡ�Ľ�� */
		return dataLst;
	}

	public List<List<String>> read(InputStream inputStream, boolean isExcel2003) {
		List<List<String>> dataLst = null;
		try {
			/** ���ݰ汾ѡ�񴴽�Workbook�ķ�ʽ */
			Workbook wb = null;
			if (isExcel2003) {
				wb = new HSSFWorkbook(inputStream);
			} else {
				wb = new XSSFWorkbook(inputStream);
			}
			dataLst = read(wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataLst;
	}

	private List<List<String>> read(Workbook wb) {
		List<List<String>> dataLst = new ArrayList<List<String>>();
		/** �õ���һ��sheet */
		Sheet sheet = wb.getSheetAt(0);
		/** �õ�Excel������ */
		this.totalRows = sheet.getPhysicalNumberOfRows();
		/** �õ�Excel������ */
		if (this.totalRows >= 1 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		/** ѭ��Excel���� */
		for (int r = 0; r < this.totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row == null) {
				continue;
			}
			List<String> rowLst = new ArrayList<String>();
			/** ѭ��Excel���� */
			for (int c = 0; c < this.getTotalCells(); c++) {
				Cell cell = row.getCell(c);
				String cellValue = "";
				if (null != cell) {
					// �������ж����ݵ�����
					switch (cell.getCellTypeEnum()) {
					case NUMERIC: // ����
						cellValue = cell.getNumericCellValue() + "";
						break;
					case STRING: // �ַ���
						cellValue = cell.getStringCellValue();
						break;
					case BOOLEAN: // Boolean
						cellValue = cell.getBooleanCellValue() + "";
						break;
					case FORMULA: // ��ʽ
						cellValue = cell.getCellFormula() + "";
						break;
					case BLANK: // ��ֵ
						cellValue = "";
						break;
					case ERROR: // ����
						cellValue = "�Ƿ��ַ�";
						break;
					default:
						cellValue = "δ֪����";
						break;
					}
				}
				rowLst.add(cellValue);
			}
			/** �����r�еĵ�c�� */
			dataLst.add(rowLst);
		}
		return dataLst;
	}

	public static void main(String[] args) throws Exception {
		ImportExecl poi = new ImportExecl();
		/*List<List<String>> ddd = poi.read("E:/temp/excle/dddd.xls");
		
		for(int i=0; i<ddd.size(); i++) {
			List<String> dddCell = ddd.get(i);
			dddCell.add(1, "test" + i);
		}
		
		File dddd1 = new File("E:/temp/excle/dddd-1.xls");
		
		HSSFWorkbook wb = ExportExcleUtils.createWookBook(ddd);
		wb.write(dddd1);
		wb.close();*/
		
		List<List<String>> tax_raw_1 = poi.read("E:/temp/excle/tax_raw-1.xls");
		List<List<String>> zotutab_1 = poi.read("E:/temp/excle/zotutab-1.xls");
		
		for(int i=0; i<zotutab_1.size(); i++){
			List<String> zoList = zotutab_1.get(i);
			String zoCell1 = zoList.get(0);
			
			for(int j=0; j<tax_raw_1.size(); j++){
				List<String> tax_List = tax_raw_1.get(j);
				String taxCell1 = tax_List.get(0);
				String taxCell2 = tax_List.get(1);
				if (zoCell1.equals(taxCell1)) {
					System.out.println(zoCell1);
					zoList.add(1, taxCell2);
				}
			}
		}
		
		File zotutab = new File("E:/temp/excle/zotutab.xls");
		if (zotutab.exists()) {
			HSSFWorkbook wb = ExportExcleUtils.createWookBook(zotutab_1);
			wb.write(zotutab);
			wb.close();
		}
		
	}

	public static File createFile(String string) {
		File f = new File("E:/temp/ssss", string);
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}

	public static void createCer() throws Exception{
		ImportExecl poi = new ImportExecl();
		List<List<String>> list = poi.read("E:/temp/dddd.xls");
		
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				System.out.print("��" + (i) + "��");
				List<String> cellList = list.get(i);
				for (int j = 0; j < cellList.size(); j++) {
					// System.out.print("    ��" + (j + 1) + "��ֵ��");
					File file = null;
					if (j == 1) {
						file = createFile(cellList.get(j) + ".cer");
						System.out.println(file);
						System.out.println(cellList.get(j + 1));
						String sss = cellList.get(j + 1);
						FileWriter fos = new FileWriter(file);
						fos.write(sss);
						fos.flush();
						fos.close();
					}
				}
				System.out.println();
			}
		}
	}
}



class WDWUtil {
	public static boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");
	}

	public static boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}
}
